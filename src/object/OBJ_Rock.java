package object;

import Entity.Entity;
import Entity.Projectile;
import main.GamePannel;

public class OBJ_Rock extends Projectile {
    GamePannel gp;
    public OBJ_Rock(GamePannel gp) {
        super(gp);
        this.gp=gp;
        name="Rock";
        speed=8;
        MaxLife=80;
        life= MaxLife;
        attack=2;
        useCost=0;
        alive=false;
        getImage();
    }
    public void getImage() {
        up1 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        up2 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        down1 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        down2 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        left1 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        left2 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        right1 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
        right2 = setup("/projects/rock_down_1.png", gp.tilesize, gp.tilesize);
    }
    public boolean hasMana(Entity user){
        boolean havemana=false;
        if(ammo>=useCost){
            havemana=true;
        }
        return havemana;
    }
    public void SubMana(Entity user){
        ammo-=useCost;
    }
}
