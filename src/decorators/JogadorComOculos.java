// JogadorComOculos.java
package decorators;

import models.Jogador;
import models.ResultadoDados;

public class JogadorComOculos extends JogadorDecorator {
    public JogadorComOculos(Jogador jogador) {
        super(jogador);
        if (!(jogador instanceof JogadorComMoleton)) {
            throw new IllegalArgumentException("O jogador precisa ter um moleton antes de usar os óculos.");
        }
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(7);  // Ganha 6 moedas extras
            System.out.println(getNome() + " ganhou 6 moedas extras por ter óculos escuros!");
        } else {
            super.adicionarMoedas(quantidade);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (com Óculos Escuros)";
    }

    @Override
    public ResultadoDados jogarDados() {
        return jogadorDecorado.jogarDados();
    }
}
