package ua.lpnuai.oop.gordon02;


import java.util.StringTokenizer;

public class Sentence implements Iterable<String>{

    private String text;

    public Sentence(String text){
        this.text = text;
    }

    private class Iterator<String> implements java.util.Iterator {

        StringTokenizer stringTokenizer = new StringTokenizer(text);

        @Override
        public boolean hasNext() {
            return stringTokenizer.hasMoreTokens();
        }

        @Override
        public Object next() {
            return stringTokenizer.nextToken();
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator();
    }
}
