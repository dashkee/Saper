package sweeper;

class Matrix
{
    private Box [] [] matrix;
    /// конструктор создаёт и инициализирует матрицу, которая представляет собой поле
    Matrix (Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for(Coord coord : Ranges.getAllCoords())
            matrix [coord.x][coord.y] = defaultBox;
    }
///возвращает значение из двумерного массива matrix по указанным координатам, если они не выходят за предел
    Box get (Coord coord)
    {
        if (Ranges.inRange(coord))
            return matrix [coord.x][coord.y];
        return null;
    }
    ///устанавливает значение box в двумерный массив matrix по указанным координатам, если они в допустимом диапазоне
    void set (Coord coord, Box box)
    {
        if (Ranges.inRange (coord))
            matrix [coord.x] [coord.y] = box;
    }
}
