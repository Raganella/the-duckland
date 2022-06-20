package agents;

import java.util.Random;

public class RegularHuman extends Human{
    private final double luck;
    public RegularHuman(int x,int y){
        super(50,3,3,new int[]{x,y});
        this.luck = new Random().nextDouble();
    }
    @Override
    public void Moving(int[] multipliers, int N){
        Random r = new Random();
        int _X = (int) ((multipliers[0]*(r.nextInt(20)-10)+this.fieldOfView/multipliers[1])%(this.mobility+1)+10*this.luck);
        int _Y = (int) ((multipliers[0]*(r.nextInt(20)-10)+this.fieldOfView/multipliers[1])%(this.mobility+1)+10*this.luck);
        this.position[0]= (this.position[0]+_X)%N;
        this.position[1]= (this.position[1]+_Y)%N;
        if(position[0]<0) position[0]=(N+position[0])%N;
        if(position[1]<0) position[1]=(N+position[1])%N;
    }
}
