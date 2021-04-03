import java.util.ArrayList;

public class Main {

    private static int smallKoboldNumber = 4;
    private static int bigKoboldNumber = 3;
    private static int mageKoboldNumber = 3;

    private static int numberOfSimulatedFights = 1000;

    public static void main (String[] args){
        FightStatistic statistic = new FightStatistic();

        for (int i = 0; i < numberOfSimulatedFights; i++){
            ArrayList<Character> playerCharacters = generateAllies();
            ArrayList<Character> enemyCharacters = generateEnemies(true, false, false);
            Fight currentFight = new Fight(playerCharacters, enemyCharacters);
            FightOutcome currentOutcome = currentFight.processFight();
            currentOutcome.calculateStatistics(statistic);
        }

        System.out.println();
        System.out.println("_______________________________________________________");
        System.out.println();
        statistic.generateReport();
    }


    private static ArrayList<Character> generateAllies(){
        Character barbarian = new Character(6, 2, 3, "Barbar");
        Character wizard = new Character(2, 6, 3, "Wizard");
        Character tallTalker = new Character(2, 3, 6, "Tall Talker");

        ArrayList<Character> playerCharacters = new ArrayList<Character>();
        playerCharacters.add(barbarian);
        playerCharacters.add(wizard);
        playerCharacters.add(tallTalker);
        return playerCharacters;
    }

    private static ArrayList<Character> generateEnemies (boolean small, boolean big, boolean mage){
        if (small){
            ArrayList<Character> smallKobolds = new ArrayList<Character>();
            for (int i = 0; i < smallKoboldNumber; i++){
                Character smallKobold = new Character(3, 3, 1, "Small Kobold "+(i+1));
                smallKobolds.add(smallKobold);
            }
            return smallKobolds;
        }

        if (big){
            ArrayList<Character> bigKobolds = new ArrayList<Character>();
            for (int i = 0; i < bigKoboldNumber; i++){
                Character bigKobold = new Character(5, 2, 1, "Big Kobold "+(i+1));
                bigKobolds.add(bigKobold);
            }
            return bigKobolds;
        }

        ArrayList<Character> mageKobolds = new ArrayList<Character>();
        for (int i = 0; i < mageKoboldNumber; i++){
            Character mageKobold = new Character(1, 6, 3, "Mage Kobold "+(i+1));
            mageKobolds.add(mageKobold);
        }
        return mageKobolds;
    }
}
