import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Your implementation of a circular singly linked list.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("You inserted a value that is" +
                    " either negative or greater than the size of the list");
        }
        LinkedListNode<T> newest = new LinkedListNode<>(data);
        LinkedListNode<T> prev = head;
        if (size == 0) {
            head = newest;
        }
        if (index == 0) {
            addToFront(data);
        } else if (size == 0) {
            head = newest;
            head.setNext(head);
            size++;
        } else if (index == size) {
            addToBack(data);
        } else {
            for (int i = 0; i <= index; i++) {
                prev = prev.getNext();
            }
            newest.setNext(prev.getNext());
            prev.setNext(newest);
            T tempData = prev.getData();
            prev.setData(data);
            newest.setData(tempData);
            size++;
        }

    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        LinkedListNode<T> newest = new LinkedListNode<>(data);
        if (size == 0) {
            head = newest;
        }
        newest.setNext(head.getNext());
        head.setNext(newest);
        T headData = head.getData();
        head.setData(data);
        newest.setData(headData);
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        LinkedListNode<T> newest = new LinkedListNode<>(data);
        if (size == 0) {
            head = newest;
        }
        newest.setNext(head.getNext());
        head.setNext(newest);
        T headData = head.getData();
        head.setData(data);
        newest.setData(headData);
        head = newest;
        size++;

    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("You inserted a value that is" +
                    " either negative or greater than the size of the list");
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == size) {
            return removeFromBack();
        } else {
            LinkedListNode<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            T returnData = temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
            size--;
            return returnData;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if  (size == 1) {
            T temp = head.getData();
            head = null;
            size--;
            return temp;

        } else {
            T returnData = head.getData();
            T data = head.getNext().getData();
            head.setData(data);
            head.getNext().setData(returnData);
            head.setNext(head.getNext().getNext());
            size--;
            return returnData;
        }
    }
    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        } else if  (size == 1) {
            T temp = head.getData();
            head = null;
            size--;
            return temp;

        }
        LinkedListNode<T> temp = head;
        for (int i = 0; i < (size - 2); i++) {
            temp = temp.getNext();
        }
        T returnData = temp.getNext().getData();
        temp.setNext(head);
        size--;
        return returnData;
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        if (size == 0) {
            return null;
        }
        LinkedListNode<T> prev = head;
        LinkedListNode<T> temp = head.getNext();
        LinkedListNode<T> beforeRemovedNode = null;
        LinkedListNode<T> removedNode = null;
        Integer inc = 0;
        for (int i = 0; i < size - 1; i++) {
            if (temp.getData().equals(data)) {
                beforeRemovedNode = prev;
                removedNode = temp;
                inc++;

            }
            temp = temp.getNext();
            prev = prev.getNext();
        }
        if (inc == 0) {
            return null;
        }
        T returnData = removedNode.getData();
        beforeRemovedNode.setNext(removedNode.getNext());
        size--;
        return returnData;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of "
                    + "the possible range. Please request an index within the "
                    + "bounds.");
        }

        LinkedListNode<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }
    @Override
    public Object[] toArray() {
        Object[] arrayVersion = new Object[size];
        LinkedListNode<T> node = head;
        for (int i = 0; i < size(); i++) {
            arrayVersion[i] = node.getData();
            node = node.getNext();
        }

        return arrayVersion;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head.setNext(head);
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}