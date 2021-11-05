package net.thejrdev.assignments.cycle_3.collections;

public class LinkedL <T>{

    private int size;

    private Node<T> head;

    public LinkedL(){
        size = 0;
    }

    public LinkedL(T... a){
        size = a.length;
        for (T t: a) {
            if(head == null){
                head = new Node<T>(null, t, null);
                size = 1;
            } else {
                head.add(size, t);
                size++;
            }
        }
    }
    public LinkedL<T> add(T a){
        if(head == null){
            head = new Node<T>(null, a, null);
            size = 1;
        } else {
            head.add(size, a);
            size++;
        }
        return this;
    }

    public T get(int index){
        return head.find(index);
    }
    public T remove(int index){
        return head.remove(index).item;
    }
    public void reverse(){
        head = head.reverse();
    }

    @Override
    public String toString() {
        String list = head.toString();
        return "[" + list + "]";
    }

    static class Node<T>{
        private Node<T> next;
        private Node<T> previous;

        private final T item;

        public Node(Node<T> previous, T a, Node<T> next){
            this.previous = previous;
            this.item = a;
            this.next = next;
        }

        public void add(int n, T a) throws IndexOutOfBoundsException{
            if(n == 1){
                Node<T> newNode = new Node<T>(this, a, getNext());
                if(next != null) {
                    next.setPrevious(newNode);
                }
                this.next = newNode;
            }else if(next != null){
                next.add(--n, a);
            }else{
                throw new IndexOutOfBoundsException();
            }
        }

        public Node<T> remove(int n){
            if(n == 0){
                if(previous != null){
                    previous.next = next;
                    next.previous = previous;
                }
                return this;
            }else if(next != null){
                return next.remove(--n);
            }else{
                throw new IndexOutOfBoundsException();
            }
        }

        public T find(int n){
            return n == 0 ? this.item : next.find(--n);
        }

        public Node<T> reverse(){
            Node<T> newHead;
            if(next != null){
                 newHead = next.reverse();
            }else{
                newHead = this;
            }
            Node<T> temp = next;
            next = previous;
            previous = temp;
            return newHead;
        }

        public Node<T> getNext(){
            return next;
        }
        public void setNext(Node<T> node){
            this.next = node;
        }
        public Node<T> getPrevious(){
            return previous;
        }
        public void setPrevious(Node<T> node){
            this.previous = node;
        }

        public String toString(){
            return item.toString() + (next == null ? "" : ", " + next.toString());
        }

    }

}
