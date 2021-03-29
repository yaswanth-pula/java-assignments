class Node<E>{
    E data;
    Node<E> next;

    Node(E elementData){
        this.data = elementData;
        this.next = null;
    }
    public String toString(){
        if(data == null)
            return "Null";
        return data.toString();
    }
}

class SingleList<E>{
    private Node<E> head;

    SingleList(){
        head = new Node<>(null);
    }


    SingleListIterator<E> iterator(){
        return new SingleListIterator<E>(head);
    }

    public String toString(){
        if(head == null)
            return "List is Empty";
        SingleListIterator<E> iterator  = new SingleListIterator<E>(head);
        StringBuilder listStringBuilder = new StringBuilder("[ ");
        while (iterator.hasNext()){
            listStringBuilder.append(iterator.next()).append(", ");
        }
        return listStringBuilder.toString()+" ]";

    }
}

class SingleListIterator<E>{
    private Node<E> currentNode;
    private int size = 0;

    SingleListIterator(Node<E> head){
        this.currentNode = head;
    }
    public Node<E> next(){
        currentNode = currentNode.next;
        return currentNode;
    }
    public boolean hasNext(){
        return currentNode.next != null;
    }

    public void add(E elementData){
        Node<E> newNode = new Node<E>(elementData);
        currentNode.next = newNode;
        currentNode = currentNode.next;
        size++;
    }
    public void remove(){
        while (currentNode.next.next != null){
            currentNode = currentNode.next;
        }

        currentNode.next = null;
        size--;
    }
    public int size(){
        return size;
    }

}

public class Assignment10 {
    public static void main(String[] args) {
        SingleList<String> sList = new SingleList<>();

        SingleListIterator<String> addElementIterator = sList.iterator();
        addElementIterator.add("hello");
        addElementIterator.add("World");
        addElementIterator.add("New world");
        System.out.println(sList);

        SingleListIterator<String> removeElementIterator = sList.iterator();
        removeElementIterator.remove();
        System.out.println(sList);

    }
}
