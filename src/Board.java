import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Board extends JPanel {

    private Settings settings;

    private BufferedImage hexagon;

    private BufferedImage hexagonWorm;

    private BufferedImage hexagonGerm;

    private int [][] board;

    public void setSettings(Settings settings) {
        this.settings = settings;
        board = new int[settings.getBoardSize()][settings.getBoardSize()];
        setPreferredSize(new Dimension((50 * (settings.getBoardSize() + 1) - 25),
                50 * settings.getBoardSize() - 12 * (settings.getBoardSize()-1)));
    }

    public void setPathogens(List<Pathogen> list) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        for(Pathogen p : list) {
            if(p instanceof Worm) {
                board[p.getX()][p.getY()] = 1;
            } else {
                board[p.getX()][p.getY()] = 2;
            }
        }
        invalidate();
        repaint();
    }

    public Board(Settings settings) {
        File file = new File("hexagon.png");
        File file2 = new File("hexagon_germ.png");
        File file3 = new File("hexagon_worm.png");

        try {
            hexagon = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            hexagonGerm = ImageIO.read(file2);
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            hexagonWorm = ImageIO.read(file3);
        } catch(IOException e) {
            e.printStackTrace();
        }
        setSettings(settings);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setBackground(Color.BLACK);
        for(int i=0; i<settings.getBoardSize(); i++) {
            for(int j=0; j<settings.getBoardSize(); j++) {
                int x = 0;
                int y = 0;
                if(j % 2 == 0)
                    x = (i * 50);
                else
                    x = (i * 50 + 25);

                if(j != 0)
                    y = (j * 50 - 12*j);
                else
                    y = (j * 50);

                if(board[i][j] == 0)
                    g2d.drawImage(hexagon, x, y, 50, 50, this);
                else if(board[i][j] == 1)
                    g2d.drawImage(hexagonWorm, x, y, 50, 50, this);
                else
                    g2d.drawImage(hexagonGerm, x, y, 50, 50, this);
            }
        }
    }

}
