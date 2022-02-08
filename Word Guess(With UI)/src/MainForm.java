
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainForm {

    private static final String fileName = "Words.txt";

    private ArrayList<String> words = new ArrayList<String>();

    public MainForm() {
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                words.add(line);

        } catch (Exception e) {
            System.out.println("Couldn't find/read file: " + fileName);
            System.out.println("Error message: " + e.getMessage());
        }
    }
    public String getRandomWord() {
        if (words.isEmpty())
            return "NO-DATA";
        return words.get((int)(Math.random()*words.size()));
    }
}