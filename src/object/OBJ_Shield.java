package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Shield extends Entity {
    public OBJ_Shield(GamePannel gp) {
        super(gp);
        type=type_shield;
        name="Wood Shield";
        down1=setup("/objects/shield_wood.png",gp.tilesize,gp.tilesize);
        defenseValue=1;
        description="["+name+"]\nAn old wooden shield";
    }
}
