package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;

public class CadreAgency implements Serializable
{
    public CadreAgency(){}

    private ObservableList<Person> people = FXCollections.observableArrayList();

    public void test()
    {
        people.add(new
                Person("Policy","12","Policy academy",LocalDate.of(2018,11,10)));

        people.add(new
                Person("Medic","10","Medic University",LocalDate.of(2016,11,10)));

        people.add(new
                Person("Programmer","9","Logos IT academy",LocalDate.of(2016,11,10)));
    }

    public ObservableList<Person> getPeople() { return people; }

}
