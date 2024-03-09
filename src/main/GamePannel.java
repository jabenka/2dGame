package main;

import Entity.Entity;
import Entity.Player;
import tile.TileManager;
import tile_Interctive.InterctiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePannel extends JPanel implements Runnable {
    //screen settings
    final int ortilesize=16;//16 pixels*16 pixels
    final int scale=3;//it becomes 48*48(16*3=48)
    public final int tilesize=ortilesize*scale;
    public final int maxScreenCol=20;
    public final int maxScreenRow=12;
    public final int ScreenWidth=tilesize*maxScreenCol; //960pixels
    public final int ScreenHeight=tilesize*maxScreenRow; //576 pixels
    public boolean fullscreeenOn=false;
    //FULLSCREEN
    int screenWidth=ScreenWidth;
    int screenHeight=ScreenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;


    //World Settings
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int maxMap=10;
    public int CurretnMap=0;


    public KeyHadler keyH=new KeyHadler(this);
    Thread gameThread;
    public Player player=new Player(this,keyH);
    public CollisionChecker cCheker=new CollisionChecker(this);
    int FPS=60;


    TileManager tileM=new TileManager(this);

    //ENTITIES AND OTHER
    public Entity obj[][]=new Entity[2][20];
    public AssetSetter aSetter=new AssetSetter(this);

    public  Entity NPC[][]=new Entity[maxMap][10];
    ArrayList<Entity> entityList=new ArrayList<Entity>();
    public Entity monster[][]=new Entity[maxMap][20];
    public ArrayList<Entity> projectileList=new ArrayList<Entity>();
    public InterctiveTile iTile[][]=new InterctiveTile[maxMap][50];
    public ArrayList<Entity> particleList=new ArrayList<Entity>();


    //UI
    public UI ui=new UI(this);

    //EVENT
    public EventHandler eHandler=new EventHandler(this);


    //SOUND
    Sound sound=new Sound();
    Sound soundEf=new Sound();


    //GAMESTATUS

    public int gameState;
    public final int DeathState=6;
    public final int titleScreen=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int characterState=4;
    public final int OptionState=5;
    public final int transitionState=7;
    

    Config config=new Config(this);




    public GamePannel(){
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void SetupGame(){
        aSetter.SetObject();
        aSetter.SetNPC();
        aSetter.SetMonster();
        aSetter.setInterTile();

        gameState=titleScreen;



        tempScreen=new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);//GAME
        g2=(Graphics2D) tempScreen.getGraphics();
        if(fullscreeenOn==true) {
            setFullScreen();
        }

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setFullScreen(){
        //GET LOCAL SCREN DEVICE
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double width=screenSize.getWidth();
        double height=screenSize.getHeight();
        main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth=(int)width;
        screenHeight=(int) height;

    }
    public void Retry(){
        player.setDefaultPos();
        player.restoreLifeandMana();
        aSetter.SetMonster();
        aSetter.SetNPC();
        playMusic(0);


    }
    public void Restart(){
        player.setDefaultValues();
        aSetter.SetObject();
        aSetter.SetNPC();
        aSetter.SetMonster();
        aSetter.setInterTile();
        playMusic(0);
    }
    @Override
    public void run()   //game loop
    {
        double drawInterval=1000000000/FPS;
        double delta=0;




        long CurrentTime;
        long lastTime=System.nanoTime();

        long timer=0;
        long drawCount=0;

        while(gameThread!=null) //updating info then draw the infooo
        {

            CurrentTime=System.nanoTime();

            timer+=(CurrentTime-lastTime);
            delta+=(CurrentTime-lastTime)/drawInterval;
            lastTime=CurrentTime;
            if(delta>=1){
                update();
                DrawToTempScreen();
                DrawToScreen();
                delta--;
                drawCount++;
            }
            if(timer>=1000000000){
                System.out.println("FPS:"+drawCount);
                timer=0;
                drawCount=0;
            }

        }
    }
    public void update(){
        if(gameState==playState) {
            //PLAYER
            player.update();
            //NPC
            for(int i=0;i<NPC[1].length;i++){
                if(NPC[CurretnMap][i]!=null){
                    NPC[CurretnMap][i].update();
                }
            }
            //MONSTERS
            for(int i=0;i<monster[1].length;i++){
                if(monster[CurretnMap][i]!=null){
                    if(monster[CurretnMap][i].alive==true&&monster[CurretnMap][i].dying==false) {
                        monster[CurretnMap][i].update();
                    }
                    if(monster[CurretnMap][i].alive==false){
                        monster[CurretnMap][i].CheckDrop();
                        monster[CurretnMap][i]=null;
                    }
                }
            }

            //PROJECTILES
            for(int i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    if(projectileList.get(i).alive==true) {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive==false){
                       projectileList.remove(i);
                    }
                }
            }
            for(int i=0;i<iTile[1].length;i++){
                if(iTile[CurretnMap][i]!=null){
                    iTile[CurretnMap][i].update();
                }
            }
            for(int i=0;i<particleList.size();i++){
                if(particleList.get(i)!=null){
                    if(particleList.get(i).alive==true) {
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive==false){
                        particleList.remove(i);
                    }
                }
            }
        }
        if(gameState==pauseState){
            //nothing
        }
    }
    public void DrawToTempScreen(){
        //TITLESCREEN
        if (gameState == titleScreen) {
            ui.draw(g2);
        } else {
            //TILE
            tileM.draw(g2);
            for(int i=0;i<iTile[1].length;i++){
                if(iTile[CurretnMap][i]!=null){
                    iTile[CurretnMap][i].draw(g2);
                }
            }

            entityList.add(player);

            for(int i=0;i<NPC[1].length;i++){
                if(NPC[CurretnMap][i]!=null){
                    entityList.add(NPC[CurretnMap][i]);
                }
            }
            for(int i=0;i<obj[1].length;i++){
                if(obj[CurretnMap][i]!=null){
                    entityList.add(obj[CurretnMap][i]);
                }
            }
            for(int i=0;i<monster[1].length;i++){
                if(monster[CurretnMap][i]!=null){
                    entityList.add(monster[CurretnMap][i]);
                }
            }
            for(int i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    entityList.add(projectileList.get(i));
                }
            }
            for(int i=0;i<particleList.size();i++){
                if(particleList.get(i)!=null){
                    entityList.add(particleList.get(i));
                }
            }
            //SORTING CORRECTING
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY,e2.worldY);


                    return result;
                }
            });
            //DRAW ENTETIES
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            entityList.clear();
            //UI
            ui.draw(g2);
        }
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void StopMusic(){
        sound.stop();
    }
    public void playSoundEffect(int i){
        soundEf.setFile(i);
        soundEf.play();


    }
    public void DrawToScreen(){
        Graphics g=getGraphics();
        g.drawImage(tempScreen,0,0,screenWidth,screenHeight,null);
        g.dispose();
    }
}