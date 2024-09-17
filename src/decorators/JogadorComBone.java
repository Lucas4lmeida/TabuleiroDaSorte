// JogadorComBone.java
package decorators;

import models.Jogador;

public class JogadorComBone extends JogadorDecorator {
    public JogadorComBone(Jogador jogador) {
        super(jogador);
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(2);  // Ganha 1 moeda extra
            System.out.println(getNome() + " ganhou 1 moeda extra por ter um boné!");
        } else {
            super.adicionarMoedas(quantidade);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (com Boné)";
    }

    @Override
    public int[] jogarDados() {
        return jogadorDecorado.jogarDados();
    }
}
