package Entity;

import main.GamePannel;

import java.awt.*;

public class Particle extends Entity{
    GamePannel gp;
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;
    public Particle(GamePannel gp,Entity generator,Color color,int size,int speed,int MaxLife,int xd,int yd) {
        super(gp);

        this.gp=gp;
        this.generator=generator;
        this.color=color;
        this.size=size;
        this.speed=speed;
        this.xd=xd;
        this.yd=yd;
        this.MaxLife=MaxLife;
        life=MaxLife;
        int ofset=(gp.tilesize/2)-(size/2);
        worldX=generator.worldX+ofset;
        worldY=generator.worldY+ofset;
    }
    public void update(){
        life--;

        if(life<MaxLife/3){
            yd++;   //GRAVITY
        }


        worldX+=xd*speed;
        worldY+=yd*speed;
        if(life==0){
            alive=false;
        }




    }
    public void draw(Graphics2D g2){

        int ScreenX=worldX-gp.player.worldX+gp.player.ScreenX;
        int ScreenY=worldY-gp.player.worldY+gp.player.ScreenY;

        g2.setColor(color);
        g2.fillRect(ScreenX,ScreenY,size,size);







    }
}
