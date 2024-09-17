// JogadorComMoleton.java
package decorators;

import models.Jogador;
import models.ResultadoDados;

import javax.swing.*;

public class JogadorComMoleton extends JogadorDecorator {
    public JogadorComMoleton(Jogador jogador) {
        super(jogador);
        this.icon = new ImageIcon("src/resources/icons/moleton_icon.png");
        if (!(jogador instanceof JogadorComBone)) {
            throw new IllegalArgumentException("O jogador precisa ter um boné antes de usar o moleton.");
        }
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(4);  // Ganha 3 moedas extras
            System.out.println(getNome() + " ganhou 3 moedas extras por ter um moleton!");
        } else {
            super.adicionarMoedas(quantidade);
        }
    }

    @Override
    public String getInfoBonus() {
        return "Moleton: +3 moedas em casas simples";
    }
    @Override
    public String toString() {
        return super.toString() + " (com Moleton)";
    }
    @Override
    public ResultadoDados jogarDados() {
        return jogadorDecorado.jogarDados();
    }
}
