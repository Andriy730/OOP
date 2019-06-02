package sample.model;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.util.Random;

public class Person
{
    private StringProperty registrationNumber;
    private StringProperty work;
    private StringProperty expereince;
    private String education;
    private ObjectProperty<LocalDate> releaseDate;
    private Random random = new Random();

    public Person(String work, String expereince, String education, LocalDate releaseDate) {
        this.registrationNumber = new SimpleStringProperty("№"+Math.abs((random.nextInt())%1000));
        this.work = new SimpleStringProperty(work);
        this.education = education;
        this.expereince = new SimpleStringProperty(expereince);
        this.releaseDate = new SimpleObjectProperty<>(releaseDate);
    }

    public StringProperty getRegistrationNumbeProperty() { return registrationNumber; }

    public String getRegistrationNumber() { return registrationNumber.get(); }

    public ObjectProperty<LocalDate> getReleaseDate() { return releaseDate; }

    public String getEducation() { return education; }

    public void setEducation(String education) { this.education = education; }

    public void setReleaseDate(ObjectProperty releaseDate) { this.releaseDate = releaseDate; }

    public StringProperty getExpereince() { return expereince; }

    public StringProperty getWorkProperty() { return work; }

    public String getWork() { return work.get(); }

    public void setExpereince(StringProperty expereince) { this.expereince = expereince; }

    public void setWork(StringProperty work) { this.work = work; }
}