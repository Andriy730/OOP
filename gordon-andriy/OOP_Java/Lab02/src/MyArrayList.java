package ua.lpnuai.oop.gordon02.myArray;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList implements MyList<String>, Serializable {
    private String[] data;
    private int size;
    private int arrindex;

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
            String data = get(index);
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
            MyArrayList.this.remove(--index);
        }
    }

    public MyArrayList(){
        data = new String[16];
        size = 0;
        arrindex = 0;
    }

    @Override
    public boolean add(String obj) {
        if(obj == null) return false;
        data[arrindex++] = obj;
        size++;
        if(size > data.length - 1){
            increasingTheArray();
        }
        return true;
    }

    @Override
    public String get(int index) {
        return data[index];
    }

    @Override
    public void set(int index, String obj) {
        if(obj == null) new IllegalArgumentException();
        data[index] = obj;
    }

    @Override
    public boolean remove(String obj) {
        int index = -1;
        for(int i = 0; i < data.length; i++){
            if(data[i].equals(obj)){
                index = i;
                break;
            }
        }

        if( index == -1) return false;

        for(int j = index; j < data.length - 1; j++){
            data[j] = data[j+1];
        }
        size--;
        arraindex--;
        return true;
    }

    public boolean remove(int index){
        if(index < 0 || index >= size) return false;
        for(int j = index; j < data.length - 1; j++){
            data[j] = data[j+1];
        }
        size--;
        arrindex--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i = 0; i < data.length; i++){
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        for(int i = 0; i < size; i++){
            objects[i] = data[i];
        }
        return objects;
    }

    @Override
    public boolean contains(String obj) {
        if(obj == null) return false;
        for(int i = 0; i < size; i++){
            if(data[i] == obj) return true;
        }
        return false;
    }

    public boolean containsAll(MyArrayList other){
        if(size == 0 || other == null) return false;
        for(String s: other){
            boolean is = false;
            for(String str: this){
                if(s.equals(str)){
                    is = true;
                    break;
                }
            }
            if(!is){
                return false;
            }
        }
        return true;
    }

    private void increasingTheArray(){
        String[] newArray = new String[data.length * 2];
        System.arraycopy(data, 0, newArray, 0, size);
        data = newArray;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(int i = 0; i < size; i++){
            if(i > 0) stringBuilder.append(", ");
            stringBuilder.append(data[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return new MyIterator();
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        MyArrayList equalTest = new MyArrayList();

        System.out.println(ColorConsoleFont.RED + "TESTS" + ColorConsoleFont.RED + "\n");

        list.add("Andrew");
        list.add("Gordon");
        list.add("CS50");

        System.out.println( ColorConsoleFont.RESET + "Elements which were added to list:" +  ColorConsoleFont.RESET);
        System.out.println(ColorConsoleFont.BLUE);
        list.forEach(System.out::println);
        System.out.println(ColorConsoleFont.BLUE);

        equalTest.add("Andrew");
        equalTest.add("Gordon");

        System.out.print( ColorConsoleFont.RESET + "CONTAINS \"CS50\": " +  ColorConsoleFont.RESET);

        System.out.println(ColorConsoleFont.BLUE + list.contains("CS50") + ColorConsoleFont.BLUE + "\n");

        System.out.println( ColorConsoleFont.RESET + "REMOVE \"CS50\": " +  ColorConsoleFont.RESET
                + ColorConsoleFont.BLUE +list.remove("CS50") + ColorConsoleFont.BLUE + "\n");

        System.out.println(list + "\n");

        System.out.println( ColorConsoleFont.RESET + "CONTAINSALL TEST" +  ColorConsoleFont.RESET + "\n");

        System.out.println("equalTestArray:" +  ColorConsoleFont.BLUE + equalTest +  ColorConsoleFont.BLUE);

        System.out.println( ColorConsoleFont.RESET + "Contains: " + ColorConsoleFont.RESET +
                ColorConsoleFont.BLUE + list.containsAll(equalTest) + ColorConsoleFont.BLUE );

        System.out.println( ColorConsoleFont.RESET + "Clear list.." +  ColorConsoleFont.RESET);
        list.clear();

        System.out.println( ColorConsoleFont.RESET + "After clear: " +  ColorConsoleFont.RESET +
                ColorConsoleFont.BLUE + list.containsAll(equalTest) + ColorConsoleFont.BLUE);

        System.out.println(list);
    }
}
