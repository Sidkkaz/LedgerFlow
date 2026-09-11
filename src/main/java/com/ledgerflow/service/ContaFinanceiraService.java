package com.ledgerflow.service;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.PopupWarning;
import com.ledgerflow.model.enums.ContaTipo;
import com.ledgerflow.repository.ContaFinanceiraRepository;
import com.ledgerflow.repository.Repository;

import java.math.BigDecimal;
import java.util.List;

public class ContaFinanceiraService {

    private final Repository<ContaFinanceira> repo = new ContaFinanceiraRepository();

    public List<ContaFinanceira> listarContas() {
        return repo.list();
    }

    public void criarConta(
            String nome,
            String agencia,
            String numero,
            ContaTipo tipo,
            BigDecimal saldoInicial
    ) {
        boolean nomeDuplicado = listarContas().stream()
                .anyMatch(c -> c.getNome().equalsIgnoreCase(nome));

        if (nomeDuplicado) {
            PopupWarning.warning(
                    "Conta duplicada!",
                    "Essa conta já existe no sistema"
            );
            return;
        }

        ContaFinanceira conta = new ContaFinanceira(nome, tipo, saldoInicial);

        if (agencia != null && !agencia.isBlank())
            conta.setAgencia(agencia);

        if (numero != null && !numero.isBlank())
            conta.setNumero(numero);

        conta.ativar();

        repo.add(conta);
    }

    public void desativarConta(ContaFinanceira conta) {
        conta.desativar();
        repo.update(conta);
    }

    public void ativarConta(ContaFinanceira conta) {
        conta.ativar();
        repo.update(conta);
    }
}
