package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePannel gp) {
        super(gp);
        name = "Door";
        down1=setup("/objects/door.png",gp.tilesize,gp.tilesize);
        collision=true;

        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;


    }
}
