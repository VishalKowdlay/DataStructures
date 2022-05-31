package com.company;


public class Main {

    public Main()
    {
        //TreeSet<String> set=new TreeSet<String>();

//        for(int x=0;x<20;x++)
//        {
//            int n=(int)(Math.random()*20);
//            System.out.print(""+(char)(n+65));
//            set.add(""+(char)(n+65));
//        }
//        System.out.println();
//
//
//        TreeSet<String> preOrderSet=new TreeSet<String>();
//        String st=set.preOrder();
//        st=st.substring(1,st.length()-1);
//        String[] pre=st.split(", ");
//        for(int x=0;x<pre.length;x++)
//        {
//            preOrderSet.add(pre[x]);
//        }
//
//        TreeSet<String> inOrderSet=new TreeSet<String>();
//        String st2=set.inOrder();
//        st2=st2.substring(1,st2.length()-1);
//        String[] in=st2.split(", ");
//        for(int x=0;x<in.length;x++)
//        {
//            inOrderSet.add(in[x]);
//        }
//        TreeSet<String> postOrderSet=new TreeSet<String>();
//        String st3=set.postOrder();
//        st3=st3.substring(1,st3.length()-1);
//        String[] post=st3.split(", ");
//        for(int x=0;x<post.length;x++)
//        {
//            postOrderSet.add(post[x]);
//        }
//        System.out.println("Original Set - PreOrder: "+set.preOrder());
//        System.out.println();
//        System.out.println("Original Set - InOrder: "+set.inOrder());
//        System.out.println();
//        System.out.println("Original Set - PostOrder: "+set.postOrder());
//        System.out.println("****************************");
//
//        System.out.println("PreOrder Copy - PreOrder: "+preOrderSet.preOrder());
//        System.out.println();
//        System.out.println("PreOrder Copy - InOrder: "+preOrderSet.inOrder());
//        System.out.println();
//        System.out.println("PreOrder Copy - PostOrder:"+preOrderSet.postOrder());
//        System.out.println("****************************");
//
//        System.out.println("InOrder Copy - PreOrder: "+inOrderSet.preOrder());
//        System.out.println();
//        System.out.println("InOrder Copy - InOrder: "+inOrderSet.inOrder());
//        System.out.println();
//        System.out.println("InOrder Copy - PostOrder: "+inOrderSet.postOrder());
//        System.out.println("****************************");
//
//        System.out.println("PostOrder Copy - PreOrder: "+postOrderSet.preOrder());
//        System.out.println();
//        System.out.println("PostOrder Copy - InOrder: "+postOrderSet.inOrder());
//        System.out.println();
//        System.out.println("PostOrder Copy - PostOrder: "+postOrderSet.postOrder());
//        System.out.println("****************************");
//        set.remove("M");
//        System.out.println("Removed M: "+set.preOrder());

        TreeSet<Integer> set=new TreeSet<Integer>();


		set.add(30);
		set.add(20);
		set.add(50);
		set.add(45);
		set.add(10);
		set.add(46);
		set.add(10);
		set.add(15);
		set.add(47);
		set.add(60);

		System.out.println("Original Set - PreOrder: Size: " +set.size() + set.preOrder());
		System.out.println();
		System.out.println("Original Set - InOrder: Size: "+ set.size()+ set.inOrder());
		System.out.println();
		System.out.println("Original Set - PostOrder: Size: "+ set.size()+ set.postOrder());
		System.out.println("****************************");



		set.remove(60);
		set.remove(44);
		set.remove(30);
		set.remove(30);


		System.out.println("Original Set - PreOrder: Size: " +set.size() + set.preOrder());
		System.out.println();
		System.out.println("Original Set - InOrder: Size: "+ set.size()+ set.inOrder());
		System.out.println();
		System.out.println("Original Set - PostOrder: Size: "+ set.size()+ set.postOrder());
		System.out.println("****************************");


    }

    public static void main(String[] args) {
	// write your code here
        Main app=new Main();
    }
}
