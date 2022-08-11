import java.util.Random;

public class Fountain 
{
    boolean used;
    String buff;
    String[] buffs = {"+2 damage", "+1 dexterity", "-2 health", "nothing"};
    
    public Fountain()
    {
        Random random = new Random();
        switch(random.nextInt(1,11))
        {
            case 1,2 -> buff = buffs[0];
            case 3,4 -> buff = buffs[1];
            case 5,6 -> buff = buffs[2];
            default -> buff = buffs[3];
        }
        used = false;
    }
}
