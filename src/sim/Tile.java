package sim;
import java.util.List;
import agents.Character;

public abstract class Tile {
    private final int[] Multiplier;
    public Tile(int[] Multiplier) {
        this.Multiplier = Multiplier;
        // współczynniki dla poszczególnych tile do przegadania
    }

    public int[] getMultiplier() {
        return Multiplier;
    }
}
