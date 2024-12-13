//базовый класс, от которого наследуются все игровые объекты
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


//класс, описывающий юниты - подразделения, люди, рабочие и т.д., которых можно контролировать:
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
        System.out.println(name+" move to "+x+";"+y);
    }
 }


//два интерфейса: Attacker и Moveable
 interface Attacker {
    void attack(Unit unit);
 }

 interface Moveable {
     void moveTo(GameObject object, int x, int y);
 }


//класс, описывающий лучника: он реализует два интерфейса: Attacker и Moveable
class Archer extends Unit implements Attacker, Moveable {
    private final float power;

    public Archer(int id, String name, int x, int y, boolean alive, float hp, float power) {
        super(id, name, x, y, alive, hp);
        this.power=power;
    }

    @Override
    public void attack(Unit unit) {
        unit.receiveDamage(unit,power);
        System.out.println(name+" attacks "+ unit.name);
    }
    @Override
    public void moveTo(GameObject object, int x, int y){
        object.x=y;
        object.y=y;
        System.out.println(name+" move to "+x+";"+y);
    }
}


// класс, описывающий постройку
 class Building extends GameObject {
    private final boolean built;

    public Building(int id, String name, int x, int y, boolean built) {
        super(id, name, x, y);
        this.built = built;
    }

    public boolean isBuilt() {
        return built;
    }
}


//класс крепости, тоже реализует интерфейс Attacker (может из пушек стрелять в противников)
 class Fort extends Building implements Attacker {
    private final float power;

    public Fort(int id, String name, int x, int y, boolean built, float power) {
        super(id, name, x, y, built);
        this.power=power;
    }

    @Override
    public void attack(Unit unit) {
        unit.receiveDamage(unit,power);
        System.out.println(name+" attacks "+ unit.name);
    }
}


// класс дома на колёсах, реализует интерфейс Moveable
 class MobileHouse extends Building implements Moveable {
    private final int speed;
    public MobileHouse(int id, String name, int x, int y, boolean built, int speed) {
        super(id, name, x, y, built);
        this.speed=speed;
    }
     @Override
    public void moveTo(GameObject object, int x, int y) {
        object.x=x;
        object.y=y;
        System.out.println(name+" move to "+x+";"+y+", speed = "+speed);
    }
}



public class Main {
    public static void main(String[] args) {
        Unit npc = new Unit(1, "npc", 1, 1, true, 100);

        Archer archer = new Archer(2,"archer",2,2,true,100,50);

        MobileHouse house = new MobileHouse(3,"house",0,0,true,10);

        Fort fort = new Fort(4,"fort",3,3,true,100);

        System.out.println(npc.name+ " live status = "+npc.isAlive());
        System.out.println(npc.name+ " has " + npc.getHp()+ " hp");
        archer.attack(npc);
        System.out.println(npc.name+ " live status = "+npc.isAlive());
        System.out.println(npc.name+ " has " + npc.getHp()+ " hp");
        archer.attack(npc);
        System.out.println(npc.name+ " live status = "+npc.isAlive());
        System.out.println(archer.name+" live status = "+ archer.isAlive());
        System.out.println(archer.name+ " has " + archer.getHp()+ " hp");
        fort.attack(archer);
        System.out.println(archer.name+" live status = "+ archer.isAlive());
        house.moveTo(house,10,10);
    }
}

