package javagames.filesandresourses;

import java.io.*;
import java.util.Random;

public class WritingDataToFiles {
    private Random rand = new Random();

    public void runTest() {
        writeOutBytes("E:\\JAVAGAMES\\byte.txt");
        writeOutStrings("E:\\JAVAGAMES\\string.txt");
    }

    public void writeOutBytes(String filename) {
        System.out.println();
        System.out.println();
        System.out.println("*******************");
        File file = new File(filename);
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            for (int i = 0; i < 1000; i++) {
                out.write(rand.nextInt(256));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
                System.out.println("Wrote: " + file.getPath());
            } catch (Exception ex) {
            }
        }
    }

    public void writeOutStrings(String filename) {
        System.out.println();
        System.out.println();
        System.out.println("*******************");
        String[] strings = {
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit,",
                "sed do eiusmod tempor incididunt ut labore et dolore magna",
                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation",
                "ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                "Duis aute irure dolor in reprehenderit in voluptate velit",
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint",
                "occaecat cupidatat non proident, sunt in culpa qui officia",
                "deserunt mollit anim id est laborum.",
        };
        File file = new File(filename);
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            for (String str : strings) {
                out.println(str);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
                System.out.println("Wrote: " + file.getPath());
            } catch (Exception ex) {
            }
        }
    }

    public static void main(String[] args) {
        new WritingDataToFiles().runTest();
    }
}
