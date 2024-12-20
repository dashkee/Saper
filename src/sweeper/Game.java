package sweeper;

public class Game
{
    private Bomb bomb;
    private Flag flag;
    private  GameState state;
    /// возвращает статус игры
    public GameState getState()
    {
        return state;
    }
/// конструктор класса
    public  Game (int cols, int rows, int bombs)
    {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }
/// подготавливает игру к началу
    public void start()
    {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }
///возвращает состояние клетки по указанным координатам
    public  Box getBox (Coord coord)
    {
        if(flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }
/// нажатие левой кнопки мыши
    public void pressLeftButton(Coord coord)
    {
        if (gameOver()) return;
        openBox(coord);
        checkWinner();
    }
/// проверка победы
    private void checkWinner()
    {
        if (state == GameState.PLAYED)
            if(flag.getCountOfCloseBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }
/// открытие ячейки
    private  void openBox(Coord coord)
    {
        switch (flag.get(coord))
        {
            case OPENED: setOpenedToClosedBoxesAroundNumber(coord);return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get (coord))
                {
                    case ZERO: openBoxesAround (coord); return;
                    case BOMB: openBomb (coord); return;
                    default  : flag.setOpenedToBox(coord);return;
                }
        }
    }
///открывает закрытые клетки вокруг числа, если все окружающие клетки, которые должны содержать бомбы, уже помечены как такие
    private void setOpenedToClosedBoxesAroundNumber(Coord coord)
    {
        if(bomb.get(coord) != Box.BOMB)
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber())
                for(Coord around : Ranges.getCoordsAround(coord))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }
/// обработка открытия бомбы
    private void openBomb(Coord bombed)
    {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord : Ranges.getAllCoords())
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosesBombBox(coord);
            else
                flag.setNoBombToFlagedSafeBox(coord);
    }
/// рекурсивное открытие ячеек около пустой
    private  void openBoxesAround (Coord coord)
    {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);
     }
/// нажатие правой кнопки мыши
    public void pressRightButton(Coord coord)
    {
        if (gameOver()) return;
        flag.toggleFlagedBox(coord);
    }
///  проверка на проигрыш
    private boolean gameOver()
    {
        if(state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
