package agents;

public abstract class Human extends Character{
    static int numberOfHumans;
    public Human(int hunger, int mobility, int fieldOfView, int[] position) {
        super( hunger, mobility, fieldOfView, position);
    }
    // statystyki poszczególnych ludzi do przegadania
    public void Duckification() {
        int[] position = this.position;
        EnduckmentOne newDuck = new EnduckmentOne(this);
        // burza mózgów to szkic jak ma to działać, możliwe przeniesienie
    }
}
