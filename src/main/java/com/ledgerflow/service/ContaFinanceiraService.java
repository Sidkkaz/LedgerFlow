package com.ledgerflow.service;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.ContaTipo;
import com.ledgerflow.repository.ContaFinanceiraRepository;
import com.ledgerflow.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class ContaFinanceiraService {

    private static final Repository<ContaFinanceira> repo = new ContaFinanceiraRepository();

    public static List<ContaFinanceira> ListarContas(){
        return repo.list();
    }

    public static ContaFinanceira BuscarConta(int id){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                return conta;
            }
        }
        return null;
    }

    public static void CriarConta(String n, int a, int num, ContaTipo tipo, double saldo, boolean bool){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getNome().equals(n)){
                return;
            }
        }

        ContaFinanceira c = new ContaFinanceira(n, a , num, tipo, saldo, bool);

        repo.add(c);
    }

    public static void DesativarConta(int id){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                conta.setAtivo(false);
                repo.update(conta);
            }
        }
    }

    public static void AtivarConta(int id){
        List<ContaFinanceira> list = ListarContas();

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                conta.setAtivo(true);
                repo.update(conta);
            }
        }
    }

    public static void AtualizarSaldo(double valor, int id){
        List<ContaFinanceira> list = ListarContas();

        if(list.isEmpty()) return;

        for(ContaFinanceira conta : list){
            if(conta.getId() == id){
                conta.setSaldo(valor);
                repo.update(conta);
            }
        }
    }

}
