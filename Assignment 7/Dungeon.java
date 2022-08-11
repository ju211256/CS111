import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    public static int turns = 0;
    public static Scanner scanner = new Scanner(System.in);
    public static int x = 2; public static int y = 4;

    public static boolean[] accessories = {false,false,false,false};
    public static boolean[] weapons = {false,false};

    public static Random random = new Random();

    public static Room[][] grid = {{new Room("N/A"),new Room("N/A"),new Room("boss"),new Room("N/A"),new Room("N/A")},
            {new Room("N/A"),new Room("accessory"),new Room("monster"),new Room("accessory"),new Room("N/A")},
            {new Room("weapon"),new Room("monster"),new Room("fountain"),new Room("monster"),new Room("weapon")},
            {new Room("N/A"),new Room("accessory"), new Room("monster"),new Room("accessory"),new Room("N/A")},
            {new Room("N/A"),new Room("N/A"),new Room("start"),new Room("N/A"),new Room("N/A")}};

    public static String possibleMoves()
    {
        return "The possible actions are:\n" + ((y - 1 >= 0 && grid[y - 1][x].visitable) ? "North: " + grid[y - 1][x].type + " room\n"  : "") + ((x + 1 <= 4 && grid[y][x + 1].visitable) ? "East: " + grid[y][x + 1].type + " room\n" : "") 
                                             + ((y + 1 <= 4 && grid[y + 1][x].visitable) ? "South: " + grid[y + 1][x].type + " room\n" : "") + ((x - 1 >= 0 && grid[y][x - 1].visitable) ? "West: " + grid[y][x - 1].type + " room\n" : "") 
                                             + (grid[y][x].type.equals("accessory") ? "Item: get " + grid[y][x].accessory.type : "") + (grid[y][x].type.equals("weapon") ? "Item: get " + grid[y][x].weapon.type : "");
    }

    public static void playerMove(String action, Hero hero)
    {
        switch(action.split(" ")[0]){
            case "\n", "" -> 
            {
                playerMove(scanner.nextLine(), hero);
            }
            case "n" -> 
            {
                if(y - 1 >= 0 && grid[y - 1][x].visitable)
                {
                    if(grid[y - 1][x].type.equals("boss") && hero.inventory.contains(new Accessory("key")))
                    {
                        y--;
                        System.out.println("\nYou walk upon sinisterly large doors, with a void key hole molded with rusted metal. Remembering the key you picked up earlier, you slide it into the lock, and push forward.");
                        turns++;
                    } else if (grid[y - 1][x].type.equals("boss") && !hero.inventory.contains(new Accessory("key"))) 
                    {
                        System.out.println("The ill-omened large doors remain locked shut. Perhaps you will need a key to get past this door...");
                    }
                    else 
                    {
                        y--;
                        System.out.println("\nYou have moved north.");
                        turns++;
                    }
                }
                else 
                {
                    System.out.println("Invalid direction. Please input new action: ");
                    System.out.println(possibleMoves());
                    playerMove(scanner.nextLine(), hero);
                }
            }
            case "s" -> 
            {
                if(y + 1 <= 4 && grid[y + 1][x].visitable)
                {
                    y++;
                    System.out.println("\nYou have moved south.");
                    turns++;
                }
                else 
                {
                    System.out.println("Invalid direction. Please input new action: ");
                    System.out.println(possibleMoves());
                    playerMove(scanner.nextLine(), hero);
                }
            }
            case "e" -> 
            {
                if(x + 1 <= 4 && grid[y][x + 1].visitable)
                {
                    x++;
                    System.out.println("\nYou have moved east.");
                    turns++;
                }
                else 
                {
                    System.out.println("Invalid direction. Please input new action: ");
                    System.out.println(possibleMoves());
                    playerMove(scanner.nextLine(), hero);
                }
            }
            case "w" -> 
            {
                if(x - 1 >= 0 && grid[y][x - 1].visitable)
                {
                    x--;
                    System.out.println("\nYou have moved west.");
                    turns++;
                }
                else 
                {
                    System.out.println("Invalid direction. Please input new action: ");
                    System.out.println(possibleMoves());
                    playerMove(scanner.nextLine(), hero);
                }
            }
            case "get" -> 
            {
                if(grid[y][x].accessory == null)
                System.out.println("There is nothing in this room.");
                else if(action.split(" ")[1].equals(grid[y][x].accessory.type))
                {
                    if(hero.inventory.size() < 3)
                    {
                        System.out.println("You have acquired a " + grid[y][x].accessory.type);
                        hero.inventory.add(new Accessory(grid[y][x].accessory.type));
                        grid[y][x].accessory = null;
                        grid[y][x].type = "empty";
                    }
                    else System.out.println("The hero's inventory is full! Please drop an item using drop <item>!");
                }
            }
            case "drop" -> 
            {
                if(action.split(" ").length != 2)
                {
                    System.out.println("Incorrect action, please use drop <item> or try another command.");
                    playerMove(scanner.nextLine(), hero);
                }
                else if(hero.inventory.contains(new Accessory(action.split(" ")[1])))
                {
                    if(grid[y][x].accessory == null) 
                    {
                        hero.inventory.removeIf(a -> a.type.equals(action.split(" ")[1]));
                        System.out.println("You have dropped a(n) " + action.split(" ")[1]);
                        grid[y][x].accessory = new Accessory(action.split(" ")[1]);
                        grid[y][x].type = "accessory";
                    }
                    else System.out.println("You cannot drop an accessory into a room that already contains one, as it will increase your chance to trip while navigating through the dungeon.");
                }
                else System.out.println("You do not have a(n) <" + action.split(" ")[1] + ">");
            }
            default -> 
            {
                System.out.println("Invalid direction. Please input new action: ");
                System.out.println(possibleMoves());
                playerMove(scanner.nextLine(), hero);
            }
        }
    }

    public static void battle(Hero hero)
    {
        if(grid[y][x].monster.health != grid[y][x].monster.maxHealth) System.out.println("The monster seems wounded from your previous battle with it.");
        String[] attackLines = {"The monster swings towards you at great speed.", "The monster charges at you like a raging bull.", "The monster strikes at you with it's club."};
        boolean enemyBlock = false;
        boolean playerDodge = false;
        while(hero.health > 0 && grid[y][x].monster.health > 0) 
        {
            if(hero.inventory.contains(new Accessory("potion")))
            {
                if(hero.health != hero.maxHealth){
                    System.out.println("You drink a bit of the potion. Current health: " + hero.health);
                    hero.health += 1;
                }
                else System.out.println("You are already at max health.");
            }
            System.out.println("Your turn:\nYou may attack, dodge, or run from battle.");
            String input = scanner.nextLine();
            while(!input.equals("attack") && !input.equals("dodge") && !input.equals("run"))
            {
                System.out.println("This is not a valid action. You can attack, dodge, or run.");
                input = scanner.nextLine();
            }
            switch (input) 
            {
                case "attack" -> 
                {
                    if (enemyBlock) 
                    {
                        System.out.println("The monster blocks and reduces some of the damage taken!");
                        int damage = (hero.damage + (hero.weapon != null ? hero.weapon.damageModifier : 0))/2;
                        grid[y][x].monster.health -= damage;
                        System.out.println("You do " + damage + " damage. The monster now has " + Math.max(grid[y][x].monster.health, 0) + " health points remaining.");
                        enemyBlock = false;
                    }
                    else 
                    {
                        int damage = (hero.damage + (hero.weapon != null ? hero.weapon.damageModifier : 0));
                        grid[y][x].monster.health -= damage;
                        System.out.println("You do " + damage + " damage. The monster now has " + Math.max(grid[y][x].monster.health, 0) + " health points remaining.");
                    }
                    playerDodge = false;
                }
                case "dodge" -> 
                {
                    System.out.println("You prepare to dodge the next attack!");
                    playerDodge = true;
                }

                case "run" -> 
                {
                    System.out.println("You try to flee from battle...");
                    if(random.nextDouble(1,11) <= hero.dexterity + (hero.weapon != null ? hero.weapon.dexModifier : 0) + (hero.inventory.contains(new Accessory("ring")) ? 1 : 0)){
                        System.out.println("You successfully flee from battle. You live to die another day...");
                        return;
                    }
                    else {
                        System.out.println("You failed to run away. Fight for your life...");
                    }
                }
            }

            if(grid[y][x].monster.health > 0 && random.nextInt(1,101) > 30)
            {
                enemyBlock = false;
                System.out.println(attackLines[random.nextInt(0,3)]);
                if(playerDodge && random.nextDouble(1,11) <= hero.dexterity + (hero.weapon != null ? hero.weapon.dexModifier : 0) + (hero.inventory.contains(new Accessory("ring")) ? 1 : 0))
                {
                    System.out.println("You successfully dodge the monster's attack!");
                }
                else 
                {
                    if(playerDodge) System.out.println("You fail to dodge the monster's attack.");
                    int damage = grid[y][x].monster.damage;
                    if(hero.inventory.contains(new Accessory("shield"))){
                        System.out.println("You shield blocks some of the damage.");
                        damage -= 1;
                    }
                    System.out.println("The hero takes " + damage + " damage.");
                    hero.health -= damage;
                }
            }
            else if(grid[y][x].monster.health > 0)
            {
                System.out.println("The monster braces itself against you");
                enemyBlock = true;
            }
        }

        if(hero.health <= 0)
        {
            System.out.println("You have died.");
            System.exit(1);
        }
        else
        {
            System.out.println("You have defeated the monster. You may move on...");
            grid[y][x].type = "empty";
            grid[y][x].monster = null;
        }
    }

    public static void bossBattle(Hero hero)
    {
        if(grid[y][x].boss.health != grid[y][x].boss.maxHealth)
        {
            System.out.println("The wizard seems to have healed up since you last fought him.");
            grid[y][x].boss.health = grid[y][x].boss.maxHealth;
        }
        String[] attackLines = {"The wizard charges up an electric spear and hurls it towards you at great speed.", "The wizard blasts rocks down from above you.", "The wizard hurls a pyro blast at you."};
        boolean enemyBlock = false;
        boolean playerDodge = false;
        while(hero.health > 0 && grid[y][x].boss.health > 0) 
        {
            if(hero.inventory.contains(new Accessory("potion"))){
                if(hero.health != hero.maxHealth){
                    System.out.println("You drink a bit of the potion. Current health: " + hero.health);
                    hero.health += 1;
                }
                else System.out.println("You are already at max health.");
            }
            System.out.println("Your turn:\nYou may attack, dodge, or run from battle.");
            String input = scanner.nextLine();
            while(!input.equals("attack") && !input.equals("dodge") && !input.equals("run")){
                System.out.println("This is not a valid action. You can attack, dodge, or run.");
                input = scanner.nextLine();
            }
            switch (input) {
                case "attack" -> 
                {
                    if (enemyBlock) 
                    {
                        System.out.println("The wizard's shadow shield absorbs damage the wizard would have taken!");
                        int damage = (hero.damage + (hero.weapon != null ? hero.weapon.damageModifier : 0))/2;
                        grid[y][x].boss.health -= damage;
                        System.out.println("You do " + damage + " damage. The wizard now has " + Math.max(grid[y][x].boss.health, 0) + " health points remaining.");
                        enemyBlock = false;
                    }
                    else {
                        int damage = (hero.damage + (hero.weapon != null ? hero.weapon.damageModifier : 0));
                        grid[y][x].boss.health -= damage;
                        System.out.println("You do " + damage + " damage. The wizard now has " + Math.max(grid[y][x].boss.health, 0) + " health points remaining.");
                    }
                    playerDodge = false;
                }
                case "dodge" -> 
                {
                    System.out.println("You prepare to dodge the next attack!");
                    playerDodge = true;
                }

                case "run" -> 
                {
                    System.out.println("You try to flee from battle...");
                    if(random.nextDouble(1,11) <= hero.dexterity + (hero.weapon != null ? hero.weapon.dexModifier : 0)+ (hero.inventory.contains(new Accessory("ring")) ? 1 : 0))
                    {
                        System.out.println("You successfully flee. You live to die another day...");
                        return;
                    }
                    else 
                    {
                        System.out.println("You failed to run away. Battle until your last breath!");
                    }
                }
            }
            if(grid[y][x].boss.health > 0 && random.nextInt(1,101) > 30)
            {
                enemyBlock = false;
                System.out.println(attackLines[random.nextInt(0,3)]);
                if(playerDodge && random.nextDouble(1,11) <= hero.dexterity + (hero.weapon != null ? hero.weapon.dexModifier : 0)+ (hero.inventory.contains(new Accessory("ring")) ? 1 : 0))
                {
                    System.out.println("You successfully evade the wizard's spell!");
                }
                else 
                {
                    if(playerDodge) System.out.println("You fail to evade the wizard's spell.");
                    int damage = grid[y][x].boss.damage;
                    if(hero.inventory.contains(new Accessory("shield"))){
                        System.out.println("You shield blocks some of the damage.");
                        damage -= 1;
                    }
                    System.out.println("The hero takes " + damage + " damage.");
                    hero.health -= damage;
                }
            }
            else if(grid[y][x].boss.health > 0)
            {
                System.out.println("The wizard cloaks itself with void shadows.");
                enemyBlock = true;
            }
        }
        if(hero.health <= 0)
        {
            System.out.println("You have perished at the hands of the evil wizard.");
            System.exit(1);
        }
        else
        {
            System.out.println("You have defeated the wizard. You can finally rest...");
            System.exit(1);
        }
    }

    public static void main(String[] args)
    {
        Hero hero = new Hero();
        System.out.println("Ruin, has come to your estate.\nA damnated, antediluvian evil srpung from the darkness of the estate hid itself in a dungeon.\nYou, the lord, must put and end to it.\nReturn home, claim your birthright, and deliver your people from the ravenous clutching shadows of the dark wizard!");
        while(hero.health > 0 && grid[0][2].boss.health > 0)
        {
            System.out.println("Choose a direction to explore: (n,e,s,w), drop an item: drop <item>, or get an item: get <item>");
            System.out.println(possibleMoves());
            playerMove(scanner.nextLine(), hero);
            switch (grid[y][x].type) 
            {
                case "empty" -> System.out.println("The room is cleared. Please continue forth.");
                case "monster" -> 
                {
                    System.out.println("You meet face to face with a monster. The battle begins!");
                    battle(hero);
                }
                case "accessory" -> 
                {
                    System.out.println("You are in an accessory room! Use the get <item> action to pick it up.");
                }
                case "weapon" -> 
                {
                    System.out.println("You walk into the room, and find a weapon on a pedestal. The weapon appears to be a " + grid[y][x].weapon.type + ". Do you want to pick it up? (y/n)");
                    System.out.println("Your current weapon is " + (hero.weapon != null ? "a " + hero.weapon.type + "." : "your fists."));
                    boolean choice = false;
                    while(!choice) {
                        switch (scanner.nextLine()) 
                        {
                            case "y" -> 
                            {
                                System.out.println("You have decided to pick up the new weapon.");
                                choice = true;
                                if(hero.weapon != null)
                                {
                                    System.out.println("You will leave your old weapon here.");
                                    Weapon temp = hero.weapon;
                                    hero.weapon = grid[y][x].weapon;
                                    grid[y][x].weapon = temp;
                                }
                                else 
                                {
                                    hero.weapon = grid[y][x].weapon;
                                    grid[y][x].weapon = null;
                                    grid[y][x].type = "empty";
                                }
                            }
                            case "n" -> 
                            {
                                System.out.println("You have decided to keep what you have.");
                                choice = true;
                            }
                            case "", "\n" -> {continue;}
                            default -> System.out.println("That is not a valid action, please respond with y or n.");
                        }
                    }
                }
                case "fountain" -> 
                {
                    if(grid[y][x].fountain.used) System.out.println("You've already drunk from the fountain.");
                    else {
                        System.out.println("You locate an omnious fountain in the middle of the room, filled with a mysterious liquid. Quench your thirst with this liquid? (y/n)");
                        boolean choice = false;
                        char yesNo = 0;
                        while (!choice) 
                        {
                            switch (scanner.next()) 
                            {
                                case "y" -> 
                                {
                                    yesNo = 'y';
                                    System.out.println("You have chosen to drink from the fountain.");
                                    choice = true;
                                }
                                case "n" -> 
                                {
                                    yesNo = 'n';
                                    System.out.println("You choose not to try your luck... for now.");
                                    choice = true;
                                }
                                case "", "\n" -> 
                                {
                                    continue;
                                }
                                default -> System.out.println("That is not a valid action, please respond with y or n.");
                            }
                            if (yesNo == 'y') 
                            {
                                // {"+2 damage", "+1 dexterity", "-2 health", "nothing"};
                                switch (grid[y][x].fountain.buff) 
                                {
                                    case "+2 damage" -> 
                                    {
                                        System.out.println("You feel slightly stronger after drinking from the fountain.");
                                        hero.damage += 2;
                                    }
                                    case "+1 dexterity" -> 
                                    {
                                        System.out.println("You feel slightly more nimble after drinking from the fountain.");
                                        hero.dexterity++;
                                    }
                                    case "-2 health" -> 
                                    {
                                        System.out.println("It seems like today is not your day... you take some damage from the fountain water.");
                                        hero.health -= 2;
                                        if (hero.health <= 0) 
                                        {
                                            System.out.println("You garble out your final words while the mysterious liquid puts an eternal hiatus to your mission. You have died.");
                                            System.exit(1);
                                        } else System.out.println("Your current health: " + hero.health);
                                    }
                                    default -> System.out.println("It tastes like fresh spring water, despite its apperance.");
                                }
                                grid[y][x].fountain.used = true;
                            }
                        }
                    }
                }
                case "boss" -> 
                {
                    System.out.println("As you walk into the pitch black room, you feel chills down the back of your neck. You pick up your " + (hero.weapon != null ? hero.weapon.type: "fists") + ", and prepare yourself for battle.");
                    bossBattle(hero);
                }
            }
        }
    }
}
