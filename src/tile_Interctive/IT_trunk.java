package tile_Interctive;

import Entity.Entity;
import main.GamePannel;

public class IT_trunk extends InterctiveTile {
    GamePannel gp;
    public IT_trunk(GamePannel gp,int col,int row) {
        super(gp,col,row);
        this.gp=gp;
        this.worldX=gp.tilesize*col;
        this.worldY=gp.tilesize*row;

        down1=setup("/Tiles_interactive/trunk.png",gp.tilesize,gp.tilesize);


        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=0;
        solidArea.height=0;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
}
