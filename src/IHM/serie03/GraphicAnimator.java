package IHM.serie03;

public class GraphicAnimator
{
    private int state;
    private int speed;
    private int maxSpeed;

    public int isAnimationPaused()
    {
        return state = 2;
    }

    public int isAnimationRunning()
    {
        return state = 1;
    }

    public int isAnimationStopped()
    {
        return state = 0;
    }

    public int isAnimationStarted()
    {
        return state = 0;
    }

    public void startAnimation()
    {
        this.state = 1;
    }

    public void pauseAnimation()
    {
        if(this.state == 1)
            this.state = 2;
    }

    public void resumeAnimation()
    {
        if(this.state == 2)
            this.state = 1;
    }

    public void stopAnimation()
    {
        if(this.state == 1 || this.state == 2)
            this.state = 0;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getMaxSpeed()
    {
        return this.maxSpeed;
    }
}
