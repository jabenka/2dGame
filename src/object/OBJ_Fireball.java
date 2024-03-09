package object;

import Entity.Entity;
import Entity.Projectile;
import main.GamePannel;
import tile_Interctive.IT_trunk;
import tile_Interctive.InterctiveTile;

import java.awt.*;

public class OBJ_Fireball extends Projectile {
    GamePannel gp;
    public OBJ_Fireball(GamePannel gp) {
        super(gp);
        this.gp=gp;
        name="Fireball";
        speed=5;
        MaxLife=80;
        life= MaxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage(){
        up1=setup("/projects/fireball_up_1.png",gp.tilesize,gp.tilesize);
        up2=setup("/projects/fireball_up_2.png",gp.tilesize,gp.tilesize);
        down1=setup("/projects/fireball_down_1.png",gp.tilesize,gp.tilesize);
        down2=setup("/projects/fireball_down_2.png",gp.tilesize,gp.tilesize);
        left1=setup("/projects/fireball_left_1.png",gp.tilesize,gp.tilesize);
        left2=setup("/projects/fireball_left_2.png",gp.tilesize,gp.tilesize);
        right1=setup("/projects/fireball_right_1.png",gp.tilesize,gp.tilesize);
        right2=setup("/projects/fireball_right_2.png",gp.tilesize,gp.tilesize);
    }
    public boolean hasMana(Entity user){
        boolean havemana=false;
        if(user.mana>=useCost){
            havemana=true;
        }
        return havemana;
    }
    public void SubMana(Entity user){
        user.mana-=useCost;
    }
    public Color getParticleColor() {
        Color color = new Color(240,50,0);
        return color;
    }
    public int getParticleSize(){
        int size=10;
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
