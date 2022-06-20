package agents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Brute extends Human{
    private final int might;
    public Brute(int x, int y) {
        super(100,5,3,new int[]{x,y});
        might = new Random().nextInt(10);
    }

    @Override
    public void Feeding(int time, int i) {
        if(time%4==1) this.hunger+=i+might;
        else this.hunger-=i;
    }

    @Override
    public Character Breeding(Character character) {
        Random r = new Random();
        try {
            Class<? extends Character> c;
            double a = r.nextDouble();
            if(a<0.3) c = character.getClass();
            else c = this.getClass();
            Constructor<?> cons = c.getConstructor(int.class, int.class);
            return (Human) cons.newInstance(position[0], position[1]);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void Killing(Character character) {
        double surive = character.SurvivalRoll();
        double kill = new Random().nextDouble();
        int damage = (int) (kill-surive/might)*50;
        if(damage<0) this.hunger+=damage;
        else {
            character.setHunger(character.getHunger() - damage);
            this.hunger+= damage /might;
        }
    }
    public double SurvivalRoll() {
        return new Random().nextDouble()*this.hunger*might/100;
    }

    @Override
    public void Moving(int[] multipliers, int N) {
        Random r = new Random();
        int _X = 0;
        int _Y = 0;
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
}
