package project;

public class VoltarMusica extends Thread{

	// construtor (cria uma instancia vazia)
    public VoltarMusica(){
    }

    // ativa a thread de voltar musica
    @Override
    public void run(){
        Play.change = true; // troca o estado de change para trocar a musica
        Play.musicaAtual -= 2; // volta duas musicas (pois o contador vai somar +1 no loop)
        
        // contorna o problema de index negativo
        if (Play.musicaAtual < -1){
            Play.musicaAtual = -1;
        }
        
        // verifica se o estado de random esta ativo
        if(Play.random) {
        	Play.musicaAtual = Play.lastPlayed;
        }
    }
}
