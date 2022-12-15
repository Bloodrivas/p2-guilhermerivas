package br.edu.femass.model;
import java.time.LocalDate;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate dataAquisicao;

    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;    

    public Exemplar(){
        this.dataAquisicao = LocalDate.now();

    }

    public Exemplar(Long Id, LocalDate dataAquisicao, Livro livro) {
        this.dataAquisicao = dataAquisicao;
        this.livro = livro;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Long getId() {
        return Id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return Id + " " + livro;
    }

}