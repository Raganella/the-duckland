package agents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Weakling extends Human{
    private final int breedingFactor;
    public Weakling(int x,int y) {
        super(50,1,1,new int[]{x,y});
        breedingFactor = new Random().nextInt(4);
    }

    @Override
    public Character Breeding(Character character) {

        Random r = new Random();
        try {
            Class<? extends Character> c;
            double a = r.nextDouble();
            if(a<0.7) c = character.getClass();
            else c = this.getClass();
            Constructor<?> cons = c.getConstructor(int.class, int.class);
            return (Human) cons.newInstance(position[0], position[1]);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public int getBreedingFactor() {
        return breedingFactor;
    }
}
