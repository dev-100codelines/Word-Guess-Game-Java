import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        boolean keepPlaying = true;
        Scanner sn = new Scanner(System.in);

        while (keepPlaying) {
            Main main = new Main();
            main.startGame();

            System.out.print("Would you like to play again? [Y or N]: ");
            String response = sn.nextLine().toUpperCase();
            keepPlaying = (response.equals("Y") || (response.equals("Yes")));
        }
    }

    private static final String[] WORDS_DATABASE = new String[] {
            "code","kids","baby", "done","beach","house","apple", "paper",
            "Java", "school", "science", "Oracle", "cricket", "carrot","sun"
    };

    private static String[] hints = new String[] {
            "Programs are made of ___", "Similar word for children", "Similar word for for infant",
            "Similar word for for completed ","This place is next to ocean", "Other word for 'home'",
            "This is a red color fruit", "pencil and ____", "programming language",
            "place for education", "Physics, chemistry, biology are all ____",
            "Larry Ellison founded this company", "Famous sport with bat and ball",
            "What rabbits eat", "moon and ___"
    };

    private void startGame() {
        boolean gameOn = true;
        int numberOfGuesses = 0;
        String original = selectRandomWord();
        String shuffled = getShuffledWord(original);

        while(gameOn) {
            System.out.println("Shuffled word is: "+shuffled);

            numberOfGuesses++;
            String userGuess = getUserGuess();
            if(original.equalsIgnoreCase(userGuess)) {
                System.out.println("Congratulations! You found the word in "+numberOfGuesses+" guesses");
                System.out.println("");
                gameOn = false;
            }else{

                System.out.println("Sorry, Wrong answer");
                System.out.println("--------");
                if (numberOfGuesses > 0){
                    displayHint(original);
                    System.out.println("");
                }
            }
        }
    }
    public String getUserGuess() {
        Scanner sn = new Scanner(System.in);
        System.out.print("Please type in the original word: ");
        return sn.nextLine();
    }

    public String selectRandomWord() {
        int rPos = ThreadLocalRandom.current().nextInt(0, WORDS_DATABASE.length);
        return WORDS_DATABASE[rPos];
    }

    public String getShuffledWord(String original) {
        String shuffledWord = original; // start with original
        int wordSize = original.length();
        int shuffleCount = 10; // let us randomly shuffle letters 10 times
        for(int i=0;i<shuffleCount;i++) {
            //swap letters in two indexes
            int position1 = ThreadLocalRandom.current().nextInt(0, wordSize);
            int position2 = ThreadLocalRandom.current().nextInt(0, wordSize);
            shuffledWord = swapCharacters(shuffledWord,position1,position2);
        }
        return shuffledWord;
    }

    private String swapCharacters(String shuffledWord, int position1, int position2) {
        char[] charArray = shuffledWord.toCharArray();
        // Replace with a "swap" function, if desired:
        char temp = charArray[position1];
        charArray[position1] = charArray[position2];
        charArray[position2] = temp;
        return new String(charArray);
    }

    private void displayHint(String s){
        for (int i = 0; i < WORDS_DATABASE.length; i++){
            if (WORDS_DATABASE[i].equals(s)){
                System.out.println("HINT:" + hints[i]);
            }
        }
    }
}