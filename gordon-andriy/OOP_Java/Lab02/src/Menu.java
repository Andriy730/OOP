package ua.lpnuai.oop.gordon02;

import ua.lpnuai.oop.gordon02.myArray.MyArrayList;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = null;
        String output = null;
        String command;
        int lengthToDelete = 0;
        MyArrayList data = new MyArrayList();

        System.out.println("a. введення даних\n" +
                "b. перегляд даних\n" +
                "c. обробка даних\n" +
                "d. відображення результату\n" +
                "e. завершення програми");

        do {
            command = in.nextLine();

            if (command.equals("a")) {
                System.out.print("Введіть вхідні дані [текст <Enter>, довжина слів, для видалення<Enter>]: ");
                input = in.nextLine();
                lengthToDelete = in.nextInt();
                data.add(input + "\n" + lengthToDelete);
            }

            if (command.equals("b")) {
                if(input != null){
                    System.out.println(input + "\n" + lengthToDelete);
                }
                else if(data.size() > 0){
                    System.out.println(data.get(data.size()-1));
                }
                else System.out.println("Відсутні вхідні дані");
            }

            if (command.equals("c")) {
                if (input != null) {
                    output = Editor.edit(input, lengthToDelete);
                }
                else {
                    System.out.println("Відсутні дані для обробки");
                }
            }

            if (command.equals("d")) {
                if (output != null) {
                    System.out.println("Результат: ");
                    System.out.println(input);
                    System.out.println(output);
                    input = null;
                    output = null;
                } else {
                    System.out.println("Відсутні вихідні дані");
                }
            }
        }while (!command.equals("e")) ;

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Data.dat"))){
            oos.writeObject(data);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.exit(0);
    }
}
