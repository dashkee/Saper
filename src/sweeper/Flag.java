package sweeper;

import java.util.concurrent.Callable;

class Flag
{
    private Matrix flagMap;
    private  int countOfClosedBoxes;
/// подготавливает игровое поле к началу игры
    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }
/// возвращает значение из матрицы flagMap по указанным координатам
    Box get (Coord coord)
    {
        return flagMap.get(coord);
    }
/// изменяет состояние клетки в матрице flagMap по указанным координатам и уменьшает счётчик закрытых клеток
    void setOpenedToBox(Coord coord)
    {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes --;
    }
/// функция переключения флага
    void toggleFlagedBox(Coord coord)
    {
        switch (flagMap.get(coord))
        {
            case FLAGED: setClosedToBox (coord); break;
            case CLOSED: setFlagedBox(coord); break;
        }
    }
/// функция закрытия ячейки, снятия флага
   private void setClosedToBox (Coord coord)
    {
        flagMap.set(coord, Box.CLOSED);
    }
/// функция постановки флага
    private void setFlagedBox(Coord coord)
    {
        flagMap.set(coord, Box.FLAGED);
    }
/// возвращает текущее значение переменной countOfClosedBoxes
    int getCountOfCloseBoxes()
    {
        return countOfClosedBoxes;
    }
/// устанавливает значение клетки в матрице flagMap по указанным координатам в состояние Box.BOMBED
    void setBombedToBox(Coord coord)
    {
        flagMap.set(coord, Box.BOMBED);
    }
///устанавливает значение клетки в матрице flagMap по указанным координатам в состояние Box.BOMBED
    void setOpenedToClosesBombBox(Coord coord)
    {
        if(flagMap.get(coord) == Box.CLOSED)
            flagMap.set (coord, Box.OPENED);
    }
/// если клетка была FLAGED, то в случае, если это оказалась мина, она станет NOBOMB
    void setNoBombToFlagedSafeBox(Coord coord)
    {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set (coord, Box.NOBOMB);
    }
/// считает количество клеток, помеченных как FLAGED
    int getCountOfFlagedBoxesAround (Coord coord)
    {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if(flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
