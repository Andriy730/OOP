package ua.lpnuai.oop.gordon05;

import ua.lpnuai.oop.gordon03.Bureau;
import ua.lpnuai.oop.gordon03.person.EyeColor;
import ua.lpnuai.oop.gordon03.person.LonelyPerson;
import ua.lpnuai.oop.gordon03.person.PersonToFind;
import ua.lpnuai.oop.gordon03.person.Sex;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String command;
        Bureau bureau = new Bureau();

        System.out.println("show. Show clients\n" +
                "in. Enter data\n" +
                "delete. Delete clients" +
                "exit. Exit\n");

        do {
            System.out.println("Enter command");
            command = in.nextLine();
            if (command.equals("in")) {
                System.out.print("Enter numbers of clients:");
                int size = in.nextInt();
                System.out.println("Enter clients:");
                for (int i = 0; i < size; i++) {
                    System.out.println("Tell me about yourself:");

                    System.out.print("Sex:");
                    String input_sex = in.next();
                    if (!checkSex(input_sex)){
                        System.out.println("Wrong data");
                        break;
                    }
                    Sex sex = Enum.valueOf(Sex.class, input_sex.toUpperCase());
                    System.out.print("Name:");
                    String name = in.next();
                    if(!checkName(name)){
                        System.out.println("Wrong data");
                        break;
                    }
                    System.out.print("age:");
                    int age1 = in.nextInt();
                    if(age1 < 16 || age1 > 80){
                        System.out.println("Wrong data");
                        break;
                    }
                    System.out.print("Your height:");
                    int height = in.nextInt();
                    in.nextLine();
                    System.out.print("your eye`s color[blue, brown, green]:");
                    String input_color = in.next();
                    if(!checkColor(input_color)){
                        System.out.println("Wrong data");
                        break;
                    }
                    EyeColor color = Enum.valueOf(EyeColor.class, input_color.toUpperCase());
                    System.out.print("Hobby:");
                    String hobby = in.nextLine();

                    LonelyPerson lonelyPerson = new LonelyPerson(sex, name, age1, height, color, hobby);

                    System.out.println("\nTell me about Person you want:\n");

                    System.out.print("Sex[men, women]:");
                    String input_sex1 = in.next();
                    if(!checkSex(input_sex1)){
                        System.out.println("Wrong data");
                        break;
                    }
                    Sex sex1 = Enum.valueOf(Sex.class, input_sex1.toUpperCase());
                    System.out.print("Age: ");
                    int age = in.nextInt();
                    if(age < 16 || age > 80) {
                        System.out.println("Wrong data");
                        break;
                    }
                    in.nextLine();
                    System.out.println("maybe eye color?");
                    EyeColor eyeColor;
                    if (in.next().equals("yes")) {
                        System.out.print("Enter color: ");
                        String input_color1 = in.next();
                        if(!checkColor(input_color1)){
                            System.out.println("Wrong data");
                            break;
                        }
                        eyeColor = EyeColor.valueOf(EyeColor.class, input_color1.toUpperCase());
                    } else eyeColor = null;
                    System.out.print("Favorite music genre: ");
                    String music = in.next();
                    System.out.print("Hobby: ");
                    String hobby1 = in.next();
                    PersonToFind personToFind = new PersonToFind(sex1, age, eyeColor, hobby1, music);

                    Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                            LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(),
                            personToFind);

                    bureau.add(client);
                }
            }

            if(command.equals("show")){
                System.out.println(bureau);
            }

        }while(!command.equals("exit"));
        in.close();
    }

    private static boolean checkSex(String str){
        Pattern p = Pattern.compile("^men|women$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private static boolean checkDate(String str){
        Pattern p = Pattern.compile("^[0-9]{4}\\.[01][0-9]\\.[0123][0-9]$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private static boolean checkColor(String str){
        Pattern p = Pattern.compile("^(brown|green|blue)$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private static boolean checkName(String str){
        Pattern p = Pattern.compile("^[a-z|A-Z][a-z]{1,14}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
