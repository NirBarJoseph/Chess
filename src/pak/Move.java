package pak;

/**
 * Created by barjon on 10-Jul-16.
 */
public class Move {

    Location from;
    Location to;
    int value = 0;

    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }
}
