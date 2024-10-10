public class Droid {
    private String name;
    private int health;
    private int damage;
    private String type;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void attack(Droid enemy){
        System.out.println(this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " шкоди");
        enemy.setHealth(enemy.getHealth() - this.damage);
    }
}

