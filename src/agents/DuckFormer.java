package agents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class DuckFormer extends Duck{
    public DuckFormer(int x, int y) {
        super(100,4,3,5,new int[]{x,y});
    }

    @Override
    public void Feeding(int time, int i) {
        if(time%8==1) this.hunger+=i*aggression;
        else this.hunger-= i /(aggression-1);
    }
    @Override
    public double SurvivalRoll() {
        return 0.5*this.hunger*aggression/300;
    }
    @Override
    public Character Breeding(Character character) {
        Random r = new Random();
        try {
            Class<? extends Character> c;
            double a = r.nextDouble();
            if(a<0.1) c = character.getClass();
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
        int _X = r.nextInt()*((multipliers[0]+mobility)*(multipliers[0]+mobility)+(fieldOfView/multipliers[1])*(fieldOfView/multipliers[1])+aggression);
        int _Y = r.nextInt()*((multipliers[0]+mobility)*(multipliers[0]+mobility)+(fieldOfView/multipliers[1])*(fieldOfView/multipliers[1])+aggression);
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
    @Override
    public void Killing(Human character) {
        double surive = character.SurvivalRoll();
        double kill = new Random().nextDouble()+(double)aggression;
        int damage = (int) (kill-surive)*70;
        if(damage<0) this.hunger+=damage;
        else {
            character.setHunger(character.getHunger()-damage);
            this.hunger+=character.getHunger()/2;
        }
    }
}
