
import models.Jogador;
import models.JogadorAzarado;
import models.JogadorSortudo;
import models.Dados;
import views.PainelTabuleiro;
import controllers.TabuleiroController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criação de jogadores
        Jogador jogador1 = new JogadorSortudo("Jogador 1", Color.RED);
        Jogador jogador2 = new JogadorAzarado("Jogador 2", Color.BLUE);

        // Lista de jogadores
        List<Jogador> jogadores = Arrays.asList(jogador1, jogador2);

        // Criação da visão do tabuleiro
        PainelTabuleiro painelTabuleiro = new PainelTabuleiro(jogadores);

        // Criação dos dados
        Dados dados = new Dados();

        // Criação do controlador
        new TabuleiroController(painelTabuleiro, dados, jogadores);
    }
}
