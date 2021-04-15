package project;
import java.util.concurrent.*;

public class Musica{
    private String nome;
    private int duracao;

    public Musica(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }
    public String getNome() {
        return this.nome;
    }
    public int getDuracao() {
        return this.duracao;
    }
}
