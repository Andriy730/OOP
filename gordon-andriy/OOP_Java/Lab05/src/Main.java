package ua.lpnuai.oop.gordon05;

import ua.lpnuai.oop.gordon03.Bureau;
import ua.lpnuai.oop.gordon03.person.EyeColor;
import ua.lpnuai.oop.gordon03.person.LonelyPerson;
import ua.lpnuai.oop.gordon03.person.PersonToFind;
import ua.lpnuai.oop.gordon03.person.Sex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("note.txt")))) {
            String strLine;
            Bureau bureau = new Bureau();
            int counter = 0;

            while ((strLine = reader.readLine()) != null){
                if(!checkSex(strLine)){
                    System.out.println("Wrong data in file!");
                    break;
                }
                Sex sex = Enum.valueOf(Sex.class, strLine.toUpperCase());

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                if(!checkName(strLine)){
                    System.out.println("Wrong data in file!");
                    break;
                }
                String Name = strLine;

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                if(!checkAge(strLine)){
                    System.out.println("Wrong data in file!");
                    break;
                }
                int age1 = Integer.parseInt(strLine);

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                if(!checkHeight(strLine)){
                    System.out.println("Wrong data in file!");
                    break;
                }

                int height = Integer.parseInt(strLine);

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                if(!checkColor(strLine)){
                    System.out.println("Wrong date in file!");
                    break;
                }

                EyeColor color = Enum.valueOf(EyeColor.class, strLine.toUpperCase());

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                String hobby = strLine;

                LonelyPerson client = new LonelyPerson(sex, Name, age1, height, color, hobby);

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                if(!checkSex(strLine)){
                    System.out.println("Wrong data in file!");
                    break;
                }

                Sex sex1 = Enum.valueOf(Sex.class, strLine.toUpperCase());

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                if(!checkAge(strLine)){
                    System.out.println("Wrong data in file!");
                    break;
                }

                int age = Integer.parseInt(strLine);

                if(age < 16){
                    System.out.println("Wrong data in file!");
                    break;
                }

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                EyeColor color1 = null;

                if(checkColor(strLine)){
                    color1 = Enum.valueOf(EyeColor.class, strLine.toUpperCase());

                    strLine = reader.readLine();
                    if(!notNull(strLine)) break;
                }

                String music = strLine;

                strLine = reader.readLine();
                if(!notNull(strLine)) break;

                String hobby1 = strLine;

                PersonToFind person = new PersonToFind(sex1, age, color1, hobby1, music);

                bureau.add(new Bureau.Client(client, LocalDate.now().getYear(),
                        LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(),person));

                counter++;
            }

            System.out.println(counter + " clients added successfully");

            System.out.println(bureau);
        }
        catch (IOException e){
            e.printStackTrace();
        }
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

    private static boolean checkHeight(String str){
        Pattern p = Pattern.compile("^[12][0-9][0-9]$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private static boolean checkAge(String str){
        Pattern p = Pattern.compile("^?1[0-9]$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean notNull(Object obj){
        if(obj == null) System.out.println("Empty file");
        return obj != null;
    }
}
