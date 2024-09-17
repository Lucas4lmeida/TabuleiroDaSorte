// JogadorFactory.java
package factories;

import models.*;
import java.awt.Color;

public class JogadorFactory {
    private static final Color[] CORES = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK};
    private static int corIndex = 0;

    public static Jogador criarJogador(String tipo, String nome) {
        Color cor = CORES[corIndex++ % CORES.length];
        switch (tipo.toLowerCase()) {
            case "normal":
                return new JogadorNormal(nome, cor);
            case "sortudo":
                return new JogadorSortudo(nome, cor);
            case "azarado":
                return new JogadorAzarado(nome, cor);
            default:
                throw new IllegalArgumentException("Tipo de jogador inválido: " + tipo);
        }
    }
}