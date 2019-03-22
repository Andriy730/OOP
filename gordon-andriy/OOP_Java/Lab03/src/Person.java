package ua.lpnuai.oop.gordon03.person;

import java.io.Serializable;

public class Person implements Serializable {
    Sex sex;
    String name;
    int age;
    EyeColor color;

    public Person(){}

    public Person(Sex sex){
        this.sex = sex;
    }

    public Person(Sex sex, String name){
        this(sex);
        this.name = name;
    }

    public Person(Sex sex, int age){
        this(sex);
        this.age = age;
    }

    public Person(Sex sex, String name, int age){
        this(sex, age);
        this.name = name;
    }

    public Person(Sex sex, int age, EyeColor color){
        this(sex, age);
        this.color = color;
    }

    public Person(Sex sex, String name, int age, EyeColor color){
        this(sex, name, age);
        this.color = color;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public EyeColor getColor() {
        return color;
    }


    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Person other = (Person) obj;

        return name.equals(other.name) && sex.equals(other.sex) && color.equals(other.color) && age == other.age;
    }
}
