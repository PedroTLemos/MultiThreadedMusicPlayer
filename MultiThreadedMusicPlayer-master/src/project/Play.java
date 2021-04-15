package project;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Play extends Thread{
	static String tempoAtual = "00:00";
    static int counter = 0;
    public static int musicaAtual = 0;
    public static boolean change = false;
    static boolean pause = false;
    static Thread action = null;

    private List<Musica> musicas;
    public Play(List<Musica> $listaMusicas) {
        this.musicas = $listaMusicas;
    }
    @Override
    public void run() {
        for (; musicaAtual < musicas.size(); musicaAtual++){
            System.out.println(musicaAtual);
            counter = 0;
            for (int j = 0; j < musicas.get(musicaAtual).getDuracao(); j++) {
                int tempo = counter;
                int minutos = tempo / 60;
                int segundos = tempo % 60;
                tempoAtual = minutos + ":" + segundos;
                counter++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (pause){
                    j--;
                    counter--;
                }
                if (action != null){
                    try {
                        action.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (change) {
                    change = false;
                    break;
                }
            }
            if (action != null){
                try {
                    action.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        musicaAtual = 0;
    }

    static public void avancarMusica(){
        action = new AvancarMusica();
        action.start();
    }

    static public void voltarMusica(){
        action = new VoltarMusica();
        action.start();
    }

    static public int retornarTempo(){
        return counter;
    }

    static public void alterarEstado(){
        pause = !pause;
    }

    static public boolean getPause(){
        return pause;
    }
    
    static public String getTempo(){
        return tempoAtual;
    }
}
