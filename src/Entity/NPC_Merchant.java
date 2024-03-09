package Entity;

import main.GamePannel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_PotionRed;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePannel gp){
        super(gp);
        direction="down";
        speed=1;
        getMerchantImage();
        SetDialouge();
        setItemMessage();



}
    public void getMerchantImage(){

        up1=setup("/NPC/merchant_down_1.png",gp.tilesize,gp.tilesize);
        up2=setup("/NPC/merchant_down_2.png",gp.tilesize,gp.tilesize);
        down1=setup("/NPC/merchant_down_1.png",gp.tilesize,gp.tilesize);
        down2=setup("/NPC/merchant_down_2.png",gp.tilesize,gp.tilesize);
        left1=setup("/NPC/merchant_down_1.png",gp.tilesize,gp.tilesize);
        left2=setup("/NPC/merchant_down_2.png",gp.tilesize,gp.tilesize);
        right1=setup("/NPC/merchant_down_1.png",gp.tilesize,gp.tilesize);
        right2=setup("/NPC/merchant_down_2.png",gp.tilesize,gp.tilesize);



    }
    public void SetDialouge(){

        dialogues[0]="Hi \nbro.";
        dialogues[1]="Hi bro.";
        dialogues[2]="Did we met before";
        dialogues[3]="anyway";



    }
    public void setItemMessage(){
        //Inventory.add(new OBJ_Key(gp));
        //Inventory.add(new OBJ_Axe(gp));
        //Inventory.add(new OBJ_PotionRed(gp));
    }

}
