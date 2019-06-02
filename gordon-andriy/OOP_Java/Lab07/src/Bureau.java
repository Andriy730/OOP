package ua.lpnuai.oop.gordon07;

import ua.lpnuai.oop.gordon03.person.LonelyPerson;
import ua.lpnuai.oop.gordon03.person.PersonToFind;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@XmlRootElement(name = "Bureau")
public class Bureau implements Serializable{
        @XmlRootElement(name = "Client")
        @XmlType(propOrder = { "client", "want", "numberOfRegistration", "dateOfRegistration" })

        public static class Client implements Serializable {
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

                ua.lpnuai.oop.gordon07.Bureau.Client otherClient = (ua.lpnuai.oop.gordon07.Bureau.Client) other;

                return numberOfRegistration == otherClient.numberOfRegistration;
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

        public void add(ua.lpnuai.oop.gordon07.Bureau.Client client){
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

        public int search(int numberOfRegistration){
            Client client = new Client(numberOfRegistration);
            for(int i = 0; i < clients.size(); i++){
                if(clients.get(i).equals(client)) return i;
            }
            return -1;
        }

        public void sortByClientsAge(){
            Collections.sort(clients, Comparator.comparingInt(c -> c.client.getAge()));
        }

        public void sort(){
            Collections.sort(clients, Comparator.comparingInt(c -> c.numberOfRegistration));
        }

        public void sortByDateOfRegistration(){
            Collections.sort(clients, Comparator.comparing(c -> c.dateOfRegistration));
        }

    @Override
    public boolean equals(Object obj) {
        return clients.equals(obj);
    }
}
