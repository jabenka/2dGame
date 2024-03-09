package main;

import Entity.Entity;

public class CollisionChecker {
    GamePannel gp;
    public CollisionChecker(GamePannel gp){
        this.gp=gp;


    }
    public void CheckTile(Entity entity){

        int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tilesize;
        int entityRightCol=entityRightWorldX/gp.tilesize;
        int entityTopRow=entityTopWorldY/gp.tilesize;
        int entityBottomRow=entityBottomWorldY/gp.tilesize;

        int tileNum1,tileNum2;

        switch (entity.direction){
            case"up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tilesize;
                tileNum1=gp.tileM.mapTileNum[gp.CurretnMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.CurretnMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision==true||
                        gp.tileM.tile[tileNum2].collision==true){
                    entity.collisianOn=true;
                }
                break;
            case"down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tilesize;
                tileNum1=gp.tileM.mapTileNum[gp.CurretnMap][entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[gp.CurretnMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision==true||
                        gp.tileM.tile[tileNum2].collision==true) {
                    entity.collisianOn = true;
                }
                break;
            case"left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tilesize;
                tileNum1=gp.tileM.mapTileNum[gp.CurretnMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.CurretnMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true||
                        gp.tileM.tile[tileNum2].collision==true) {
                    entity.collisianOn = true;
                }
                break;
            case"right":
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tilesize;
                tileNum1=gp.tileM.mapTileNum[gp.CurretnMap][entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.CurretnMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true||
                        gp.tileM.tile[tileNum2].collision==true) {
                    entity.collisianOn = true;
                }
                break;
        }
    }

    public int CheckObject(Entity entity,boolean player){
        int index=999;
        for(int i=0;i<gp.obj[1].length;i++){
            if(gp.obj[gp.CurretnMap][i]!=null){
                //Get entity's solidarea position
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                //get object's solidarea position
                gp.obj[gp.CurretnMap][i].solidArea.x=gp.obj[gp.CurretnMap][i].worldX+gp.obj[gp.CurretnMap][i].solidArea.x;
                gp.obj[gp.CurretnMap][i].solidArea.y=gp.obj[gp.CurretnMap][i].worldY+gp.obj[gp.CurretnMap][i].solidArea.y;

                switch(entity.direction){
                    case"up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.CurretnMap][i].solidArea)){
                            if(gp.obj[gp.CurretnMap][i].collision==true){
                                entity.collisianOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case"down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.CurretnMap][i].solidArea)){
                            if(gp.obj[gp.CurretnMap][i].collision==true){
                                entity.collisianOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case"left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.CurretnMap][i].solidArea)){
                            if(gp.obj[gp.CurretnMap][i].collision==true){
                                entity.collisianOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case"right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.CurretnMap][i].solidArea)){

                            if(gp.obj[gp.CurretnMap][i].collision==true){
                                entity.collisianOn=true;
                            }
                            if(player==true){
                                index=i;
                            }

                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[gp.CurretnMap][i].solidArea.x=gp.obj[gp.CurretnMap][i].solidAreaDefaultX;
                gp.obj[gp.CurretnMap][i].solidArea.y=gp.obj[gp.CurretnMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    //NPC/MONSTER COLLISION
    public int checkEntity(Entity entity,Entity[][] target){
        int index=999;
        for(int i=0;i<target[1].length;i++){
            if(target[gp.CurretnMap][i]!=null){
                //Get entity's solidarea position
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                //get object's solidarea position
               target[gp.CurretnMap][i].solidArea.x=target[gp.CurretnMap][i].worldX+target[gp.CurretnMap][i].solidArea.x;
               target[gp.CurretnMap][i].solidArea.y=target[gp.CurretnMap][i].worldY+target[gp.CurretnMap][i].solidArea.y;

                switch(entity.direction){
                    case"up":
                        entity.solidArea.y-=entity.speed;
                        break;
                    case"down":
                        entity.solidArea.y+=entity.speed;
                        break;
                    case"left":
                        entity.solidArea.x-=entity.speed;
                        break;
                    case"right":
                        entity.solidArea.x+=entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[gp.CurretnMap][i].solidArea)){
                    if(target[gp.CurretnMap][i]!=entity) {
                        entity.collisianOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
               target[gp.CurretnMap][i].solidArea.x=target[gp.CurretnMap][i].solidAreaDefaultX;
               target[gp.CurretnMap][i].solidArea.y=target[gp.CurretnMap][i].solidAreaDefaultY;
            }
        }
        return index;


    }

    
    
    public boolean CheckPlayer(Entity entity){

        boolean contactPlayer=false;

            //Get entity's solidarea position
            entity.solidArea.x=entity.worldX+entity.solidArea.x;
            entity.solidArea.y=entity.worldY+entity.solidArea.y;
            //get object's solidarea position
            gp.player.solidArea.x=gp.player.worldX+gp.player.solidArea.x;
            gp.player.solidArea.y=gp.player.worldY+gp.player.solidArea.y;

            switch(entity.direction){
                case"up":
                    entity.solidArea.y-=entity.speed;
                    break;
                case"down":
                    entity.solidArea.y+=entity.speed;
                    break;
                case"left":
                    entity.solidArea.x-=entity.speed;
                    break;
                case"right":
                    entity.solidArea.x+=entity.speed;
                    break;
            }
            if(entity.solidArea.intersects(gp.player.solidArea)){

                entity.collisianOn=true;
                contactPlayer=true;

            }
            entity.solidArea.x=entity.solidAreaDefaultX;
            entity.solidArea.y=entity.solidAreaDefaultY;
            gp.player.solidArea.x=gp.player.solidAreaDefaultX;
            gp.player.solidArea.y=gp.player.solidAreaDefaultY;



            return  contactPlayer;

        }
        
        
        
        
        
        
    }

