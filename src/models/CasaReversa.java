// CasaReversa.java
package models;

import javax.swing.JOptionPane;

public class CasaReversa extends Casa {
    public CasaReversa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        Jogador jogadorMaisAtras = tabuleiro.getJogadorMaisAtras(jogador);
        if (jogadorMaisAtras != null) {
            int posicaoTemp = jogador.getPosicao();
            jogador.setPosicao(jogadorMaisAtras.getPosicao());
            jogadorMaisAtras.setPosicao(posicaoTemp);
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " trocou de lugar com " + jogadorMaisAtras.getNome() + "!",
                    "Casa Reversa",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " é o último e não trocou de lugar.",
                    "Casa Reversa",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}