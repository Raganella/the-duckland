package agents;


import java.util.Random;

public class EnduckmentOne extends Duck{
    public EnduckmentOne(int x,int y) {
        super(75,2,3,2, new int[]{x,y});
    }
    @Override
    public double SurvivalRoll() {
        this.BonusAction(null);
        return new Random().nextDouble()*this.hunger*aggression/500;
    }
    public EnduckmentOne(Human poorBoy){
        super(poorBoy.hunger, poorBoy.mobility, poorBoy.fieldOfView, 1, poorBoy.position);
    }

    @Override
    public Duck BonusAction(Human h) {
        AcientOne.prayers++;
        return null;
    }
}
