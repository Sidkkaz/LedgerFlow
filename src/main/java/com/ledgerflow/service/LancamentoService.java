package com.ledgerflow.service;


import com.ledgerflow.model.Lancamento;
import com.ledgerflow.repository.LancamentoRepository;
import com.ledgerflow.repository.Repository;
import java.util.List;


public class LancamentoService {

    private final Repository<Lancamento> repo = new LancamentoRepository();

    public List<Lancamento> Listar(){
        return repo.list();
    }

    public Lancamento BuscarPorId(int id){
        return null;
    }

    public void Criar(Lancamento l){
        repo.add(l);
    }

    public void Atualizar(Lancamento l){
        repo.update(l);
    }

    public void Excluir(Lancamento l){
        repo.delete(l);
    }
}
