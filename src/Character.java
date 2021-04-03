public class Character {

    public int body;
    public int mind;
    public int charisma;
    public String name;
    public int maxHitpoints;
    public int currentHitpoints;
    public boolean isDead = false;
    public int initiative;

    public Character(int body, int mind, int charisma, String name){
        this.body = body;
        this.mind = mind;
        this.charisma = charisma;
        this.name = name;
        this.currentHitpoints = 10 + body;
        this.maxHitpoints = currentHitpoints;
    }

    public void processFightTurn(){

    }

    public void takeDamage(int damage){
        this.currentHitpoints -= damage;
        System.out.println("Character " + name + " takes " + damage + " damage!");
        checkDead();
    }

    public void checkDead(){
        if (this.currentHitpoints < 1){
            isDead = true;
            System.out.println("Character " + this.name + " has died.");
            System.out.println();
        }
        else {
            System.out.println("Character " + this.name + " has " + this.currentHitpoints + " hitpoints left.");
            System.out.println();
        }
    }

    public void setInitiative(int initiative){
        this.initiative = initiative;
    }
}
