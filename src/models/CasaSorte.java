package models;

import javax.swing.JOptionPane;

public class CasaSorte extends Casa {
    public CasaSorte(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        if (!(jogador instanceof JogadorAzarado)) {
            int totalCasas = tabuleiro.getCasas().size();
            jogador.mover(3, totalCasas);
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " avançou 3 casas na casa da sorte!",
                    "Casa Sorte",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " é azarado e não avançou na casa da sorte.",
                    "Casa Sorte",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}