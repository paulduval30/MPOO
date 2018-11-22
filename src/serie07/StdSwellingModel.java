package serie07;

import java.awt.*;
import java.util.Observable;

public class StdSwellingModel extends Observable implements SwellingModel
{
    private Dimension dimension;
    private Dimension maxDim;
    private Dimension minDim;


    @Override
    public Dimension getDimension()
    {
        return this.dimension;
    }

    @Override
    public Dimension getMaxDimension()
    {
        return this.maxDim;
    }

    @Override
    public Dimension getMinDimension()
    {
        return minDim;
    }

    @Override
    public void setBounds(Dimension min, Dimension max)
    {
        this.minDim = min;
        this.maxDim = max;
    }

    @Override
    public void scale(double factor)
    {
        Dimension tempDim = new Dimension((int)(this.dimension.getWidth() * (1 - factor)),
                (int)(this.dimension.getHeight() * (1 - factor)));
    }

    private int compareDim(Dimension d1, Dimension d2)
    {
        if(d1.getWidth() >= d2.getWidth() || d1.getHeight() >= d2.getHeight())
            return 1;
        if(d1.getWidth() <= d2.getWidth() || d1.getHeight() <= d2.getHeight())
            return -1;
        return 0;
    }
}
