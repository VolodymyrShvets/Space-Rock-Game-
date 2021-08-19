package javagames.filesandresourses;

import javagames.util.ResourceLoader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceLoaderExample {
    public ResourceLoaderExample() {
    }

    public void runTest() {
        Class<?> clazz = ResourceLoaderExample.class;
        //load absolute resource
        String filePath = "not/used";
        String resPath = "/javagames/filesandresourses/Test1.txt";
        InputStream in = ResourceLoader.load(clazz, filePath, resPath);
        printResource(in);

        //load relative resource
        filePath = "not/used";
        resPath = "Test2.txt";
        in = ResourceLoader.load(clazz, filePath, resPath);
        printResource(in);

        // load absolute file path
        filePath = "E:\\JAVAGAMES\\SpaceRocks\\Space Rocks\\src\\javagames\\filesandresourses\\Test3.txt";
        resPath = "/not/available";
        in = ResourceLoader.load(clazz, filePath, resPath);
        printResource(in);

        // load relative file path
        filePath = "SpaceRocks\\Space Rocks\\src\\javagames\\filesandresourses\\Test3.txt";
        resPath = "/not/available";
        in = ResourceLoader.load(clazz, filePath, resPath);
        printResource(in);

        // error with both is null
        filePath = "fat/finger";
        resPath = "fat/finger/too";
        in = ResourceLoader.load(clazz, filePath, resPath);
        printResource(in);
    }

    private void printResource(InputStream in) {
        try {
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(reader);
            String line = null;
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        new ResourceLoaderExample().runTest();
    }
}
