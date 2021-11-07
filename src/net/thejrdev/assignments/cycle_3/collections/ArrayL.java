package net.thejrdev.assignments.cycle_3.collections;


import java.util.NoSuchElementException;

public class ArrayL<T> {

    private T[] data;
    private int size;


    public ArrayL(){
        data = (T[]) new Object[1];
        size = 0;
    }

    public ArrayL(int size){
        data = (T[]) new Object[size];
        this.size = size;
    }

    public void add(T a){
        if(size == data.length){
            increaseSize();
        }
        data[size++] = a;
    }

    public void add(int index, T a){
        if(size == data.length){
            increaseSize();
        }
        if(index != size) {
            slideRight(index);
        }
        data[index] = a;
        size++;
    }

    public void set(int index, T a) throws IndexOutOfBoundsException{
        if(index >= size){throw new IndexOutOfBoundsException();}
        data[index] = a;
    }

    public T remove(T a) throws NoSuchElementException {
        int index = indexOf(a);
        if(index == -1){throw new NoSuchElementException();}
        T item = data[index];
        data[index] = null;
        slideLeft(index);
        return item;
    }

    public boolean contains(T a){
        return indexOf(a) >= 0;
    }

    public T get(int index){
        return data[index];
    }

    public int indexOf(T a){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(a)){
                return i;
            }
        }
        return -1;
    }

    public int size(){
        return size;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size-1; i++) {
            builder.append(data[i].toString()).append(", ");
        }
        builder.append(data[size-1]).append("]");
        return builder.toString();
    }

    private void slideRight(int index){
        if(data.length <= size){increaseSize();}
        for (int i = size-1; i >= index; i--) {
            swapAdj(i);
        }
    }

    private void slideLeft(int index){
        for (int i = index; i < size; i++) {
            swapAdj(i);
        }
    }

    private void swapAdj(int index){
        T temp = data[index];
        data[index] = data[++index];
        data[index] = temp;
    }

    private void increaseSize(){
        T[] newData = (T[]) new Object[data.length * 2];

        if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

}
