import javax.swing.*;
import java.awt.*;
import java.awt.font.NumericShaper;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Ranges;

public class JavaSweeper extends JFrame
{
    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args)
    {
        new JavaSweeper();
    }

    private JavaSweeper()
    {
        Ranges.setSize (new Coord(COLS,ROWS));
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent (Graphics g) {
                super.paintComponent(g);
                for(Coord coord : Ranges.getAllCoords())
                {
                    g.drawImage((Image) Box.BOMB.image, coord.x *  IMAGE_SIZE, coord.y *  IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }

    private void initFrame()
    {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaSweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
    }

    private void setImages ()
    {
        for(Box box: Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private  Image getImage (String name)
    {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon (getClass().getResource(filename));
        return icon.getImage();
    }
}
