import java.util.ArrayList;

public class FightOutcome {

    public boolean alliesWon;
    public ArrayList<Character> survivingTeam;
    public int hpLeftTotal = 0;
    public int deadTotal = 0;

    public FightOutcome(boolean alliesWon, ArrayList<Character> survivingTeam){
        this.alliesWon = alliesWon;
        this.survivingTeam = survivingTeam;
    }

    public void calculateStatistics(FightStatistic statistic) {
        for (Character character : survivingTeam){
            if (character.isDead){
                deadTotal++;
            }
            else {
                hpLeftTotal += character.currentHitpoints;
            }
        }
        transferToFightStatistic(statistic);
    }

    public void transferToFightStatistic(FightStatistic statistic){
        if (alliesWon){
            statistic.allyWins++;
            statistic.totalHPLeftAllies += hpLeftTotal;
            statistic.totalDeadAllies += deadTotal;
        }
        else {
            statistic.enemyWins++;
            statistic.totalHPLeftEnemies += hpLeftTotal;
            statistic.totalDeadEnemies += deadTotal;
        }
    }
}
