package decorators;

import models.Jogador;
import javax.swing.ImageIcon;

public class JogadorComBone extends JogadorDecorator {
    private static final ImageIcon BONE_ICON = new ImageIcon("src/main/resources/bone_icon.png");

    public JogadorComBone(Jogador jogador) {
        super(jogador);
    }

    @Override
    public ImageIcon getIcon() {
        return BONE_ICON;
    }

    @Override
    public String getInfoItens() {
        return super.getInfoItens() + ", Boné";
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(2);  // Ganha 1 moeda extra
        } else {
            super.adicionarMoedas(quantidade);
        }
    }
}