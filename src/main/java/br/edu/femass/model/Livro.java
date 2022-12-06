package br.edu.femass.model;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long codigo;
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    
    private String ano;
    

    public Livro(){

    }

    public Livro(String titulo, Autor autor, String ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Livro [codigo=" + codigo + 
        ", titulo=" + titulo + 
        ", autor=" + autor + 
        ", ano=" + ano + "]";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    
    
}
