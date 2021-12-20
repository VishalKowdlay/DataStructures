import javax.lang.model.util.ElementScanner6;

public class SuperList<E>
{
    //Constructor, add, add, get, remove, push, pop, isEmpty, poll, size, contains
    //StackPeek, queuePeek, clear, toString
     
    ListNode<E> root;
    ListNode<E> end;
    int size;

    public SuperList()
    {
        root = null;
        end = null;
        size = 0;
    }

    public void add(E value)
    {
        ListNode<E> newNode = new ListNode<E>(value);
        if(isEmpty())
        {
            root = newNode;
            end = root;
            
        }

        else
        {
            newNode.setPrevious(end);
            end.setNext(newNode);
            end = newNode;
            

        }
        size++;

    }

    public void add(int index, E value)
    {
        ListNode<E> newNode = new ListNode<E>(value);
        if(index<0 || index>size)
        {
            throw new IndexOutOfBoundsException();
        }

        if(isEmpty())
        {
            add(value);
        }

        else if(index==0)
        {            
            root.setPrevious(newNode);
            newNode.setNext(root);
            size++;
            root = newNode;
        }
        else if(index==size)
        {
            add(value);
        }
        else
        {
            ListNode<E> temp = root;
            for(int i=1; i<index; i++)
            {
                temp = temp.getNext();
                
            }
            ListNode<E> tempNext = temp.getNext();
            tempNext.setPrevious(newNode);
            newNode.setNext(tempNext);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
            size++;
        }

    }

    public int size()
    {
        return size;
    }

    public String toString()
    {
        String st = "[";
        ListNode<E> temp = root;
        for(int i=0; i<size; i++)
        {
            st+=temp.getValue()+", ";
            temp = temp.getNext();
        }
        if(st.length()>1)
            st = st.substring(0, st.length()-2);
        st+="]";
        return st;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void remove(int index, E value)
    {
        ListNode<E> newNode = new ListNode<E>(value);
        if(index<0 || index>size)
        {
            throw new IndexOutOfBoundsException();
        }

        if(isEmpty())
        {
            throw new NullPointerException();
        }

        else if(index==0)
        {            
            root.setNext(newNode);
            newNode.setPrevious(root);
            size--;
            root = newNode;
        }
        else if(index==size)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            ListNode<E> temp = root;
            for(int i=1; i<index; i++)
            {
                temp = temp.getNext();
                
            }
            ListNode<E> tempNext = temp.getNext();
            tempNext.setNext(newNode);
            newNode.setPrevious(tempNext);
            temp.setPrevious(newNode);
            newNode.setNext(temp);
            size--;
        }

    }

    public class ListNode<E>
    {
        E value;
        ListNode<E> next;
        ListNode<E> previous;

        public ListNode(E v)
        {
            value = v;
            next = null;
            previous = null;            

        }

        public E getValue()
        {
            return value;
        }

        public ListNode<E> getNext()
        {
            return next;
        }

        public void setNext(ListNode<E> listNext)
        {
            next = listNext;
        }

        public ListNode<E> getPrevious()
        {
            return previous;
        }

        public void setPrevious(ListNode<E> listPrev)
        {
            previous = listPrev;
        }

        public boolean hasNext()
        {
            return next != null;
        }

        public boolean hasPrevious()
        {
            return previous != null;
        }

    }



}