package agents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Human extends Character{
    static int numberOfHumans = 0;
    public Human(int hunger, int mobility, int fieldOfView, int[] position) {
        super( hunger, mobility, fieldOfView, position);
        numberOfHumans++;
    }

    @Override
    public void Moving(int[] multipliers, int N){
        Random r = new Random();
        int _X = (multipliers[0]*(r.nextInt(20)-10)+this.fieldOfView/multipliers[1])%(this.mobility+1);
        int _Y = (multipliers[0]*(r.nextInt(20)-10)+this.fieldOfView/multipliers[1])%(this.mobility+1);
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
    @Override
    public Character Breeding(Character character) {
        Random r = new Random();
        try {
            Class<? extends Character> c;
            double a = r.nextDouble();
            if(a<0.5) c = character.getClass();
            else c = this.getClass();
            Constructor<?> cons = c.getConstructor(int.class, int.class);
            return (Human) cons.newInstance(position[0], position[1]);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void Feeding(int time, int i) {
        if(time%3==1) this.hunger+=i;
        else this.hunger-=5;
    }

    @Override
    public void Killing(Character character) {
        double surive = character.SurvivalRoll();
        double kill = new Random().nextDouble();
        int damage = (int) (kill-surive)*50;
        if(damage<0) this.hunger+=damage;
        else character.setHunger(character.getHunger()-damage);
    }
    @Override
    public double SurvivalRoll() {
        return new Random().nextDouble()*this.hunger/100;
    }
    @Override
    public Duck BonusAction(Human h) {return null;}

    }
