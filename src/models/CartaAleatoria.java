package models;

import java.util.Random;

public class CartaAleatoria {
    public enum Tipo { CARTA_NORMAL, CARTA_SORTUDO, CARTA_AZARADO }
    private Tipo tipo;
    private static Random random = new Random();

    private CartaAleatoria(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public static CartaAleatoria sortearCarta() {
        Tipo[] tipos = Tipo.values();
        return new CartaAleatoria(tipos[random.nextInt(tipos.length)]);
    }

    @Override
    public String toString() {
        switch (tipo) {
            case CARTA_NORMAL:
                return "Você se tornou um jogador normal!";
            case CARTA_SORTUDO:
                return "Que sorte! Você se tornou um jogador sortudo!";
            case CARTA_AZARADO:
                return "Que azar! Você se tornou um jogador azarado!";
            default:
                return "Carta desconhecida";
        }
    }
}