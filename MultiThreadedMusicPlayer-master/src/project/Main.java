package project;
import java.util.concurrent.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws InterruptedException {
    	
    	MainFrame frame = new MainFrame();
    	
        System.out.println("Spotifly");
        List<Musica> musicas = new ArrayList<>();
        String input = "";
        do {
            System.out.println("Por favor, insira o comando que deseja executar:\n" +
                    "'A'- ADICIONAR 'D'- DELETAR 'S'- SAIR.");
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            Thread action = null;
            Thread play = null;
            if(input.equals("A")) {
                System.out.print("Digite o nome da música que deseja adicionar:");
                String nomeMusica = in.nextLine();
                int duracaoMusica = in.nextInt();
                in.nextLine();
                action = new AdicionarMusica(musicas, nomeMusica, duracaoMusica);
                action.start();
            }else if(input.equals("D")) {
                System.out.print("Digite o nome da música que deseja deletar:");
                String nomeMusica = in.nextLine();
                action = new DeletarMusica(musicas, nomeMusica);
                action.start();
            }else if(input.equals("S")) {
                System.out.println("Adeus!");
            }else if(input.equals("Play")) {
                if (Play.getPause()){
                    Play.alterarEstado();
                }
                else{
                    play = new Play(musicas);
                    play.start();
                }
            }else if(input.equals("Pause")) {
                Play.alterarEstado();
            }else if(input.equals(">")) {
                Play.avancarMusica();
            }else if(input.equals("<")) {
                Play.voltarMusica();
            }else {
                System.out.println("Opcao invalida!");
            }
            
            if(action != null){
                action.join();
            }

        }while(!input.equals("S"));
        
    }
}
