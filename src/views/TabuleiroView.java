package views;

import javax.swing.*;
import models.Jogador;
import java.awt.*;
import java.util.List;

public class TabuleiroView {
    private JFrame frame;
    private PainelTabuleiro painelTabuleiro;

    public TabuleiroView(List<Jogador> jogadores) {
        frame = new JFrame("Tabuleiro do Jogo");
        painelTabuleiro = new PainelTabuleiro(jogadores);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(painelTabuleiro, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void atualizar() {
        painelTabuleiro.repaint();
    }

    public void pularVez() {
        // Implementar lógica para pular a vez do jogador
    }

    public void retrocederJogador() {
        // Implementar lógica para retroceder o jogador
    }

    public void alterarTipoJogador(Jogador jogador, Jogador novoTipo) {
        // Implementar lógica para alterar o tipo do jogador
    }

    public void trocarPosicaoJogadores(Jogador jogador) {
        // Implementar lógica para trocar a posição dos jogadores
    }
}
