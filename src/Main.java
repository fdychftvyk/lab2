abstract class GameObject {
    protected int id;
    protected String name;
    protected int x;
    protected int y;

    public GameObject(int id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
//    private static int generateRandomID() {
//        Random rand = new Random();
//        return rand.nextInt(101);
//    }
    public int getId() {
    return id;
}
    public String getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
 class Unit extends GameObject implements Moveable {
    private boolean alive;
    private float hp;

    public Unit(int id, String name, int x, int y, boolean alive, float hp) {
        super(id, name, x, y);
        this.alive = alive;
        this.hp = hp;
    }

    public boolean isAlive() {
        return alive;
    }
    public float getHp() {
        return hp;
    }

    public void receiveDamage(Unit unit,float damage) {
        if(isAlive()) unit.hp -= damage;
        if (hp<=0) alive=false;
    }
    @Override
    public void moveTo(GameObject object, int x, int y){
        object.x=x;
        object.y=y;
    }
 }
 interface Attacker {
    void attack(Unit unit);
 }
 interface Moveable {
     void moveTo(GameObject object, int x, int y);
 }
class Archer extends Unit implements Attacker, Moveable {
    private float power;

    public Archer(int id, String name, int x, int y, boolean alive, float hp, float power) {
        super(id, name, x, y, alive, hp);
        this.power=power;
    }

    @Override
    public void attack(Unit unit) {
        unit.receiveDamage(unit,power);
    }
    @Override
    public void moveTo(GameObject object, int x, int y){
        object.x=y;
        object.y=y;
    }
}
 class Building extends GameObject {
    private boolean built;

    public Building(int id, String name, int x, int y, boolean built) {
        super(id, name, x, y);
        this.built = built;
    }

    public boolean isBuilt() {
        return built;
    }
}
 class Fort extends Building implements Attacker {
    private float power;

    public Fort(int id, String name, int x, int y, boolean built, float power) {
        super(id, name, x, y, built);
        this.power=power;
    }

    @Override
    public void attack(Unit unit) {
        unit.receiveDamage(unit,power);
    }
}
 class MobileHouse extends Unit implements Moveable {
    private int speed;
    public MobileHouse(int id, String name, int x, int y, boolean alive, float hp, int speed) {
        super(id, name, x, y, alive, hp);
        this.speed=speed;
    }
     @Override
    public void moveTo(GameObject object, int x, int y) {
        object.x=x;
        object.y=y;
    }
}
public class Main {
    public static void main(String[] args) {
        Unit human1 = new Unit(1, "Dude", 1, 1, true, 100);
        Archer archer = new Archer(2,"vilian",2,2,true,100,50);
        System.out.println(human1.getHp());
        archer.attack(human1);
        System.out.println(human1.getHp());
        archer.attack(human1);
        System.out.println(human1.getHp());
        System.out.println(human1.isAlive());
    }
}

