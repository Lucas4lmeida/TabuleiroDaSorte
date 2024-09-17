package factories;

import models.*;
import java.awt.Color;

public class JogadorFactory {
    public static Jogador criarJogador(String tipo, String nome, Color cor) {
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