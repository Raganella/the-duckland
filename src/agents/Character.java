package agents;

public abstract class Character implements Behaviour{
    protected int hunger;
    protected int mobility;
    protected int[] position;
    protected int fieldOfView;
    public Character(int hunger, int mobility, int fieldOfView, int[] position) {
        this.fieldOfView = fieldOfView;
        this.mobility = mobility;
        this.hunger = hunger;
        this.position = position;
    }
    public final void FinalizeDeath() {
        this.hunger=0;
        this.mobility=0;
        this.fieldOfView=0;
        this.position = new int[]{-1,-1};
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int[] getPosition() {
        return position;
    }
}
