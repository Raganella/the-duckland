package agents;

public abstract class Duck extends Character{
    static int numberOfDucks;
    private static int aggression; // Do obgadania czy nie ustawić jako finalne pole w symulacji
    public Duck(int hunger, int mobility, int fieldOfView, int aggression, int[] position) {
        super(hunger,mobility,fieldOfView, position);
        Duck.aggression = aggression;
    }

    private <T extends Duck,V extends Duck> void ChangeClass(T duckBefore) {
        V newDuck;
        newDuck = new V();  // do obgadania i wyjaśnienia, szkielet działąnia, możliwe rozwiązanie: dopisanie konstruktora kopiującego
        duckBefore.FinalizeDeath();
    }
}
