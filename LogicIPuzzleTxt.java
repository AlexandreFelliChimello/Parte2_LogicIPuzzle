import java.util.Scanner;

public class LogicIPuzzleTxt {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Erro: Indique o numero de linhas e colunas.");
            return;
        }

        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);

        LogicIPuzzleRandom generator = new LogicIPuzzleRandom(rows, cols);

        Scanner reader = new Scanner(System.in);

        while (generator.hasNextPuzzle()) {
            LogicIPuzzle puzzle = generator.nextPuzzle();

            System.out.println("As pistas do puzzle: ");
            System.out.println(puzzle.cluesToString());
            System.out.println("---------------------");

            play(puzzle, reader);

            System.out.println("Iniciar outro jogo? (s/n): ");
            String resposta = reader.nextLine().trim().toLowerCase();
            if (resposta.equals("n")) {
                break;
            }
        }

        System.out.println("Não existem mais puzzles de tamanho " + rows + " x " + cols + " para jogar. Fim.");
    }

    static void play(LogicIPuzzle puzzle, Scanner reader) {
        int totalSquares = puzzle.rows() * puzzle.columns();
        int hints = (int) Math.sqrt(totalSquares);

        LogicIPuzzleGame game = new LogicIPuzzleGame(puzzle, hints);

        System.out.println("Existem disponíveis " + game.getAvailableHints() + " dicas. Boa sorte!");

        while (!game.isOver()) {

            System.out.println("0 - Preto");
            System.out.println("1 - Branco");
            System.out.println("2 - Esvaziar");
            System.out.println("3 - Verificar casas preenchidas");
            System.out.println("Jogada? ");

            String line = reader.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            int jogada;
            try {
                jogada = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            if (jogada == 0 || jogada == 1 || jogada == 2) {

                System.out.println("Casa? ");
                String casaStr = reader.nextLine().trim();

                int pos;
                try {
                    pos = Integer.parseInt(casaStr);
                } catch (NumberFormatException e) {
                    System.out.println("Casa inválida.");
                    continue;
                }

                int cols = game.columns();
                int row = pos / cols + 1;
                int col = pos % cols + 1;

                if (game.hasClue(row, col)) {
                    System.out.println("Essa casa possui uma pista e não pode ser alterada.");
                } else {
                    if (jogada == 0) {
                        game.fill(row, col, Color.Black);
                    } else if (jogada == 1) {
                        game.fill(row, col, Color.White);
                    } else { // jogada == 2
                        game.unfill(row, col);
                    }
                }
            } else if (jogada == 3) {
                if (game.getAvailableHints() > 0) {
                    boolean ok = game.checkIfCorrectUsingHint();
                    if (ok) {
                        System.out.println("Todas as casas preenchidas estão corretas!");
                    } else {
                        System.out.println("Existem casas preenchidas incorretamente.");
                    }
                } else {
                    System.out.println("Não existem dicas disponíveis.");
                }
            } else {
                System.out.println("Opção inválida.");
            }
            System.out.println("-------------------");
            System.out.println("Estado atual do jogo: ");
            System.out.println(game.toString());
            System.out.println("-------------------");
            System.out.println("As pistas do puzzle: ");
            System.out.println(puzzle.cluesToString());
        }
        System.out.println("Parabéns! Você completou o puzzle!");
    }
}
