package sim;

import java.util.Random;

public class Maps {
    protected Tile[][] fields;
    public Maps(int N) {
        this.fields = new Tile[N][N];// wersja wstępna
        Random r = new Random();
        int rng;
        for(int i = 0;i<N;i++)
            for(int j = 0;j<N;j++){
                rng = r.nextInt(4);
                switch (rng) {
                    case 1 -> fields[i][j] = new Forest();
                    case 2 -> fields[i][j] = new Mountain();
                    case 3 -> fields[i][j] = new Waterfield();
                    default -> fields[i][j] = new Grassland();
                }
            }

        }
    public int[] CheckTile(int[] position){
            return fields[position[0]][position[1]].getMultiplier();
        }

    public Tile[][] getFields() {
        return fields;
    }
}
