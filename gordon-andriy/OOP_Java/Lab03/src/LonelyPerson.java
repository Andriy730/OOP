package ua.lpnuai.oop.gordon03.person;

import java.time.LocalDate;
import java.util.Objects;

public class LonelyPerson extends Person{
    private LocalDate birthday;
    private int height;
    private String hobby;

    public LonelyPerson(){}

    public LonelyPerson(Sex sex, String name, int year, int month, int day){
        super(sex, name);
        birthday = LocalDate.of(year, month, day);
    }

    public LonelyPerson(Sex sex, String name, int year, int month, int day, int height){
        this(sex, name, year, month, day);
        this.height = height;
    }

    public LonelyPerson(Sex sex, String name, int year, int month, int day, int height, EyeColor color){
        this(sex, name, year, month, day, height);
        this.color = color;
    }

    public LonelyPerson(Sex sex, String name, int year, int month, int day, int height, EyeColor color, String hobby){
        this(sex, name, year, month, day, height, color);
        this.hobby = hobby;
    }

    public LonelyPerson(Sex sex, String name, int height, EyeColor color, String hobby){
        super(sex, name);
        this.height = height;
        this.color = color;
        this.hobby = hobby;
    }

    public LonelyPerson(Sex sex, int height, EyeColor color, String hobby){
        super(sex);
        this.height = height;
        this.color = color;
        this.hobby = hobby;
    }

    public String getName() {
        return super.getName();
    }

    public Sex getSex() {
        return super.getSex();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getHeight() {
        return height;
    }

    public EyeColor getColor() {
        return super.getColor();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }



    @Override
    public boolean equals(Object obj) {
        if(!super.equals(obj)) return false;
        if(getClass() != obj.getClass()) return false;

        LonelyPerson other = (LonelyPerson) obj;

        return Objects.equals(birthday, other.birthday) && height == other.height &&
                Objects.equals(hobby, other.hobby);
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", sex: " + getSex() + ", birthday: " + birthday + ", height: " +
                height + ", eye color: " + getColor();
    }

}
