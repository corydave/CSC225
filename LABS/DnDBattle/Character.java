public abstract class Character {

    // Instance variables
    private String name;
    private String className;
    private int currentHealth;
    private int maxHealth;
    private int strength;
    private int defense;
    private int special;
    private int points;
    
    private boolean debug = true; // Will be changed to false during official battle
    
    private int[] myStats; //    [currentHealth, maxHealth, strength, defense, special, points]
    private int[] oppInfo; //    [currentHealth, maxHealth, strength, defense, special, points]   
    

    // Getters and setters
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public boolean getDebug() {
        return debug;
    }
    
    public int[] getMyInfo() {
        if (debug) {
            System.out.println(name + "'s stats have been given away");
        }
        return myStats;
    }
    
    public void setMyInfo(){
        myStats[0] = currentHealth;
        myStats[1] = maxHealth;
        myStats[2] = strength;
        myStats[3] = defense;
        myStats[4] = special;
        myStats[4] = points;
    }    

    // Other methods


    @Override
    public String toString() {
        return  "name='" + name +
                ", className=" + className +
                ", currentHealth=" + currentHealth +
                ", maxHealth=" + maxHealth +
                ", strength=" + strength +
                ", defense=" + defense +
                ", special=" + special +
                ", points=" + points;
    }

    public void loadOppInfo(int[] oppStats) {
        
        if (debug) {
            System.out.println("Opponents stats have been received.");
        }
        
        oppInfo = oppStats;
    }

    public abstract double attack();
    public abstract double defend();
    public abstract void specialEffect();
    



}








