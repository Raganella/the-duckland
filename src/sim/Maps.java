package sim;

public class Maps {
    Tile[][] fields;
    public Maps(int N) {
        this.fields = new Tile[N][N];// wersja wstępna


    }
    private String CheckTile(int x, int y) {
        return String.valueOf(this.fields[x][y].getClass());
    }

}
