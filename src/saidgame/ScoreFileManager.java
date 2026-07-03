package saidgame;

import java.io.*;

public class ScoreFileManager {

    private static final String SCORE_FILE = "score.txt";

    public static void saveScore(String username, int score, int levelReached) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE, true))) { //افتح ملف الحفط اذا غير موجود انشء ولا تحذف
            writer.write(username + " | Score: " + score + " | Level Reached: " + levelReached);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getScoreFile() {
        return new File(SCORE_FILE); //لنستطيع فتح ملف ب سكوربورد
    }
}
