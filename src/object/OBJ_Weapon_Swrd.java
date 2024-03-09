package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Weapon_Swrd extends Entity {


    public OBJ_Weapon_Swrd(GamePannel gp) {
        super(gp);
        type=type_sword;
        name="Sword";
        down1=setup("/objects/sword_normal.png",gp.tilesize,gp.tilesize);
        AttackValue=1;
        description="["+name+"]\nAn old sword";
        attackArea.width=36;
        attackArea.height=36;


    }
}
