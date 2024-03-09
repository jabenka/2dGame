package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePannel gp){
        super(gp);
        setup("/objects/chest.png",gp.tilesize,gp.tilesize);
        name="Chest";

    }
}
