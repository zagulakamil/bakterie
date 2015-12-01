import java.util.Random;

public class Worm extends Pathogen {

    private Gene gene;

    public Worm() {
        super();
        super.setStatus(2);
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

    public int getDirection() {
        Random rd = new Random();
        double [] tmp = new double[6];
        double sum = 0;
        for(int i=0; i<gene.getGene().length; i++) {
            tmp[i] = p(i);
            sum += tmp[i];
        }
        for ( int i=0; i<gene.getGene().length; i++) {
            tmp[i] = (tmp[i]/sum)*100;
        }
        int rand = rd.nextInt(101);
        double tempsum = 0;
        for ( int i=0; i<gene.getGene().length; i++) {
            tempsum += gene.getGene()[i];
            if(rand < tempsum) return i;

        }

        return 0;
    }

    private double p(int ki) {
        int sum = 0;
        for(int i=0; i<gene.getGene().length; i++) {
            sum += Math.exp(gene.getGene()[i]);
        }

        return Math.exp(gene.getGene()[ki])/sum;
    }

    @Override
    public String toString() {
        return "Worm: weight = "+super.getWeight()+" position = ("+super.getX()+","+super.getY()+")";
    }
}
