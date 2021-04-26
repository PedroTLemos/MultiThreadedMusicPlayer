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
	
	// aqui sao declarados os butoes utilizados na interface
	JButton backB = new JButton(); // volta a musica
	JButton playB = new JButton(); // da play ou pause
	JButton skipB = new JButton(); // passa para proxima musica
	JButton randomizeB = new JButton(); // randomiza ou para a randomizacao de musicas
	JButton insertSongB = new JButton(); // insere a musica com nome e duracao nos campos ao lado 
	JButton rmvSongB = new JButton(); // remove a musica com nome no campo ao lado
	
	// aqui sao declarados os campos de texto utilizados na interface
	JTextField insertSong = new JTextField("Nome da Musica"); // nome da musica a ser inserida
	JTextField insertDuration = new JTextField("Duracao (segundos)"); // duracao da musica a ser inserida
	JTextField rmvSong = new JTextField("Nome da Musica"); // nome da musica a ser removida

	// apenas textos
	JLabel musicPlaying = new JLabel("Nome da Musica"); // nome da musica que esta tocando
	JLabel musicTimer = new JLabel("00:00"); // tempo atual da musica que esta tocando
	
	// barra de progresso da musica
	JProgressBar bar = new JProgressBar();

	// aqui e criada a lista que vai conter objetos do tipo musica
	List<Musica> musicas = new ArrayList<>();
	JList songList = new JList();
	
	// aqui sao criadas as threads que serao utilizadas no decorrer do programa
    Thread action = null;
    Thread play = null;

    // aqui e criado um objeto do tipo timer que ajuda na sincronizacao do tempo
	Timer timer = new Timer(1000, this);

	MainFrame(){
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // fecha a aplicacao quando fecha a janela
    	this.setResizable(false); // impede de mudar o tamanho da janela
    	this.setTitle("Spotifly"); // cria titulo para a frame
    	this.setSize(600, 500); // tamanho da frame
    	this.setLayout(null); // faz com que a frame nao possua layout, deixando a gente definir melhor os tamanhos
    
    	//cria a lista
    	songList.setBounds(10,140,290,200); // tamanho e localizacao
    	songList.setFont(new Font("Comic Sans", Font.BOLD, 14)); // caracteristicas de design
    	songList.setEnabled(false); // torna nao clicavel
    	
    	JScrollPane songPanel = new JScrollPane(songList); // cria um painel que vai conter as musicas
    	songPanel.setBounds(10,140,290,200);
    	
    	// tamanho, localizacao e design do mostrador de musica atual
    	musicPlaying.setBounds(12,80,200,20);
    	musicPlaying.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	// tamanho, localizacao e design do timer de musica atual
		musicTimer.setBounds(528,80,60,20);
		musicTimer.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
		// tamanho, localizacao e design do inserir musica
    	insertSong.setBounds(210,355,200,25);
    	insertSong.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	// tamanho, localizacao e design do inserir duracao
    	insertDuration.setBounds(210,380,200,25);
    	insertDuration.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	// tamanho, localizacao e design do remover musuica
    	rmvSong.setBounds(210,410,200,25);
    	rmvSong.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	
    	// seta a barra de progresso como vazia e adiciona localizacao e tamanho da mesma
    	bar.setValue(0);
    	bar.setBounds(10,100,560,8);
    	
    	// botao de inserir musica (as descricoes se aplicam para funcoes semelhantes de outros botoes)
    	insertSongB.setBounds(10,355,200,50); // tamanho e localizacao
    	insertSongB.setText("Adicionar"); // texto do botao
    	insertSongB.setFocusable(false);  // evita com que aparecam caracteristicas visuais do botao estar sendo corgitado com o mouse
    	insertSongB.addActionListener(this); // faz o botao ser util, ter uma acao
    	insertSongB.setFont(new Font("Comic Sans", Font.BOLD, 14)); // design
    	insertSongB.setForeground(Color.lightGray); // design do background no click
    	insertSongB.setBackground(new Color(0,0,0)); // design do background
    	
    	// botao remover musica
    	rmvSongB.setBounds(10,410,200,25);
    	rmvSongB.setText("Remover");
    	rmvSongB.setFocusable(false);
    	rmvSongB.addActionListener(this);
    	rmvSongB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	rmvSongB.setForeground(Color.lightGray);
    	rmvSongB.setBackground(new Color(0,0,0));
    	
    	// botao voltar musica
    	backB.setBounds(200,30,50,30);
    	backB.setText("|<");
    	backB.setFocusable(false);
    	backB.addActionListener(this);
    	backB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	backB.setForeground(Color.lightGray);
    	backB.setBackground(new Color(0,0,0));
    	
    	// botao de tocar musica
    	playB.setBounds(250,20,100,50);
    	playB.setText("Play");
    	playB.setFocusable(false);
    	playB.addActionListener(this);
    	playB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	playB.setForeground(Color.lightGray);
    	playB.setBackground(new Color(0,0,0));
    	
    	// botao de passar a musica
    	skipB.setBounds(350,30,50,30);
    	skipB.setText(">|");
    	skipB.setFocusable(false);
    	skipB.addActionListener(this);
    	skipB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	skipB.setForeground(Color.lightGray);
    	skipB.setBackground(new Color(0,0,0));
    	
    	// botao de randomizar a playlist
    	randomizeB.setBounds(250,72,100,20);
    	randomizeB.setText("Random");
    	randomizeB.setFocusable(false);
    	randomizeB.addActionListener(this);
    	randomizeB.setFont(new Font("Comic Sans", Font.BOLD, 14));
    	randomizeB.setForeground(Color.lightGray);
    	randomizeB.setBackground(new Color(0,0,0));
    	
    	// parte de imagens associadas
    	this.getContentPane().setBackground(new Color(23,220,23));
    	ImageIcon image = new ImageIcon("spotifly.png"); // pega uma imagem para ser o logo
    	ImageIcon image2 = new ImageIcon("spotify.png"); // pega imagem para colocar na janela
    	this.setIconImage(image2.getImage()); // seta a imagem da janela como o logo
    	
    	// seta a imagem do painel
    	JLabel logo = new JLabel();
    	logo.setText("");
    	logo.setIcon(image);
    	logo.setBounds(350,140,200,200); // tamaho e posicao
    	
    	this.setVisible(true); // deixa visivel
    	
    
    	// adicionando tudo que foi criado ao frame
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
    	this.add(randomizeB);

    	// inicia o timer
		timer.start();

	}
	
	// essa funcao cuida das acoes relacionadas aos cliques de botao
	@Override
	public void actionPerformed(ActionEvent e) {
		// apertar botao de inserir musica
		if(e.getSource()==insertSongB){
			int duration = Integer.parseInt(insertDuration.getText()); // pega a duracao da musica e converte pra int (segundos)
			action = new AdicionarMusica(musicas, insertSong.getText(), duration); // adiciona a musica
            action.start(); // inicia a thread
            // faz o join
			try {
				action.join();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
			songList.setListData(musicas.stream().map(Musica::getNome).toArray(String[]::new)); // atualiza a lista de musicas que aparece no painel
		}
		// apertar o botao de remover musica
		else if(e.getSource()==rmvSongB) {
			action = new DeletarMusica(musicas, rmvSong.getText()); // remove a musica
            action.start();
			try {
				action.join();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
            songList.setListData(musicas.stream().map(Musica::getNome).toArray(String[]::new)); // atualiza a lista de musicas que aparece no painel
		}
		// apertar o botao de play/pause
		else if(e.getSource()==playB) {
			if(playB.getText()=="Play") {
				if (Play.pause && !Play.finished){
	                Play.alterarPause(); // da play vindo do pause ou de alguma reproducao anterior
	            }
	            else{
	                play = new Play(musicas); // cria uma nova instancia da classe play para o primeiro play dado
	                play.start();
	            }
				playB.setText("Pause"); // troca o texto para pause
			}else{
				Play.alterarPause(); // pausa a playlist
				playB.setText("Play"); // troca o texto para play
			}
		}
		// se relaciona com o timer da musica atual
		else if (e.getSource().equals(timer)) {
			if (musicas.size() != 0){
				musicPlaying.setText(Play.atual.getNome()); // muda o texto para o nome da musica atual
				musicTimer.setText(Play.getTempo()); // muda o timer pra o timer atual
				int dur=Play.atual.getDuracao(); // pega a duracao total da musica
				bar.setValue((Play.getCounter()*100 / dur)-1); // atualiza a barra conforme a situacao atual da musica
			}
			// verifica se a playlist acabou
			if (Play.finished){
				// se sim, reinicia tudo relacionado
				playB.setText("Play"); 
				musicPlaying.setText("Nome da Musica"); 
				musicTimer.setText("00:00");
				bar.setValue(0);
			}
		}
		// apertar o botao de voltar
		else if(e.getSource()==backB) {
			Play.voltarMusica(); // volta a musica
		}
		// apertar o botao de passar
		else if(e.getSource()==skipB) {
			Play.avancarMusica(); // passa a musica
		}
		// apertar o botao de randomizacao
		else if(e.getSource()==randomizeB) {
			Play.alterarRandom(); // troca o estado de tocar musica
			if(Play.random) {
				randomizeB.setBackground(new Color(0,80,200)); // muda a cor do botao
			}else {
				randomizeB.setBackground(new Color(0,0,0)); // volta a cor do botao pra original
			}
		}
	}
}
