public class Settings {

    private int boardSize;

    private long freqSpawnGerm;

    private int startWormAmount;

    private int startGermAmount;

    public int getStartGermAmount() {
        return startGermAmount;
    }

    public void setStartGermAmount(int startGermAmount) {
        this.startGermAmount = startGermAmount;
    }

    public int getStartWormAmount() {
        return startWormAmount;
    }

    public void setStartWormAmount(int startWormAmount) {
        this.startWormAmount = startWormAmount;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public long getFreqSpawnGerm() {
        return freqSpawnGerm;
    }

    public void setFreqSpawnGerm(long freqSpawnGerm) {
        this.freqSpawnGerm = freqSpawnGerm;
    }
}
