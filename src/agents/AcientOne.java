package agents;

public class AcientOne extends Duck{
    private int prayers;
    public AcientOne(int x,int y) {
        super(100,0,-1,1,new int[]{x,y});
    }

    @Override
    public void BonusAction() {
        //asnwer prayers
    }

    @Override
    public void Killing(Character character) {
        //
    }

    @Override
    public void SurvivalRoll() {
        //
    }

    @Override
    public void Feeding(int time, int i) {}

    @Override
    public Character Breeding(Character character) {
        return character;
    }

    @Override
    public void Tracking() {}

    @Override
    public int[] Moving(int[] multipliers,int N) {
        return new int[]{0,0};
    }
}
