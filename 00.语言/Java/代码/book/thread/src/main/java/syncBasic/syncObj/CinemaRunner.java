package syncBasic.syncObj;

/**
 * Created by Yao on 2015/8/20.
 */
public class CinemaRunner {

    public static void main(String[] args) {
        Cinema cinema= new Cinema();
        WebSeller webSeller= new WebSeller(cinema);
        LineSeller lineSeller= new LineSeller(cinema);
        Thread web = new Thread(webSeller);
        Thread line= new Thread(lineSeller);

        System.out.printf("Init Ticket:\n\tCount for A: %d\n\tCount for B: %d", cinema.getCountForA(), cinema.getCountForB());

        web.start();
        line.start();
        try{
            web.join();
            line.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Last Ticket:\n\tCount for A: %d\n\tCount for B: %d", cinema.getCountForA(), cinema.getCountForB());
    }
}
