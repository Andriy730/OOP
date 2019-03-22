package ua.lpnuai.oop.gordon04;

import ua.lpnuai.oop.gordon02.myArray.ColorConsoleFont;

import java.io.*;
import java.util.*;

/**
 * Лабораторна робота №4
 * <p>Параметризований клас-контейнер для зберігання даних</p>
 *
 *  реалізація в вигляді зв'язного списку
 *
 * @param <T> визначає тип даних, що зберігаються
 *
 * @version 1.0 05-03-2019
 * @author Andriy Gordon
 */

public class Container<T> implements Iterable <T>, Serializable, Externalizable {

    private class Node implements Serializable{
        private Node next;
        private T data;

        Node() {
        }

        Node(T data){
            this();
            this.data = data;
            next = null;
        }

    }

    private class MyIterator implements Iterator {
        private int index;
        private boolean canRemove;

        public MyIterator(){
            index = 0;
            canRemove = false;
        }
        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Object next() throws NoSuchElementException {
            T data = get(index);
            if(data == null){
                throw new NoSuchElementException();
            }
            index++;
            canRemove = true;
            return data;
        }

        @Override
        public void remove() throws IllegalStateException{
            if(!canRemove){
                throw new IllegalStateException();
            }
            Container.this.remove(--index);
        }
    }

    private int size;
    private Node head;
    private Node tail;


    public Container(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Метод для додавання елементів до списку
     * @param data елемент, що потрібно додати
     * @throws IllegalArgumentException
     */
    public void add(T data) throws IllegalArgumentException{
        if(data == null){
            throw new IllegalArgumentException("null");
        }
        if(head == null) {
            head = new Node(data);
            tail = head;
            size = 1;
        }
        else {
            Node last = tail;
            tail = new Node(data);
            last.next = tail;
            size++;
        }
    }

    /**
     * Метод для отримання елементу за заданим індексом
     * @param index
     * @return елемент, що знаходиться за заданим індексом або {@code null},
     * якщо індекс виходить за межі розміру списку
     */
    public T get(int index) {
        if(index == 0) {
            return head.data;
        }
        else if(index > 0 && index < size) {
            Node crawler = head;
            for(int i = 0; i < index; i++) {
                if(crawler.next != null) {
                    crawler = crawler.next;
                }
                else {
                    return null;
                }
            }

            return crawler.data;
        }
        else return null;
    }

    public T getFirst(){
        return head.data;
    }

    public T getLast(){
        return tail.data;
    }

    /**
     *
     * @param index позиція елементу який потрібно видалити
     * @return {@code true}, якщо елемент успішно видалено, інакше {@code false}
     */
    public boolean remove(int index) {
        if(head == null) {
            return false;
        }
        if(index == 0) {
            head = head.next;
            size--;
            return true;
        }
        if(index > 0) {
            Node crawler = head;
            for(int i = 0; i < index - 1; i++) {
                if(crawler.next == null) {
                    return false;
                }
                crawler = crawler.next;
            }
            Node tmp = crawler.next;
            crawler.next = tmp.next;
            size--;
            return true;
        }
        return false;
    }

    public boolean remove(T obj) throws IllegalArgumentException{
        if(head == null){
            return false;
        }
        if(obj == null){
           throw new IllegalArgumentException();
        }
        Node crawler = head;
        if(Objects.equals(crawler.data, obj)){
            head = crawler.next;
            size--;
            return true;
        }
        while (crawler != null){
            Node tmp = crawler.next;
            if(Objects.equals(tmp.data, obj)){
                crawler.next = tmp.next;
                size--;
                return true;
            }
            crawler = crawler.next;
        }
        return false;
    }

    /**
     *
     * @return розмір списку
     */
    public int size() {
        return size;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public String toString(){
        if(size == 0){
            return"";
        }
        StringBuilder str = new StringBuilder();
        Node crawler = head;
        for(int i = 0; i < size; i++){
            str.append(crawler.data + " ");
            crawler = crawler.next;
        }
        return str.toString();
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Container other = (Container) obj;

        if(size != other.size) return false;

        return containsAll(other);
    }

    public Object[] toArray(){
        Object[] objects = new Object[size];
        Node crawler = head;
        for(int i = 0; i < size; i++){
            objects[i] = crawler.data;
            crawler = crawler.next;
        }
        return objects;
    }

    public boolean contains(T data){
        if(size == 0){
            return false;
        }
        Node crawler = head;

        for(int i = 0; i < size; i++){
            if(Objects.equals(crawler.data, data)){
                return true;
            }
            crawler = crawler.next;
        }
        return false;
    }

    public boolean containsAll(Container<T> collection){
       if(size == 0 || collection == null){
           return  false;
       }
        for(T e: collection){
            Node crawler = head;
            boolean is = false;
            for(int i = 0; i < size; i++){
                if(Objects.equals(crawler.data, e)){
                    is = true;
                    break;
                }
                crawler = crawler.next;
            }
            if(!is){
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator(){
        return new MyIterator();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(size);
        out.writeObject(head);
        out.writeObject(tail);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        size = in.readInt();
        head = (Node) in.readObject();
        tail = (Node) in.readObject();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Container<Integer> container = new Container();
        System.out.println(ColorConsoleFont.RED + "Test add" + ColorConsoleFont.RED);
        String command;

        do {
            System.out.println("Break?");
            command = in.next();
            if(command.equals("break")){
                break;
            }
            System.out.print("Enter a number:");
            int a = in.nextInt();
            container.add(a);
        }while (true);

        System.out.println(ColorConsoleFont.BLUE + "elements which added:" + ColorConsoleFont.BLUE);
        System.out.println(ColorConsoleFont.RESET + container + ColorConsoleFont.RESET);
        System.out.println("which element you want to delete?");
        int elem = in.nextInt();
        in.nextLine();
        if(container.remove(new Integer(elem))) System.out.println(ColorConsoleFont.GREEN + "success!" + ColorConsoleFont.GREEN);
        else System.out.println(ColorConsoleFont.RED + "wrong element" + ColorConsoleFont.RED);

        System.out.println("Do you want to save list?");
        if(in.nextLine().equals("yes")){
            System.out.println("Enter file name");
            String fileName = in.nextLine();
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".dat"))){
                oos.writeObject(container);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
