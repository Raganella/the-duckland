package agents;

public class DuckHunter extends Duck{
    public DuckHunter(int x,int y) {
        super(1,2,3,4, new int[]{x,y});
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
    public int[] Moving(int[] multipliers, int N) {
        return new int[0];
    }
}
