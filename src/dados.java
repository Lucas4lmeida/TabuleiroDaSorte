import java.util.Random;

public class dados {
    private Random random = new Random();

    public int[] lancarDados() {
        int dado1 = random.nextInt(6) + 1; // Valores entre 1 e 6
        int dado2 = random.nextInt(6) + 1;
        return new int[]{dado1, dado2};
    }
}