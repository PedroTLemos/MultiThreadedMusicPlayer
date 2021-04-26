package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Play extends Thread{
    //Define algumas variaveis estaticas necessarias para o programa
	static String tempoAtual = "00:00"; // string que representa o tempo a ser apresentado na tela
    static Musica atual = new Musica("", 1); // cria o objeto estatico que representa a musica atual
    static int counter = 0; // inteiro que representa a quantidade de segundos passados na musica
    public static int musicaAtual = 0; // indice da musica tocando no momento
    public static boolean change = false; // booleano que sinaliza que a musica deve parar e ser trocada
    static boolean pause = false; // booleano que define o estado da musica (pause/play)
    static boolean finished = false; // booleane que define se a playlist acabou
    static boolean random = false; // booleano que define se a funcao random esta ativa ou nao
    static Thread action = null; // Thread generica

    //instancia a lista de musicas e o construtor
    private List<Musica> musicas;
    public Play(List<Musica> $listaMusicas) {
        this.musicas = $listaMusicas;
    }
    @Override
    public void run() {
        // Reiniciamos o valor de finished
        finished = false;
        // Definimos o array de musicas que serao tocadas
        List<Musica> reproducaoAtual = new ArrayList<>(musicas);
        for (; musicaAtual < reproducaoAtual.size(); musicaAtual++){
            // Reiniciamos o valor de counter
            counter = 0;
            // Iniciamos o loop que ira representar os segundos passando
            for (int j = 0; j < reproducaoAtual.get(musicaAtual).getDuracao(); j++) {
                atual = reproducaoAtual.get(musicaAtual);
                //Definimos o novo valor de tempoAtual, que sera utilizado pelo frontend e incrementamos o contador
                // logo em seguida
                int tempo = counter;
                int minutos = tempo / 60;
                int segundos = tempo % 60;

                tempoAtual = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
                counter++;
                // Fazemos a execucao pausar por 1 segundo, para sincronizar a passagem de tempo
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Travamos o contador em um certo valor caso o pause esteja ativo
                if (pause){
                    j--;
                    counter--;
                }
                // Caso a Thread action esteja executando algo, esperamos o termino
                if (action != null){
                    try {
                        action.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Caso change esteja verdadeiro, fazemos o tratamento de mudanca de musica
                if (change) {
                    change = false;
                    //Caso random esteja ativo, sera atribuido um valor aleatorio dentro dos
                    // limites do array ao indice da musica atual, caso contrario ele so finaliza
                    // o loop e vai para a proxima
                    if(random) {
                        // Certificamos que a proxima musica randomica nunca seja igual a musica atual
                        int indiceAtual = musicaAtual;
                        musicaAtual = (int)(Math.random()*reproducaoAtual.size()) - 1;
                        while (indiceAtual == musicaAtual + 1){
                            musicaAtual = (int)(Math.random()*reproducaoAtual.size()) - 1;
                            musicaAtual = Math.max(musicaAtual, 0);
                        }
                    }
                    break;
                }
            }
            //Tambem esperamos a acao de action apos o final de uma musica
            if (action != null){
                try {
                    action.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Fazemos mais uma fez o tratamento de random caso a musica acabe naturalmente
            if(random) {
                int indiceAtual = musicaAtual;
                musicaAtual = (int)(Math.random()*reproducaoAtual.size()) - 1;
                while (indiceAtual == musicaAtual + 1){
                    musicaAtual = (int)(Math.random()*reproducaoAtual.size()) - 1;
                    musicaAtual = Math.max(musicaAtual, 0);
                }
            }
        }
        //Rezetamos musicaAtual e atribuimos true para finished, indicando que a playlist acabou
        musicaAtual = 0;
        finished = true;
    }

    static public void avancarMusica(){
        //Metodo que atribui a action a Thread de avancar musica, iniciando em seguida
        action = new AvancarMusica();
        action.start();
    }

    static public void voltarMusica(){
        //Metodo que atribui a action a Thread de voltar musica, iniciando em seguida
        action = new VoltarMusica();
        action.start();
    }

    //Getters e setter necessarios para os botoes
    static public int retornarMusicaAtual(){
        return musicaAtual;
    }
    static public void alterarPause(){
        pause = !pause;
    }
    static public void alterarRandom() {
    	random = !random;
    }
    static public String getTempo(){
        return tempoAtual;
    }
    static public int getCounter() {
    	return counter;
    }
}
