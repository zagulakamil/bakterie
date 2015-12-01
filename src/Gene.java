import java.util.Random;

public class Gene {

    private int [] geneNumbers = new int[6];

    private int sum = 0;

    public Gene() {
        Random rd = new Random();
        for(int i = 0; i<geneNumbers.length; i++) {
            geneNumbers[i] = rd.nextInt(51);
            sum += geneNumbers[i];
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
        sum = 0;
        for(int i=0; i<geneNumbers.length; i++) {
            sum += geneNumbers[i];
        }
    }

    public int [] getGene() {
        return geneNumbers;
    }

    @Override
    public String toString() {
        return geneNumbers[0]+","+geneNumbers[1]+","+geneNumbers[2]+","+geneNumbers[3]+","+geneNumbers[4]+","+geneNumbers[5];
    }
}
