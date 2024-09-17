// TabuleiroView.java
package views;

import models.*;
import javax.swing.*;
import java.awt.*;

public class TabuleiroView extends JFrame {
    private PainelTabuleiro painelTabuleiro;
    private JTextArea infoArea;

    public TabuleiroView() {
        setTitle("Jogo de Tabuleiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        painelTabuleiro = new PainelTabuleiro();
        add(painelTabuleiro, BorderLayout.CENTER);

        infoArea = new JTextArea(5, 30);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void atualizarTabuleiro(Tabuleiro tabuleiro) {
        painelTabuleiro.atualizarTabuleiro(tabuleiro);
        atualizarInfoJogadores(tabuleiro);
        repaint();
    }

    private void atualizarInfoJogadores(Tabuleiro tabuleiro) {
        StringBuilder info = new StringBuilder("Informações dos Jogadores:\n");
        for (Jogador jogador : tabuleiro.getJogadores()) {
            info.append(String.format("%s: Posição %d, Moedas %d\n",
                    jogador.getNome(), jogador.getPosicao(), jogador.getMoedas()));
        }
        infoArea.setText(info.toString());
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}