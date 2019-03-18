package ua.lpnuai.oop.gordon02;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Data.dat"))){
            Object data = ois.readObject();
            System.out.println(data.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
