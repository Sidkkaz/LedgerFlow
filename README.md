# LedgerFlow

Projeto de sistema financeiro em **Java**, desenvolvido para estudos mais aprofundados em Java, arquitetura de software e tomada de decisões de domínio.

## Decisões de Domínio

Uma das decisões de domínio do LedgerFlow é separar claramente **fatos**, **consequências** e **estado**:

* **`Lançamento`** é um **Fato**;
* **`MovimentaçãoFinanceira`** representa uma **Consequência**;
* **`ContaFinanceira`** representa o **Estado** que é afetado pelas movimentações.

Essa separação evita tratar o saldo da conta como se fosse uma movimentação financeira. O saldo representa o estado atual da conta, enquanto os lançamentos representam fatos ocorridos no domínio.

---

## Regras de Saldo

A entidade `ContaFinanceira` mantém dois valores diferentes relacionados ao saldo:

* **`saldoInicial`**: representa o saldo que a conta possuía no momento em que foi cadastrada no LedgerFlow. Esse valor pode ser positivo, zero ou negativo.
* **`saldo`**: representa o saldo atual da conta, considerando as movimentações realizadas após o cadastro.

### Criação da conta

Ao criar uma nova `ContaFinanceira`, o saldo atual é inicializado com o mesmo valor do saldo inicial:

```java
this.saldoInicial = saldoInicial;
this.saldo = saldoInicial;
```

Por exemplo, caso uma conta seja cadastrada com um saldo devedor de `-R$ 10.000.000,00`:

```text
saldoInicial = -10.000.000,00
saldo        = -10.000.000,00
```

O saldo negativo é um estado válido e representa a situação financeira da conta no momento em que ela foi cadastrada no sistema.

### Alteração do saldo

Após a criação da conta, as operações financeiras alteram somente o `saldo` atual.

```text
Saldo inicial:  -10.000.000,00

Depósito:        +2.000.000,00
Saldo atual:     -8.000.000,00

Depósito:        +5.000.000,00
Saldo atual:     -3.000.000,00
```

O `saldoInicial` permanece `-R$ 10.000.000,00`, pois representa o estado original da conta no momento do cadastro.

---

## Persistência e Reidratação

Tanto `saldoInicial` quanto `saldo` são persistidos no banco de dados.

Ao reidratar uma `ContaFinanceira`, os valores persistidos devem ser restaurados diretamente, sem realizar cálculos sobre eles:

```text
Banco de dados:

saldoInicial = -10.000.000,00
saldo        = -3.000.000,00

            ?

ContaFinanceira:

saldoInicial = -10.000.000,00
saldo        = -3.000.000,00
```

**Não deve ser realizada a operação `saldoInicial + saldo` durante a reidratação.**

O `saldo` persistido já representa o estado atual da conta. Portanto, ele não deve ser interpretado como uma movimentação ou como um valor incremental.

A reidratação tem apenas a responsabilidade de **restaurar o estado persistido da entidade**.

---

## Padrão de Construção de `ContaFinanceira`

A classe `ContaFinanceira` separa explicitamente dois contextos de instanciação:

### Criação — novo cadastro

Utilizada quando uma nova conta é cadastrada no sistema.

Nesse contexto, o saldo ainda não existe como estado persistido. Portanto, o `saldo` é inicializado a partir do `saldoInicial`.

```java
new ContaFinanceira(nome, tipo, saldoInicial)
```

Internamente:

```java
this.saldoInicial = saldoInicial;
this.saldo = saldoInicial;
```

O construtor de criação é responsável por validar as regras necessárias para a criação de uma nova conta.

### Reidratação — reconstrução do banco

Utilizada exclusivamente pelo repositório para reconstruir uma `ContaFinanceira` a partir dos dados persistidos.

```java
ContaFinanceira.rehydrate(
    id,
    nome,
    agencia,
    numero,
    tipo,
    saldoInicial,
    saldo,
    ativo
)
```

Nesse contexto, `saldoInicial` e `saldo` são **estados já existentes** e devem ser restaurados diretamente.

A reidratação não deve:

* recalcular o saldo;
* aplicar movimentações;
* executar regras de negócio relacionadas à criação;
* modificar os valores persistidos.

Seu objetivo é apenas reconstruir a entidade com o estado que estava armazenado.

> O construtor de criação valida os dados necessários para uma nova conta.
> O método `rehydrate` assume que os dados foram previamente validados pelas regras de domínio e que o estado persistido está íntegro.

Dessa forma, a criação e a reidratação possuem responsabilidades diferentes:

```text
Criação
    ?
Validação
    ?
Inicialização do estado
    ?
ContaFinanceira

Reidratação
    ?
Dados persistidos
    ?
Restauração do estado
    ?
ContaFinanceira
```
