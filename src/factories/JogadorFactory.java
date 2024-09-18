package factories;

import models.*;
import java.awt.Color;

public class JogadorFactory {
    public static Jogador criarJogador(String tipo, String nome, Color cor) {
        Jogador jogador;
        switch (tipo.toLowerCase()) {
            case "normal":
                jogador = new JogadorNormal(nome, cor);
                break;
            case "sortudo":
                jogador = new JogadorSortudo(nome, cor);
                break;
            case "azarado":
                jogador = new JogadorAzarado(nome, cor);
                break;
            default:
                throw new IllegalArgumentException("Tipo de jogador inválido: " + tipo);
        }
        return jogador;
    }
}