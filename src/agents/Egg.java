package agents;

import java.util.Random;

public class Egg extends Duck{

    private final Duck offspring;
    private int TimeToHatch = 2;
    public Egg(Duck duck) {
        super(40,0,0,0, duck.position);
        this.offspring = duck;
    }
    @Override
    public void Moving(int[] multipliers, int N) {
        this.TimeToHatch--;
        Random r = new Random();
        int _X = 0;
        int _Y = 0;
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
    @Override
    public double SurvivalRoll() {
        return 0.1;
    }

    @Override
    public Duck BonusAction(Human h) {
        if(TimeToHatch==0){
            this.FinalizeDeath();
            return offspring;
        }
        else return null;
    }

    @Override
    public void Feeding(int time, int i) {}

    @Override
    public Character Breeding(Character character) {
        return null;
    }
    @Override
    public void Killing(Character character) {}

    public Duck getOffspring() {
        return offspring;
    }
}
