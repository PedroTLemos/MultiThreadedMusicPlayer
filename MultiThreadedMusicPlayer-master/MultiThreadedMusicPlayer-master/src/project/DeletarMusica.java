package project;
import java.util.List;

public class DeletarMusica extends Thread{
    private List<Musica> musicas;
    private String nomeMusica;
    public DeletarMusica(List<Musica> $listaMusicas ,String nomeMusica) {
        this.nomeMusica = nomeMusica;
        this.musicas = $listaMusicas;
    }
    @Override
    public void run() {
        int indiceDesejado = -1;
        for(int i = 0; i < musicas.size() && indiceDesejado==-1; i++) {
            if(musicas.get(i).getNome().equalsIgnoreCase(nomeMusica)) {
                indiceDesejado = i;
            }
        }
        if(indiceDesejado != -1) {
            musicas.remove(indiceDesejado);
            System.out.println("Música '" + nomeMusica + "' deletada com sucesso.");
        }
        else{
            System.out.println("Esta música não está na fila.");
        }


    }

}
