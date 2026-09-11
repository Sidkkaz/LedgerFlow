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

    public List<ContaFinanceira> ListarContas(){
        return repo.list();
    }

    public ContaFinanceira BuscarPorId(long id){
        return null;
        //Vou criar o metodo no repositorio dps
    }

    public void CriarConta(
            String n,
            String agencia,
            String num,
            ContaTipo tipo,
            BigDecimal saldoI
    ){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getNome().equals(n)){
                PopupWarning.warning(
                        "Conta duplicada!",
                        "Essa conta já existe no sistema"
                );
                return;
            }
        }

        ContaFinanceira c = new ContaFinanceira(null,
                n,
                tipo,
                saldoI,
                BigDecimal.ZERO
        );

        if(agencia != null && num != null){
            c.setAgencia(agencia);
            c.setNumero(num);
        }
        
        c.ativar();

        repo.add(c);
    }

    public void DesativarConta(ContaFinanceira conta){
        conta.desativar();
        repo.update(conta);
    }

    public void AtivarConta(ContaFinanceira conta){
        conta.ativar();
        repo.update(conta);
    }

}
