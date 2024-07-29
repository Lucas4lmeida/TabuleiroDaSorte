package views;

import javax.swing.*;
import models.Jogador;
import java.awt.*;
import java.util.List;

public class TabuleiroView {
    private JFrame frame;
    private PainelTabuleiro painelTabuleiro;
    private List<Jogador> jogadores;
    private int jogadorAtualIndex = 0;

    public TabuleiroView(List<Jogador> jogadores) {
        this.jogadores = jogadores;
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
        // Avança o índice do jogador atual e atualiza a visão
        jogadorAtualIndex = (jogadorAtualIndex + 1) % jogadores.size();
        atualizar();
    }

    public void retrocederJogador() {
        Jogador jogadorAtual = jogadores.get(jogadorAtualIndex);
        int novaPosicao = jogadorAtual.getPosicao() - 1; // Retrocede uma casa
        if (novaPosicao < 0) novaPosicao = 0; // Garante que a posição não seja negativa
        jogadorAtual.setPosicao(novaPosicao);
        atualizar();
    }

    public void alterarTipoJogador(Jogador jogador, Jogador novoTipo) {
        int index = jogadores.indexOf(jogador);
        if (index >= 0) {
            jogadores.set(index, novoTipo);
            painelTabuleiro = new PainelTabuleiro(jogadores); // Atualiza o painel com a nova lista
            frame.setContentPane(painelTabuleiro);
            atualizar();
        }
    }

    public void trocarPosicaoJogadores(Jogador jogador) {
        Jogador jogadorMaisAtras = null;
        for (Jogador j : jogadores) {
            if (j != jogador && (jogadorMaisAtras == null || j.getPosicao() < jogadorMaisAtras.getPosicao())) {
                jogadorMaisAtras = j;
            }
        }

        if (jogadorMaisAtras != null && jogadorMaisAtras.getPosicao() < jogador.getPosicao()) {
            int posicaoOriginal = jogador.getPosicao();
            jogador.setPosicao(jogadorMaisAtras.getPosicao());
            jogadorMaisAtras.setPosicao(posicaoOriginal);
            atualizar();
        } else {
            JOptionPane.showMessageDialog(frame, "Não há jogadores atrás para trocar de lugar.");
        }
    }
}
