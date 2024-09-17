// JogadorSortudo.java
package models;

import java.awt.Color;

public class JogadorSortudo extends Jogador {
    public JogadorSortudo(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public int[] jogarDados() {
        int[] resultado;
        do {
            resultado = new Dados().lancarDados();
        } while (resultado[2] < 7);
        return resultado;
    }
}