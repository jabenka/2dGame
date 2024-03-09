package Entity;

import main.GamePannel;

public class Projectile extends Entity{

    Entity user;
    public Projectile(GamePannel gp){
    super(gp);

    }
    public void set(int worldX,int worldY,String direction,boolean alive,Entity user){

        this.worldX=user.worldX;
        this.worldY=user.worldY;
        this.direction=user.direction;
        this.alive=alive;
        this.user=user;
        this.life=this.MaxLife;
    }
    public void update() {


        if (user == gp.player) {
            int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex,attack);
                generateParticle(user.projectile,gp.monster[gp.CurretnMap][monsterIndex]);
                alive = false;
            }
        }
        if(user!=gp.player){
            boolean contactPlayer=gp.cCheker.CheckPlayer(this);
            if(gp.player.invincible==false && contactPlayer==true){
                DamagePlayer(attack);
                generateParticle(user.projectile,gp.player);
                alive=false;
            }
        }
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
            life--;
            if (life <= 0) {
                alive = false;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }



    }
}
