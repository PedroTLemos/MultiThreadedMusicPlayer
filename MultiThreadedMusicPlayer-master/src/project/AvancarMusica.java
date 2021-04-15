package project;

public class AvancarMusica extends Thread{

    public AvancarMusica(){

    }
    @Override
    public void run() {
        Play.change = true;
    }
}
