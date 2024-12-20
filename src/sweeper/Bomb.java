package sweeper;

class Bomb
{
    private Matrix bombMap;
    private int totalBomb;
/// конструктор класса
    Bomb (int totalBomb)
    {
        this.totalBomb = totalBomb;
        fixBombsCount();
    }
/// функуия запуска
    void start ()
    {
        bombMap = new Matrix (Box.ZERO);
        for(int j = 0; j < totalBomb; j++)
            placeBomb();
    }
/// геттер
    Box get (Coord coord)
    {
        return bombMap.get(coord);
    }
/// определяет допустимое количество мин
    private void fixBombsCount()
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBomb > maxBombs)
            totalBomb = maxBombs;
    }
/// расстановка мин
    private void placeBomb()
    {
        while (true)
        {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumberAroundBomb(coord);
            break;
        }
    }
/// увеличивает значение вокруг "бомбы" на единицу для всех соседних ячеек
    private void incNumberAroundBomb(Coord coord)
    {
        for (Coord around : Ranges.getCoordsAround(coord))
            if(Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    /// геттер количества мин
    int getTotalBombs()
    {
        return totalBomb;
    }
}
