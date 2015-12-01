import java.util.ArrayList;
import java.util.Iterator;
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
            if(!isPathogenInPosition(x, y)) {
                Worm worm = new Worm(x, y);
                worm.setWeight(settings.getStartWormWeight());
                pathogens.add(worm);
            } else
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
        int simulationCount = 0;
        while(simulation) {
            simulationCount++;
            try {
                Thread.sleep(settings.getFreqSpawnGerm());
            } catch (InterruptedException e) {
                System.out.println("Niespodziewany nowy przebieg symulacji");
            }
            // poruszanie robaków
            for(Pathogen p : pathogens) {
                if(p instanceof Worm) {
                    Worm w = (Worm) p;
                    switch(w.getDirection()) {
                        case 0:
                            w.setX(w.getX()+1);
                            w.setY(w.getY()+1);
                            if(w.getX() >= board.getBoardSize()) {
                                w.setX(w.getX()-board.getBoardSize());
                            }
                            if(w.getY() >= board.getBoardSize()) {
                                w.setY(w.getY()-board.getBoardSize());
                            }
                            break;
                        case 1:
                            w.setX(w.getX()+1);
                            if(w.getX() >= board.getBoardSize()) {
                                w.setX(w.getX()-board.getBoardSize());
                            }
                            break;
                        case 2:
                            w.setY(w.getY()-1);
                            w.setX(w.getX()+1);
                            if(w.getX() >= board.getBoardSize()) {
                                w.setX(w.getX()-board.getBoardSize());
                            }
                            if(w.getY() >= board.getBoardSize()) {
                                w.setY(w.getY()-board.getBoardSize());
                            }
                            break;
                        case 3:
                            w.setY(w.getY()-1);
                            if(w.getY() >= board.getBoardSize()) {
                                w.setY(w.getY()-board.getBoardSize());
                            }
                            break;
                        case 4:
                            w.setX(w.getX()-1);
                            if(w.getX() >= board.getBoardSize()) {
                                w.setX(w.getX()-board.getBoardSize());
                            }
                            break;
                        case 5:
                            w.setY(w.getY()+1);
                            if(w.getY() >= board.getBoardSize()) {
                                w.setY(w.getY()-board.getBoardSize());
                            }
                            break;
                    }
                }
            }
            // robaczek zgłodniał po wykonaniu ruchu
            for(Pathogen p : pathogens) {
                if(p instanceof Worm) {
                    ((Worm) p).subtractWeight(1);
                }
            }

            List<Pathogen> toRemove = new ArrayList<>();
            // zjadanie przez robaki bakterii
            Iterator<Pathogen> iterator = pathogens.iterator();
            while(iterator.hasNext()) {
                Pathogen p = iterator.next();
                Iterator<Pathogen> it2 = pathogens.iterator();
                while(it2.hasNext()) {
                    Pathogen p2 = it2.next();
                    if(p instanceof Worm) {
                        if(p2 instanceof Germ) {
                            if(p.getX() == p2.getX() && p.getY() == p2.getY()) {
                                ((Worm)p).addWeight(p2.getWeight());
                                toRemove.add(p2);
                                //it2.remove();
                            }
                        }
                    }
                }
            }

            for(int i=0; i<toRemove.size(); i++) {
                pathogens.remove(toRemove.get(i));
            }

            // sprawdzanie ktory robaczek umarl
            iterator = pathogens.iterator();
            while(iterator.hasNext()) {
                Pathogen p = iterator.next();
                if(p.getWeight() == 0) {
                    iterator.remove();
                }
            }

            // rozmnożenie robaczka
            List<Pathogen> toAdd = new ArrayList<>();
            for(Pathogen p : pathogens) {
                if(p instanceof Worm) {
                    if(p.getWeight() >= settings.getMultiplyWormWeight()) {
                        int newWeight = p.getWeight() / 2;
                        p.setWeight(newWeight);
                        Worm worm = new Worm(p.getX(), p.getY());
                        worm.setWeight(newWeight);
                        worm.setGene(((Worm) p).getGene().clone());
                        ((Worm) p).getGene().changeOneNumber();
                        worm.getGene().changeOneNumber();
                        toAdd.add(worm);
                    }
                }
            }
            pathogens.addAll(toAdd);

            // nowe bakterie
            if(simulationCount % 5 == 0) {
                int x;
                int y;
                do {
                    x = rnd.nextInt(settings.getBoardSize());
                    y = rnd.nextInt(settings.getBoardSize());
                } while (isPathogenInPosition(x, y));
                pathogens.add(new Germ(x, y));
            }
            board.setPathogens(pathogens);

            System.out.println("Ilosc patogenow: "+pathogens.size());
            int i=0;
            for(Pathogen p : pathogens) {
                System.out.println(p);
                if(p instanceof Worm) {
                    i++;
                }
            }
            if(simulationCount % 100 == 0) {
                for(Pathogen p : pathogens) {
                    if( p instanceof Worm) {
                        System.out.println("Geny robaka: "+((Worm)p).getGene());
                    }
                }
                simulation = false;
            }
            if(i == 0) {
                System.out.println(" !!!!!!!!!!!!!! BRAK ROBAKÓW !!!!!!!!!!!!!!!! ");
            } else if( i == 1) {
                    for(Pathogen p : pathogens) {
                        if( p instanceof Worm) {
                            System.out.println("Geny robaka: "+((Worm)p).getGene());
                        }
                    }
                    simulation = false;
            } else {
                System.out.println(" !!!!!!!!!!!!!!! "+i+" ROBAKÓW !!!!!!!!!!!!!!!! ");
            }
        }
    }
}
