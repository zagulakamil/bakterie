/**
 * Created by kamil on 18.11.2015.
 */
public class Worm extends Pathogen {

    public Worm() {
        super();
        super.setStatus(2);
    }

    public Worm(int x, int y) {
        this();
        super.setX(x);
        super.setY(y);
    }
}
