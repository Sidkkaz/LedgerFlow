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

    public  List<ContaFinanceira> ListarContas(){
        return repo.list();
    }

    public  ContaFinanceira BuscarConta(long id){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                return conta;
            }
        }
        return null;
    }

    public  void CriarConta(
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

    public  void DesativarConta(long id){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                conta.desativar();
                repo.update(conta);
            }
        }
    }

    public  void AtivarConta(long id){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                conta.ativar();
                repo.update(conta);
            }
        }
    }

    public  void AtualizarSaldo(BigDecimal valor, long id){
        List<ContaFinanceira> list = ListarContas();

        if(list.isEmpty()) return;

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                conta.depositar(valor);
                repo.update(conta);
            }
        }
    }

}
