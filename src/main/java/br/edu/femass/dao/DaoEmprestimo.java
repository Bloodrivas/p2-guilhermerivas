package br.edu.femass.dao;
import java.util.List;
import br.edu.femass.model.Emprestimo;

public class DaoEmprestimo extends Dao<Emprestimo> {
    

    public List<Emprestimo> buscarTodos(){
        return em.createQuery("select a from Emprestimo a order by a.Id").getResultList();
    }
}