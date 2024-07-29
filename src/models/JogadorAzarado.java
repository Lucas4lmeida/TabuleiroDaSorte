package models;

import java.awt.Color;
import javax.swing.JOptionPane;

public class JogadorAzarado extends Jogador {

    public JogadorAzarado(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public void mover(int casas) {
        if (!podeJogar) return;

        int novaPosicao = this.posicao + casas;
        if (novaPosicao >= 40) {
            novaPosicao = 39;
        }
        this.posicao = novaPosicao;

        verificarCasaEspecial();
        this.incrementarJogadas();
    }

    private void verificarCasaEspecial() {
        if (posicao == 10 || posicao == 25 || posicao == 38) {
            JOptionPane.showMessageDialog(null, "Você não joga a próxima rodada.");
        } else if (posicao == 17 || posicao == 27) {
            // Lógica para retroceder jogador (a ser implementada conforme o jogo)
        }
    }
}
