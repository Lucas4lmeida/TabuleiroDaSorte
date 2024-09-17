// JogadorAzarado.java
package models;

import java.awt.Color;

public class JogadorAzarado extends Jogador {
    public JogadorAzarado(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public int[] jogarDados() {
        int[] resultado;
        do {
            resultado = new Dados().lancarDados();
        } while (resultado[2] > 6);
        return resultado;
    }
}
