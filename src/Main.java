
import models.Jogador;
import models.JogadorAzarado;
import models.JogadorSortudo;
import models.Dados;
import views.PainelTabuleiro;
import controllers.TabuleiroController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // Solicita o número de jogadores
        int numJogadores = obterNumeroJogadores();

        // Criação dos dados
        Dados dados = new Dados();

        // Criação da visão do tabuleiro
        List<Jogador> jogadores = criarJogadores(numJogadores);
        PainelTabuleiro painelTabuleiro = new PainelTabuleiro(jogadores);

        // Atualiza jogadores com o painelTabuleiro se necessário
        atualizarJogadoresComPainel(jogadores, painelTabuleiro);

        // Criação do controlador
        new TabuleiroController(painelTabuleiro, dados, jogadores);
    }

    private static int obterNumeroJogadores() {
        int numJogadores = 0;
        while (numJogadores < 2 || numJogadores > 6) {
            String input = JOptionPane.showInputDialog(null, "Informe o número de jogadores (2 a 6):");
            try {
                numJogadores = Integer.parseInt(input);
                if (numJogadores < 2 || numJogadores > 6) {
                    JOptionPane.showMessageDialog(null, "O número de jogadores deve estar entre 2 e 6.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
            }
        }
        return numJogadores;
    }

    private static List<Jogador> criarJogadores(int numJogadores) {
        List<Jogador> jogadores = new ArrayList<>();
        Set<Color> coresUsadas = new HashSet<>();
        Color[] coresDisponiveis = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK
        };
        Random random = new Random();

        for (int i = 1; i <= numJogadores; i++) {
            String nome = "Jogador " + i;
            Color cor = gerarCorUnica(coresUsadas, coresDisponiveis, random);
            Jogador jogador;
            if (i % 2 == 0) {
                jogador = new JogadorAzarado(nome, cor);
            } else {
                jogador = new JogadorSortudo(nome, cor, jogadores); // Passando a lista de jogadores
            }
            jogadores.add(jogador);
        }
        return jogadores;
    }

    private static Color gerarCorUnica(Set<Color> coresUsadas, Color[] coresDisponiveis, Random random) {
        if (coresUsadas.size() == coresDisponiveis.length) {
            throw new RuntimeException("Todas as cores disponíveis foram usadas.");
        }

        Color cor;
        do {
            cor = coresDisponiveis[random.nextInt(coresDisponiveis.length)];
        } while (coresUsadas.contains(cor));

        coresUsadas.add(cor);
        return cor;
    }

    private static void atualizarJogadoresComPainel(List<Jogador> jogadores, PainelTabuleiro painelTabuleiro) {
        for (Jogador jogador : jogadores) {
            if (jogador instanceof JogadorSortudo) {
                ((JogadorSortudo) jogador).setPainelTabuleiro(painelTabuleiro);
            } else if (jogador instanceof JogadorAzarado) {
                ((JogadorAzarado) jogador).setPainelTabuleiro(painelTabuleiro);
            }
        }
    }
}
