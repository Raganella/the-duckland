package sim;
import java.util.List;
import agents.Character;

public abstract class Tile {
    private final int[] Multiplier;
    private List<Character> Characters;
    public Tile(int[] Multiplier) {
        this.Multiplier = Multiplier;
        // współczynniki dla poszczególnych tile do przegadania
    }
    private void AddCharacter(Character agent) {
        Characters.add(agent);
    }

    private void removeCharacter(Character agent) {
            Characters.remove(agent);
    }

    public int[] getMultiplier() {
        return Multiplier;
    }
    public List<Character> getCharacters() {
        return Characters;
    }
}
