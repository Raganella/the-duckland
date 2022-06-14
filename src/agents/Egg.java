package agents;

public class Egg extends Duck{

    private final Duck offspring;
    private int TimeToHatch = 2;
    public Egg(Duck duck) {
        super(10,0,0,0, duck.position);
        this.offspring = duck;
    }
    @Override
    public int[] Moving(int[] multipliers, int N) {
        this.TimeToHatch--;
        return new int[]{0,0};
    }
    @Override
    public void SurvivalRoll() {
        //
    }

    @Override
    public void BonusAction() {
    }

    @Override
    public void Feeding(int time, int i) {}

    @Override
    public Character Breeding(Character character) {
        return character;
    }

    @Override
    public void Killing(Character character) {}

    @Override
    public void Tracking() {}

    public Duck getOffspring() {
        return offspring;
    }
}
