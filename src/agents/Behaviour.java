package agents;

public interface Behaviour {
    void Feeding(int time, int i);
    Character Breeding(Character character);
    void Killing(Character character);
    void SurvivalRoll();
    void Tracking();
    void BonusAction();
    int[] Moving(int[] multipliers,int N);
}
