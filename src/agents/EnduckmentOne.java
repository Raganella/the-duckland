package agents;

public class EnduckmentOne extends Duck{
    public EnduckmentOne(int x,int y) {
        super(1,2,3,4, new int[]{x,y});
    }
    public EnduckmentOne(Human poorBoy){
        super(poorBoy.hunger, poorBoy.mobility, poorBoy.fieldOfView, 1, poorBoy.position);
    }
    @Override
    public void BonusAction() {
        //prey
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
