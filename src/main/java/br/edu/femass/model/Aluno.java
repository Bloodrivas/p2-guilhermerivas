package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno extends Leitor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String matricula;
    
    public Aluno(String matricula) {
        this.setPrazoMaximoDevolucao(15);
    }

    public Aluno(String nome, String endereco, String telefone, String matricula) {
        super(nome, endereco, telefone);
        this.matricula = matricula;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + "]";
    }

    public void setId(Long id) {
        Id = id;
    }

}
