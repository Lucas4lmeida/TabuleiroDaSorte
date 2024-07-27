import java.util.Random;

public class cartaAleatoria {
    public enum Tipo { CARTA_AZARADO, CARTA_AFORTUNADO }
    private Tipo tipo;

    private cartaAleatoria(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public static cartaAleatoria sortearCarta() {
        Random rand = new Random();
        return new cartaAleatoria(rand.nextBoolean() ? Tipo.CARTA_AZARADO : Tipo.CARTA_AFORTUNADO);
    }

    @Override
    public String toString() {
        return tipo == Tipo.CARTA_AZARADO ? "Que azar! Você é um jogador azarado!" : "Parabéns! Você é um jogador sortudo!";
    }
}
