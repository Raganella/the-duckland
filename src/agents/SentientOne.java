package agents;

import java.time.temporal.JulianFields;
import java.util.Random;

public class SentientOne extends Duck{
    public SentientOne(int x, int y) {
        super(100,2,3,0,new int[]{x,y});
    }
    @Override
    public Duck BonusAction(Human h) {
        AcientOne.prayers+=2;
        return null;
    }
    @Override
    public void Feeding(int time, int i) {
        this.BonusAction(null);
        if(time%5==1) this.hunger+=i+aggression;
        else this.hunger-=i;
    }

    @Override
    public Character Breeding(Character character){
        return null;
    }

    @Override
    public double SurvivalRoll() {
        this.BonusAction(null);
        return new Random().nextDouble()*this.hunger*aggression/500;
    }
}
