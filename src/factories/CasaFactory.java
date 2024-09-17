package factories;

import models.*;

public class CasaFactory {
    public static Casa criarCasaEspecifica(String tipo, int numero) {
        switch (tipo) {
            case "Surpresa":
                return new CasaSurpresa(numero);
            case "Prisão":
                return new CasaPrisao(numero);
            case "Sorte":
                return new CasaSorte(numero);
            case "Azar":
                return new CasaAzar(numero);
            case "Reversa":
                return new CasaReversa(numero);
            case "Joga de Novo":
                return new CasaJogaDeNovo(numero);
            case "Troca":
                return new CasaTroca(numero);
            default:
                return new CasaSimples(numero);
        }
    }
}