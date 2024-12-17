import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;

public class App {
    public static void main(String[] args) throws Exception {

        Musica musica1 = new Musica();
        musica1.setNome("Cópia");
        musica1.setArquivoAudio("./assets/Cópia.wav");
        musica1.setGenero("Forró");

        Musica musica2 = new Musica();
        musica2.setNome("Medley Explosão");
        musica2.setArquivoAudio("./assets/Medley-explosão.wav");
        musica2.setGenero("Reggae");

        Musica musica3 = new Musica(); 
        musica3.setNome("Barbie");
        musica3.setArquivoAudio("./assets/Barbie.wav"); 
        musica3.setGenero("Rock");

        
        Album album1 = new Album();
        album1.setNome("Primeiro album");
        album1.setAno(2024);
        album1.addMusica(musica1);
        album1.addMusica(musica2);
        album1.addMusica(musica3); 

        Album album2 = new Album();
        album1.setNome("Segundo album");
        album1.setAno(2024);
        album1.addMusica(musica1);
        album1.addMusica(musica2);
        album1.addMusica(musica3);

        Album album3 = new Album();
        album1.setNome("Terceiro album");
        album1.setAno(2024);
        album1.addMusica(musica1);
        album1.addMusica(musica2);
        album1.addMusica(musica3);


        Artista redHot = new Artista();
        redHot.setNome("Léo Foguete");
        redHot.addAlbum(album1);

        
        redHot.setNome("Antoniel");
        redHot.addAlbum(album2);

      
        redHot.setNome("Mc tuto");
        redHot.addAlbum(album3);

        AudioPlayer player = new AudioPlayer();
        final int[] currentMusicIndex = { 0 }; 

        
        Runnable loadAndPlay = () -> {
            player.stopAudio();
            String arquivoAudio = redHot.getAlbuns().get(0).getMusicas().get(currentMusicIndex[0]).getArquivoAudio();
            player.loadAudio(arquivoAudio);
            player.playAudio();
        };

        // Criando a interface gráfica com JFrame
        JFrame frame = new JFrame("PlayMusic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); 
        frame.setLayout(null); 

        // Criando JLabel para mostrar o nome da música
        JLabel songLabel = new JLabel("Música: " + redHot.getAlbuns().get(0).getMusicas().get(currentMusicIndex[0]).getNome());
        songLabel.setBounds(50, 30, 200, 30);
        frame.add(songLabel);

        
        JButton playStopButton = new JButton("Play");
        playStopButton.setBounds(50, 80, 80, 30);
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player.isPlaying) {
                    player.playAudio();
                    playStopButton.setText("Stop");
                } else {
                    player.stopAudio();
                    playStopButton.setText("Play");
                }
            }
        });
        frame.add(playStopButton);

        // Botão Próxima música
        JButton nextButton = new JButton("Avançar");
        nextButton.setBounds(150, 80, 100, 30);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Avançando para a próxima música
                currentMusicIndex[0] = (currentMusicIndex[0] + 1) % redHot.getAlbuns().get(0).getMusicas().size();
                playStopButton.setText("Stop");
                loadAndPlay.run(); // Atualiza a música e exibe na interface

                // Atualiza o nome da música no JLabel
                songLabel.setText("Música: " + redHot.getAlbuns().get(0).getMusicas().get(currentMusicIndex[0]).getNome());
            }
        });
        frame.add(nextButton);

        // Botão Música anterior
        JButton prevButton = new JButton("Voltar");
        prevButton.setBounds(50, 120, 100, 30);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Voltando para a música anterior
                currentMusicIndex[0] = (currentMusicIndex[0] - 1 + redHot.getAlbuns().get(0).getMusicas().size())
                        % redHot.getAlbuns().get(0).getMusicas().size();
                playStopButton.setText("Stop");
                loadAndPlay.run(); // Atualiza a música e exibe na interface

                // Atualiza o nome da música no JLabel
                songLabel.setText("Música: " + redHot.getAlbuns().get(0).getMusicas().get(currentMusicIndex[0]).getNome());
            }
        });
        frame.add(prevButton);

        
        frame.setLocationRelativeTo(null);

        
        frame.setVisible(true);

       
        loadAndPlay.run();

        
        if (player.audioClip != null) {
            player.audioClip.close();
        }
    }
}
