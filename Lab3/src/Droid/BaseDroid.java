package Droid;

public class BaseDroid {
    protected String name;
    protected int health;
    protected int damage;

    public Boolean isAlive(){
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
