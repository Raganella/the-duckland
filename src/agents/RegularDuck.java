package agents;

public class RegularDuck extends Duck{
    public RegularDuck(int x,int y) {
        super(25,2,2,3,new int[]{x,y});
    }

    @Override
    public void Feeding(int time, int i) {

    }

    @Override
    public Character Breeding(Character character) {

        return character;
    }

    @Override
    public void Killing(Character character) {

    }

    @Override
    public void SurvivalRoll() {

    }

    @Override
    public void Tracking() {

    }

    @Override
    public void BonusAction() {

    }

    @Override
    public int[] Moving(int[] multiplier, int N) {
        return new int[0];
    }
}
