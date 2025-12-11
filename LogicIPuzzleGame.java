public class LogicIPuzzleGame {
    private LogicIPuzzle puzzle;
    private int availableHints;
    private Color[][] grid;
    private boolean over;

    public LogicIPuzzleGame(LogicIPuzzle puzzle, int hints) {
        this.puzzle = puzzle;
        this.availableHints = hints;
        this.grid = new Color[puzzle.rows()][puzzle.columns()];
        this.over = false;
    }

    public int rows() {
        return puzzle.rows();
    }

    public int columns() {
        return puzzle.columns();
    }

    public int getAvailableHints() {
        return availableHints;
    }

    public boolean isOver() {
        return over;
    }

    public boolean hasClue(int row, int col) {
        return puzzle.hasClue(row, col);
    }

    public Clue getClue(int row, int col) {
        return puzzle.getClue(row, col);
    }

    public boolean isFilled(int row, int col) {
        return grid[row - 1][col - 1] != null;
    }

    public Color getColor(int row, int col) {
        return grid[row - 1][col - 1];
    }

    public void fill(int row, int col, Color color) {
        grid[row - 1][col - 1] = color;
    }

    public void unfill(int row, int col) {
        grid[row - 1][col - 1] = null;
    }

    public boolean checkIfCorrectUsingHint() {
        availableHints--;

        boolean allCorrect = true;
        boolean allFilled = true;

        for (int r = 1; r <= rows(); r++) {
            for (int c = 1; c <= columns(); c++) {

                if (!isFilled(r, c)) {
                    allFilled = false;
                    continue;
                }

                Color current = getColor(r, c);

                if (puzzle.hasClue(r, c)) {
                    Color correct = puzzle.getColor(r, c);
                    if (current != correct) {
                        allCorrect = false;
                    }
                }
            }
        }
        if (allCorrect && allFilled) {
            over = true;
        }
        return allCorrect;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (over) {
            sb.append("Puzzle resolvido.\n");
        } else {
            sb.append("Puzzle ainda não resolvido.\n");
        }

        for (int r = 1; r <= rows(); r++) {
            for (int c = 1; c <= columns(); c++) {
                char ch;

                if (isFilled(r, c)) {
                    Color color = getColor(r, c);
                    if (color == Color.Black) {
                        ch = '*'; // preto
                    } else {
                        ch = 'o'; // branco
                    }
                } else {
                    ch = '.'; // vazio
                }

                sb.append(ch);

                if (c < columns()) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        sb.append(availableHints).append(" dicas disponiveis.");

        return sb.toString();
    }
}
