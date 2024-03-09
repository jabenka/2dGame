package Entity;

import main.UtilityTool;
import main.GamePannel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.Obj_ManaCrystal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {



    //WORLD SETTINGS
    public int worldX,worldY;

    GamePannel gp;
    public BufferedImage image,image2,image3;
    //PLAYER SETTINGS
    public int speed;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;

    //ENTITES SETTINGS
    public String name;
    public boolean collision=false;
    public int ActionClockCounter;
    public String direction="down";
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisianOn=false;
    public String dialogues []=new String[20];
    int dialogueIndex=0;
    public String description=" ";



    public boolean invincible=false;
    public int invincibleCounter=0;




    //CHAR STATUS
    public int MaxLife;
    public int life;
    public boolean attacking=false;
    public Rectangle attackArea=new Rectangle(0,0,0,0);
    public boolean alive=true;
    public boolean dying=false;
    int dyingCounter=0;
    public int maxMana;
    public int mana;
    boolean HpBarOn=false;
    int hpBarCounter=0;
    public int strenght;
    public int agility;
    public int attack;
    public int defense;
    public int level;
    public int exp;
    public int nextlevelExp;
    public int money;
    public Entity currentWeapon;
    public Entity currentShield;
    public int ammo;
    public int AttackValue;
    public int defenseValue;



    //TYPES
    public int type;
    public int type_player=0;
    public final int type_npc=1;
    public final int type_monster=2;
    public final int type_sword=3;
    public final int type_axe=4;
    public final int type_shield=5;
    public final int type_consumable=6;
    public final int type_pickuponly=7;


    public int shotAvailableCounter=0;






    public int value;

    public Projectile projectile;
    public int useCost;

    public Entity(GamePannel gp){
        this.gp=gp;




    }

    public void setAction(){}

    public void DamageReaction(){}
    public void update(){
        setAction();

        collisianOn=false;
        gp.cCheker.CheckTile(this);
        gp.cCheker.CheckObject(this,false);
        gp.cCheker.checkEntity(this,gp.NPC);
        gp.cCheker.checkEntity(this,gp.monster);
        boolean contactPlayer=gp.cCheker.CheckPlayer(this);

        if(this.type==type_monster && contactPlayer==true) {
            DamagePlayer(attack);
        }

        //IF COOLISION IS FALSE PLAYER CANT MOVE
        if(collisianOn==false){
            switch (direction){
                case"up":
                    worldY-=speed;
                    break;
                case"down":
                    worldY+=speed;
                    break;
                case"left":
                    worldX-=speed;
                    break;
                case"right":
                    worldX+=speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum==1){
                spriteNum=2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }

        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }


    }
    public Color getParticleColor() {
        Color color =null;
        return color;
    }
    public int getParticleSize(){
        int size=0;
        return size;
    }
    public int getParticleSpeed(){
        int speed=0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife=0;
        return maxLife;
    }
    public void generateParticle(Entity generator,Entity target){
        Color color=generator.getParticleColor();
        int size=generator.getParticleSize();
        int speed=generator.getParticleSpeed();
        int maxlife=generator.getParticleMaxLife();

        Particle p1=new Particle(gp,target,color,size,speed,maxlife,-2,-1);
        Particle p2=new Particle(gp,target,color,size,speed,maxlife,2,-1);
        Particle p3=new Particle(gp,target,color,size,speed,maxlife,-2,1);
        Particle p4=new Particle(gp,target,color,size,speed,maxlife,2,1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);


    }

    public void DamagePlayer(int attack) {

            if(gp.player.invincible==false){
                int damage=attack-gp.player.defense;
                if(damage<0){
                    damage=0;
                }
                gp.player.life-=damage;
                gp.player.invincible=true;
                gp.ui.drawPlayerLife();
            }

    }

    public void use(Entity entity){}
    public void CheckDrop(){}
    public void DropItem(int type){
        Entity item=new OBJ_Heart(gp);
        switch (type){
            case 0: item=new OBJ_Coin(gp); break;
            case 1: item=new OBJ_Heart(gp); break;
            case 2: item=new Obj_ManaCrystal(gp); break;
        }
        for(int i=0;i<gp.obj[1].length;i++){
            if(gp.obj[gp.CurretnMap][i]==null){
                gp.obj[gp.CurretnMap][i]=item;
                //ITEM APPEARS ON THE MOSTERS PLACE
                gp.obj[gp.CurretnMap][i].worldX=worldX;
                gp.obj[gp.CurretnMap][i].worldY=worldY;

                break;
            }
        }
    }


    public void draw(Graphics2D g2){

        BufferedImage image=null;

        int ScreenX=worldX-gp.player.worldX+gp.player.ScreenX;
        int ScreenY=worldY-gp.player.worldY+gp.player.ScreenY;

        if(worldX+gp.tilesize>gp.player.worldX-gp.player.ScreenX &&
                worldX-gp.tilesize<gp.player.worldX+gp.player.ScreenX &&
                worldY+gp.tilesize>gp.player.worldY-gp.player.ScreenY &&
                worldY-gp.tilesize<gp.player.worldY+gp.player.ScreenY){



            switch(direction){
                case "up":
                    if(spriteNum==1){
                        image=up1;
                    }
                    if(spriteNum==2){
                        image=up2;
                    }

                    break;
                case "down":
                    if(spriteNum==1){
                        image=down1;
                    }
                    if(spriteNum==2){
                        image=down2;
                    }

                    break;
                case "left":
                    if(spriteNum==1){
                        image=left1;
                    }
                    if(spriteNum==2){
                        image=left2;
                    }

                    break;
                case "right":
                    if(spriteNum==1){
                        image=right1;
                    }
                    if(spriteNum==2){
                        image=right2;
                    }

                    break;
            }

            //MONSTER HP BAR
            if(type==2&& HpBarOn==true) {

                double oneScale=(double)gp.tilesize/MaxLife;
                double hpBarValue=oneScale*life;


                g2.setColor(new Color(35,35,35));
                g2.fillRect(ScreenX-1,ScreenY-16,gp.tilesize+2,12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(ScreenX,ScreenY-15,(int) hpBarValue,10);

                hpBarCounter++;

                if(hpBarCounter>600){
                    hpBarCounter=0;
                    HpBarOn=false;
                }
            }




            if(invincible==true){
                HpBarOn=true;
                hpBarCounter=0;
               CheangeAlpha(g2,0.4f);
            }
            if(dying==true){
                dyingAnimation(g2);
            }

            g2.drawImage(image,ScreenX,ScreenY,null);

            CheangeAlpha(g2,1.0f);


        }




    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int i=5;
        if(dyingCounter<=i){

            CheangeAlpha(g2,0f);
        }
        if(dyingCounter>i&& dyingCounter<=i*2){
            CheangeAlpha(g2,1f);
        }
        if(dyingCounter>i*2 &&dyingCounter<=i*3){
            CheangeAlpha(g2,0f);

        }
        if(dyingCounter>i*3 && dyingCounter<=i*4){
            CheangeAlpha(g2,1f);
        }
        if(dyingCounter>i*4 &&dyingCounter<=i*5){
            CheangeAlpha(g2,0f);
        }
        if(dyingCounter>i*5 && dyingCounter<=i*6){
            CheangeAlpha(g2,1f);
        }
        if(dyingCounter>i*6 &&dyingCounter<=i*7){
            CheangeAlpha(g2,0f);
        }
        if(dyingCounter>i*7 && dyingCounter<=i*8){
            CheangeAlpha(g2,1f);
        }
        if (dyingCounter > i*8) {

            alive=false;
        }

    }
    public void CheangeAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }

    public void Speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialoude = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;


        }
    }
    public BufferedImage setup(String imagename,int width,int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledimage = null;

        try {

            scaledimage=ImageIO.read(getClass().getResourceAsStream(imagename));
            scaledimage=uTool.scaleImage(scaledimage,width,height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledimage;
    }
    public boolean hasMana(Entity user){
        boolean havemana=false;
        return havemana;
    }
    public void SubMana(Entity user){

    }


}