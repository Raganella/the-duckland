package agents;

public class SentientOne extends Duck{
    public SentientOne(int x, int y) {
        super(100,0,-1,0,new int[]{x,y});
    }
    @Override
    public void BonusAction() {

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
    public int[] Moving(int[] multipliers, int N) {
        return new int[0];
    }
}
