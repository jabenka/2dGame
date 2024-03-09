package main;

import jdk.jshell.spi.ExecutionControl;
import object.OBJ_Heart;
import object.Obj_ManaCrystal;
import Entity.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class UI {
    Graphics2D g2;
    //public int messageCounter=0;
    public boolean messageOn=false;
   //public String message="";
    public boolean GameFinish=false;
    public String currentDialoude="";
    public int commandNum=0;
    ArrayList<String> message=new ArrayList<>();
    ArrayList<Integer> messageCounter=new ArrayList<>();
    public int SlotCol=0;
    public int SlotRow=0;
    int substate=0;
    GamePannel gp;
    Font pixelfont;
    Font font_80BIG;
    public int titleScreenState=0;
    Image heart_full,heart_half,heart_blank;
    BufferedImage crystal_f,crystal_b;
    int counter=0;

    public UI(GamePannel gp){



        this.gp=gp;

        try( InputStream is=getClass().getResourceAsStream("/Fonts/upheavtt.ttf")) {
            pixelfont = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (FontFormatException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }


        //HUD
        Entity heart=new OBJ_Heart(gp);
        heart_full=heart.down1;
        heart_half=heart.image2;
        heart_blank=heart.image3;
        Entity crystal=new Obj_ManaCrystal(gp);
        crystal_f=crystal.down1;
        crystal_b=crystal.image2;



    }
    public void draw(Graphics2D g2) {

        this.g2=g2;

        g2.setFont(pixelfont);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);


        if(gp.gameState==gp.playState){
            drawPlayerLife();
            drawMessage();

        }
        if(gp.gameState==gp.pauseState){
            drawPlayerLife();
            drawPausedScreen();

        }
        if(gp.gameState==gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();

        }
        if(gp.gameState==gp.titleScreen){
            drawTitleScreen();
        }
       if(gp.gameState==gp.DeathState){
            drawDeathScreen();
       }
        if(gp.gameState==gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        if(gp.gameState==gp.OptionState){
            drawOptionScreen();
        }
        if(gp.gameState==gp.transitionState){
            drawTransition();
        }

    }

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        if(counter==50){
            counter=0;
            gp.gameState=gp.playState;
            gp.CurretnMap=gp.eHandler.tempMap;
            gp.player.worldX=gp.eHandler.tempmapCol*gp.tilesize;
            gp.player.worldY=gp.eHandler.tempmapRow*gp.tilesize;
            gp.eHandler.previouseeventX=gp.player.worldX;
            gp.eHandler.previouseeventY=gp.player.worldY;
        }
    }

    public void drawOptionScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int frameX=gp.tilesize*6;
        int frameY=gp.tilesize;
        int frameWidth=gp.tilesize*8;
        int frameHeight=gp.tilesize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight,0,0,0,200);



        switch (substate){
            case 0:
                Options_Top(frameX,frameY);
                break;
            case 1:
                options_fullscreenNotification(frameX,frameY);
                break;
            case 2:
                options_control(frameX,frameY);
                break;
            case 3:
                options_quit(frameX,frameY);
                break;
        }

        gp.keyH.EnterPressed=false;

    }

    public void options_quit(int frameX, int frameY) {
        int textX=frameX+gp.tilesize;
        int textY=frameY+gp.tilesize*3;
        g2.setFont(g2.getFont().deriveFont(22F));
        currentDialoude="Quit the game and \nreturn to the title screen?";
        for(String line:currentDialoude.split("\n")){
            g2.drawString(line,textX,textY);
            textY+=40;
        }
        String text="Yes";
        textX=GetXCentered(text);
        textY+=gp.tilesize*3;
        g2.drawString(text,textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.EnterPressed==true){
                substate=0;
                gp.gameState=gp.titleScreen;
            }
        }
        text="No";
        textX=GetXCentered(text);
        textY+=gp.tilesize;
        g2.drawString(text,textX,textY);
        if(commandNum==1){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.EnterPressed==true){
                substate=0;
                commandNum=4;
            }
        }



    }

    public void Options_Top(int frameX, int frameY) {
        int textX;
        int textY;


        String text="Options";
        textX=GetXCentered(text);
        textY=frameY+gp.tilesize;
        g2.drawString(text,textX,textY);
        g2.setFont(g2.getFont().deriveFont(24F));

        //FULLSCREEN ON/OFF
        textX=frameX+gp.tilesize;
        textY+=gp.tilesize*2;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-30,textY);
            if(gp.keyH.EnterPressed==true){
                if(gp.fullscreeenOn==false){
                    gp.fullscreeenOn=true;
                }
                else if(gp.fullscreeenOn==true){
                    gp.fullscreeenOn=false;
            }
                substate=1;
            }
        }


        //MUSIC
        textY+=gp.tilesize;
        g2.drawString("Music",textX,textY);
        if(commandNum==1){
            g2.drawString(">",textX-30,textY);
        }


        //SE

        textY+=gp.tilesize;
        g2.drawString("Sound Effects",textX,textY);
        if(commandNum==2){
            g2.drawString(">",textX-30,textY);
        }
        //CONTROL

        textY+=gp.tilesize;
        g2.drawString("Controls",textX,textY);
        if(commandNum==3){
            g2.drawString(">",textX-30,textY);
            if(gp.keyH.EnterPressed==true){
                substate=2;
                commandNum=0;
            }
        }

        //ENDGAME


        textY+=gp.tilesize;
        g2.drawString("Quit",textX,textY);
        if(commandNum==4){
            g2.drawString(">",textX-30,textY);
            if(gp.keyH.EnterPressed==true){
                substate=3;
                commandNum=0;
            }
        }


        textY+=gp.tilesize*2;
        g2.setFont(g2.getFont().deriveFont(28F));
        g2.drawString("Back",textX,textY);
        if(commandNum==5){
            g2.drawString(">",textX-30,textY);
            if(gp.keyH.EnterPressed==true){
                gp.gameState=gp.playState;
                commandNum=0;
            }
        }

        //FULLSCREEN CHECK BOX
        textX=frameX+(int)(gp.tilesize*5);
        textY=frameY+gp.tilesize*2+30;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if(gp.fullscreeenOn==true){
            g2.fillRect(textX,textY,24,24);
        }

        //MUSIC volume
        textY+=gp.tilesize;
        g2.drawRect(textX,textY,120,24); // 120/5=24
        int volumeWidth=24*gp.sound.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);
        //SE volume
        textY+=gp.tilesize;
        g2.drawRect(textX,textY,120,24);
        volumeWidth=24*gp.soundEf.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);


        gp.config.saveConfig();
    }
    public void options_fullscreenNotification(int frameX,int framey){
        int textX=frameX+gp.tilesize;
        int textY=framey+gp.tilesize*3;
            g2.setFont(g2.getFont().deriveFont(20F));
        currentDialoude="The change will affect \nafter restarting the game";
        for(String line: currentDialoude.split("\n")) {
            g2.drawString(line, textX, textY);
            textY+=gp.tilesize;
        }
        //Back
        textY+=gp.tilesize*3;
        g2.setFont(g2.getFont().deriveFont(28F));
        g2.drawString("Back",textX,textY);
        if(commandNum==0){
            g2.setFont(g2.getFont().deriveFont(25F));
            g2.drawString(">",textX-30,textY);
            if(gp.keyH.EnterPressed==true){
                substate=0;
            }
        }



    }
    public void options_control(int frameX,int frameY){
        int textX;
        int textY;
        String text="Controls";
        textX=GetXCentered(text);
        textY=frameY+gp.tilesize;
        g2.drawString(text,textX,textY);
        g2.setFont(g2.getFont().deriveFont(24F));

        textX=frameX+gp.tilesize-30;
        textY+=gp.tilesize;
        g2.drawString("Move",textX,textY);
        textY+=gp.tilesize;

        g2.drawString("Interact/Attack",textX,textY);
        textY+=gp.tilesize;

        g2.drawString("Cast",textX,textY);
        textY+=gp.tilesize;

        g2.drawString("Stats",textX,textY);
        textY+=gp.tilesize;

        g2.drawString("Pause",textX,textY);
        textY+=gp.tilesize;

        g2.drawString("Options",textX,textY);

        //BACK
        textX=frameX+gp.tilesize;
        textY=frameY+gp.tilesize*9;
        g2.drawString("Back",textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.EnterPressed==true){
                substate=0;
                commandNum=3;
            }
        }

        textX=frameX+gp.tilesize*6-15;
        textY=frameY+gp.tilesize*2;
        g2.drawString("WASD",textX,textY);
        textY+=gp.tilesize;
        g2.drawString("ENTER",textX,textY);
        textY+=gp.tilesize;
        g2.drawString("F",textX,textY);
        textY+=gp.tilesize;
        g2.drawString("C",textX,textY);
        textY+=gp.tilesize;
        g2.drawString("P",textX,textY);
        textY+=gp.tilesize;
        g2.drawString("ESCAPE",textX,textY);
        textY+=gp.tilesize;















    }
    public void drawInventory() {
            //FARME
        int frameX=gp.tilesize*12;
        int frameY=gp.tilesize;
        int frameWidth=gp.tilesize*6;
        int frameHeight=gp.tilesize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight,0,0,0,200);
        //SLOT
        final int SlotXStart=frameX+20;
        final int SlotYStart=frameY+20;
        int SlotX=SlotXStart;
        int SlotY=SlotYStart;
        int SlotSize=gp.tilesize+3;

        //CURSOR
        int cursorX=SlotXStart+(SlotSize*SlotCol);
        int cursorY=SlotYStart+(SlotSize*SlotRow);
        int cursorWidth=SlotSize;
        int cursorHeigth=SlotSize;




        //DRAW ITEMS
        for(int i=0;i<gp.player.Inventory.size();i++){
            if(gp.player.Inventory.get(i)!=null) {
                if(gp.player.Inventory.get(i)==gp.player.currentWeapon || gp.player.Inventory.get(i)==gp.player.currentShield){
                    g2.setColor(new Color(240,190,90));
                    g2.fillRoundRect(SlotX,SlotY,gp.tilesize,gp.tilesize,10,10);
                }


                g2.drawImage(gp.player.Inventory.get(i).down1, SlotX, SlotY, null);

                SlotX += SlotSize;

                if (i == 4 || i == 9 || i == 14) {

                    SlotX = SlotXStart;
                    SlotY += SlotSize;

                }
            }
        }


        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeigth,10,10);
        g2.setStroke(new BasicStroke(3));


        int dFrameX=frameX;
        int dFrameY=frameY+frameHeight;
        int dFrameWidth=frameWidth;
        int dFrameHeigth=gp.tilesize*3;

        //DESCRIPTION TEXT
        int textX=dFrameX+13;
        int textY=dFrameY+gp.tilesize-15;
        int itemIndex=getItemIndex();
        if(itemIndex<gp.player.Inventory.size()) {
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeigth,0,0,0,200);
            g2.setFont(g2.getFont().deriveFont(20F));
            for (String line : gp.player.Inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY+=24;
            }
        }

    }
    public int getItemIndex(){
        int itemIndex=SlotCol+SlotRow*5;
        return itemIndex;
    }
    public void drawMessage() {

        int messageX=gp.tilesize;
        int messageY=gp.tilesize*4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));

        for(int i=0;i<message.size();i++){

            if(message.get(i)!=null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+2,messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i),messageX,messageY);
                int counter=messageCounter.get(i)+1;
                messageCounter.set(i,counter);
                messageY+=50;

                if(messageCounter.get(i)>180){
                    message.remove(i);
                    messageCounter.remove(i);
                }

            }

        }


    }
   public void drawDeathScreen(){
        gp.StopMusic();
        Color c=new Color(0,0,0);

        g2.setColor(c);
        g2.fillRect(0,0, gp.ScreenWidth, gp.ScreenHeight);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "YOU DIED!";
        int x = GetXCentered(text);
        int y = gp.tilesize * 3;



        g2.setColor(Color.ORANGE);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.YELLOW);
        g2.drawString(text, x, y);
        //RETRY
        g2.setFont(g2.getFont().deriveFont(50F));
        text="Retry";
        x=GetXCentered(text);
        y+=gp.tilesize*4;
        g2.drawString(text, x, y);
        if(commandNum==0){
            g2.drawString(">", x-40, y);
        }
        text="Quit";
        x=GetXCentered(text);
        y+=gp.tilesize;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-40, y);
        }



    }
     public void drawPlayerLife(){
        int x=gp.tilesize/2;
        int y=gp.tilesize/2;
        int i=0;

        //BLANK HEARTS
        while(i<gp.player.MaxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+=gp.tilesize;
        }

        x=gp.tilesize/2;
        y=gp.tilesize/2;
        i=0;

        //CURRENT LIFE
         while(i<gp.player.life){
             g2.drawImage(heart_half,x,y,null);
             i++;
             if(i<gp.player.life){
                 g2.drawImage(heart_full,x,y,null);
             }
             i++;
             x+=gp.tilesize;
         }

         //MANA
         x=gp.tilesize/2-5;
         y= (int)((int)gp.tilesize*1.5);
         i=0;
         while(i< gp.player.maxMana){
             g2.drawImage(crystal_b,x,y,null);
             i++;
             x+=30;
         }
         x=gp.tilesize/2-5;
         y= (int)((int)gp.tilesize*1.5);
         i=0;
         while(i<gp.player.mana){
             g2.drawImage(crystal_f,x,y,null);
             i++;
             x+=30;
         }

     }
    public void drawPausedScreen(){
        //PAUSE STATE
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
        String text="PAUSED";
        int x=GetXCentered(text);
        int y=gp.ScreenHeight/2;

        g2.drawString(text,x,y);
        //DIALOGUE
        if(gp.gameState==gp.dialogueState){
            drawDialogueScreen();
        }




    }
    public void drawDialogueScreen(){
        //DialogueWINIDOW
        int x=gp.tilesize*2;
        int y=gp.tilesize/2;
        int width=gp.ScreenWidth-(gp.tilesize*4);
        int height=gp.tilesize*4;
        drawSubWindow(x,y,width,height,0,0,0,255);

        x+=gp.tilesize;
        y+=gp.tilesize;



        for(String line:currentDialoude.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
    }
    public void drawCharacterScreen() {
        //CREATE A FRAME
        final int frameX=gp.tilesize*2;
        final int frameY=gp.tilesize;
        final int frameWidth=gp.tilesize*5;
        final int frameHeight=gp.tilesize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight,0,0,0,200);
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));
        int textX=frameX+gp.tilesize/2;
        int textY=frameY+gp.tilesize;
        final int lineheight=35;

        //Names
        g2.drawString("Level",textX,textY);
        textY+=lineheight;
        g2.drawString("Life",textX,textY);
        textY+=lineheight;
        g2.drawString("Mana",textX,textY);
        textY+=lineheight;
        g2.drawString("Strength",textX,textY);
        textY+=lineheight;
        g2.drawString("Agility",textX,textY);
        textY+=lineheight;
        g2.drawString("Attack",textX,textY);
        textY+=lineheight;
        g2.drawString("Defense",textX,textY);
        textY+=lineheight;
        g2.drawString("Exp",textX,textY);
        textY+=lineheight;
        g2.drawString("Next level",textX,textY);
        textY+=lineheight;
        g2.drawString("Money",textX,textY);
        textY+=lineheight;
        g2.drawString("Weapon",textX,textY);
        textY+=lineheight;
        g2.drawString("Shield",textX,textY);

        //Values
        int tailX=(frameX+frameWidth)-30;
        textY=frameY+gp.tilesize;   //reset textY
        String value;
        g2.setFont(g2.getFont().deriveFont(28F));

        value=String.valueOf(gp.player.level);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.life+"/"+gp.player.MaxLife);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.mana+"/"+gp.player.maxMana);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.strenght);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.agility);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.attack);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.defense);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.exp);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.nextlevelExp);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

        value=String.valueOf(gp.player.money);
        textX=getXAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineheight;

       g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tilesize,textY-gp.tilesize+16,null);
        textY+=lineheight;

        g2.drawImage(gp.player.currentShield.down1,tailX-gp.tilesize,textY-gp.tilesize+16,null);





    }
    public int getXAlignToRightText(String text,int taliX){
        int len=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=taliX-len;
        return x;
    }
    public void drawSubWindow(int x,int y,int widht,int height,int r,int g,int b,int alpha){

        Color c=new Color(r,g,b,alpha);

        g2.setColor(c);
        g2.fillRoundRect(x,y,widht,height,35,35);

        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28));
        g2.drawRoundRect(x+5,y+5,widht-10,height-10,25,25);


    }
    public int GetXCentered(String text){
        int len=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.ScreenWidth/2-len/2;
        return x;
    }
    public void AddMessage (String text){

                message.add(text);
                messageCounter.add(0);

    }
    public void drawTitleScreen(){


        if(titleScreenState==0) {

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);

            //TITLE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "GAME TITLE";
            int x = GetXCentered(text);
            int y = gp.tilesize * 3;


            //SHADOW
            g2.setColor(Color.GRAY);
            g2.drawString(text, x + 5, y + 5);
            //TEXT
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);


            //PERSON IMAGE
            x = gp.ScreenWidth / 2 - gp.tilesize;
            y += gp.tilesize * 2 - gp.tilesize;
            g2.drawImage(gp.player.down1, x, y, gp.tilesize * 2, gp.tilesize * 2, null);


            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = GetXCentered(text);
            y += gp.tilesize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tilesize, y);
            }

            text = "LOAD GAME";
            x = GetXCentered(text);
            y += gp.tilesize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tilesize, y);
            }

            text = "QUIT";
            x = GetXCentered(text);
            y += gp.tilesize * 1.4;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tilesize, y);
            }
        }


    }
}

