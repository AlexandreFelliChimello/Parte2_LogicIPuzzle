/** 
 * Projeto: Logic IPuzzle - Java 8
 * 
 * Este projeto implementa as funções e códigos necessários para que seja possível jogar o jogo IPuzzle, permitindo a criação do jogo com as informações 
 * solicitadas inicialmente e a continuação deste como "solução-puzzle-em-construção"
 * 
 * Em que consiste o jogo? O jogo consiste em uma grelha retangular (n x m), onde algumas posições contêm números (pistas numéricas de 1 a 9) 
 * e outras contêm partes pintadas de preto (partes onde os números não podem estar)
 * 
 * As posições são numeradas de cima para baixo e da esquerda para a direita
 * 
 * @author Alexandre Felli Chimello Número: 66474 e Ruan Vieira Damasceno Pimentel Número:66502
 * 
*/
import java.util.Scanner;

public class LogicIPuzzle {
    private final int rows;
    private final int cols;
    private final Clue[] gridClues;
    /**
     * Verifica se os parâmetros definem corretamente um puzzle para uma grelha
     * de dimensão rows x cols.
     *
     * @param rows             número de linhas da grelha (>= 2)
     * @param cols             número de colunas da grelha (>= 2)
     * @param squaresWithClues vetor com as posições (0 .. rows*cols-1) que têm pistas
     * @param clues            vetor com as pistas correspondentes a cada posição
     * @return true se os vetores definem um puzzle válido, false caso contrário
     */
    public static boolean definesPuzzle(int rows, int cols, int[] squaresWithClues, Clue[] clues) {
        
        int totalSquares = rows * cols;
        boolean[] seenPositions = new boolean[totalSquares];
        int[] counts = new int[Clue.values().length];

        //Verifica se tem o tamanho mínimo para ser considerado um puzzle
        if (rows < 2 || cols < 2) {
            return false;
        }

        if (squaresWithClues == null || clues == null) {
            return false;
        }
        //Garantir que há pistas
        if (squaresWithClues.length == 0) {
            return false;
        }
        //Garantir que não faltam pistas ou posições
        if (squaresWithClues.length != clues.length) {
            return false;
        }
        for (int pos : squaresWithClues) {
            if (pos < 0 || pos >= totalSquares) {
                return false;
            }
            if (seenPositions[pos]) {
                return false;
            }
            seenPositions[pos] = true;
        }

        for (Clue clue : clues) {
            if (clue == null) {
                return false;
            }
            counts[clue.ordinal()]++;
        }

        for (Clue clue : Clue.values()) {
            int count = counts[clue.ordinal()];

            if (count > 1 && clue != Clue.Black) {
                return false;
            }
        }

        if (!hasUniqueSolution(rows, cols, squaresWithClues, clues)) {
            return false;
        }

        return true;
    }

    private static boolean hasUniqueSolution(int rows, int cols,
                                             int[] squaresWithClues,
                                             Clue[] clues) {

        return true;
    }
    public LogicIPuzzle(int rows, int cols, int[] squaresWithClues, Clue[] clues){
        this.rows = rows;
        this.cols = cols;
        this.gridClues = new Clue[rows * cols];

        // Preencher o vetor gridClues com as pistas nas posições indicadas
        for (int i = 0; i < squaresWithClues.length; i++) {
            int pos = squaresWithClues[i]; // posição linear 0..rows*cols-1
            Clue clue = clues[i];
            gridClues[pos] = clue;
        }
    }
    public int rows(){
    return rows;
    }
    public int columns(){
    return cols;
    }
/**
     * Indica se o puzzle dá uma pista para a casa na linha e coluna dadas.
     * Assume que 1 <= row <= rows() e 1 <= col <= columns().
     */
    public boolean hasClue(int row, int col) {
        int index = toIndex(row, col);
        return gridClues[index] != null;
    }
    public Clue getClue(int row, int col) {
        int index = toIndex(row, col);
        return gridClues[index];
    }
    public Color getColor(int row, int col){
        Clue clue = getClue(row, col);
        return clue.toColor();
    }
    public String cluesToString() {
    StringBuilder sb = new StringBuilder();

    for (int r = 1; r <= rows; r++) {
        for (int c = 1; c <= cols; c++) {
            int index = toIndex(r, c);
            Clue clue = gridClues[index];

            if (clue == null) {
                sb.append('.');
            } else if (clue == Clue.Black) {
                sb.append('P');
            } else {
                // pista branca: usar o valor 1..9 como caractere
                int value = clue.getValue(); // 1..9
                sb.append((char) ('0' + value));
            }
        }
        if (r < rows) {
            sb.append('\n');
        }
    }

    return sb.toString();
    }
    public String toString(){
    return rows + " " + cols + "\n" + cluesToString();
    }
    public boolean sameClues(LogicIPuzzle other) {
        if (this.rows != other.rows || this.cols != other.cols) {
        return false;
        }

    int total = rows * cols;
        for (int i = 0; i < total; i++) {
        Clue c1 = this.gridClues[i];
        Clue c2 = other.gridClues[i];

            if (c1 != c2) { 
            return false;
            }
        }
    return true;
}
    private int toIndex(int row, int col) {
        return (row - 1) * cols + (col - 1);
    }
}

