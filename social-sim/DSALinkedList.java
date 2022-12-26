// [Kade McGarraghy].[First Year].[Modified Previously submitted DSA Prac 03]

import java.io.*;
import java.util.*;

public class DSALinkedList implements Iterable, Serializable
{
    private class DSAListNode implements Serializable
    {
        public Object value;
        public DSAListNode next;
        public DSAListNode prev;
        
        public DSAListNode(Object inValue)
        {
            value = inValue;
            next = null;
            prev = null;
        }
    }
    
    private DSAListNode head;
    private DSAListNode tail;
    
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }
    
    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;
        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }
        public boolean hasNext()
        {
            return (iterNext != null);
        }
        public Object next()
        {
            Object value;
            if (iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.value;
                iterNext = iterNext.next;
            }
            return value;
        }
            
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
        
    }

    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    public void insertFirst(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty())
        {
            tail = newNd;
        }
        else
        {
            head.prev = newNd;
        }
        newNd.next = head;
        head = newNd;
    }

    public void insertLast(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty())
        {
            head = newNd;
        }
        else
        {
            tail.next = newNd;
            newNd.prev = tail;
        }
        tail = newNd;
    }
    
    public Object removeFirst()
    {
        Object nodeValue;
        if (isEmpty())
        {
            throw new IllegalArgumentException("Linked list is empty.");
        }
        else
        {
            nodeValue = head.value;
            if (head.next == null)
            {
                tail = null;
            }
            else
            {
                head.next.prev = null;
            }
            head = head.next;
        }
        return nodeValue;
    }

    public Object removeLast()
    {
        Object nodeValue;
        nodeValue = tail.value;
        if (isEmpty())
        {
            throw new IllegalArgumentException("Linked list is empty.");
        }
        else if (head.next == null)
        {
            head = null; 
        }
        else
        {
            tail.prev.next = null;
        }
        tail = tail.prev;
        return nodeValue;
    }

    // REMOVE: Code inspired by Data Structures & Algorithms in Java Second Edition by R.Lafore

    public Object remove(Object valueToFind)
    {
        DSAListNode node = head;
        Object removedItem = null;
        if (isEmpty())
        {
            throw new IllegalArgumentException("Linked list is empty.");
        }
        else
        {
            while (!(node.value).equals(valueToFind))
            {
                node = node.next;
                if (node.equals(null))
                {
                    removedItem = null;
                }
            }
            removedItem = node.value;
            if (node == head)
            {
                head = node.next;
            }
            else
            {
                node.prev.next = node.next;
            }

            if (node == tail)
            {
                tail = node.prev;
            }
            else
            {
                node.next.prev = node.prev;
            }
        }
        return removedItem;
    }

    // REMOVE: Code inspired by Data Structures & Algorithms in Java Second Edition by R.Lafore

    public boolean isEmpty()
    {
        boolean empty;
        empty = (head == null);
        return empty;
    }

    public Object peekFirst()
    {
        Object nodeValue;
        if (isEmpty())
        {
            throw new IllegalArgumentException("empty.");
        }
        else
        {
            nodeValue = head.value;
        }
        return nodeValue;
    }
    
    public Object peekLast()
    {
        Object nodeValue;
        if (isEmpty())
        {
            throw new IllegalArgumentException("empty.");
        }
        else
        {
            nodeValue = tail.value;            
        }
        return nodeValue;
    }
        
}
          
