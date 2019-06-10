package com.company.JonsBurgerJoint;

public class Prod implements Runnable
{
    private Slider resource;
    public Prod(Slider res){
        resource=res;
    }
    @Override
    public void run()
    {
        int newLevel=0;
        while(true){
            newLevel = resource.addOne(); // sover muligvis i Res objektet
            System.out.println("Producer: "+newLevel);
            int pause = (int)(Math.random()*2000);
            try
            {
                Thread.sleep(pause);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}
