package agents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Duck extends Character{
    static int numberOfDucks = 0;
    protected int aggression; // Do obgadania czy nie ustawić jako finalne pole w symulacji
    public Duck(int hunger, int mobility, int fieldOfView, int aggression, int[] position) {
        super(hunger,mobility,fieldOfView, position);
        this.aggression = aggression;
        numberOfDucks++;
    }
    public Duck(Duck duck){
        super(duck.hunger,duck.mobility,duck.fieldOfView,duck.position);
        this.aggression = duck.aggression;
    }
    public Duck(Human human){
        super(human.hunger, human.mobility, human.fieldOfView, human.position);
        this.aggression = 2;
    }
    @Override
    public void Feeding(int time, int i) {
        if(time%3==1) this.hunger+=i+aggression;
        else this.hunger-=i;
    }
    @Override
    public double SurvivalRoll() {
        return new Random().nextDouble()*this.hunger*aggression/500;
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
            return new Egg((Duck) cons.newInstance(position[0], position[1]));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void Moving(int[] multipliers, int N){
        Random r = new Random();
        int _X = r.nextInt()*((multipliers[0]+mobility)*(multipliers[0]+mobility)-fieldOfView/multipliers[1])+aggression/N;
        int _Y = r.nextInt()*((multipliers[0]+mobility)*(multipliers[0]+mobility)-fieldOfView/multipliers[1])+aggression/N;
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
    @Override
    public void Killing(Human character) {
        double surive = character.SurvivalRoll();
        double kill = new Random().nextDouble()+(double)aggression/5;
        int damage = (int) (kill-surive)*50;
        if(damage<0) this.hunger+=damage;
        else character.setHunger(character.getHunger()-damage);
    }
    @Override
    public void Killing(Duck duck){}
    @Override
    public void Killing(Character c){}
    @Override
    public Duck BonusAction(Human h){return null;}
}

