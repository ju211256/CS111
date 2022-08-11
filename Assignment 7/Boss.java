import java.util.Random;

public class Boss 
{
    int damage;
    int health;
    int maxHealth;
    
    public Boss()
    {
        Random random = new Random();
        damage = random.nextInt(4, 9);
        health = random.nextInt(12, 25);
        maxHealth = health;
    }
}
