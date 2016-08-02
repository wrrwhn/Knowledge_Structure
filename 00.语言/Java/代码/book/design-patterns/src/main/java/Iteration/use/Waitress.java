package Iteration.use;

import java.util.Iterator;

/**
 * Created by Yao on 2015/4/12.
 */
public class Waitress {
    IMenu listMenu;
    IMenu arrayMenu;

    public static void main(String[] args){
        new Waitress().printMenu();;
    }

    public Waitress() {
        this.listMenu = new ListMenu();
        this.arrayMenu = new ArrayMenu();
    }

    public void printMenu(){
        Iterator iArray= arrayMenu.createIterator();
        Iterator iList= listMenu.createIterator();
        System.out.println("System.Out.Println(ArrayMenu)");
        printMenu(iArray);
        System.out.println("System.Out.Println(ListMenu)");
        printMenu(iList);
    }

    private void printMenu(Iterator iterator){
        while (iterator.hasNext()){
            System.out.println("\t"+ iterator.next());
        }
    }
}
