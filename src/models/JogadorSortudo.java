package models;

import java.awt.Color;
import javax.swing.JOptionPane;

public class JogadorSortudo extends Jogador {

    public JogadorSortudo(String nome, Color cor) {
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
        if (posicao == 13) {
            CartaAleatoria carta = CartaAleatoria.sortearCarta();
            JOptionPane.showMessageDialog(null, "Você tirou a carta: " + carta);
            if (carta.getTipo() == CartaAleatoria.Tipo.CARTA_AZARADO) {
                // Alterar tipo do jogador (a ser implementado conforme o jogo)
            }
        } else if (posicao == 5 || posicao == 15 || posicao == 30) {
            // Lógica para avançar posições (a ser implementada conforme o jogo)
        }
    }
}
