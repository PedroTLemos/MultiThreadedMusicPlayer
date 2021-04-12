package project;

public class VoltarMusica extends Thread{

    public VoltarMusica(){

    }

    @Override
    public void run(){
        Play.change = true;
        Play.musicaAtual -= 2;
        if (Play.musicaAtual < -1){
            Play.musicaAtual = -1;
        }
    }
}
