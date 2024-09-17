// JogadorSortudo.java
package models;

import java.awt.Color;

public class JogadorSortudo extends Jogador {
    public JogadorSortudo(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public ResultadoDados jogarDados() {
        Dados dados = new Dados();
        ResultadoDados resultado;
        do {
            resultado = dados.lancarDados();
        } while (resultado.getTotal() < 7);
        return resultado;
    }
}