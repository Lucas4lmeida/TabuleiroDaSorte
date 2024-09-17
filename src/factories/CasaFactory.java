// CasaFactory.java
package factories;

import models.*;
import java.util.Random;

public class CasaFactory {
    private static final Random random = new Random();

    public static Casa criarCasa(int numero) {
        int tipo = random.nextInt(8); // 8 tipos de casas
        switch (tipo) {
            case 0:
                return new CasaSimples(numero);
            case 1:
                return new CasaSurpresa(numero);
            case 2:
                return new CasaPrisao(numero);
            case 3:
                return new CasaSorte(numero);
            case 4:
                return new CasaAzar(numero);
            case 5:
                return new CasaReversa(numero);
            case 6:
                return new CasaJogaDeNovo(numero);
            case 7:
                return new CasaTroca(numero);
            default:
                return new CasaSimples(numero);
        }
    }

    public static Casa criarCasaEspecifica(String tipo, int numero) {
        switch (tipo.toLowerCase()) {
            case "simples":
                return new CasaSimples(numero);
            case "surpresa":
                return new CasaSurpresa(numero);
            case "prisao":
                return new CasaPrisao(numero);
            case "sorte":
                return new CasaSorte(numero);
            case "azar":
                return new CasaAzar(numero);
            case "reversa":
                return new CasaReversa(numero);
            case "jogadenovo":
                return new CasaJogaDeNovo(numero);
            case "troca":
                return new CasaTroca(numero);
            default:
                throw new IllegalArgumentException("Tipo de casa inválido: " + tipo);
        }
    }
}