// CasaPrisao.java
package models;

public class CasaPrisao extends Casa {
    public CasaPrisao(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        jogador.setPreso(true);
        jogador.setTurnosPreso(2);
        System.out.println(jogador.getNome() + " foi preso por 2 turnos!");
    }
}