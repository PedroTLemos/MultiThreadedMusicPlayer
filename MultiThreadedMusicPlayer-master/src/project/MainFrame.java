package project;

import javax.swing.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainFrame extends JFrame implements ActionListener{
	
	JButton backB = new JButton();
	JButton playB = new JButton();
	JButton skipB = new JButton();
	JButton insertSongB = new JButton();
	JButton rmvSongB = new JButton();
	JButton congelar = new JButton();
	
	JTextField insertSong = new JTextField("Nome da Musica");
	JTextField insertDuration = new JTextField("Duracao (segundos)");
	JTextField rmvSong = new JTextField("Nome da Musica");

	JLabel musicPlaying = new JLabel("Nome da Musica");
	JLabel musicTimer = new JLabel("00:00");

	
	JProgressBar bar = new JProgressBar();

 main

	List<Musica> musicas = new ArrayList<>();
	String[] musica = {"a","b"};
	JList songList = new JList();
	
    Thread action = null;
    Thread play = null;

	Timer timer = new Timer(1000, this);


	MainFrame(){
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // fecha a aplicacao quando fecha a janela
    	this.setResizable(false); // impede de mudar o tamanho da janela
    	this.setTitle("Spotifly"); // cria titulo para a frame
    	this.setSize(600, 500); // tamanho da frame
    	this.setLayout(null);
    
    	songList.setBounds(10,140,290,200);
    	songList.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	songList.setEnabled(false);
    	
    	JScrollPane songPanel = new JScrollPane(songList);
    	songPanel.setBounds(10,140,290,200);
    	

    	musicPlaying.setBounds(12,80,200,20);
    	musicPlaying.setFont(new Font("Comic Sans", Font.BOLD, 14));

		musicTimer.setBounds(528,80,60,20);
		musicTimer.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	insertSong.setBounds(210,355,200,25);
    	insertSong.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	insertDuration.setBounds(210,380,200,25);
    	insertDuration.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	rmvSong.setBounds(210,410,200,25);
    	rmvSong.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	bar.setValue(0);
    	bar.setBounds(10,100,560,8);
    	
    	insertSongB.setBounds(10,355,200,50);
    	insertSongB.setText("Adicionar");
    	insertSongB.setFocusable(false);
    	insertSongB.addActionListener(this);
    	insertSongB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	insertSongB.setForeground(Color.lightGray);
    	insertSongB.setBackground(new Color(0,0,0));
    	
    	rmvSongB.setBounds(10,410,200,25);
    	rmvSongB.setText("Remover");
    	rmvSongB.setFocusable(false);
    	rmvSongB.addActionListener(this);
    	rmvSongB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	rmvSongB.setForeground(Color.lightGray);
    	rmvSongB.setBackground(new Color(0,0,0));
    	
    	backB.setBounds(200,30,50,30);
    	backB.setText("|<");
    	backB.setFocusable(false);
    	backB.addActionListener(this);
    	backB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	backB.setForeground(Color.lightGray);
    	backB.setBackground(new Color(0,0,0));
    	
    	playB.setBounds(250,20,100,50);
    	playB.setText("Play");
    	playB.setFocusable(false);
    	playB.addActionListener(this);
    	playB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	playB.setForeground(Color.lightGray);
    	playB.setBackground(new Color(0,0,0));
    	
    	skipB.setBounds(350,30,50,30);
    	skipB.setText(">|");
    	skipB.setFocusable(false);
    	skipB.addActionListener(this);
    	skipB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	skipB.setForeground(Color.lightGray);
    	skipB.setBackground(new Color(0,0,0));
    	
    	congelar.setBounds(550,400,50,30);
    	
    	//JLabel labelSong = new JLabel("Tocando:");
    	//labelSong.setBounds(0,0,50,50);
        JLabel labelCounter = new JLabel("00:00");
        JLabel labelDuration = new JLabel("00:00");
        
    	
    	this.getContentPane().setBackground(new Color(23,220,23));
    	ImageIcon image = new ImageIcon("spotifly.png"); // pega uma imagem para ser o logo
    	ImageIcon image2 = new ImageIcon("spotify.png");
    	this.setIconImage(image2.getImage()); // seta a imagem da janela como o logo
    	
    	JLabel logo = new JLabel();
    	logo.setText("");
    	logo.setIcon(image);
    	logo.setBounds(350,140,200,200);
    	
    	this.setVisible(true); // deixa visivel
    	
    
    	this.add(songPanel);
    	
    	this.add(logo);
    	this.add(bar);
    	this.add(musicPlaying);
    	this.add(musicTimer);
    	this.add(insertSong);
    	this.add(insertDuration);
    	this.add(rmvSong);
    	this.add(insertSongB);
    	this.add(rmvSongB);
    	this.add(backB);
    	this.add(playB);
    	this.add(skipB);
    	//this.add(congelar);
    	//this.add(labelSong);

		timer.start();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==insertSongB){
			int duration = Integer.parseInt(insertDuration.getText());
			action = new AdicionarMusica(musicas, insertSong.getText(), duration);
            action.start();
			try {
				action.join();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
			songList.setListData(musicas.stream().map(Musica::getNome).toArray(String[]::new));
		}
		else if(e.getSource()==rmvSongB) {
			action = new DeletarMusica(musicas, rmvSong.getText());
            action.start();
			try {
				action.join();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
            songList.setListData(musicas.stream().map(Musica::getNome).toArray(String[]::new));
		}
		else if(e.getSource()==playB) {
			if(playB.getText()=="Play") {
				if (Play.pause && !Play.finished){
	                Play.alterarPause();
	            }
	            else{
	                play = new Play(musicas);
	                play.start();
	            }
				playB.setText("Pause");
			}else{
				Play.alterarPause();
 

				playB.setText("Play");
			}
		}
		else if (e.getSource().equals(timer)) {
			if (musicas.size() != 0){
				musicPlaying.setText(musicas.get(Play.retornarMusicaAtual()).getNome());
				musicTimer.setText(Play.getTempo());
			}
			if (Play.finished){
        main
				playB.setText("Play");
				musicPlaying.setText("Nome da Música");
				musicTimer.setText("00:00");
			}
		}
		else if (e.getSource().equals(timer)) {
			if (musicas.size() != 0){
				musicPlaying.setText(musicas.get(Play.retornarMusicaAtual()).getNome());
				musicTimer.setText(Play.getTempo());
				int dur=musicas.get(Play.retornarMusicaAtual()).getDuracao();
				bar.setValue((Play.getCounter()*100 / dur)-1);
			}
			if (Play.finished){
				playB.setText("Play");
				musicPlaying.setText("Nome da Musica");
				musicTimer.setText("00:00");
				bar.setValue(0);
			}
		}
		else if(e.getSource()==backB) {
			Play.voltarMusica();
		}
		else if(e.getSource()==skipB) {
			Play.avancarMusica();
		}
	}
}
