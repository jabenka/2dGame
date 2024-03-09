package Entity;

import main.GamePannel;

import java.util.Random;

public class NPC_OLDman extends Entity{


    public NPC_OLDman(GamePannel gp) {
        super(gp);

        direction="down";
        speed=1;

       getOldManImage();
       SetDialouge();

    }
    public void setAction(){

        ActionClockCounter++;
        if(ActionClockCounter==120) {

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
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            ActionClockCounter=0;
        }


    }
    public void getOldManImage(){

        up1=setup("/NPC/oldman_up_1.png",gp.tilesize,gp.tilesize);
        up2=setup("/NPC/oldman_up_2.png",gp.tilesize,gp.tilesize);
        down1=setup("/NPC/oldman_down_1.png",gp.tilesize,gp.tilesize);
        down2=setup("/NPC/oldman_down_2.png",gp.tilesize,gp.tilesize);
        left1=setup("/NPC/oldman_left_1.png",gp.tilesize,gp.tilesize);
        left2=setup("/NPC/oldman_left_2.png",gp.tilesize,gp.tilesize);
        right1=setup("/NPC/oldman_right_1.png",gp.tilesize,gp.tilesize);
        right2=setup("/NPC/oldman_right_2.png",gp.tilesize,gp.tilesize);



    }

    public void SetDialouge(){

        dialogues[0]="Hi \nbro.";
        dialogues[1]="Hi bro.";
        dialogues[2]="Did we met before";
        dialogues[3]="anyway";



    }

    public void Speak(){
       super.Speak();






    }



}
