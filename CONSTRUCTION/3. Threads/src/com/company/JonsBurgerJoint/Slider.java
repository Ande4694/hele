package com.company.JonsBurgerJoint;

public class Slider
{
    private int antalBurgere;
    private final int MAX=8;

    public Slider(int startvalue){
        antalBurgere =startvalue;
    }

    public synchronized int addOne(){ //producér én, men kun op til MAX
        try
        {
            while(antalBurgere >=MAX){
                wait(); // Does release the lock on this object, allowing consumer access.
            }
            antalBurgere++;
            notifyAll();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return antalBurgere;
    }

    public synchronized int takeOne(){
        try
        {
            while(antalBurgere == 0){
                wait();  // venter her, indtil der bliver kaldt notifyAll()
            }
            antalBurgere--;  // we consume one item
            notifyAll();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return antalBurgere;
    }

}