package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_PotionRed extends Entity {

    GamePannel gp;
    public OBJ_PotionRed(GamePannel gp) {
        super(gp);
        this.gp=gp;
        type=type_consumable;
        name="Red Potion";
        value=5;
        down1=setup("/objects/potion_red.png",gp.tilesize,gp.tilesize);
        description="["+name+"]\nHealing potion\nHeals you by "+value;

    }

    public void use(Entity entity){


        gp.gameState=gp.dialogueState;
        gp.ui.currentDialoude="You drink the "+name+"!\n"+"Your life has been \nrecovered by "+value;
        entity.life+=value;

    }
}
