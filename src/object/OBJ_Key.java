package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Key extends Entity
{
    public OBJ_Key(GamePannel gp){
        super(gp);
        name="Key";
        down1=setup("/objects/key.png",gp.tilesize,gp.tilesize);
        description="["+name+"]\nAn old key";




    }





}
