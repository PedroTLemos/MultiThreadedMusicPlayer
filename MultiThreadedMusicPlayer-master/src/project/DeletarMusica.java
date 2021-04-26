package project;
import java.util.List;

public class DeletarMusica extends Thread{
    private List<Musica> musicas;
    private String nomeMusica;
    public DeletarMusica(List<Musica> $listaMusicas ,String nomeMusica) {
        this.nomeMusica = nomeMusica;
        this.musicas = $listaMusicas;
    }
    //Thread para tentar deletar a primeira masica com um nome específico na playlist
    @Override
    public void run() {
        int indiceDesejado = -1;
        //Procura uma masica com o nome especificado na busca
        for(int i = 0; i < musicas.size() && indiceDesejado==-1; i++) {
            //Caso encontre, ele armazena o indice correspondente...
            if(musicas.get(i).getNome().equalsIgnoreCase(nomeMusica)) {
                indiceDesejado = i;
            }
        }
        if(indiceDesejado != -1) {
            //... e depois remove o item correspondente do array, fazendo o log
            musicas.remove(indiceDesejado);
            System.out.println("Música '" + nomeMusica + "' deletada com sucesso.");
        }
        else{
            //Caso nao encontre, o sistema faz o log que a musica não esta presente
            System.out.println("Esta música não está na fila.");
        }


    }

}
