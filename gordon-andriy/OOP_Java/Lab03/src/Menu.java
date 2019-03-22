package ua.lpnuai.oop.gordon03;

import ua.lpnuai.oop.gordon03.person.*;

import java.beans.*;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;


public class Menu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command;
        Bureau bureau = new Bureau();

        System.out.println("show. Show clients\n" +
                "in. Enter data\n" +
                "save. Save data\n" +
                "delete. Delete clients" +
                "exit. Exit\n");

        do {
            command = in.nextLine();
            if(command.equals("in")){
                System.out.print("Enter numbers of clients:");
                int size = in.nextInt();
                System.out.println("Enter clients:");
                for(int i = 0; i < size; i++){
                    System.out.println("Tell me about yourself:");

                    System.out.print("Sex[men, women]:");
                    Sex sex = Enum.valueOf(Sex.class, in.next().toUpperCase());
                    System.out.print("Name:");
                    String name = in.next();
                    System.out.print("Birthday[year month day]:");
                    int year = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    System.out.print("Your height:");
                    int height = in.nextInt();
                    in.nextLine();
                    System.out.print("your eye`s color[blue, brown, green]:");
                    EyeColor color = Enum.valueOf(EyeColor.class, in.next().toUpperCase());
                    System.out.print("Hobby:");
                    String hobby = in.next();

                    LonelyPerson lonelyPerson = new LonelyPerson(sex, name, year, month, day, height, color, hobby);

                    System.out.println("\nTell me about Person you want:\n");

                    System.out.print("Sex[men, women]:");
                    Sex sex1 = Enum.valueOf(Sex.class, in.next().toUpperCase());
                    System.out.print("Age: ");
                    int age = in.nextInt();
                    in.nextLine();
                    System.out.println("maybe eye color?");
                    EyeColor eyeColor;
                    if(in.next().equals("yes")){
                        System.out.print("Enter color: ");
                        eyeColor = EyeColor.valueOf(EyeColor.class, in.next().toUpperCase());
                    }
                    else eyeColor = null;
                    System.out.print("Favorite music genre: ");
                    String music = in.next();
                    System.out.print("Hobby: ");
                    String hobby1 = in.next();
                    PersonToFind personToFind = new PersonToFind(sex1, age, eyeColor, hobby1, music);

                    Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                            LocalDate.now().getMonthValue() + 1, LocalDate.now().getDayOfMonth(),
                            personToFind);

                    bureau.add(client);
                }
            }
            if(command.equals("show")){
                System.out.println(bureau);
            }
            if(command.equals("save")){
                Path path = Paths.get("C:\\Users\\Home\\IdeaProjects\\gordon_andriy\\");
                DirectoryStream.Filter<Path> filter = entry -> (Files.isDirectory(entry));
                String nameOfFile = path.toString();
                try(DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter)) {
                    System.out.println(path + ":");
                    stream.forEach(path1 -> System.out.println(path1.getFileName()));
                    System.out.println("Create new directory or move to existed");
                    if(in.nextLine().equals("Create new")){
                        System.out.print("Enter name of directory:");
                        String nameOfDirectory = in.nextLine();
                        File dir = new File(path + "\\" + nameOfDirectory);
                        dir.mkdir();
                        nameOfFile += "\\" + nameOfDirectory + "\\" + "bureau.dat";
                    }
                    else {
                        String nameOfDirectory;
                        String enter;
                        while(true){
                            System.out.println("Enter name of directory you want to move ");
                            nameOfDirectory = in.nextLine();
                            if(new File((path + "\\" + nameOfDirectory)).isDirectory()) {
                                nameOfDirectory = path + "\\" + nameOfDirectory;

                                try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(Paths.get(nameOfDirectory + "\\"), filter)) {
                                    System.out.println(nameOfDirectory + ":");
                                    stream1.forEach(path1 -> System.out.println(path1.getFileName()));
                                }
                                System.out.println("Move back?");
                                enter = in.nextLine();
                                if (enter.equals("yes")) {
                                    String[] str = nameOfDirectory.split(" \\ ");
                                    for(int i = 0; i < str.length -1 ; i++){
                                        nameOfDirectory = str[i] + "\\";
                                    }
                                }
                                System.out.println("Enter break to break, else no");
                                enter = in.nextLine();
                                if(enter.equals("break")) {
                                    break;
                                }
                            }
                            else{
                                System.out.println("Wrong name!");
                            }
                        }
                        nameOfFile = nameOfDirectory + "\\" + "bureau.dat";
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }

                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nameOfFile))){
                    oos.writeObject(bureau);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
            if(command.equals("delete")){
                System.out.println("Enter number of registration of person you want to delete");
                int numberOfRegistration = in.nextInt();
                boolean success = bureau.remove(numberOfRegistration);
                System.out.println( success ? "successfully" : "Wrong number!");
            }

        }while(!command.equals("exit"));
    }
}
