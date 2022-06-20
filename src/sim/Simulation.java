package sim;

import agents.*;
import agents.Character;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;


public class Simulation {
    static int Time;
    static int ForwardCheck;
    private static Maps map;
    private static void RunSimulation() {
         int N;
        Random rng = new Random();
        // przekazanie z gui'a do N
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy:");
        N = s.nextInt();
        System.out.println("Podaj ile postaci z danego typu:");
        int[] population = new int[8];
        for(int i=0;i<8;i++){
        population[i] = s.nextInt();
        }
        System.out.println("Podaj ilość iteracji");
        int MaxTime = s.nextInt();
        ArrayList <Character> CharacterList = new ArrayList<>();
        map = new Maps(N);

        // populate map with characters
        Populate(N, rng, CharacterList, population);
        // tu się kończy
        int[][] Position = new int[N][N];
        frequency(CharacterList, Position);

        Set<String> killingCopy = new HashSet<>();
        Set<String> breedingCopy = new HashSet<>();
        for(int i = 0;i<MaxTime;i++){
            SetCopies(N, Position, killingCopy, breedingCopy);
            for (int j = 0; j < CharacterList.size(); j++) {
                Character c = CharacterList.get(j);
                Dislocation(N, Position, c);
                c.Feeding(Time, map.fields[c.getPosition()[0]][c.getPosition()[1]].getMultiplier()[2]);
                KillingAndBreeding(CharacterList, Position, killingCopy, breedingCopy, c);
                if (c.getHunger() <= 0) {
                    Position[c.getPosition()[0]][c.getPosition()[1]]--;
                    c.FinalizeDeath();
                    CharacterList.remove(c);
                }
                if (c.getClass() == Egg.class) {
                    CharacterList.remove(c);
                    CharacterList.add(c.BonusAction(null));
                }

            }
        Collections.shuffle(CharacterList);
        }
        Save();
        System.out.println(CharacterList.size());
    }

    private static void SetCopies(int N, int[][] Position, Set<String> killingCopy, Set<String> breedingCopy) {
        for(int j = 0; j< N; j++)
            for(int y = 0; y< N; y++) if(Position[j][y]!=0) {
                String s1 = j + "_" + y;
                killingCopy.add(s1);
                breedingCopy.add(s1);
            }
    }

    private static void KillingAndBreeding(ArrayList<Character> CharacterList,int[][] Position, Set<String> killingCopy, Set<String> breedingCopy, Character c) {
        String s1 = c.getPosition()[0]+"_"+c.getPosition()[1];
        if(Position[c.getPosition()[0]][c.getPosition()[1]] <= 1) return;
        if(Position[c.getPosition()[0]][c.getPosition()[1]]>1 && killingCopy.contains(s1)){
            int k=0;
            if(c.getClass().isAssignableFrom(Duck.class)){
                while(k< CharacterList.size() &&
                        CharacterList.get(k).getPosition()!= c.getPosition() &&
                        CharacterList.get(k).getClass().isAssignableFrom(Human.class)) k++;
                c.Killing(CharacterList.get(k));
                killingCopy.remove(s1);
            }
            if(c.getClass().isAssignableFrom(Human.class)){
                while(k< CharacterList.size() &&
                        CharacterList.get(k).getPosition()!= c.getPosition() &&
                        CharacterList.get(k).getClass().isAssignableFrom(Duck.class)) k++;
                c.Killing(CharacterList.get(k));
                killingCopy.remove(s1);
            }
        }
        if(Position[c.getPosition()[0]][c.getPosition()[1]]>1 && breedingCopy.contains(s1)){
            int b=0;
            if(c.getClass().isAssignableFrom(Duck.class) &&
                    !(c.getClass().equals(Egg.class) ||
                    c.getClass().equals(AcientOne.class) ||
                    c.getClass().equals(SentientOne.class))
            ){
                while(b<CharacterList.size() &&
                        CharacterList.get(b).getPosition()!= c.getPosition() &&
                        CharacterList.get(b).getClass().isAssignableFrom(Duck.class) &&
                        !(CharacterList.get(b).getClass().equals(Egg.class) ||
                                CharacterList.get(b).getClass().equals(AcientOne.class) ||
                                CharacterList.get(b).getClass().equals(SentientOne.class))
                ) b++;
                Character d = c.Breeding(CharacterList.get(b));
                CharacterList.add(d);
                breedingCopy.remove(s1);
                Position[d.getPosition()[0]][d.getPosition()[1]]++;
            }
            if(c.getClass().isAssignableFrom(Human.class)){
                while(b< CharacterList.size() &&
                        CharacterList.get(b).getPosition()!= c.getPosition() &&
                        CharacterList.get(b).getClass().isAssignableFrom(Human.class)) b++;
                Character h = c.Breeding(CharacterList.get(b));
                if(!c.getClass().equals(Weakling.class)) {
                    CharacterList.add(h);
                    breedingCopy.remove(s1);
                    Position[h.getPosition()[0]][h.getPosition()[1]]++;
                }
                else{
                    int bf = ((Weakling) c).getBreedingFactor();
                    for(int i =0;i<bf;i++) CharacterList.add(h);
                    breedingCopy.remove(s1);
                    Position[h.getPosition()[0]][h.getPosition()[1]]+=bf;
                }
            }

        }
        if(c.getClass()== AcientOne.class){
            int f = ((AcientOne) c).GetFollowers();
            Character e = c.Breeding(null);
            IntStream.range(0,f).forEach( (n) ->{
                CharacterList.add(e);
                Position[c.getPosition()[0]][c.getPosition()[1]]++;});
        }
    }

    private static void Dislocation(int N, int[][] Position, @NotNull Character c) {
        int[] positionHolder;
        int[] multiplierHolder;
        int[] newPositionHolder;
        positionHolder = new int[]{c.getPosition()[0],c.getPosition()[1]};
        multiplierHolder = map.CheckTile(positionHolder);
        c.Moving(multiplierHolder, N);
        newPositionHolder = c.getPosition();
        Position[positionHolder[0]][positionHolder[1]]--;
        Position[newPositionHolder[0]][newPositionHolder[1]]++;
    }

    private static void Populate(int N, Random rng, ArrayList<Character> CharacterList, int[] population) {
        int x,y;
        CharacterList.add(new AcientOne(N /2, N /2));
        for(int i=0;i < 8;i++)
            for(int j=0;j<population[i];j++){
                y = rng.nextInt(N);
                x = rng.nextInt(N);
                CharacterList.add(
                        switch(i) {
                            default -> new RegularHuman(x,y);
                            case 1 -> new Brute(x,y);
                            case 2 -> new Weakling(x,y);
                            case 3 -> new RegularDuck(x,y);
                            case 4 -> new DuckFormer(x,y);
                            case 5 -> new DuckHunter(x,y);
                            case 6 -> new EnduckmentOne(x,y);
                            case 7 -> new SentientOne(x,y);
                        }
                        );
             }
    }

    private static void frequency(ArrayList<Character> CharacterList, int[][] Position) {
        CharacterList.forEach( (n) -> Position[n.getPosition()[0]][n.getPosition()[1]]++);
    }

    private static void Save() {
    }


    public static void main(String[] args) {
        RunSimulation();
    }
}
