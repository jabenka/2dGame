package Monster;

import Entity.Entity;
import main.GamePannel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Rock;
import object.Obj_ManaCrystal;

import java.util.Random;

public class MON_GreenSlime extends Entity{

GamePannel gp=new GamePannel();
    public MON_GreenSlime(GamePannel gp) {

        super(gp);

        name="Green Slime";
        speed=1;
        direction="down";
        MaxLife=4;
        life=MaxLife;
        attack=2;
        defense=0;
        solidArea.x=3;
        solidArea.y=18;
        solidArea.width=42;
        solidArea.height=30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        type=type_monster;
        exp=2;
        projectile=new OBJ_Rock(gp);


        getImage();



    }


    public void getImage(){
        up1=setup("/Monster/greenslime_down_1.png",gp.tilesize,gp.tilesize);
        up2=setup("/Monster/greenslime_down_2.png",gp.tilesize,gp.tilesize);
        down1=setup("/Monster/greenslime_down_1.png",gp.tilesize,gp.tilesize);
        down2=setup("/Monster/greenslime_down_2.png",gp.tilesize,gp.tilesize);
        left1=setup("/Monster/greenslime_down_1.png",gp.tilesize,gp.tilesize);
        left2=setup("/Monster/greenslime_down_2.png",gp.tilesize,gp.tilesize);
        right1=setup("/Monster/greenslime_down_1.png",gp.tilesize,gp.tilesize);
        right2=setup("/Monster/greenslime_down_2.png",gp.tilesize,gp.tilesize);

    }
    public void DamageReaction(){

        ActionClockCounter=0;
        direction=gp.player.direction;


    }
    public void setAction(){

        ActionClockCounter++;
        if(ActionClockCounter==90) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 40) {
                direction = "down";
            }
            if (i > 40 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            ActionClockCounter=0;
        }
//        int i=101;
//        if(i>99 && projectile.alive==false && shotAvailableCounter==30){
//
//            projectile.set(worldX,worldY,direction,true,this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter=0;
//            gp.playSoundEffect(7);
//
//        }

    }
    public void CheckDrop(){
        int i=new Random().nextInt(100)+1;

        if(i<50){
            DropItem(0);
        }
        if(i>=50&& i<75)
        {
            DropItem(1);
        }
        if(i>=75&& i<100)
        {
            DropItem(2);
        }

    }

}
