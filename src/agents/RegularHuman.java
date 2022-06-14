package agents;

import java.util.Random;

public class RegularHuman extends Human{
    private final double luck;
    public RegularHuman(int x,int y){
        super(50,3,3,new int[]{x,y});
        this.luck = new Random().nextDouble();
    }
    @Override
    public int[] Moving(int[] multipliers,int N){
        Random r = new Random();
        int _X = (int) ((multipliers[0]*(r.nextInt(20)-10)+this.fieldOfView/multipliers[1])%(this.mobility+1)+10*this.luck);
        int _Y = (int) ((multipliers[0]*(r.nextInt(20)-10)+this.fieldOfView/multipliers[1])%(this.mobility+1)+10*this.luck);
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        return new int[]{_X,_Y};
    }

    @Override
    public void Feeding(int time, int i) {

    }

    @Override
    public Character Breeding(Character character) {int i = 1;
        return character;
    }

    @Override
    public void Killing(Character character) {
        int i = 1;
    }

    @Override
    public void SurvivalRoll() {
        int i = 1;
    }

    @Override
    public void Tracking() {}
    int i = 1;
    @Override
    public void BonusAction() {int i = 1;}
}
