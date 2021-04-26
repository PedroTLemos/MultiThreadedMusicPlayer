package project;

public class AvancarMusica extends Thread{

	// construtor (cria uma instancia vazia)
    public AvancarMusica(){
    }
    
    // ativa a thread para mudar a musica
    @Override
    public void run() {
        Play.change = true;
    }
}
