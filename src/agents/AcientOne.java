package agents;

import java.util.Random;

public class AcientOne extends Duck{
    protected static int prayers;
    private int followers =0;
    public AcientOne(int x,int y) {
        super(100,0,-1,1,new int[]{x,y});
    }
    @Override
    public double SurvivalRoll() {
        return 0.9+(double) prayers/100;
    }

    public int GetFollowers(){
        return followers;
    }
    @Override
    public void Feeding(int time, int i) {}
    @Override
    public Character Breeding(Character character){
        this.followers=0;
        return new EnduckmentOne(this.position[0],this.position[1]);
    }

    @Override
    public void Killing(Character character) {
        if(prayers>=10) {
            followers++;
            character.setHunger(-10);
            prayers-=10;
        }
        else{
            double surive = character.SurvivalRoll();
            double kill = new Random().nextDouble()+(double)aggression/5;
            int damage = (int) (kill-surive)*50;
            if(damage<0) this.hunger+=damage;
            else character.setHunger(character.getHunger()-damage);
        }
    }

    @Override
    public void Moving(int[] multipliers, int N) {
        Random r = new Random();
        int _X = 0;
        int _Y = 0;
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
}
