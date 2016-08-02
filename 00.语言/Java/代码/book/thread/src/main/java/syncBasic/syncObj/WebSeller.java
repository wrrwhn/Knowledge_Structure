package syncBasic.syncObj;

/**
 * Created by Yao on 2015/8/20.
 */
public class WebSeller implements Runnable {

    private Cinema cinema;

    public WebSeller(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTicket4A(3);
        cinema.sellTicket4A(2);
        cinema.sellTicket4B(2);
        cinema.returnTicket4A(3);
        cinema.sellTicket4A(5);
        cinema.sellTicket4B(2);
        cinema.sellTicket4B(2);
        cinema.sellTicket4B(2);
    }
}
