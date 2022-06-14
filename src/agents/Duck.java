package agents;

import sim.Simulation;

public abstract class Duck extends Character{
    static int numberOfDucks;
    protected int aggression; // Do obgadania czy nie ustawić jako finalne pole w symulacji
    public Duck(int hunger, int mobility, int fieldOfView, int aggression, int[] position) {
        super(hunger,mobility,fieldOfView, position);
        this.aggression = aggression;
    }
    public Duck(Duck duck){
        super(duck.hunger,duck.mobility,duck.fieldOfView,duck.position);
        this.aggression = duck.aggression;
    }
}
