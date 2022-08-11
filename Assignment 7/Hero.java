import java.util.ArrayList;
import java.util.Random;

public class Hero 
{
    ArrayList<Accessory> inventory;
    Weapon weapon;
    int items;
    int health;
    int damage;
    int dexterity;
    int maxHealth;

    public Hero()
    {
        Random random = new Random();
        weapon = null;
        items = 0;
        health = random.nextInt(10,21);
        damage = random.nextInt(1,7);
        dexterity = random.nextInt(3,9);
        maxHealth = health;
        inventory = new ArrayList<>();
    }
}
