package br.edu.femass.model;
import java.time.LocalDate;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Exemplar extends Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private Long codigo;
    private LocalDate dataAquisicao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;    

    public Exemplar(){
        this.dataAquisicao = LocalDate.now();
    }

    public Exemplar(Long codigo, LocalDate dataAquisicao, Livro livro) {
        this.codigo = codigo;
        this.dataAquisicao = dataAquisicao;
        this.livro = livro;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Exemplar [codigo=" + codigo + 
        ", dataAquisicao=" + dataAquisicao + "]";
    }

}