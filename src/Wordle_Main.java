import java.io.FileNotFoundException;
import java.util.*;

public class Wordle_Main {
    public static void main(String[] args) throws FileNotFoundException {

        Player player = new Player();

        Game game = new Game();

        writeSlots(game);

        for (int i = 0; i < 5; i++) {
            boolean input;

            do {
                input = getAnswer(player, game);
            } while (!input);

            findColors(player, game);

            writeSlots(game);

            player.row++;

            if (player.answer.equals(game.answer)) {
                player.correct = true;
                break;
            }
        }

        if (player.correct) System.out.println("\nCongratulations!");
        else {
            System.out.printf("The correct answer is %S.%n", game.answer);
            System.out.println("\nTry Again.");
        }
    }

    static void writeSlots(Game game) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (game.slots[i][j].color == Colors.GREEN) {
                    System.out.print("\u001b[42;1m" + " " + game.slots[i][j].Char + " " + "\033[0m ");

                } else if (game.slots[i][j].color == Colors.YELLOW) {
                    System.out.print("\u001b[43;1m" + " " + game.slots[i][j].Char + " " + "\033[0m ");

                } else System.out.print("\u001b[40;1m" + " " + game.slots[i][j].Char + " " + "\033[0m ");

            }
            System.out.println("\n");
        }
    }

    static boolean getAnswer(Player player, Game game) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        player.answer = sc.next().toUpperCase();

        if (game.dictList.contains(player.answer)) {
            for (int i = 0; i < 5; i++) {
                game.slots[player.row][i].Char = player.answer.charAt(i);
            }
            return true;
        } else {
            System.out.println("You entered an invalid word!");
            return false;
        }
    }

    static void findColors(Player player, Game game) {

        LinkedHashMap<Character, Integer> letters = new LinkedHashMap<>(game.letters);

        for (int i = 0; i < 5; i++) {
            char Char = game.slots[player.row][i].Char;

            if (Char == game.answer.charAt(i) && letters.get(Char) >= 1) {
                game.slots[player.row][i].color = Colors.GREEN;
                letters.put(Char, letters.get(Char) - 1);
            }
        }
        for (int i = 0; i < 5; i++) {
            char Char = game.slots[player.row][i].Char;

            if (game.answer.contains(String.valueOf(Char)) && letters.get(Char) >= 1) {
                game.slots[player.row][i].color = Colors.YELLOW;
                letters.put(Char, letters.get(Char) - 1);
            }
        }
    }
}

