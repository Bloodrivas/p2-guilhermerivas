package br.edu.femass.model;
import javax.persistence.Entity;

@Entity
public class Professor extends Leitor{    
    private String disciplina;

    public Professor() {
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
        return this.getNome()+ " - " + disciplina;
    }

}
