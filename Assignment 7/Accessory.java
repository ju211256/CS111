import java.util.Arrays;

public class Accessory 
{
    String[] types = {"key", "potion", "ring", "shield"};
    String type;
    
    public Accessory(int index)
    {
        type = types[index];
    }

    public Accessory(String type)
    {
        if(Arrays.asList(types).contains(type))
        {
            this.type = type;
        }
    }

    public boolean equals(Object o)
    {
        if(o instanceof Accessory a)
        {
            return this.type.equals(a.type);
        }
        return false;
    }

    public int hashCode()
    {
        return type.hashCode();
    }
}
