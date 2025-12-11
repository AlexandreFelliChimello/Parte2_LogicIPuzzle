public class LogicIPuzzleRandom {
    private int rows;
    private int cols;
    private LogicIPuzzle[] puzzles;
    private int nextIndex;

    public LogicIPuzzleRandom(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.puzzles = new LogicIPuzzle[0];
        this.nextIndex = 0;
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return cols;
    }

    public boolean hasNextPuzzle() {
        return nextIndex < puzzles.length;
    }

    public LogicIPuzzle nextPuzzle() {
        if (!hasNextPuzzle()) {
            return null;
        }
        return puzzles[nextIndex++];
    }
}
