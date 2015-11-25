
public class Worm extends Pathogen {

    private Gene gene;

    public Worm() {
        super();
        super.setStatus(2);
        super.setWeight(5);
        gene = new Gene();
    }

    public Worm(int x, int y) {
        this();
        super.setX(x);
        super.setY(y);
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public void addWeight(int value) {
        super.setWeight(getWeight()+value);
    }

    public void subtractWeight(int value) {
        super.setWeight(getWeight()-value);
    }

    @Override
    public String toString() {
        return "Worm: weight = "+super.getWeight()+" position = ("+super.getX()+","+super.getY()+")";
    }
}
