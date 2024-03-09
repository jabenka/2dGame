package tile_Interctive;

import main.GamePannel;

import java.awt.*;

public class IT_DryTree extends InterctiveTile{
    GamePannel gp;
    public IT_DryTree(GamePannel gp,int col,int row) {
        super(gp,col,row);
        this.gp=gp;
        this.worldX=gp.tilesize*col;
        this.worldY=gp.tilesize*row;
        life=3;

        down1=setup("/Tiles_interactive/drytree.png",gp.tilesize,gp.tilesize);
        destructable=true;
    }
    public void PlaySE(){
        gp.playSoundEffect(8);
    }
    public InterctiveTile getDestroyedForm(){
        InterctiveTile tile=new IT_trunk(gp,worldX/gp.tilesize,worldY/gp.tilesize);
        return tile;
    }
    public Color getParticleColor() {
        Color color = new Color(65,50,30);
        return color;
    }
    public int getParticleSize(){
        int size=6;
        return size;
    }
    public int getParticleSpeed(){
        int speed=1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife=20;
        return maxLife;
    }








}
