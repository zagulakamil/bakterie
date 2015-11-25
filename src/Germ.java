import java.util.Random;

public class Germ extends Pathogen {

    public Germ() {
        super();
        super.setStatus(1);
        Random rd = new Random();
        super.setWeight(rd.nextInt(9)+1);
    }

    public Germ(int x, int y) {
        this();
        super.setX(x);
        super.setY(y);
    }

    @Override
    public String toString() {
        return "Germ: weight = "+super.getWeight()+" position = ("+super.getX()+","+super.getY()+")";
    }
}
