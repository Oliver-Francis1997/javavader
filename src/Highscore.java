
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Highscore {
    //defines file
    File f = new File("lib/highscores.txt");
    //sets sanner to manipulate the file
    Scanner fileScan = null;
    //defiens array
    ArrayList<Integer> highscores = new ArrayList<Integer>();
    //constuctor so the methords can be called
    public Highscore() {

    }
    //sets the array list with the info on the file
    public ArrayList<Integer> setHighscore() {
        try {
            highscores.clear();
            fileScan = new Scanner(f);
            while (fileScan.hasNextInt()) {
                highscores.add(fileScan.nextInt());
            }
        } catch (FileNotFoundException e) {
        } finally {
            fileScan.close();
        }
        highscores.sort(Comparator.reverseOrder());
        return highscores;
    }
    //returns the highest score
    public int getHighScore() {
        highscores = setHighscore();
        return highscores.get(0);
    }
    //returns top five scores
    public String getTop5(){
        highscores = setHighscore();
        ArrayList<Integer> top5 = new ArrayList<Integer>();
        for(int i = 0; i < 4 || i < highscores.size(); i++){
            top5.add(highscores.get(i));
        }
        //string formatting
        String allScores = top5.toString();
        allScores = allScores.replace(",", "");
        allScores = allScores.substring(0, allScores.length() - 1);
        allScores = allScores.replace("[", "\n");
        allScores = allScores.replace(" ", "\n");
        return allScores;
    }
    // Updates highscore
    public void updateHighscore(int score) {
        highscores = setHighscore();
        highscores.add(score);
        Set<Integer> set = new HashSet<>(highscores);
        highscores.clear();
        highscores.addAll(set);
        highscores.sort(Comparator.reverseOrder());
        // Updates the high score text file if your score exceeds the previous high score
        try {
            String allScores = highscores.toString(); 
            allScores = allScores.replace(",", "");
            allScores = allScores.substring(1, allScores.length() - 1);
            allScores = allScores.replace(" ", "\n");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
            pw.write(allScores);
            pw.close();
        } 
        catch (FileNotFoundException e){}
    }

}