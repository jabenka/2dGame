package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePannel gp) {
        super(gp);
        type=type_axe;
        name="Woodcutter's Axe";
        down1=setup("/objects/axe.png",gp.tilesize,gp.tilesize);
        AttackValue=2;
        attackArea.width=30;
        attackArea.height=30;
        description="["+name+"]\nStill can cut trees";
    }
}
