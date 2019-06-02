package ua.lpnuai.oop.gordon06;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Generator {

    public static void main(String[] args) {
        Random random = new Random();

        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("test.txt")))
        ) {
            for(int i = 0; i < 1000; i++){
                writer.write(Integer.toString(random.nextInt()));
                if(i != 999) writer.newLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
