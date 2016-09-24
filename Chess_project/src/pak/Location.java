package pak;

/**
 * Created by barjon on 10-Jul-16.
 */
public class Location {

    byte c;
    byte r;

    public Location(byte c, byte r) throws InvalidParameters{
        if(0 > c || c > 7  || 0 > r || r > 7) {
            throw new InvalidParameters(
                    String.format("One or more of this parameters are invalid: c = %d, r = %d", c, r));
        }
        this.c = c;
        this.r = r;
    }

    public Location(String xy){
        this.c = (byte) (xy.charAt(1) - 'a');
        this.r = (byte) (xy.charAt(3) - '0' - 1);
    }

}
