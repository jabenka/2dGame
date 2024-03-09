package main;
import javax.swing.*;

public class main {
    public static  JFrame window;
    public static void main(String[] args){
        window=new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        GamePannel gamepanel=new GamePannel();
        window.add(gamepanel);

        gamepanel.config.loadConfig();
        if(gamepanel.fullscreeenOn==true){
            window.setUndecorated(true);

        }

        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.SetupGame();

        gamepanel.startGameThread();
    }
}
