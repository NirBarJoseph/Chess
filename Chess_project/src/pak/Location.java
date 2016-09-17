package pak;

/**
 * Created by barjon on 10-Jul-16.
 */
public class Location {

    byte c;
    byte r;

    public Location(byte c, byte r){
        this.c = c;
        this.r = r;
    }

    public Location(String xy){
        this.c = (byte) (xy.charAt(1) - 'a');
        this.r = (byte) (xy.charAt(3) - '0' - 1);
    }

}
