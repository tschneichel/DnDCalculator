import java.math.BigDecimal;
import java.math.MathContext;
import java.util.logging.*;

public class FightStatistic {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    int allyWins = 0;
    int enemyWins = 0;

    int totalHPLeftAllies = 0;
    int totalHPLeftEnemies = 0;

    int totalDeadAllies = 0;
    int totalDeadEnemies = 0;

    public FightStatistic(){
    }

    public void generateReport(){
        MathContext context = new MathContext(3);
        if (allyWins > 0){
            BigDecimal averageDead = BigDecimal.valueOf((double) totalDeadAllies / allyWins);
            averageDead = averageDead.round(context);
            BigDecimal averageHPLeft = BigDecimal.valueOf((double) totalHPLeftAllies / allyWins) ;
            averageHPLeft = averageHPLeft.round(context);
            System.out.println(ANSI_GREEN + "Allies won " + allyWins + " times." + ANSI_RESET);
            System.out.println(ANSI_GREEN + averageDead + " allies died on average. The surviving members had on average " + averageHPLeft  + " total HP left." + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_GREEN + "Allys NEVER won." + ANSI_RESET);
        }
        if (enemyWins > 0){
            BigDecimal averageDead = BigDecimal.valueOf((double) totalDeadEnemies / enemyWins);
            averageDead = averageDead.round(context);
            BigDecimal averageHPLeft = BigDecimal.valueOf((double) totalHPLeftEnemies / enemyWins) ;
            averageHPLeft = averageHPLeft.round(context);
            System.out.println(ANSI_RED + "Enemies won " + enemyWins + " times." + ANSI_RESET);
            System.out.println(ANSI_RED + averageDead + " enemies died on average. The surviving members had on average " + averageHPLeft + " total HP left." + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + "Enemies NEVER won." + ANSI_RESET);
        }

    }
}
