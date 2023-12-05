//OrderedAddOnce.java

package koester;

import java.util.Iterator;

public class OrderedAddOnce<E extends Comparable<?super E>> implements Iterable, AddOnce<E> {       
    private Node head;

    private class Node
    {
        private E data;
        private Node next;

        public Node(E item)
        {
            data = item;
            next = null;
        }
    }
    
    private class OrderedAddOnceIterator implements Iterator
    {
        Node nextNode = head;

        @Override
        public boolean hasNext()
        { 
            return nextNode != null;
       	}

        @Override
        public E next()
        {
            E result = nextNode.data; 
            nextNode = nextNode.next;
            return result;
   	}
    }
    public OrderedAddOnce()
    {
        head = null;
    }
    
    private boolean findDuplicate(E item)
    {
        Node current = head;
        while(current != null)
        {
            if (current.data.compareTo(item) == 0)
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    @Override
    public E AddOnce(E Item)
    {
        Node current;
        Node newNode = new Node(Item);
            
        if (head == null || head.data.compareTo(newNode.data) > 0)
        {
            newNode.next = head;
            head = newNode;
        }
            
        else if (findDuplicate(Item))
        {
            return Item;
        }
        
        else
        {
            current = head;
            while (current.next != null && current.next.data.compareTo(newNode.data) < 0)
            {    
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }         
        return newNode.data;
    }
    
    @Override
    public Iterator iterator()
    {
        return new OrderedAddOnceIterator(); 
    }

}

