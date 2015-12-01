import java.awt.*;

public class Start {

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {

            Settings settings = new Settings();
            settings.setBoardSize(15);
            settings.setFreqSpawnGerm(100);
            settings.setStartWormAmount(20);
            settings.setStartGermAmount(10);
            settings.setStartWormWeight(30);
            settings.setMultiplyWormWeight(31);

            new WindowFrame(settings);
        });
    }
}