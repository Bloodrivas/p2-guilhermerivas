package br.edu.femass.model;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Professor extends Leitor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long Id;
    private String disciplina;

    public Professor(String disciplina) {
        this.setPrazoMaximoDevolucao(30);
    }

    public Professor(String nome, String endereco, String telefone, String disciplina) {
        super(nome, endereco, telefone);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Professor [disciplina=" + disciplina + "]";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

}
