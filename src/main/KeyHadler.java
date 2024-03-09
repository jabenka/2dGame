package main;

import javax.swing.*;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHadler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }
    GamePannel gp;
    public KeyHadler(GamePannel gp){
        this.gp=gp;
    }
    public boolean Uppressed;
    public boolean Downpressed;
    public boolean Leftpressed;
    public boolean Rightpressed;
    public boolean EnterPressed;
    public boolean ShootPressed;

    @Override
    public void keyPressed(KeyEvent e) {


        int code=e.getKeyCode();
        //TITLESTATE
        if(gp.gameState==gp.titleScreen) {
            titleState(code);
        }
        //GAMESTATES
        else if(gp.gameState== gp.playState) {
            playState(code);
        }
        else if(gp.gameState==gp.dialogueState){
            dialogueState(code);
        }
        else if(gp.gameState==gp.pauseState){
           pausedState(code);
        }

        else if(gp.gameState==gp.characterState){
           characterState(code);
        }
        else if(gp.gameState==gp.OptionState){
            optionState(code);
        }
        else if(gp.gameState==gp.DeathState){
            DeathState(code);
        }


    }
    public void DeathState(int code){
        if(code==KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=1;
            }
        }
        if(code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum>1){
                gp.ui.commandNum=0;
            }
        }
        if(code==KeyEvent.VK_ENTER){
            if(gp.ui.commandNum==0){
                gp.gameState=gp.playState;
                gp.Retry();
            }
            if(gp.ui.commandNum==1){
                gp.gameState=gp.titleScreen;
                gp.Restart();
            }
        }
    }

    public void optionState(int code) {
        if(code==KeyEvent.VK_ESCAPE){
            gp.gameState=gp.playState;
        }
        if(code==KeyEvent.VK_ENTER){
            EnterPressed=true;
        }
        int maxCoomandNum=0;
        switch (gp.ui.substate){
            case 0:
                maxCoomandNum=5;
                break;
            case 3:maxCoomandNum=1;
                break;
        }
        if(code==KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=maxCoomandNum;
            }
        }
        if(code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum>maxCoomandNum){
                gp.ui.commandNum=0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if(gp.ui.substate==0) {
                if (gp.ui.commandNum == 1 && gp.sound.volumeScale > 0) {
                    gp.sound.volumeScale--;
                    gp.sound.checkVolume();
                }
                if (gp.ui.commandNum == 2 && gp.soundEf.volumeScale >0) {
                    gp.soundEf.volumeScale--;
                    gp.soundEf.checkVolume();
                }

            }


        }
        if(code==KeyEvent.VK_D){
            if(gp.ui.substate==0) {
                if (gp.ui.commandNum == 1 && gp.sound.volumeScale < 5) {
                    gp.sound.volumeScale++;
                    gp.sound.checkVolume();
                }

                    if (gp.ui.commandNum == 2 && gp.soundEf.volumeScale < 5) {
                        gp.soundEf.volumeScale++;
                    }
                }
            }



    }

    public void titleState(int code){
        if (gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commandNum) {
                    case 0:
                        gp.gameState=gp.playState;
                        gp.playMusic(0);

                        break;
                    case 1:


                        break;
                    case 2:
                        System.exit(0);

                }
            }
        }

//        //SECOND TITLE SCREEN
//        else if (gp.ui.titleScreenState == 1) {
//
//            if (code == KeyEvent.VK_W) {
//                gp.ui.commandNum--;
//                if (gp.ui.commandNum < 0) {
//                    gp.ui.commandNum = 3;
//                }
//            }
//            if (code == KeyEvent.VK_S) {
//                gp.ui.commandNum++;
//                if (gp.ui.commandNum > 3) {
//                    gp.ui.commandNum = 0;
//                }
//            }
//            if (code == KeyEvent.VK_ENTER) {
//                switch (gp.ui.commandNum) {
//                    case 0:
//                        //add later
//                        System.out.println("FIGHTER");
//                        gp.gameState = gp.playState;
//                        break;
//                    case 1:
//                        //add later
//                        System.out.println("THIEF");
//                        gp.gameState = gp.playState;
//                        break;
//                    case 2:
//                        //add later
//                        System.out.println("Soulreaper");
//                        gp.gameState = gp.playState;
//                    case 3:
//                        //add later
//                        System.out.println("Exit");
//                        gp.ui.titleScreenState = 0;
//
//                        break;
//
//                }
//            }


//        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            Uppressed = true;
        }
        if (code == KeyEvent.VK_S) {
            Downpressed = true;
        }
        if (code == KeyEvent.VK_D) {
            Rightpressed = true;
        }
        if (code == KeyEvent.VK_A) {
            Leftpressed = true;
        }

        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if(code==KeyEvent.VK_ENTER){
            EnterPressed=true;
        }
        if(code== KeyEvent.VK_C){
            gp.gameState=gp.characterState;
        }
        if(code==KeyEvent.VK_F){
            ShootPressed=true;
        }
    if(code==KeyEvent.VK_ESCAPE){
        gp.gameState=gp.OptionState;
    }
    }
    public void pausedState(int code){
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;

        }
    }
    public void dialogueState(int code){
        if(code==KeyEvent.VK_ENTER){
            gp.gameState=gp.playState;
        }
    }

    public void characterState(int code){
        if(code==KeyEvent.VK_C){
            gp.gameState=gp.playState;
        }
        if(code==KeyEvent.VK_W){
            if(gp.ui.SlotRow!=0) {
                gp.ui.SlotRow--;
            }
        }
        if(code==KeyEvent.VK_S){
            if(gp.ui.SlotRow!=3) {
                gp.ui.SlotRow++;
            }
        }
        if(code==KeyEvent.VK_A){
            if(gp.ui.SlotCol!=0) {
                gp.ui.SlotCol--;
            }
        }
        if(code==KeyEvent.VK_D) {
            if (gp.ui.SlotCol != 4) {

            gp.ui.SlotCol++;
        }
        }
        if(code==KeyEvent.VK_ENTER){
            gp.player.SelectItem();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code=e.getKeyCode();
        if(code==KeyEvent.VK_W)
        {
            Uppressed=false;
        }
        if(code==KeyEvent.VK_S)
        {
            Downpressed=false;
        }
        if(code==KeyEvent.VK_D)
        {
           Rightpressed=false;
        }
        if(code==KeyEvent.VK_A)
        {
            Leftpressed=false;
        }
        if(code==KeyEvent.VK_F){
            ShootPressed=false;
        }
    }
}
