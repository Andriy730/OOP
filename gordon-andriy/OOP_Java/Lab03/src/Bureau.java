package ua.lpnuai.oop.gordon03;

import ua.lpnuai.oop.gordon03.person.EyeColor;
import ua.lpnuai.oop.gordon03.person.LonelyPerson;
import ua.lpnuai.oop.gordon03.person.PersonToFind;
import ua.lpnuai.oop.gordon03.person.Sex;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;


@XmlRootElement(name = "Bureau")
public class Bureau implements Serializable {
    @XmlRootElement(name = "Client")
    @XmlType(propOrder = { "client", "want", "numberOfRegistration", "dateOfRegistration" })
    public static class Client implements Externalizable {
        private static int id;
        private LonelyPerson client;
        private PersonToFind want;
        private int numberOfRegistration;
        private Date dateOfRegistration;

        static {
            id = 0;
        }

        public Client(){}

        public Client(LonelyPerson client, int year, int month, int day, PersonToFind want){
            this.client = client;
            this.dateOfRegistration = new Date(year, month, day);
            this.want = want;
            numberOfRegistration = ++id;
        }

        private Client(int id){
            numberOfRegistration = id;
        }
        @XmlElement
        public LonelyPerson getClient() {
            return client;
        }
        @XmlElement
        public Date getDateOfRegistration() {
            return dateOfRegistration;
        }

        @XmlAttribute(name = "id")
        public int getNumberOfRegistration() {
            return numberOfRegistration;
        }

        public PersonToFind getWant() {
            return want;
        }

        public void setWant(PersonToFind want){
            this.want = want;
        }

        public void setClient(LonelyPerson client) {
            this.client = client;
        }

        public void setNumberOfRegistration(int numberOfRegistration) {
            this.numberOfRegistration = numberOfRegistration;
        }

        public void setDateOfRegistration(Date dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
        }

        @Override
        public String toString() {
            return "client=" + client +
                    ", want=" + want +
                    ", numberOfRegistration= " + numberOfRegistration +
                    ", dateOfRegistration= " + dateOfRegistration.getYear() + " " + (dateOfRegistration.getMonth() + 1) + " " + dateOfRegistration.getDate();
        }

        public boolean equals(Object other){
            if(this == other) return true;
            if(other == null) return false;
            if(getClass() != other.getClass()) return false;

            Client otherClient = (Client) other;

            return numberOfRegistration == otherClient.numberOfRegistration;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(client);
            out.writeObject(want);
            out.writeInt(numberOfRegistration);
            out.writeObject(dateOfRegistration);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            client = (LonelyPerson) in.readObject();
            want = (PersonToFind) in.readObject();
            numberOfRegistration = in.readInt();
            dateOfRegistration = (Date) in.readObject();
        }
    }

    @XmlElementWrapper
    private ArrayList<Client> clients;

    public Bureau(){
        clients = new ArrayList<>();
    }

    public int size(){
        return clients.size();
    }

    public void add(Client client){
        clients.add(client);
    }

    public Client get(int index)throws ArrayIndexOutOfBoundsException{
        return clients.get(index);
    }

    public boolean remove(int numberOfRegistration){
        Client client = new Client(numberOfRegistration);
        return clients.remove(client);
    }

    @Override
    public String toString() {
        return "Bureau{" +
                "clients=" + clients +
                '}';
    }

    public static void main(String[] args) {
        Bureau bureau = new Bureau();
        Client client1 = new Client(new LonelyPerson(Sex.MEN, "Andrew", 2001, 5, 1, 173,
                EyeColor.GREEN), 2019, 3, 19,
                new PersonToFind(Sex.WOMEN, 24, EyeColor.BLUE, "music", "jazz"));
        Client client2 = new Client(new LonelyPerson(Sex.WOMEN, "Oksana", 2001, 3, 18),
                2019, 3, 19, new PersonToFind(Sex.MEN, 21, EyeColor.BROWN, "computers", " Rock"));
        bureau.add(client1);
        bureau.add(client2);
        System.out.println(bureau);
        bureau.remove(2);
        System.out.println(bureau);
    }


}
