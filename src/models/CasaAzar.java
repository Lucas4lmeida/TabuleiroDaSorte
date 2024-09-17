// CasaAzar.java
package models;

import javax.swing.JOptionPane;

public class CasaAzar extends Casa {
    public CasaAzar(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        if (!(jogador instanceof JogadorSortudo)) {
            jogador.removerMoedas(3);
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " perdeu 3 moedas na casa de azar!",
                    "Casa Azar",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " é sortudo e não perdeu moedas na casa de azar.",
                    "Casa Azar",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}