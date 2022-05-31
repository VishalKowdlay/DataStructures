package com.company;


public class TreeSet<E extends Comparable<E>>
{
    private TreeNode<E> root;
    private int size;
    private String st;
    private boolean found;
    public TreeSet()
    {
        root=null;
        size=0;
    }
    public void add(E value)
    {
        if(size == 0){
            root = new TreeNode<E>(value);
            size++;
            return;
        }

        add(root, value);
    }
    public void add(TreeNode<E> root, E value)
    {
        if(value.equals(root.getValue()))
        {
            return;
        }
        else
        {
            if(value.compareTo(root.getValue())<0)
            {
                if(root.getLeft() == null) {
                    root.setLeft(new TreeNode<E>(value));
                    size++;
                    return;
                }
                else
                {
                    add(root.getLeft(), value);
                }

            }
            else
            {
                if(root.getRight() == null) {
                    root.setRight(new TreeNode<E>(value));
                    size++;
                    return;
                }
                else
                {
                    add(root.getRight(), value);
                }
            }
        }
    }


    public E minValue(TreeNode<E> root)
    {
        if(root.getLeft() == null){
            return root.getValue();
        }
        return minValue(root.getLeft());

    }
    public void remove(E value) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null){
            if (value.equals(root.getValue())) {
                root = null;
                size = 0;
                return;

            }
            else{
                return;
            }
        }
        found = false;
        root = remove(root, value);
        if(found)
        {
            size--;

        }

    }

    public TreeNode<E> remove(TreeNode<E> root, E value)
    {
        if(root == null)
        {
            return root;
        }
        if(value.compareTo(root.getValue())<0){
            root.setLeft(remove(root.getLeft(), value));
        }
        else if(value.compareTo(root.getValue())>0){
            root.setRight(remove(root.getRight(), value));
        }
        else{
            found = true;
            if(root.getLeft() == null)
                return root.getRight();
            else if(root.getRight() == null)
                return root.getLeft();
            else
            {
                E minVal = minValue(root.getRight());
                root.setValue(minVal);
                root.setRight(remove(root.getRight(), minVal));
            }
        }
        return root;
    }


    public int size()
    {
        return size;
    }


    public String preOrder()
    {
        //size considerations?
        if(size == 0)
            return "[]";
        if(size==1){
            return "["+root.getValue()+"]";
        }

        //start assemblying the "st" String
        st="[";
        preOrder(root);
        //return st fully assembled
        return st.substring(0, st.length()-2)+"]";
    }
    public void preOrder(TreeNode<E> root)
    {
        if(root != null) {
            st += root.getValue() + ", ";
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }

    }

    public String inOrder()
    {
        if(size == 0)
            return "[]";
        if(size==1){
            return "["+root.getValue()+"]";
        }

        //start assemblying the "st" String
        st="[";
        inOrder(root);
        //return st fully assembled
        return st.substring(0, st.length()-2)+"]";
    }

    public void inOrder(TreeNode<E> root)
    {
        if(root != null) {
            inOrder(root.getLeft());
            st += root.getValue() + ", ";
            inOrder(root.getRight());
        }
    }

    public String postOrder()
    {
        if(size == 0)
            return "[]";
        if(size==1){
            return "["+root.getValue()+"]";
        }

        //start assemblying the "st" String
        st="[";
        postOrder(root);
        //return st fully assembled
        return st.substring(0, st.length()-2)+"]";
    }

    public void postOrder(TreeNode<E> root)
    {
        if(root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            st += root.getValue() + ", ";
        }
    }

    public boolean contains(TreeNode<E> root, E value)
    {
        if(root == null)
            return false;
        if(root.getValue().equals(value))
            return true;
        if(value.compareTo(root.getValue()) < 0)
            return contains(root.getLeft(), value);
        return contains(root.getRight(), value);
    }



    public class TreeNode<E extends Comparable<E>> //kekw
    {
        private E value;
        private TreeNode<E> right;
        private TreeNode<E> left;
        public TreeNode(E value)
        {
            this.value=value;
            right=null;
            left=null;
        }
        public TreeNode<E> getRight()
        {
            return right;
        }
        public TreeNode<E> getLeft()
        {
            return left;
        }
        public void setRight(TreeNode<E> node)
        {
            right=node;
        }
        public void setLeft(TreeNode<E> node)
        {
            left=node;
        }
        public E getValue()
        {
            return value;
        }
        public void setValue(E val)
        {
            value=val;
        }
        public String toString()
        {
            return ""+value;
        }
    }
}
