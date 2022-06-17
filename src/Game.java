import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    String answer;
    LinkedHashMap<Character, Integer> letters = new LinkedHashMap<>();
    ArrayList<String> dictList;
    ArrayList<String> answersList;
    Slot[][] slots = new Slot[5][5];

    public Game() throws FileNotFoundException {
        createSlots();
        pickWord();
        createDict();
    }

    private void createSlots() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Slot slot = new Slot();
                slots[i][j] = slot;
            }
        }
    }

    private void pickWord() throws FileNotFoundException {

        File answersTxt = new File("src/Words/Answers.txt");

        Scanner answersScanner = new Scanner(answersTxt);

        answersList = new ArrayList<>();

        while (answersScanner.hasNextLine()) {
            answersList.add(answersScanner.nextLine().toUpperCase());
        }

        answer = answersList.get((int) (Math.random() * answersList.size()));

        int k;
        for (int i = 0; i < 5; i++) {
            k = 1;
            for (int j = 0; j < 5; j++) {
                if (i != j && answer.charAt(i) == answer.charAt(j))
                    k++;
            }
            letters.put(answer.charAt(i), k);
        }
    }

    private void createDict() throws FileNotFoundException {
        File dictTxt = new File("src/Words/Dict.txt");

        Scanner dictScanner = new Scanner(dictTxt);

        dictList = new ArrayList<>();

        while (dictScanner.hasNextLine()) {
            dictList.add(dictScanner.nextLine().toUpperCase());
        }
    }
}
