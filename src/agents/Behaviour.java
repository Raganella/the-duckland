package agents;

public interface Behaviour {
    void Feeding(int time, int i);
    Character Breeding(Character character);
    void Killing(Character character);
    void Killing(Human character);
    void Killing(Duck character);
    double SurvivalRoll();
    Duck BonusAction(Human h);
    void Moving(int[] multipliers, int N);
}
