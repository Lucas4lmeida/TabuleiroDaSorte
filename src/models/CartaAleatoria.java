// CartaAleatoria.java
package models;

import java.util.Random;

public class CartaAleatoria {
    public enum Tipo { CARTA_AZARADO, CARTA_SORTUDO }
    private Tipo tipo;
    private static Random random = new Random();

    private CartaAleatoria(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public static CartaAleatoria sortearCarta() {
        Tipo tipoSorteado = random.nextBoolean() ? Tipo.CARTA_AZARADO : Tipo.CARTA_SORTUDO;
        return new CartaAleatoria(tipoSorteado);
    }

    @Override
    public String toString() {
        switch (tipo) {
            case CARTA_AZARADO:
                return "Que azar! Você se tornou um jogador azarado!";
            case CARTA_SORTUDO:
                return "Que sorte! Você se tornou um jogador sortudo!";
            default:
                return "Carta desconhecida";
        }
    }
}