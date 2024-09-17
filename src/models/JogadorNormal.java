// JogadorNormal.java
package models;

import java.awt.Color;

public class JogadorNormal extends Jogador {
    public JogadorNormal(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public int[] jogarDados() {
        return new Dados().lancarDados();
    }
}