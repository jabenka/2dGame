package tile_Interctive;

import Entity.Entity;
import main.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InterctiveTile extends Entity {
    GamePannel gp;
    public boolean destructable=false;
    public InterctiveTile(GamePannel gp,int Col,int Row) {
        super(gp);
        this.gp=gp;
    }
    public void update(){
        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>20){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if(entity.currentWeapon.type==type_axe) {
            isCorrectItem=true;
        }

        return isCorrectItem;
    }
    public void PlaySE(){}
    public InterctiveTile getDestroyedForm(){
        InterctiveTile tile=null;
        return tile;
    }
    public void draw(Graphics2D g2){


        int ScreenX=worldX-gp.player.worldX+gp.player.ScreenX;
        int ScreenY=worldY-gp.player.worldY+gp.player.ScreenY;

        if(worldX+gp.tilesize>gp.player.worldX-gp.player.ScreenX &&
                worldX-gp.tilesize<gp.player.worldX+gp.player.ScreenX &&
                worldY+gp.tilesize>gp.player.worldY-gp.player.ScreenY &&
                worldY-gp.tilesize<gp.player.worldY+gp.player.ScreenY){

                    g2.drawImage(down1,ScreenX,ScreenY,null);

        }
    }

}
