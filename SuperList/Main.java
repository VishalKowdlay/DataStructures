public class Main
 {
    public static void main(String[]args){
        SuperList<Integer> superList = new SuperList<>();
        for(int i=1; i<=12; i++)
        {
            superList.add(i);
        }
        System.out.println(superList);
        superList.add(superList.size(), 20);
        superList.add(0, 30);
        superList.add(10, 40);
        superList.add(5, 6);
        //superList.remove(10, 40);
        System.out.println(superList);

    }

}
