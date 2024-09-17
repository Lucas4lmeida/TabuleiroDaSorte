// CasaSimples.java
package models;

import javax.swing.JOptionPane;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        jogador.adicionarMoedas(1);
        JOptionPane.showMessageDialog(null,
                jogador.getNome() + " ganhou 1 moeda na casa simples.",
                "Casa Simples",
                JOptionPane.INFORMATION_MESSAGE);
    }
}