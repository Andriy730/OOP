package ua.lpnuai.oop.gordon03.person;

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

    public String getHobby() {
        return hobby;
    }

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

    @Override
    public String toString() {
        return "Sex: " + getSex() + ", age: " + getAge() + (getColor() == null ? ", " : ", eye color: "
                + getColor() + ", ") + "hobby: " + hobby + ", music: " + music;
    }
}
