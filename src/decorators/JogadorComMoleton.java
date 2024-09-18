package decorators;

import models.Jogador;
import javax.swing.ImageIcon;

public class JogadorComMoleton extends JogadorDecorator {
    private static final ImageIcon MOLETON_ICON = new ImageIcon("src/main/resources/moleton_icon.png");

    public JogadorComMoleton(Jogador jogador) {
        super(jogador);
    }

    @Override
    public ImageIcon getIcon() {
        return MOLETON_ICON;
    }

    @Override
    public String getInfoItens() {
        return super.getInfoItens() + ", Moleton";
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(4);  // Ganha 3 moedas extras
        } else {
            super.adicionarMoedas(quantidade);
        }
    }
}