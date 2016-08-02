package packages;

/**
 * Created by Yao on 2015/3/17.
 */
public class Stereo {

    private String locate;

    public Stereo(String locate) {
        this.locate = locate;
    }

    public void setCD(){
        System.out.println("Stereo setting the CD");
    }

    public  void setVolume(int num){
        System.out.println(String.format("Stereo set the num to %s", num));
    }

    public void on(){
        System.out.println("Stereo On");
    }

    public void off(){
        System.out.println("Stereo Off");
    }

    @Override
    public String toString() {
        return "Stereo{" +
                "locate='" + locate + '\'' +
                '}';
    }
}
