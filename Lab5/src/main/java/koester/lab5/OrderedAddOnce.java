//OrderedAddOnce.java

package koester.lab5;

import java.util.Iterator;

public class OrderedAddOnce<E extends Comparable<?super E>> implements Iterable, AddOnce<E> {       
    private Node root;

    private class Node
    {
        public E key;
        public Node parent;
        public Node left;
        public Node right;

        public Node(E nodeKey) 
        {
            key = nodeKey;
            parent = null;
            left = null;
            right = null;
        }
    }
    
    private class OrderedAddOnceIterator implements Iterator
    {
        Node nextNode = root;
        
        public OrderedAddOnceIterator()
        {
            while (nextNode.left != null)
            {
                nextNode = nextNode.left;
            }
        }
        
        @Override
        public boolean hasNext()
        { 
            return nextNode != null;
        }
        
        public Node getSuccessor()
        {
            if (nextNode.right != null) 
            {
                Node successor = nextNode.right;
                while (successor.left != null)
                {
                    successor = successor.left;
                }
                return successor;
            }
            
            Node otherNode = nextNode;
            while (otherNode.parent != null && otherNode == otherNode.parent.right)
            {
                otherNode = otherNode.parent;
            }
            return otherNode.parent;
        }
        
        @Override
        public E next()
        {
            E result = nextNode.key;
            nextNode = getSuccessor();
            return result;
        }
        
    }
    
    public OrderedAddOnce()
    {
        root = null;
    }
    
    public Node getRoot() 
    {
        return root;
    }
    
    public Node search(E desiredKey) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.key.compareTo(desiredKey) == 0) 
            {
                return currentNode;
            }
         
            else if (currentNode.key.compareTo(desiredKey) > 0) 
            {
                currentNode = currentNode.left;
            }
         
            else
            {
                currentNode = currentNode.right;
            }
        }
      
        return null;
    }
    
    public void insert(Node node)
    {        
        // Add parent references 
        if (root == null) 
        {
            root = node;
        }
        else 
        {
            Node currentNode = root;
            while (currentNode != null) 
            {
                if (currentNode.key.compareTo(node.key) > 0) 
                {
                    // If no left child exists, add the new node
                    // here; otherwise repeat from the left child.
                    if (currentNode.left == null) 
                    {
                        currentNode.left = node;
                        currentNode.left.parent = currentNode;
                        currentNode = null;
                    }    
                    else 
                    {
                       currentNode = currentNode.left;
                    }
                }
                else 
                {
                    // If no right child exists, add the new node
                    // here; otherwise repeat from the right child.
                    if (currentNode.right == null) 
                    {
                        currentNode.right = node;
                        currentNode.right.parent = currentNode;
                        currentNode = null;
                    }
                    else 
                    {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }
    
    private E findDuplicate(E item)
    {
        Node current = root;
        while(current != null)
        {
            if (current.key.compareTo(item) == 0)
            {
                return current.key;
            }
            
            else if (current.key.compareTo(item) > 0) 
            {
                current = current.left;
            }
         
            else
            {
                current = current.right;
            }
        }
        return null;
    }
    
    @Override
    public E AddOnce(E Item)
    {
        E ref = findDuplicate(Item);
        if (ref != null)
        {
            
        }
        
        else
        {
            Node tempNode = new Node(Item);
            ref = Item;
            insert(tempNode);
        } 
        return ref;
    }
      
    @Override
    public Iterator iterator()
    {
        return new OrderedAddOnceIterator(); 
    }
}
