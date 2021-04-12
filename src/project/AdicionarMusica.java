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
    @Override
    public void run() {
        this.musicas.add(new Musica(this.nomeMusica, this.duracaoMusica));
        System.out.println("Música '" + nomeMusica + "' adicionada com sucesso");
    }
}
