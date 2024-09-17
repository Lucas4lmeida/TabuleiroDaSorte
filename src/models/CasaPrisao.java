// CasaPrisao.java
package models;

import javax.swing.JOptionPane;

public class CasaPrisao extends Casa {
    public CasaPrisao(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        jogador.setPreso(true);
        jogador.setTurnosPreso(2);
        JOptionPane.showMessageDialog(null,
                jogador.getNome() + " foi preso por 2 turnos!",
                "Casa Prisão",
                JOptionPane.WARNING_MESSAGE);
    }
}