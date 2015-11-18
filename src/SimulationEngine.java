import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine implements Runnable {

    List<Pathogen> pathogens = new ArrayList<>();

    private boolean simulation;

    private Settings settings;

    private Board board;

    public SimulationEngine(Settings settings, Board board) {
        this.settings = settings;
        this.board = board;
        Random rnd = new Random();
        for(int i=0;i<settings.getStartGermAmount(); i++) {
            int x=rnd.nextInt(settings.getBoardSize());
            int y=rnd.nextInt(settings.getBoardSize());
            if(!isPathogenInPosition(x, y))
                pathogens.add(new Germ(x, y));
            else
                i--;
        }

        for(int i=0;i<settings.getStartWormAmount(); i++) {
            int x=rnd.nextInt(settings.getBoardSize());
            int y=rnd.nextInt(settings.getBoardSize());
            if(!isPathogenInPosition(x, y))
                pathogens.add(new Worm(x, y));
            else
                i--;
        }

        simulation = true;
        board.setPathogens(pathogens);
    }

    private boolean isPathogenInPosition(int x, int y) {
        for(Pathogen p : pathogens) {
            if(p.getX() == x && p.getY() == y)
                return true;
        }
        return false;
    }

    public void stopSimulation() {
        simulation = false;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        while(simulation) {
            try {
                Thread.sleep(settings.getFreqSpawnGerm());
            } catch (InterruptedException e) {
                System.out.println("Niespodziewany nowy przebieg symulacji");
            }
            int x;
            int y;
            do {
                x = rnd.nextInt(settings.getBoardSize());
                y = rnd.nextInt(settings.getBoardSize());
            } while(isPathogenInPosition(x, y));
            pathogens.add(new Germ(x, y));
            board.setPathogens(pathogens);
        }
    }
}
