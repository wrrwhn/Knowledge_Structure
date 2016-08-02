package appearance;

/**
 * Created by Yao on 2015/4/1.
 */
public class HomeTheaterFacade {

    IDevice amp;
    IDevice tuner;
    IDevice dvd;

    public static void main(String[] args){
        HomeTheaterFacade facade= new HomeTheaterFacade(new Amplifier(), new Tuner(), new DvdPlayer());
        facade.watchMovie();
        facade.endMovie();
    }


    public HomeTheaterFacade(Amplifier amp, Tuner tuner, DvdPlayer dvd) {
        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
    }

    public void watchMovie(){
        System.out.println("=============================\nWaiting for watching movie\n=============================");
        amp.on();
        tuner.on();
        dvd.on();
    }

    public void endMovie(){
        System.out.println("=============================\nWating for ending movie\n=============================");
        dvd.off();
        tuner.off();
        amp.off();
    }
}
