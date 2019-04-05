package ua.lpnuai.oop.gordon03.person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"hobby", "music"})
public class PersonToFind extends Person {
    private String hobby;
    private String music;

    public PersonToFind(){}

    public PersonToFind(Sex sex, int age, EyeColor color, String hobby, String music){
        super(sex, age, color);
        this.hobby = hobby;
        this.music = music;
    }

    public PersonToFind(Sex sex, int age, String hobby, String music){
        super(sex, age);
        this.hobby = hobby;
        this.music = music;
    }

    @Override
    @XmlTransient
    public String getName() {
        return super.getName();
    }

    @XmlElement
    public String getHobby() {
        return hobby;
    }

    @XmlElement
    public String getMusic() {
        return music;
    }

    @Override
    public Sex getSex(){
        return super.getSex();
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public EyeColor getColor() {
        return super.getColor();
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        return "Sex: " + getSex() + ", age: " + getAge() + (getColor() == null ? ", " : ", eye color: "
                + getColor() + ", ") + "hobby: " + hobby + ", music: " + music;
    }
}
