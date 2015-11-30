import java.util.Random;

public class Gene {

    private int [] geneNumbers = new int[6];

    public Gene() {
        Random rd = new Random();
        for(int i=0; i<geneNumbers.length; i++) {
            geneNumbers[i] = rd.nextInt(51);
        }
    }

    @Override
    protected Gene clone() {
        Gene gene = new Gene();
        for(int i=0; i<this.geneNumbers.length; i++) {
            gene.geneNumbers[i] = geneNumbers[i];
        }
        return gene;
    }

    public void changeOneNumber() {
        Random rd = new Random();
        geneNumbers[rd.nextInt(geneNumbers.length)] = rd.nextInt(51);
    }

    public int [] getGene() {
        return geneNumbers;
    }
}
