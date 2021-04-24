package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Play extends Thread{
	static String tempoAtual = "00:00";
    static int counter = 0;
    static int lastPlayed = 0;
    public static int musicaAtual = 0;
    public static boolean change = false;
    static boolean pause = false;
    static boolean finished = false;
    static boolean random = false;
    static Thread action = null;


    private List<Musica> musicas;
    public Play(List<Musica> $listaMusicas) {
        this.musicas = $listaMusicas;
    }
    @Override
    public void run() {
        finished = false;
        List<Musica> reproducaoAtual = new ArrayList<>(musicas);
        for (; musicaAtual < reproducaoAtual.size(); musicaAtual++){
            System.out.println(musicaAtual);
            counter = 0;
            for (int j = 0; j < reproducaoAtual.get(musicaAtual).getDuracao(); j++) {
                int tempo = counter;
                int minutos = tempo / 60;
                int segundos = tempo % 60;

                tempoAtual = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
                System.out.println(tempoAtual);
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
                    if(random) {
                    	musicaAtual = (int)(Math.random()*reproducaoAtual.size()) - 1;
                    }
                    lastPlayed = musicaAtual;
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
            if(random) {
            	musicaAtual = (int)(Math.random()*reproducaoAtual.size()) - 1;
            }
            lastPlayed = musicaAtual;
        }
        musicaAtual = 0;
        finished = true;
    }

    static public void avancarMusica(){
        action = new AvancarMusica();
        action.start();
    }

    static public void voltarMusica(){
        action = new VoltarMusica();
        action.start();
    }

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
