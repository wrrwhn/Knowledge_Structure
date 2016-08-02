package syncBasic.syncObj;

/**
 * Created by Yao on 2015/8/20.
 */
public class LineSeller implements Runnable {

    private Cinema cinema;

    public LineSeller(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTicket4B(2);
        cinema.sellTicket4B(4);
        cinema.sellTicket4A(2);
        cinema.sellTicket4A(1);
        cinema.returnTicket4B(2);
        cinema.sellTicket4A(3);
        cinema.sellTicket4A(2);
        cinema.sellTicket4B(2);
        cinema.sellTicket4A(2);
    }
}
