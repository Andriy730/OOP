package ua.lpnuai.oop.gordon01;

import java.util.Scanner;

/**
 *  <p>Лабораторна робота №1</p>
 *  Варіант 7
 *  <p>Текстове меню користувача.</p>
 *
 *  Використовуэ утилітарний клас
 * @see ua.lpnuai.oop.gordon01.StringEditor
 *
 * @version 1.0 05-03-2019
 * @author Andriy Gordon
 */


public class TextMenu {

    public static void main(String[] args) {
        // ініціалізація всіх початкових змінних
        Scanner in = new Scanner(System.in);
        String input = null;
        String output = null;
        String command;
        int lengthToDelete = 0;
        boolean debug = false;

        // перевірка аргументів командного рядка
        if(args != null && args.length == 1){
            if(args[0].equals("-h")){
                System.out.println("Ввести текст. З тексту видалити всі слова заданої довжини, що\n" +
                        "починаються на приголосну літеру. Вивести початковий текст та\n" +
                        "результат.\n");
            }
            if(args[0].equals("-d")){
                debug = true;
            }
        }

        // вивід текстового меню для користувача
        System.out.println(
                "a. введення даних\n" +
                        "b. перегляд даних\n" +
                        "c. виконання обчислень\n" +
                        "d. відображення результату\n" +
                        "e. завершення програми");

        // обробка команд
        do{
            command = in.nextLine();

            if(command.equals("a")){
                System.out.print("Введіть вхідні дані текст [<Enter>, довжина слів, для видалення<Enter>]: )");
                input = in.nextLine();
                lengthToDelete = in.nextInt();
            }

            if(command.equals("b")){
                if(input != null){
                    System.out.println(input + "\n" + lengthToDelete);
                }
                else{
                    System.out.println("Відсутні вхідні дані");
                }
            }

            if(command.equals("c")){
                if(input != null){
                    output = StringEditor.edit(input, lengthToDelete, debug);
                }
                else{
                    System.out.println("Відсутні вхідні дані");
                }
            }

            if(command.equals("d")){
                if(output != null){
                    System.out.println("Результат: ");
                    System.out.println(input);
                    System.out.println(output);
                    input = null;
                    output = null;
                }
                else {
                    System.out.println("Відсутні вихідні дані");
                }
            }
        }while (!command.equals("e"));

        System.exit(0);
    }
}
