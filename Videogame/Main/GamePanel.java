/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* GamePanel.java
* Run the animation of the game
*/
package Videogame.Main;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Videogame.Observer.GameKeys;
import Videogame.State.GameContext;

public class GamePanel extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    private GameContext gameContext;
    
    // Graphics
    private Graphics dbg;
    private Image dbImage = null;
    
    // Animation Control
    private volatile boolean isRunning;
    
    // Size of the panel
    private static final int PanelWidth = 1500; //1920
    private static final int PanelHeight = 700; //1080
    
    // FPS Control
    private int kFPS = 60;
    private long period = 1000/kFPS; 

    public GamePanel(GameContext gameContext){
        this.gameContext = gameContext;
        
        setBackground(Color.white);
        setPreferredSize(new Dimension(PanelWidth, PanelHeight));
        setFocusable(true);
        requestFocus();
        addKeyListener(GameKeys.getInstance());
    }
    
    // Start game
    public void addNotify(){
        super.addNotify();
        this.startGame();
    }
    private void startGame(){
        ExecutorService application = Executors.newCachedThreadPool();
        application.execute(this);
        application.shutdown();
    }
    
    // Run animation
    @Override
    public void run() {
        System.out.println("GamePanel Thread Started Running The Animation");
        long startTime, endTime, elapsedTime, sleepTime, oversleptTime=0L, excessTime = 0L;
        int kDelaysPerYield = 16, kMaxFrameSkips = 5;
        int delays = 0, skips;
        startTime = System.nanoTime(); // High resolution timer
        isRunning = true;
        while(isRunning) {
            this.gameUpdate();
            this.gameRender();
            this.paintScreen();
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            sleepTime = (period - elapsedTime) - oversleptTime;
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime/1000000L); // Convert from nanoseconds to milliseconds
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                // Check if thread slept more than expected
                oversleptTime = System.nanoTime()-endTime-sleepTime;
            }else {
                // No time for sleeping this loop, loop took more than expected to execute
                // SleepTime is negative
                excessTime -= sleepTime;
                oversleptTime = 0L;
                if(++delays >= kDelaysPerYield) {
                    Thread.yield(); // Give other threads chance to run if there have been 16 or more delays
                    delays = 0;
                }
            }
            startTime = System.nanoTime();
            skips = 0;
            
            // Update game state without rendering to not slow the game processing
            while(excessTime > period && skips <= kMaxFrameSkips) {
                //this saved one period
                excessTime -= period;
                this.gameUpdate();
                skips++;
            }
        }
    }
    private void gameUpdate() {
        gameContext.gameUpdate();
    }
    private void gameRender() {
        if(dbImage == null){
            dbImage = createImage(PanelWidth,PanelHeight);
            if(dbImage == null) {
                System.out.println("dbImage is null");
                return;
            }else {
                dbg = dbImage.getGraphics();
            }
        }
        gameContext.gameRender(dbg,PanelWidth,PanelHeight);
    }
    private void paintScreen() {
        Graphics g;
        try{
            g = this.getGraphics();
            if((g != null) && (dbImage != null)){
                g.drawImage(dbImage,0,0,null);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        catch(Exception e){
            System.out.println("Graphics context error: "+e);
        }
    }
    public void stopAnimation(){
        System.out.println("GamePanel Thread Stop Running");
        System.out.println("Boss Thread Stop Running");
        isRunning = false;
    }
}
