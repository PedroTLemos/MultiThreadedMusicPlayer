package project;
import java.util.concurrent.*;

public class Musica{
    private String nome; // uma musica tem nome
    private int duracao; // e duracao

    // construtor da musica
    public Musica(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }
    
    // para pegar o nome da musica
    public String getNome() {
        return this.nome;
    }
    
    // para pegar a duracao da musica
    public int getDuracao() {
        return this.duracao;
    }
}
