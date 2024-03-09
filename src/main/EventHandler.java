package main;

public class EventHandler {
    GamePannel gp;
    int previouseeventX;
    int previouseeventY;
    boolean canTouchEvent=true;
    int tempMap;
    int tempmapCol;
    int tempmapRow;
    EventRect eventRect[][][];
    EventHandler(GamePannel gp){

        this.gp=gp;
        eventRect=new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map=0;
        int col=0;
        int row=0;
        while(map<gp.maxMap&&col<gp.maxWorldCol&&row<gp.maxWorldRow){

            eventRect[map][col][row]=new EventRect();
            eventRect[map][col][row].x=23;
            eventRect[map][col][row].y=23;
            eventRect[map][col][row].width=5;
            eventRect[map][col][row].height=5;
            eventRect[map][col][row].eventRectDefalutX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefalutY=eventRect[map][col][row].y;
            col++;
            if(col==gp.maxWorldCol){
                row++;
                col=0;

                if(row==gp.maxWorldRow){
                    row=0;
                    map++;
                }
            }

        }


    }

    public void CheckEvent(){

        int xDistance=Math.abs(gp.player.worldX-previouseeventX);
        int yDistance=Math.abs(gp.player.worldY-previouseeventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance>gp.tilesize){
            canTouchEvent=true;
        }

        if(canTouchEvent==true) {
            if (hit(27, 16, "right",0) == true) {
                DamagePit( gp.dialogueState);
            }
            else if (hit(23, 12, "up",0) == true) {
                HealingPool(gp.dialogueState);
            }
            else if(hit(10,39,"any",0)==true){
                teleport(1,12,13);
            }
            else if(hit(12,13,"any",1)==true){
                teleport(0,10,39);
            }
        }
    }

    public void teleport(int map,int col,int row) {

        gp.gameState=gp.transitionState;
        tempMap=map;
        tempmapCol=col;
        tempmapRow=row;


        canTouchEvent=false;
    }

    public boolean hit(int col,int row,String regDirecton,int map){
        boolean hit=false;
        if(map==gp.CurretnMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tilesize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tilesize + eventRect[map][col][row].y;
            if (gp.player.solidArea.intersects(eventRect[map][col][row])) {
                if ((gp.player.direction.contentEquals(regDirecton) || regDirecton.contentEquals("any")) && eventRect[map][col][row].eventDone == false) {
                    hit = true;

                    previouseeventX = gp.player.worldX;
                    previouseeventY = gp.player.worldY;

                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefalutX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefalutY;
        }

        return hit;
    }
    public void DamagePit(int gamestate){


        gp.gameState=gamestate;
        gp.ui.currentDialoude="You fall into a pit";
        gp.player.life-=1;
        //eventRect[col][row].eventDone=true;
        canTouchEvent=false;
    }
    public void HealingPool(int gamestate){
        if(gp.keyH.EnterPressed==true){
            gp.gameState=gamestate;
            gp.player.attackCancel=true;
            gp.ui.currentDialoude="y drink the water.\nYour life and mana recovered";
            gp.player.life=gp.player.MaxLife;
            gp.player.mana=gp.player.maxMana;

        }

    }
}
