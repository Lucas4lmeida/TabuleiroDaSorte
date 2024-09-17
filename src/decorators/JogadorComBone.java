// JogadorComBone.java
package decorators;

import models.Jogador;
import models.ResultadoDados;

import javax.swing.ImageIcon;

public class JogadorComBone extends JogadorDecorator {
    public JogadorComBone(Jogador jogador) {
        super(jogador);
        this.icon = new ImageIcon("src/resources/icons/bone_icon.png");
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(2);  // Ganha 1 moeda extra
        } else {
            super.adicionarMoedas(quantidade);
            System.out.println(getNome() + " ganhou 2 moedas extras por ter um Boné!");
        }
    }

    @Override
    public String getInfoBonus() {
        return "Boné: +1 moeda em casas simples";
    }

    @Override
    public String toString() {
        return super.toString() + " (com Boné)";
    }

    @Override
    public ResultadoDados jogarDados() {
        return jogadorDecorado.jogarDados();
    }
}
