// CasaJogaDeNovo.java
package models;

import javax.swing.JOptionPane;

public class CasaJogaDeNovo extends Casa {
    public CasaJogaDeNovo(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        JOptionPane.showMessageDialog(null,
                jogador.getNome() + " joga novamente!",
                "Casa Joga De Novo",
                JOptionPane.INFORMATION_MESSAGE);
    }
}