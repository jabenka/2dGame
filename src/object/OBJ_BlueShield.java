package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_BlueShield extends Entity {
    public OBJ_BlueShield(GamePannel gp) {
        super(gp);
        type=type_shield;
        name="Blue Shield";
        down1=setup("/objects/shield_blue.png",gp.tilesize,gp.tilesize);
        defenseValue=3;
        description="["+name+"]\nGood blue shield";
    }
}
