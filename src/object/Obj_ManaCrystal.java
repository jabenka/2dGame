package object;

import Entity.Entity;
import main.GamePannel;

public class Obj_ManaCrystal extends Entity {
    GamePannel gp;
    public Obj_ManaCrystal(GamePannel gp) {
        super(gp);
        this.gp=gp;
        name="Mana Crystal";
        value=1;
        type=type_pickuponly;
        down1=setup("/objects/manacrystal_full.png",gp.tilesize,gp.tilesize);
        image2=setup("/objects/manacrystal_blank.png",gp.tilesize,gp.tilesize);
    }
    public void use(Entity entity) {
        gp.playSoundEffect(2);
        gp.ui.AddMessage("Mana +" + value);
        entity.mana += value;
    }
    }
