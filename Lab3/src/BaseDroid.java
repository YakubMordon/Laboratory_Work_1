public class BaseDroid {
    protected String name;
    protected int health;
    protected int damage;

    Boolean isAlive(){
        return health > 0;
    }

}
