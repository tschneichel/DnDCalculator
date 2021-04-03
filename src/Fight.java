import java.util.ArrayList;
import java.util.Random;

public class Fight {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private ArrayList<Character> alliedForces;
    private ArrayList<Character> enemyForces;
    private ArrayList<Character> allCharacters = new ArrayList<Character>();
    private ArrayList<Character> fightingOrder;
    private Random RNGGenerator = new Random();
    private boolean alliesDead = false;
    private boolean enemiesDead = false;

    public Fight(ArrayList<Character> alliedForces, ArrayList<Character> enemyForces){
        this.alliedForces = alliedForces;
        this.enemyForces = enemyForces;
        allCharacters.addAll(alliedForces);
        allCharacters.addAll(enemyForces);
    }

    public int rollD6xTimes(int times){
        int result = 0;
        for (int i = 1; i <= times; i++){
            result+= RNGGenerator.nextInt(6)+1;
        }
        return result;
    }

    public FightOutcome processFight(){
        this.fightingOrder = new ArrayList<Character>();
        for (Character character : allCharacters){
            int initiative = character.body + character.mind + rollD6xTimes(2);
            character.setInitiative(initiative);
        }
        sortByInitiative(allCharacters);
        int roundCounter = 0;
        while (! alliesDead && ! enemiesDead){
            roundCounter++;
            processFightTurn();
            System.out.println("___________________________________________________________________");
            System.out.println();
            checkAllDead();
        }
        System.out.println("Fight ended after " + roundCounter +  " rounds.");
        return processFightOutcome();
    }

    private FightOutcome processFightOutcome() {
        printFightOutcome();
        if (alliesDead){
            return new FightOutcome(false, enemyForces);
        }
        return new FightOutcome(true, alliedForces);
    }

    private void printFightOutcome() {
        if (alliesDead){
            System.out.println(ANSI_RED + "Oh no! The heroes have been wiped out! \n Surviving enemies:" + ANSI_RESET);
            for (int i = 0; i < enemyForces.size(); i++){
                if (! enemyForces.get(i).isDead){
                    System.out.println(enemyForces.get(i).name + " lives with " + enemyForces.get(i).currentHitpoints + " left from " + enemyForces.get(i).maxHitpoints + ".");
                }
            }
        }
        if (enemiesDead){
            System.out.println(ANSI_GREEN + "The heroes emerged victorious!" + ANSI_RESET +  "\nSurviving heroes:");
            for (int i = 0; i < alliedForces.size(); i++){
                if (! alliedForces.get(i).isDead){
                    System.out.println(alliedForces.get(i).name + " lives with " + alliedForces.get(i).currentHitpoints + " hitpoints left from " + alliedForces.get(i).maxHitpoints + ".");
                }
            }
        }
    }

    private void checkAllDead() {
        alliesDead = true;
        enemiesDead = true;
        for (Character character : alliedForces){
            if (!character.isDead){
                alliesDead = false;
            }
        }
        for (Character character : enemyForces){
            if (!character.isDead){
                enemiesDead = false;
            }
        }
    }

    public void processFightTurn(){
        for (Character character : allCharacters){
            if (! character.isDead && ! alliesDead && ! enemiesDead){
                Character opponent;
                if (alliedForces.contains(character)) {
                    opponent = pickRandomOpponent(enemyForces);
                }
                else {
                    opponent = pickRandomOpponent(alliedForces);
                }
                attack(character, opponent, character.mind > character.body);
            }
            checkAllDead();
        }
    }

    public Character pickRandomOpponent(ArrayList<Character> possibleOpponents){
        Character opponent = null;
        while (opponent == null){
            opponent = possibleOpponents.get(RNGGenerator.nextInt(possibleOpponents.size()));
            if (!opponent.isDead){
                return opponent;
            }
            else {
                opponent = null;
            }
        }
        return null;
    }

    public void sortByInitiative(ArrayList<Character> characters){
        Character tempChar = null;
        int highestPosition = 0;
        for (int j = 0; j < characters.size(); j++){
            for (int i = j; i < characters.size(); i++){
                if (tempChar == null || tempChar.initiative < characters.get(i).initiative) {
                    tempChar = characters.get(i);
                    highestPosition = i;
                }
            }
            characters.set(highestPosition, characters.get(j));
            characters.set(j, tempChar);
            tempChar = null;
        }
    }

    public void attack (Character attacker, Character defender, boolean magicAttack){
        int attackerValue;
        int defenderValue;
        if (magicAttack){
            attackerValue = attacker.mind;
            defenderValue = defender.mind;
            System.out.println(ANSI_BLUE + "Attacker " + attacker.name + " attacks " + defender.name + " using a magic attack!" + ANSI_RESET);
        }
        else {
            attackerValue = attacker.body;
            defenderValue = defender.body;
            System.out.println(ANSI_RED + "Attacker " + attacker.name + " attacks " + defender.name + " using a physical attack!" + ANSI_RESET);
        }
        attackerValue += rollD6xTimes(1);
        defenderValue += rollD6xTimes(1);
        System.out.println("ATTACK: " +attackerValue);
        System.out.println("DEFENSE: " +defenderValue);
        if (attackerValue > defenderValue){
            defender.takeDamage(attackerValue - defenderValue);
        }
        else {
            System.out.println("Defender " + defender.name + " withstands the attack of " + attacker.name + "!");
        }
    }
}
