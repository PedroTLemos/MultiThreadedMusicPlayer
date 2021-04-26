package project;

import java.util.List;

public class AdicionarMusica extends Thread{
    private List<Musica> musicas;
    private String nomeMusica;
    private int duracaoMusica;
    public AdicionarMusica(List<Musica> $listaMusicas, String nomeMusica, int duracaoMusica) {
        this.musicas = $listaMusicas;
        this.nomeMusica = nomeMusica;
        this.duracaoMusica = duracaoMusica;
    }
    // Thread que adiciona uma masica no array e faz o log
    @Override
    public void run() {
        //instancia o objeto Musica com os atributos fornecidos e adiciona no array, fazendo o log
        this.musicas.add(new Musica(this.nomeMusica, this.duracaoMusica));
        System.out.println("Música '" + nomeMusica + "' adicionada com sucesso");
    }
}
