package ua.lpnuai.oop.gordon07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lpnuai.oop.gordon03.person.EyeColor;
import ua.lpnuai.oop.gordon03.person.LonelyPerson;
import ua.lpnuai.oop.gordon03.person.PersonToFind;
import ua.lpnuai.oop.gordon03.person.Sex;

import java.time.LocalDate;

class Tests {

    @Test
    void addAndGetTest(){
        Bureau bureau = new Bureau();

        LonelyPerson lonelyPerson = new LonelyPerson(Sex.MEN, "Andrew", 17, 173, EyeColor.GREEN, "music");
        PersonToFind person = new PersonToFind(Sex.WOMEN, 18, "music", "jazz");

        Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person);

        bureau.add(client);

        Assertions.assertEquals(client, bureau.get(0));
    }

    @Test
    void removeTest(){
        Bureau bureau = new Bureau();

        LonelyPerson lonelyPerson = new LonelyPerson(Sex.MEN, "Andrew", 17, 173, EyeColor.GREEN, "music");
        PersonToFind person = new PersonToFind(Sex.WOMEN, 18, "music", "jazz");

        Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person);

        bureau.add(client);

        int id = client.getNumberOfRegistration();

        Assertions.assertTrue(bureau.remove(id));
        Assertions.assertFalse(bureau.remove(50));
        Assertions.assertFalse(bureau.remove(-1));
    }

    @Test
    void searchTest(){
        Bureau bureau = new Bureau();

        LonelyPerson lonelyPerson = new LonelyPerson(Sex.MEN, "Andrew",17, 173, EyeColor.GREEN, "music");
        PersonToFind person = new PersonToFind(Sex.WOMEN, 18, "music", "jazz");

        Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person);

        bureau.add(client);

        LonelyPerson lonelyPerson1 = new LonelyPerson(Sex.MEN, "Nazar", 18, 173, EyeColor.BROWN, "music");
        PersonToFind person1 = new PersonToFind(Sex.WOMEN, 18, "", "Rep");

        Bureau.Client client1 = new Bureau.Client(lonelyPerson1, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person1);

        bureau.add(client1);

        Assertions.assertEquals(0, bureau.search(client.getNumberOfRegistration()));
        Assertions.assertEquals(1, bureau.search(client1.getNumberOfRegistration()));
        Assertions.assertEquals(-1, bureau.search(500));
    }

    @Test
    void sortByAgeTest(){
        Bureau bureau = new Bureau();

        LonelyPerson lonelyPerson = new LonelyPerson(Sex.MEN, "Andrew",17, 173, EyeColor.GREEN, "music");
        PersonToFind person = new PersonToFind(Sex.WOMEN, 18, "music", "jazz");

        Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person);

        bureau.add(client);

        LonelyPerson lonelyPerson1 = new LonelyPerson(Sex.MEN, "Nazar", 18, 173, EyeColor.BROWN, "music");
        PersonToFind person1 = new PersonToFind(Sex.WOMEN, 18, "", "Rep");

        Bureau.Client client1 = new Bureau.Client(lonelyPerson1, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person1);

        bureau.add(client1);

        Bureau bureau1 = new Bureau();
        bureau1.add(client);
        bureau1.add(client1);

        bureau.sortByClientsAge();

        Assertions.assertEquals(bureau1.toString(), bureau.toString());
    }

    @Test
    void sort(){
        Bureau bureau = new Bureau();

        LonelyPerson lonelyPerson = new LonelyPerson(Sex.MEN, "Andrew",17, 173, EyeColor.GREEN, "music");
        PersonToFind person = new PersonToFind(Sex.WOMEN, 18, "music", "jazz");

        Bureau.Client client = new Bureau.Client(lonelyPerson, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person);

        LonelyPerson lonelyPerson1 = new LonelyPerson(Sex.MEN, "Nazar", 18, 173, EyeColor.BROWN, "music");
        PersonToFind person1 = new PersonToFind(Sex.WOMEN, 18, "", "Rep");

        Bureau.Client client1 = new Bureau.Client(lonelyPerson1, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), person1);

        bureau.add(client1);
        bureau.add(client);

        Bureau bureau1 = new Bureau();
        bureau1.add(client1);
        bureau1.add(client);

        bureau1.sortByClientsAge();
        bureau.sort();

        Assertions.assertEquals(bureau.toString(), bureau1.toString());
    }

}
