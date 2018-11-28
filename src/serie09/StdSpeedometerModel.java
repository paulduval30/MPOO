package serie09;

import util.Contract;

import java.util.Observable;

public class StdSpeedometerModel extends Observable implements SpeedometerModel
{
    private double maxSpeed;
    private double speed;
    private double step;

    private boolean on;

    private SpeedUnit unit;

    public StdSpeedometerModel(double step, double max){
        Contract.checkCondition(step >= 1 && max > 0 && step <= max, "illegal value");
        this.step = step;
        this.maxSpeed = max;
        this.unit = SpeedUnit.KMH;
    }
    @Override
    public double getMaxSpeed()
    {
        return this.maxSpeed;
    }

    @Override
    public double getSpeed()
    {
        return this.speed;
    }

    @Override
    public double getStep()
    {
        return this.step;
    }

    @Override
    public SpeedUnit getUnit()
    {
        return this.unit;
    }

    @Override
    public boolean isOn()
    {
        return this.on;
    }

    @Override
    public void setUnit(SpeedUnit unit)
    {
        Contract.checkCondition(unit != null);
        this.speed = this.speed / this.getUnit().getUnitPerKm() * unit.getUnitPerKm();
        this.step = this.step / this.getUnit().getUnitPerKm() * unit.getUnitPerKm();
        this.maxSpeed = this.maxSpeed / this.getUnit().getUnitPerKm() * unit.getUnitPerKm();
        this.unit = unit;
        setChanged();
        notifyObservers();

    }

    @Override
    public void slowDown()
    {
        Contract.checkCondition(this.on);
        this.speed = Math.max(0, this.speed - this.step);
        setChanged();
        notifyObservers();
    }

    @Override
    public void speedUp()
    {
        Contract.checkCondition(this.on);
        this.speed = Math.min(this.maxSpeed, this.speed + this.step);
        setChanged();
        notifyObservers();
    }

    @Override
    public void turnOff()
    {
        Contract.checkCondition(this.on);
        this.on = false;
        this.speed = 0;
        setChanged();
        notifyObservers();
    }

    @Override
    public void turnOn()
    {
        Contract.checkCondition(!this.on);
        this.on = true;
        setChanged();
        notifyObservers();
    }
}
