package main;

import javax.management.StringValueExp;
import java.io.*;

public class Config
{
    GamePannel gp;

    public Config(GamePannel gp){
        this.gp=gp;
    }
    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            if(gp.fullscreeenOn==true){
                bw.write("On");
            }
            if(gp.fullscreeenOn==false){
                bw.write("Off");
            }
            bw.newLine();
            //MUSIC VOL
            bw.write(String.valueOf(gp.sound.volumeScale));
            bw.newLine();

            //SEVOL
            bw.write(String.valueOf(gp.soundEf.volumeScale));


            bw.newLine();
            bw.close();



        }catch (IOException e){
            e.printStackTrace();
        }
    }
public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s=br.readLine();

            //FULLSCREEN
            if(s.equals("On")){
                gp.fullscreeenOn=true;
            }

            if(s.equals("Off")){
                gp.fullscreeenOn=false;
            }

            //MUSIC
            s=br.readLine();
            gp.sound.volumeScale=Integer.parseInt(s);

            //SE
            s=br.readLine();
            gp.soundEf.volumeScale=Integer.parseInt(s);
            br.close();


        }catch (IOException e){
            e.printStackTrace();
        }
}
}
