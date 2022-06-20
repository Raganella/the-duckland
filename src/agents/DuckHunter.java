package agents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class DuckHunter extends Duck{
    public DuckHunter(int x,int y) {
        super(75,6,10,7, new int[]{x,y});
    }

    @Override
    public void Feeding(int time, int i) {
        if(time%6==1) this.hunger+=i*aggression;
        else this.hunger-= i /(aggression);
    }
    @Override
    public double SurvivalRoll() {
        return 0.8*this.hunger*aggression/800;
    }
    @Override
    public Character Breeding(Character character) {
        Random r = new Random();
        try {
            Class<? extends Character> c;
            double a = r.nextDouble();
            if(a<0.444) c = character.getClass();
            else c = this.getClass();
            Constructor<?> cons = c.getConstructor(int.class, int.class);
            return new Egg((Duck) cons.newInstance(position[0], position[1]));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void Moving(int[] multipliers, int N){
        Random r = new Random();
        int _X = (int) (2*r.nextInt()*(Math.pow(multipliers[0]+mobility,4)+(fieldOfView/multipliers[1])+aggression));
        int _Y = (int) (3*r.nextInt()*(Math.pow(multipliers[0]+mobility,4)+(fieldOfView/multipliers[1])+aggression));
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
    @Override
    public void Killing(Human character) {
        double surive = character.SurvivalRoll();
        double kill = Math.pow(new Random().nextDouble(),0.5)+Math.pow(aggression,2);
        int damage = (int) (kill-surive)*100;
        if(damage<0) this.hunger+=damage;
        else {
            this.hunger+=character.getHunger();
            character.setHunger(character.getHunger()-damage);
        }
    }
}
