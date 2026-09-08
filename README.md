# LedgerFlow
Projeto de sistema financeiro em JAVA, feito para estudos mais aprofundados em JAVA, arquitetura e tomada de decisões

## Decisões de Dominio
Uma das decisões de dominio que tive foi:
- **`Lançamento`** é um **Fato**;
- **`Movimentação Financeira`** é uma **Consequência**;
- **`Conta Financeira`** é o **Estado** que vai ser afetado;

## Regras de Saldo

A entidade `ContaFinanceira` mantém dois valores diferentes relacionados ao saldo:

* **`saldoInicial`**: representa o saldo que a conta possuía no momento em que foi cadastrada no LedgerFlow. Esse valor pode ser positivo, zero ou negativo
* **`saldo`**: representa o saldo atual da conta, considerando as movimentações realizadas após o cadastro

### Criação da conta

Ao criar uma nova `ContaFinanceira`, o saldo atual inicia com o mesmo valor do saldo inicial:

```java
this.saldoInicial = saldoInicial;
this.saldo = saldoInicial;
```

Por exemplo, caso uma conta seja cadastrada com um saldo devedor de `-R$ 10.000.000,00`:

```text
saldoInicial = -10.000.000,00
saldo        = -10.000.000,00
```

O saldo negativo é um estado válido, para representar a situação financeira da conta antes de ser colocado no sistema

### Alteração do saldo

Após a criação, operações financeiras alteram somente o `saldo` atual

```text
Saldo inicial:  -10.000.000,00

Depósito:        +2.000.000,00
Saldo atual:     -8.000.000,00

Depósito:        +5.000.000,00
Saldo atual:     -3.000.000,00
```

O `saldoInicial` permanece `-R$ 10.000.000,00`, pois representa o estado original da conta

### Persistência e reconstrução

Tanto `saldoInicial` quanto `saldo` são persistidos no banco de dados

Ao reconstruir uma `ContaFinanceira`, os valores persistidos devem ser restaurados diretamente:

```text
Banco de dados:

saldoInicial = -10.000.000,00
saldo        = -3.000.000,00

            ↓

ContaFinanceira:

saldoInicial = -10.000.000,00
saldo        = -3.000.000,00
```

**Não deve ser realizada a operação `saldoInicial + saldo` durante a reconstrução. Pois pode haver uma duplicação do valor**

O `saldo` persistido já representa o estado atual da conta e, portanto, não deve ser tratado como uma movimentação ou como um valor incremental.

