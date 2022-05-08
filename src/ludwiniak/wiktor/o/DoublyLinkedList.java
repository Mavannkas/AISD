package ludwiniak.wiktor.o;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T>  {
    private Node head;
    private Node tail;
    private int size = 0;


    public void add(T value) {
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
        }else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void addAt(int index, T value) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }

        Node current = head;
        Node newNode = new Node(value);
        if(index == size){
            add(value);
        }
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            head.prev = null;
            size++;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            newNode.prev = current;
            newNode.next.prev = newNode;
            size++;
        }


    }
    private void append(Node newNode){

    }
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public boolean contains(T value) {
        Node curr = head;
        for (int i = 0; i < size; i++) {
            if (curr.data.equals(value)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public T get(int index) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }

        Node current = head;
        if (index == 0) {
            return (T) current.data;
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T) current.data;
    }

    public void set(int index, T value) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }
        Node current = head;
        if (index == 0) {
            head.data = value;
        }
//       traverse(index, current);
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = value;
    }

    public int indexOf(T value) {
        Node temp = head;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(value)) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T removeAt(int index) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }
        if(index == 0){
            Node nodeToRemove = head;
            head = head.next;
            size--;
            return (T) nodeToRemove.data;
        }
        Node current = head;

        //traverse(index-1, current);
        for (int i = 0; i < index-1; i++) {
            current = current.next;
        }

        Node nodeToRemove = current.next;
        current.next = current.next.next;
        current.next.prev = current;
        size--;
        return (T) nodeToRemove.data;
    }

    public boolean remove(T value) {

        Node cur = head;

        if(head.data.equals(value)){
            head = head.next;
            head.prev = null;
            decreaseSize();

            return true;
        }
        for(int i =0; i< size; i++){
            if(cur.next.data.equals(value)){
                cur.next = cur.next.next;
                cur.next.prev = cur;
                decreaseSize();
                return true;
            }
        }
        return false;
    }
    private void decreaseSize(){
        size--;
    }
    private void increaseSize(){
        size++;
    }
    public int size() {
        return this.size;
    }

    public void print() {
        if (head == null) {
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void printBackwards(){
        if(head == null){
            return;
        }
        Node temp = tail;
        while(temp != null) {
            System.out.print(temp.data + " <-- ");
            temp = temp.prev;
        }
        System.out.println("null");
    }
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    class Node<T> {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(hasNext()){
                T data = (T) current.data;
                current = current.next;
                return data;
            }
            throw  new NoSuchElementException();
        }
    }
}
