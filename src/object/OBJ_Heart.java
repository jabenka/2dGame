package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Heart extends Entity {

    GamePannel gp;
    public OBJ_Heart(GamePannel gp) {
        super(gp);
        this.gp=gp;
        name = "Heart";
        type=type_pickuponly;
        value=2;
        down1=setup("/objects/heart_full.png",gp.tilesize,gp.tilesize);
        image2=setup("/objects/heart_half.png",gp.tilesize,gp.tilesize);
        image3=setup("/objects/heart_blank.png",gp.tilesize,gp.tilesize);


    }
    public void use(Entity entity){
        gp.playSoundEffect(2);
        gp.ui.AddMessage("Life +"+value);
        entity.life+=value;




    }

}
