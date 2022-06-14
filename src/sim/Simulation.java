package sim;

import agents.*;
import agents.Character;

import java.util.*;


public class Simulation {
    static int Time;
    static int ForwardCheck;
    private static int[] population;
    private static Maps map;
    private static void RunSimulation() {
         int N;
        Random rng = new Random();
        // przekazanie z gui'a do N
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy:");
        N = s.nextInt();
        System.out.println("Podaj ile postaci z danego typu:");
        population = new int[8];
        population[0] = s.nextInt();
        System.out.println("Podaj ilość iteracji");
        int MaxTime = s.nextInt();
        ArrayList <Character> CharacterList = new ArrayList<>();
        Map<int[],Integer> Position;
        map = new Maps(N);

        // populate map with characters
        Populate(N, rng, CharacterList);
        // tu się kończy
        Position = frequency(CharacterList);

        Set<int[]> killingCopy;
        Set<int[]> breedingCopy;
        for(int i = 0;i<MaxTime;i++){
            killingCopy = Set.copyOf(Position.keySet());
            breedingCopy = Set.copyOf(Position.keySet());
            for(Character c:CharacterList){
                Dislocation(N, Position, c);
                c.Feeding(Time,map.fields[c.getPosition()[0]][c.getPosition()[1]].getMultiplier()[2]);
                KillingAndBreeding(CharacterList, Position, killingCopy, breedingCopy, c);
                if(c.getHunger()<=0){
                    c.FinalizeDeath();
                    CharacterList.remove(c);
                    int am = Position.get(c.getPosition());
                    if(am>1){
                        Position.put(c.getPosition(),am-1);
                        Position.remove(c.getPosition(),am);
                    }
                    else if(am==1)Position.remove(c.getPosition(),am);
                }
                c.BonusAction();
            }
        }
        Save();
    }

    private static void KillingAndBreeding(ArrayList<Character> CharacterList, Map<int[], Integer> Position, Set<int[]> killingCopy, Set<int[]> breedingCopy, Character c) {
        if(Position.get(c.getPosition()).equals(1)) return;
        if(Position.get(c.getPosition())>1 && killingCopy.contains(c.getPosition())){
            int k=0;
            if(c.getClass().isAssignableFrom(Duck.class)){
                while(k< CharacterList.size() &&
                        CharacterList.get(k).getPosition()!= c.getPosition() &&
                        CharacterList.get(k).getClass().isAssignableFrom(Human.class)) k++;
                c.Killing(CharacterList.get(k));
                killingCopy.remove(c.getPosition());
            }
            if(c.getClass().isAssignableFrom(Human.class)){
                while(k< CharacterList.size() &&
                        CharacterList.get(k).getPosition()!= c.getPosition() &&
                        CharacterList.get(k).getClass().isAssignableFrom(Duck.class)) k++;
                c.Killing(CharacterList.get(k));
                killingCopy.remove(c.getPosition());
            }
        }
        if(Position.get(c.getPosition())>1 && breedingCopy.contains(c.getPosition())){
            int b=0;
            if(c.getClass().isAssignableFrom(Duck.class)){
                while(b< CharacterList.size() &&
                        CharacterList.get(b).getPosition()!= c.getPosition() &&
                        CharacterList.get(b).getClass().isAssignableFrom(Duck.class)) b++;
                Character d = c.Breeding(CharacterList.get(b));
                CharacterList.add(d);
                breedingCopy.remove(c.getPosition());
                int a = Position.get(d.getPosition());
                Position.put(d.getPosition(),a+1);
                Position.remove(d.getPosition(),a);
            }
            if(c.getClass().isAssignableFrom(Human.class)){
                while(b< CharacterList.size() &&
                        CharacterList.get(b).getPosition()!= c.getPosition() &&
                        CharacterList.get(b).getClass().isAssignableFrom(Human.class)) b++;
                Character h = c.Breeding(CharacterList.get(b));
                CharacterList.add(h);
                breedingCopy.remove(c.getPosition());
                int a = Position.get(h.getPosition());
                Position.put(h.getPosition(),a+1);
                Position.remove(h.getPosition(),a);
            }
        }
    }

    private static void Dislocation(int N, Map<int[], Integer> Position, Character c) {
        int[] positionHolder;
        int[] multiplierHolder;
        int[] shift;
        int k;
        int[] newPositionHolder;
        positionHolder = c.getPosition();
        multiplierHolder = map.CheckTile(positionHolder);
        shift = c.Moving(multiplierHolder, N);
        k = Position.get(positionHolder);
        if(k>1){
            Position.put(positionHolder,k-1);
            Position.remove(positionHolder,k);
        }
        else if(k==1)Position.remove(positionHolder,k);
        newPositionHolder = new int[]{positionHolder[0]+shift[0],positionHolder[1]+shift[1]};
        if(Position.containsKey(newPositionHolder)){
            k = Position.get(newPositionHolder);
            Position.put(newPositionHolder,k+1);
            Position.remove(positionHolder,k);
        }
        else Position.put(newPositionHolder,1);
    }

    private static void Populate(int N, Random rng, ArrayList<Character> CharacterList) {
        int x,y;
        CharacterList.add(new AcientOne(N /2, N /2));
        for(int i=0;i < 8;i++){
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
    }

    private static Map<int[], Integer> frequency(ArrayList<Character> CharacterList) {
        Set<int[]> S = new HashSet<>();
        Map<int[], Integer> Position = new HashMap<>();
        CharacterList.forEach((n) -> S.add(n.getPosition()));
        S.forEach((n) -> Position.put(n,Collections.frequency(CharacterList,n)+1));
        return Position;
    }

    private static void Save() {
    }


    public static void main(String[] args) {
        RunSimulation();
    }
}
