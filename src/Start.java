import java.awt.*;

public class Start {

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {

            Settings settings = new Settings();
            settings.setBoardSize(15);
            settings.setFreqSpawnGerm(2000);
            settings.setStartWormAmount(2);
            settings.setStartGermAmount(10);
            settings.setStartWormWeight(6);
            settings.setMultiplyWormWeight(30);

            new WindowFrame(settings);
        });
    }
}