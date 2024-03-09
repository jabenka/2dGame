package main;

import Entity.NPC_Merchant;
import Entity.NPC_OLDman;
import Monster.MON_GreenSlime;
import object.*;
import tile_Interctive.IT_DryTree;

public class AssetSetter {
        int mapNum;
    GamePannel gp;
    public AssetSetter(GamePannel gp){
        this.gp=gp;
        mapNum=0;
    }

    public void SetObject(){
        int i=0;

        gp.obj[mapNum][i]=new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX=gp.tilesize*31;
        gp.obj[mapNum][i].worldY=gp.tilesize*21;
        i++;
        gp.obj[mapNum][i]=new OBJ_BlueShield(gp);
        gp.obj[mapNum][i].worldX=gp.tilesize*33;
        gp.obj[mapNum][i].worldY=gp.tilesize*21;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionRed(gp);
        gp.obj[mapNum][i].worldX=gp.tilesize*22;
        gp.obj[mapNum][i].worldY=gp.tilesize*27;





    }
    public void SetNPC(){
        mapNum=0;
        int i=0;
        gp.NPC[mapNum][i]=new NPC_OLDman(gp);
        gp.NPC[mapNum][i].worldX=gp.tilesize*21;
        gp.NPC[mapNum][i].worldY=gp.tilesize*21;
        i++;
//        mapNum=1;
//        i=0;
//        gp.NPC[mapNum][i]=new NPC_Merchant(gp);
//        gp.NPC[mapNum][i].worldX=gp.tilesize*12;
//        gp.NPC[mapNum][i].worldY=gp.tilesize*7;
//        mapNum=0;


    }
    public void SetMonster(){
        int i=0;
        gp.monster[mapNum][i]=new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tilesize*24;
        gp.monster[mapNum][i].worldY=gp.tilesize*37;
        i++;
        gp.monster[mapNum][i]=new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tilesize*34;
        gp.monster[mapNum][i].worldY=gp.tilesize*42;
        i++;
        gp.monster[mapNum][i]=new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tilesize*38;
        gp.monster[mapNum][i].worldY=gp.tilesize*42;
        i++;
    }
    public void setInterTile(){
        int i=0;

        gp.iTile[mapNum][i]=new IT_DryTree(gp,27,12);

        i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,28,12);

        i++;

        gp.iTile[mapNum][i]=new IT_DryTree(gp,29,12);

        i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,30,12);

        i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,31,12);

        i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,32,12);
        i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,33,12);
        i++;




    }


}
