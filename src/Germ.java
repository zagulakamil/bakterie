/**
 * Created by kamil on 18.11.2015.
 */
public class Germ extends Pathogen {

    public Germ() {
        super();
        super.setStatus(1);
    }

    public Germ(int x, int y) {
        this();
        super.setX(x);
        super.setY(y);
    }
}
