import java.util.AbstractList;


class MyLinkedList<E> extends AbstractList<E> {
    private LLNode<E> head;
    private int size;

    /** Create a new empty LinkedList */
    public MyLinkedList() {
        size = 0;
    }

    /**
     * Appends an element to the end of the list
     * @param element The element to add
     */
    public boolean add(E element ) {
        LLNode<E> newNode = new LLNode<>(element);
        LLNode<E> currentNode = head;

        newNode.next = null;

        if(head == null){
            newNode.prev = null;
            head = newNode;
            this.size++;
            return true;
        }
        while (currentNode.next !=null){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        newNode.prev = currentNode;
        this.size++;
        return true;
    }

    /** Get the element at position index
     * @throws IndexOutOfBoundsException if the index is out of bounds. */
    public E get(int index) {
        if(this.size == 0 || index < 0 || index > this.size()-1)
            throw  new IndexOutOfBoundsException();
        if(index == 0)
            return head.data;

        LLNode<E> currentNode = head;
        while(index > 0){
            currentNode = currentNode.next;
            index --;
        }

        return currentNode.data;
    }


    /** Return the size of the list */
    public int size()
    {
        return this.size;
    }

    /** Remove a node at the specified index and return its data element.
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     *
     */
    public E remove(int index) {
        // TODO: Implement this method
        if(index < 0 || index > this.size() - 1)
            throw new IndexOutOfBoundsException(index);

        LLNode<E> removalNode = head;
        E removedData;

        for(int iterator = 0 ; removalNode!=null && iterator<index;iterator++){
            removalNode = removalNode.next;
       }
       if(removalNode == null)
            return null;

       if(head == removalNode) {
           removedData = head.data;
           head = removalNode.next;
       }
       if(removalNode.prev !=null)
                removalNode.prev.next = removalNode.next;
        removedData = removalNode.data;
        removalNode = null;

        this.size--;
        return removedData;
    }

    /**
     * Set an index position in the list to a new element
     * @param index The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E set(int index, E element) {
        if(element == null)
            throw new NullPointerException();
        if(index < 0 || index > this.size()-1)
            throw new IndexOutOfBoundsException(index);

        E replacedData;
        if(index == 0){
            replacedData = head.data;
            head.data = element;
            return replacedData;
        }

        LLNode<E> currentNode = head;
        while(index > 0){
            currentNode = currentNode.next;
            index--;
        }
        replacedData = currentNode.data;
        currentNode.data = element;

        return replacedData;
    }

    public void printList(){

        LLNode<E> currentNode = head;
        while(currentNode != null){
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    public LLNode(E e)
    {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}

public class LinkedListRunner {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("hello");
        myLinkedList.add("world");
        myLinkedList.add("new word");
        System.out.println("Before Deletion");
        myLinkedList.printList();
        myLinkedList.set(0,"india");
        System.out.println("After Deletion index 1");
        myLinkedList.remove(1);
        myLinkedList.printList();
        System.out.println( myLinkedList.size() );
    }
}
