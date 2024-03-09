package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePannel gp) {
        super(gp);
        setup("/objects/boots.png",gp.tilesize,gp.tilesize);
        name = "Boots";

    }





}
