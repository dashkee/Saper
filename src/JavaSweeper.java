import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.NumericShaper;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame
{
    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;
/// является точкой входа программы на языке Java
    public static void main(String[] args)
    {
        new JavaSweeper();
    }
/// конструктор класса
    private JavaSweeper()
    {
        game = new Game (COLS, ROWS, BOMBS);
        game.start();
        Ranges.setSize (new Coord(COLS,ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }
/// отвечает за создание и добавление метки (JLabel) в интерфейс приложения
    private  void initLabel ()
    {
        label = new JLabel("Welcome!");
        add (label, BorderLayout.SOUTH);
    }
///создает и настраивает панель для рисования изображения
    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent (Graphics g) {
                super.paintComponent(g);
                for(Coord coord : Ranges.getAllCoords())
                {
                    g.drawImage((Image) game.getBox(coord).image, coord.x *  IMAGE_SIZE, coord.y *  IMAGE_SIZE, this);
                }
            }
        };
/// добавление обработки кликов мыши
    panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX() / IMAGE_SIZE;
            int y = e.getY() / IMAGE_SIZE;
            Coord coord = new Coord(x,y);
            if(e.getButton() == MouseEvent.BUTTON1)
                game.pressLeftButton (coord);
            if(e.getButton() == MouseEvent.BUTTON3)
                game.pressRightButton (coord);
            if(e.getButton() == MouseEvent.BUTTON2)
                game.start();
            label.setText(getMessage());
            panel.repaint();
        }
    });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }
/// в соответствии со статусом игры, выводит сообщения
    private String getMessage()
    {
        switch (game.getState())
        {
            case PLAYED: return "Think twice";
            case BOMBED: return "You lose!";
            case WINNER: return "Congratulation!";
            default: return "Welcome!";
        }
    }
/// отвечает за инициализацию главного окна приложения (фрейма)
    private void initFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaSweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }
/// загружает изображения для всех возможных состояний клеток (представленных как Box)
    private void setImages ()
    {
        for(Box box: Box.values())
            box.image = getImage(box.name().toLowerCase());
    }
/// отвечает за загрузку изображения по указанному имени файла
    private  Image getImage (String name)
    {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon (getClass().getResource(filename));
        return icon.getImage();
    }
}
