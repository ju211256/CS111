public class Weapon 
{
    String type;
    String[] types = {"sword", "flamethrower"};
    int damageModifier;
    int dexModifier;
    
    public Weapon(int index)
    {
        type = types[index];
        if (index == 0)
        {
            dexModifier = 0;
            damageModifier = 3;
        }
        if (index == 1)
        {
            dexModifier = -2;
            damageModifier = 10;
        }
    }
}
