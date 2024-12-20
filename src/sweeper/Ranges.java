package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Coord size;
    private static ArrayList<Coord> allCoords;
    private static Random random = new Random();
///устанавливает размеры для структуры данных и генерирует список всех возможных координат в пределах этих размеров
    public static void setSize (Coord _size)
    {
        size = _size;
        allCoords = new ArrayList<Coord>();
        for(int y = 0; y < size.y; y++)
            for(int x = 0; x < size.x; x++)
                allCoords.add(new Coord(x,y));
    }
/// возвращает текущий размер сетки
    public static Coord getSize()
    {
        return size;
    }
/// возвращает список всех возможных координат в пределах установленной сетки
    public static ArrayList<Coord> getAllCoords()
    {
        return allCoords;
    }
/// проверяет, находятся ли координаты coord в пределах установленного размера сетки
    static boolean inRange (Coord coord)
    {
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }
/// генерирует случайную координату в пределах установленной сетки
    static Coord getRandomCoord ()
    {
        return  new Coord(random.nextInt(size.x),
                          random.nextInt(size.y));
    }
/// возвращает список всех координат, окружающих указанную координату coord
    static ArrayList<Coord> getCoordsAround(Coord coord)
    {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for(int x = coord.x - 1; x <= coord.x + 1; x++)
            for(int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x,y)))
                    if(!around.equals(coord))
                        list.add (around);
        return list;
    }
}
