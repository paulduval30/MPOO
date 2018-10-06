package serie03;

import util.Contract;

import java.util.*;

public class StdHistory<E> implements History<E>
{
    private int maxHeight;
    private int currentPosition;
    private int endPosition;

    private Deque<E> head;
    private Queue<E> tail;

    public StdHistory(int maxHeight)
    {
        this.maxHeight = maxHeight;
        this.currentPosition = 0;
        this.endPosition = 0;
        this.head = new LinkedList<E>();
        this.tail = new LinkedList<E>();
    }
    @Override
    public int getMaxHeight()
    {
        return this.maxHeight;
    }

    @Override
    public int getCurrentPosition()
    {
        Contract.checkCondition(this.currentPosition > 0, "You can't have a position under 0");
        return this.currentPosition;
    }

    @Override
    public E getCurrentElement()
    {
        Contract.checkCondition(this.currentPosition > 0, "Their is nothing in the queue");
        return this.head.getFirst();
    }

    @Override
    public int getEndPosition()
    {
        return this.endPosition;
    }

    @Override
    public void add(E e)
    {
        Contract.checkCondition(e != null, "You can't add a null object to the queue");
        if(this.currentPosition < this.maxHeight)
        {
            head.addFirst(e);
            tail.clear();
        }
        else
        {
            head.removeLast();
            head.addFirst(e);
            tail.clear();
        }
        this.currentPosition = Math.min(this.currentPosition + 1, this.maxHeight);
        this.endPosition = this.currentPosition;
    }


    @Override
    public void goForward()
    {
        Contract.checkCondition(this.currentPosition < this.endPosition, "Their is nothing more forward");
        this.currentPosition++;
        this.head.add(this.tail.remove());
    }

    @Override
    public void goBackward()
    {
        Contract.checkCondition(this.currentPosition > 0, "Their is nothing more backward");
        this.currentPosition --;
        this.tail.add(this.head.removeFirst());
    }
}
