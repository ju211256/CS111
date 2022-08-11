import java.util.Random;

public class Monster 
{
    int damage;
    int health;
    int maxHealth;

    public Monster()
    {
        Random random = new Random();

        damage = random.nextInt(1,5);
        health = random.nextInt(5,11);
        maxHealth = health;
    }
}
