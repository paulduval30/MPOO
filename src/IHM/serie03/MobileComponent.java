package IHM.serie03;

import java.awt.*;

public class MobileComponent implements AnimableComponent
{

    private Point center;
    private int horizontalShift;
    private int verticalShift;
    private boolean isCaught;

    private Mobile model;

    @Override
    public Point getDiscCenter()
    {
        return this.center;
    }

    @Override
    public int getHorizontalShift()
    {
        return this.horizontalShift;
    }

    @Override
    public Mobile getModel()
    {
        return this.model;
    }

    @Override
    public int getVerticalShift()
    {
        return this.verticalShift;
    }

    @Override
    public boolean isDiscCaught()
    {
        return this.isCaught;
    }

    @Override
    public void animate()
    {

    }

    @Override
    public void setDiscCenter(Point p)
    {
        this.center = p;
    }

    @Override
    public void setDiscShift(int dx, int dy)
    {
        this.horizontalShift = dy;
        this.verticalShift = dx;
    }
}
