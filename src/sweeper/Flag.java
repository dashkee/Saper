package sweeper;

import java.util.concurrent.Callable;

class Flag
{
    private Matrix flagMap;
    private  int countOfClosedBoxes;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coord coord)
    {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord)
    {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes --;
    }

    void toggleFlagedBox(Coord coord)
    {
        switch (flagMap.get(coord))
        {
            case FLAGED: setClosedToBox (coord); break;
            case CLOSED: setFlagedBox(coord); break;
        }

    }

   private void setClosedToBox (Coord coord)
    {
        flagMap.set(coord, Box.CLOSED);
    }

    private void setFlagedBox(Coord coord)
    {
        flagMap.set(coord, Box.FLAGED);
    }

    int getCountOfCloseBoxes()
    {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord)
    {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosesBombBox(Coord coord)
    {
        if(flagMap.get(coord) == Box.CLOSED)
            flagMap.set (coord, Box.OPENED);
    }

    void setNoBombToFlagedSafeBox(Coord coord)
    {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set (coord, Box.NOBOMB);
    }


    int getCountOfFlagedBoxesAround (Coord coord)
    {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if(flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }

}
