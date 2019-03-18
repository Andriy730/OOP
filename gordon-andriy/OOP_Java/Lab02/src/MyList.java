package ua.lpnuai.oop.gordon02.myArray;

public interface MyList<T> extends  Iterable<T>{

    boolean add(T obj);

    T get(int index);

    void set(int index, T obj);

    boolean remove(T obj);

    int size();

    void clear();

    Object[] toArray();

    boolean contains(T obj);
}
