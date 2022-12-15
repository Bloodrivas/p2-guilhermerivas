package br.edu.femass.dao;
import java.util.List;
import br.edu.femass.model.Exemplar;

public class DaoExemplar extends Dao<Exemplar> {
    

    public List<Exemplar> buscarTodos(){
        return em.createQuery("select a from Exemplar a order by a.Id").getResultList();
    }

    public List<Exemplar> buscarTodosPorId() {
        return em.createQuery("select a from Exemplar a order by a.Id").getResultList();
    }
}