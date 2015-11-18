import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowFrame extends JFrame {

    private Settings settings;

    private Board board;

    private JPanel rightPanel;

    public WindowFrame(Settings settings) {
        super("Robaczki i bakterie");
        this.settings = settings;

        drawMyWindowComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        SimulationEngine simulationEngine = new SimulationEngine(settings, board);
        new Thread(simulationEngine).start();
    }

    private void drawMyWindowComponents() {
        JPanel leftPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(190,600));
        leftPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(800, 600));
        // adding items to left panel
        leftPanel.setLayout(new FlowLayout());
        JLabel boardSizeLabel = new JLabel("Rozmiar planszy:");
        leftPanel.add(boardSizeLabel);
        JTextField boardSize = new JTextField(""+this.settings.getBoardSize());
        boardSize.setPreferredSize(new Dimension(180, 24));
        leftPanel.add(boardSize);
        JLabel freqSpawnLabel = new JLabel("<html>Częstotliwość losowania bakterii i ruchu robaków (w ms):</html>");
        freqSpawnLabel.setPreferredSize(new Dimension(180, 48));
        leftPanel.add(freqSpawnLabel);
        JTextField freqSpawn = new JTextField(""+this.settings.getFreqSpawnGerm());
        freqSpawn.setPreferredSize(new Dimension(180, 24));
        leftPanel.add(freqSpawn);
        JLabel amountWormLabel = new JLabel("Ilość robaków na początku:");
        leftPanel.add(amountWormLabel);
        JTextField amountWorm = new JTextField(""+this.settings.getStartWormAmount());
        amountWorm.setPreferredSize(new Dimension(180, 24));
        leftPanel.add(amountWorm);
        JLabel startGermAmountLabel = new JLabel("Początkowa ilość bakterii:");
        leftPanel.add(startGermAmountLabel);
        JTextField startGermAmount = new JTextField(""+this.settings.getStartGermAmount());
        startGermAmount.setPreferredSize(new Dimension(180, 24));
        leftPanel.add(startGermAmount);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            this.settings.setBoardSize(Integer.parseInt(boardSize.getText()));
            this.settings.setStartGermAmount(Integer.parseInt(startGermAmount.getText()));
            this.settings.setFreqSpawnGerm(Long.parseLong(freqSpawn.getText()));
            this.settings.setStartWormAmount(Integer.parseInt(amountWorm.getText()));
            board.setSettings(this.settings);
            SimulationEngine simulationEngine = new SimulationEngine(this.settings, board);
            new Thread(simulationEngine).start();
        });
        leftPanel.add(restartButton);

        // settings right panel
        board = new Board(this.settings);
        rightPanel.add(board);

        add(leftPanel);
        add(rightPanel);
        setLayout(new FlowLayout());
        setSize(1000,630);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                leftPanel.setPreferredSize(new Dimension(190, e.getComponent().getHeight()-30));
                rightPanel.setPreferredSize(new Dimension(e.getComponent().getWidth()-210, e.getComponent().getHeight()-30));
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        setLocation(100, 50);
    }
}
