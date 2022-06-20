package agents;

import java.util.Random;

public class RegularDuck extends Duck{
    public RegularDuck(int x,int y) {
        super(25,2,2,3,new int[]{x,y});
    }

    @Override
    public void Feeding(int time, int i) {
        if(time%2==1) this.hunger+=i+aggression/3;
        else this.hunger-=i;
    }
}
