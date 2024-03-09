package Entity;
import main.KeyHadler;
import main.UtilityTool;
import main.GamePannel;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield;
import object.OBJ_Weapon_Swrd;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{

    KeyHadler keyH;
    public ArrayList<Entity> Inventory=new ArrayList<Entity>();
    public final int MaxInventorySize=20;
    public final int ScreenX;
    public final int ScreenY;
    int standCounter=0;
    public boolean attackCancel=false;
    public Player(GamePannel gp, KeyHadler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH=keyH;

        ScreenX=gp.ScreenWidth/2-(gp.tilesize/2);
        ScreenY=gp.ScreenHeight/2-(gp.tilesize/2);
        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=26;
        solidArea.height=26;
        maxMana=4;
        mana=maxMana;

        getPlayerImage();
        setDefaultValues();
        SetItems();
        GetPlayerAttack();
    }
    public void SetItems(){
        Inventory.clear();
        Inventory.add(currentWeapon);
        Inventory.add(currentShield);
        Inventory.add(new OBJ_Key(gp));


    }
    public void setDefaultValues(){

        worldX=gp.tilesize*23;
        worldY=gp.tilesize*21;

        speed=4;
        direction="down";


        //PLAYER STATUS
        level =1;
        MaxLife=6;  //3 hearts
        life=MaxLife;
        strenght=1;
        agility=1;
        exp=0;
        nextlevelExp=5;
        money=0;
        currentWeapon=new OBJ_Weapon_Swrd(gp);
        currentShield=new OBJ_Shield(gp);

        projectile=new OBJ_Fireball(gp);



        attack=getAttack();
        defense=getDefense();


    }

    public int getDefense() { return defense=agility* currentShield.defenseValue; }

    public int getAttack() {
        attackArea=currentWeapon.attackArea;
        return attack=strenght* currentWeapon.AttackValue; }

    public void getPlayerImage(){

        up1=setup("/Player/boy_up_1.png",gp.tilesize,gp.tilesize);
        up2=setup("/Player/boy_up_2.png",gp.tilesize,gp.tilesize);
        down1=setup("/Player/boy_down_1.png",gp.tilesize,gp.tilesize);
        down2=setup("/Player/boy_down_2.png",gp.tilesize,gp.tilesize);
        left1=setup("/Player/boy_left_1.png",gp.tilesize,gp.tilesize);
        left2=setup("/Player/boy_left_2.png",gp.tilesize,gp.tilesize);
        right1=setup("/Player/boy_right_1.png",gp.tilesize,gp.tilesize);
        right2=setup("/Player/boy_right_2.png",gp.tilesize,gp.tilesize);

    }
    public void GetPlayerAttack(){

            if(currentWeapon.type==type_sword){

                attackUp1=setup("/Player/boy_attack_up_1.png",gp.tilesize,gp.tilesize*2);
                attackUp2=setup("/Player/boy_attack_up_2.png",gp.tilesize,gp.tilesize*2);
                attackDown1=setup("/Player/boy_attack_down_1.png",gp.tilesize,gp.tilesize*2);
                attackDown2=setup("/Player/boy_attack_down_2.png",gp.tilesize,gp.tilesize*2);
                attackLeft1=setup("/Player/boy_attack_left_1.png",gp.tilesize*2,gp.tilesize);
                attackLeft2=setup("/Player/boy_attack_left_2.png",gp.tilesize*2,gp.tilesize);
                attackRight1=setup("/Player/boy_attack_right_1.png",gp.tilesize*2,gp.tilesize);
                attackRight2=setup("/Player/boy_attack_right_2.png",gp.tilesize*2,gp.tilesize);
            }
            if(currentWeapon.type==type_axe){

                attackUp1=setup("/Player/boy_axe_up_1.png",gp.tilesize,gp.tilesize*2);
                attackUp2=setup("/Player/boy_axe_up_2.png",gp.tilesize,gp.tilesize*2);
                attackDown1=setup("/Player/boy_axe_down_1.png",gp.tilesize,gp.tilesize*2);
                attackDown2=setup("/Player/boy_axe_down_2.png",gp.tilesize,gp.tilesize*2);
                attackLeft1=setup("/Player/boy_axe_left_1.png",gp.tilesize*2,gp.tilesize);
                attackLeft2=setup("/Player/boy_axe_left_2.png",gp.tilesize*2,gp.tilesize);
                attackRight1=setup("/Player/boy_axe_right_1.png",gp.tilesize*2,gp.tilesize);
                attackRight2=setup("/Player/boy_axe_right_2.png",gp.tilesize*2,gp.tilesize);
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


    public void update(){

        if(attacking==true){
            attacking();
        }
        else if(keyH.Uppressed==true||keyH.Downpressed==true||
                keyH.Leftpressed==true||keyH.Rightpressed==true|| keyH.EnterPressed==true){
            
            if(keyH.Uppressed==true){
                direction="up";
            }
            else if(keyH.Downpressed==true){
                direction="down";
            }
            else if(keyH.Leftpressed==true){
                direction="left";
            }
            else if(keyH.Rightpressed==true){
                direction="right";
            }
            //TILE COLLISION
            collisianOn=false;
            gp.cCheker.CheckTile(this);


            //OBJECTS COLLISION
            int objCcheck=gp.cCheker.CheckObject(this,true);
            //pickupobject maybe
            pickUpObj(objCcheck);
            //MONSTER COLLISION
            int monsterIndex=gp.cCheker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);

            //INERACT TILES
            int iTileIndex=gp.cCheker.checkEntity(this,gp.iTile);




            //NPC COLLISION
            int NpcIndex=gp.cCheker.checkEntity(this, gp.NPC);
            iteractNpc(NpcIndex);

            //EVENTS
            gp.eHandler.CheckEvent();








            //IF COOLISION IF FALSE PLAYER CANT MOVE
            if(collisianOn==false&&keyH.EnterPressed==false){
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

            if((keyH.EnterPressed==true&& attackCancel==false) && shotAvailableCounter==30){
                    attacking=true;
                    spriteCounter=0;
            }
            attackCancel=false;

            gp.keyH.EnterPressed=false;

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

        }else {
            standCounter++;
            if(standCounter==24) {
                spriteNum = 1;
                standCounter=0;
            }

        }
        if(gp.keyH.ShootPressed==true && projectile.alive==false&&shotAvailableCounter==30&&projectile.hasMana(this)==true){

            projectile.set(worldX,worldY,direction,true,this);

            projectile.SubMana(this);

            gp.projectileList.add(projectile);

            gp.playSoundEffect(7);

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
        if(life>MaxLife){
            life=MaxLife;
        }
        if(mana>maxMana){
            mana=maxMana;
        }
        if(life<=0){
            gp.gameState=gp.DeathState;
            gp.ui.commandNum=-1;
        }
    }
    public void setDefaultPos(){
        worldX=gp.tilesize*23;
        worldY=gp.tilesize*21;
        direction="down";



    }
    public void restoreLifeandMana(){
        life=MaxLife;
        mana=maxMana;
        invincible=false;
    }

    public void attacking(){
        spriteCounter++;
        if(spriteCounter<=5){
            spriteNum=1;
        }
        if(spriteCounter>5 && spriteCounter<=25){
            spriteNum=2;

            int currentWorldX=worldX;
            int curretnWorldY=worldY;
            int solidAreaWidth=solidArea.width;
            int solidAreaHeight=solidArea.height;

            switch (direction){
                case"up":worldY-=attackArea.height; break;
                case"down":worldY+=attackArea.height;break;
                case"left":worldX-=attackArea.width;break;
                case"right":worldX+=attackArea.height;break;


            }
            solidArea.width=attackArea.width;
            solidArea.height=attackArea.height;

            int monsterIndex=gp.cCheker.checkEntity(this,gp.monster);
            damageMonster(monsterIndex,attack);


            int iTileIndex=gp.cCheker.checkEntity(this,gp.iTile);
            damageInteractTile(iTileIndex);


            worldX=currentWorldX;
            worldY=curretnWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;


        }
        if(spriteCounter>25 ){
            spriteNum=1;
            spriteCounter=0;
            attacking=false;
        }


    }

    public void damageInteractTile(int i) {

        if(i!=999&& gp.iTile[gp.CurretnMap][i].destructable==true&&
                gp.iTile[gp.CurretnMap][i].isCorrectItem(this)==true&&gp.iTile[gp.CurretnMap][i].invincible==false){


                gp.iTile[gp.CurretnMap][i].life--;
                gp.iTile[gp.CurretnMap][i].PlaySE();
                gp.iTile[gp.CurretnMap][i].invincible=true;

                generateParticle(gp.iTile[gp.CurretnMap][i],gp.iTile[gp.CurretnMap][i]);

                if(gp.iTile[gp.CurretnMap][i].life==0){
                    gp.iTile[gp.CurretnMap][i]=gp.iTile[gp.CurretnMap][i].getDestroyedForm();
                }


        }


    }


    public void iteractNpc(int i){
        if(gp.keyH.EnterPressed==true) {
            if (i != 999) {
                    attackCancel=true;

                    gp.gameState = gp.dialogueState;
                    gp.NPC[gp.CurretnMap][i].Speak();


            }
        }

    }
    public void contactMonster(int i){
        if(i!=999){
            if(invincible==false && gp.monster[gp.CurretnMap][i].dying==false) {
                gp.playSoundEffect(5);
                int damage=gp.monster[gp.CurretnMap][i].attack-defense;
                if(damage<0){
                    damage=0;
                }
                life -= damage;
                invincible = true;
                gp.ui.drawPlayerLife();

            }
        }
    }
    public void damageMonster(int i,int attack){
        if(i!=999){

            if(gp.monster[gp.CurretnMap][i].invincible==false){
                gp.playSoundEffect(6);
                int damage=attack-gp.monster[gp.CurretnMap][i].defense;
                if(damage<0){
                    damage=0;
                }
                gp.monster[gp.CurretnMap][i].life-=damage;
                gp.ui.AddMessage(damage+" Damage!");
                gp.monster[gp.CurretnMap][i].invincible=true;
                gp.monster[gp.CurretnMap][i].DamageReaction();


                if(gp.monster[gp.CurretnMap][i].life<=0){
                    gp.monster[gp.CurretnMap][i].dying=true;
                    gp.ui.AddMessage("You slain a "+gp.monster[gp.CurretnMap][i].name);
                    gp.ui.AddMessage("You gain "+gp.monster[gp.CurretnMap][i].exp+" exp!");
                    exp+=gp.monster[gp.CurretnMap][i].exp;
                    checkLvlUp();
                }
            }

        }
    }

    public void checkLvlUp() {
        if(exp>=nextlevelExp){
            exp-=nextlevelExp;
            level++;
            nextlevelExp=nextlevelExp*2;
            MaxLife+=2;
            strenght++;
            agility++;
            attack=getAttack();
            defense=getDefense();
            gp.playSoundEffect(4);
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialoude="You are level " + level+" now\nStrength+1,Agility+1,Life+2";
        }
    }

    public void pickUpObj(int i){

        if(i!=999) {

            //PICKUP ONLY ITEMS
            if (gp.obj[gp.CurretnMap][i].type == type_pickuponly) {
                gp.obj[gp.CurretnMap][i].use(this);
                gp.obj[gp.CurretnMap][i]=null;




            } else {

                String text;
                if (Inventory.size() != MaxInventorySize) {
                    Inventory.add(gp.obj[gp.CurretnMap][i]);
                    gp.playSoundEffect(1);
                    text = "You got a " + gp.obj[gp.CurretnMap][i].name + "!";
                } else {
                    text = "Your inventory is full";
                }
                gp.ui.AddMessage(text);
                gp.obj[gp.CurretnMap][i] = null;
            }
        }
    }
    public void draw(Graphics2D g2){


        BufferedImage image=null;
        int tempScreenX=ScreenX;
        int tempScreenY=ScreenY;
        switch(direction){
            case "up":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if(attacking==true){
                    tempScreenY=ScreenY-gp.tilesize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }

                break;
            case "down":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if(attacking==true){
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }

                break;
            case "left":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if(attacking==true){
                    tempScreenX=ScreenX-gp.tilesize;

                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }

                }
                break;
            case "right":
                if(attacking==false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if(attacking==true){
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }

                break;
        }
        if(invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3F));
        }


        g2.drawImage(image,tempScreenX,tempScreenY,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0F));
    }


    public void SelectItem(){
        int itemIndex=gp.ui.getItemIndex();
        if(itemIndex<Inventory.size()) {
            Entity selectedItem = Inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                GetPlayerAttack();

            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                    selectedItem.use(this);
                    Inventory.remove(itemIndex);
            }
        }
    }





}