package object;

import main.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public int worldX,worldY;
    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision=false;

    public Image im1,im2,im3;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    public void draw(Graphics2D g2, GamePannel gp){
        int ScreenX=worldX-gp.player.worldX+gp.player.ScreenX;
        int ScreenY=worldY-gp.player.worldY+gp.player.ScreenY;

        if(worldX+gp.tilesize>gp.player.worldX-gp.player.ScreenX &&
                worldX-gp.tilesize<gp.player.worldX+gp.player.ScreenX &&
                worldY+gp.tilesize>gp.player.worldY-gp.player.ScreenY &&
                worldY-gp.tilesize<gp.player.worldY+gp.player.ScreenY){
            g2.drawImage(image,ScreenX,ScreenY,gp.tilesize,gp.tilesize,null);


        }



    }



}
