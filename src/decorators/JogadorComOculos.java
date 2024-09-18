package decorators;

import models.Jogador;
import javax.swing.ImageIcon;

public class JogadorComOculos extends JogadorDecorator {
    private static final ImageIcon OCULOS_ICON = new ImageIcon("src/main/resources/oculos_icon.png");

    public JogadorComOculos(Jogador jogador) {
        super(jogador);
    }

    @Override
    public ImageIcon getIcon() {
        return OCULOS_ICON;
    }

    @Override
    public String getInfoItens() {
        return super.getInfoItens() + ", Óculos";
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        if (quantidade == 1) {  // Apenas para casa simples
            super.adicionarMoedas(7);  // Ganha 6 moedas extras
        } else {
            super.adicionarMoedas(quantidade);
        }
    }
}