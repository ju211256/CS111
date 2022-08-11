import java.util.Random;

public class Room 
{
    public boolean visitable;
    Monster monster;
    String type;
    Accessory accessory;
    Weapon weapon;
    Fountain fountain;
    Boss boss;

    public Room(String type)
    {
        this.type = type;
        switch(type)
        {
            case "N/A" -> 
            {
                visitable = false;
            }
            case "start" -> 
            {
                visitable = true;
                monster = null;
                accessory = null;
                weapon = null;
                fountain = null;
                boss = null;
            }
            case "monster" -> 
            {
                visitable = true;
                monster = new Monster();
                accessory = null;
                weapon = null;
                fountain = null;
                boss = null;
            }
            case "accessory" -> 
            {
                Random random = new Random();
                boolean end = false;
                while(!end)
                {
                    int index = random.nextInt(0,4);
                    if(!Dungeon.accessories[index])
                    {
                        accessory = new Accessory(index);
                        Dungeon.accessories[index] = true;
                        break;
                    }
                }
                visitable = true;
                monster = null;
                weapon = null;
                fountain = null;
                boss = null;
            }
            case "weapon" -> 
            {
                Random random = new Random();
                boolean end = false;
                while(!end)
                {
                    int index = random.nextInt(0,2);
                    if(!Dungeon.weapons[index])
                    {
                        weapon = new Weapon(index);
                        Dungeon.weapons[index] = true;
                        break;
                    }
                }
                visitable = true;
                monster = null;
                accessory = null;
                fountain = null;
                boss = null;
            }
            case "fountain" ->
            {
                visitable = true;
                monster = null;
                accessory = null;
                weapon = null;
                fountain = new Fountain() ;
                boss = null;
            }
            case "boss" -> 
            {
                visitable = true;
                monster = null;
                accessory = null;
                weapon = null;
                fountain = null;
                boss = new Boss();
            }
            case "empty" -> 
            {
                visitable = true;
                monster = null;
                accessory = null;
                weapon = null;
                fountain = null;
                boss = null;
            }
        }
    }
}
