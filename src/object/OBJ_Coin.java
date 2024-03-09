package object;

import Entity.Entity;
import main.GamePannel;

public class OBJ_Coin extends Entity {
    GamePannel gp;
    public OBJ_Coin(GamePannel gp) {
        super(gp);
        name="Coin";
        this.gp=gp;
        type=type_pickuponly;
        value=1;
        down1=setup("/objects/coin_bronze.png",gp.tilesize,gp.tilesize);

    }
    public void use(Entity entity){
        gp.playSoundEffect(1);
        gp.ui.AddMessage("+"+value+" to money");
        gp.player.money+=value;


    }
}
