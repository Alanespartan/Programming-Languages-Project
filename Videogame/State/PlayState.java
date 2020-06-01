/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* PlayState.java
* Defines behavior of playing in game
*/
package Videogame.State;

import Videogame.Figures.*;
import Videogame.HUD.HUD;
import Videogame.ImageLoader.ImageLoader;
import java.util.LinkedList;
import java.awt.*;

public class PlayState extends State {
    public Boolean flag = true;
    public Boss boss = new Boss();

    public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<Thunder> thunders = new LinkedList<Thunder>();

    @Override
    public synchronized void gameUpdate() {
        // Boss start shooting with a thread
        if (flag) {
            System.out.println("Boss Thread Start Atacking");
            boss.start();
            flag = false;
        }
        // Resume thread boss
        setPaused(false);
        
        // Get thunder and bullets (in case the player or boss shot more)
        bullets = Player.getInstance().getBullets();
        thunders = boss.getThunders();

        // Move thunders
        for (Thunder auxThunder : thunders) {
            int playerX2 = Player.getInstance().getX2();
            int playerY1 = Player.getInstance().getY1();
            int playerY2 = Player.getInstance().getY2();
            int thunderCenter = auxThunder.getY1() + auxThunder.getHeight() / 2;
            // Check if thunder is out of panel
            if (auxThunder.getX1() == 0) {
                thunders.remove(auxThunder);
                continue;
            }
            // Check collision with player
            if (thunderCenter >= playerY1 && thunderCenter <= playerY2) {
                if (auxThunder.getX1() <= playerX2) {
                    Player.getInstance().setHP(Player.getInstance().getHP() - 10);
                    if (Player.getInstance().getHP() <= 0) {
                        Player.getInstance().setIsDead(true);
                    }
                    thunders.remove(auxThunder);
                    continue;
                }
            }
            auxThunder.update();
        }

        // Move bullets
        for (Bullet auxBullet : bullets) {
            // Control collisions with boss
            if (auxBullet.getY2() >= 0 && auxBullet.getY2() <= 229) {
                if (auxBullet.getX2() >= 1130) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            if (auxBullet.getY2() >= 230 && auxBullet.getY2() <= 325) {
                if (auxBullet.getX2() >= 998) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            if (auxBullet.getY2() >= 326 && auxBullet.getY2() <= 349) {
                if (auxBullet.getX2() >= 1046) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            if (auxBullet.getY2() >= 350 && auxBullet.getY2() <= 385) {
                if (auxBullet.getX2() >= 1070) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            if (auxBullet.getY2() >= 386 && auxBullet.getY2() <= 415) {
                if (auxBullet.getX2() >= 1136) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            if (auxBullet.getY2() >= 416 && auxBullet.getY2() <= 565) {
                if (auxBullet.getX2() >= 1112) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            if (auxBullet.getY2() >= 566 && auxBullet.getY2() <= 643) {
                if (auxBullet.getX2() >= 1088) {
                    bullets.remove(auxBullet);
                    continue;
                }
            }
            auxBullet.update();
        }
    }

    @Override
    public void gameRender(final Graphics g, final int PanelWidth, final int PanelHeight) {
        // Draw background
        background = ImageLoader.getInstance().getImage("fondo");
        g.drawImage(background, 0, 0, PanelWidth, PanelHeight, null);

        // Draw boss and his thunders
        boss.render(g);
        for (final Thunder auxThunder : thunders) {
            auxThunder.render(g);
        }

        // Draw player and his bullets
        Player.getInstance().render(g);
        for (final Bullet auxBullet : bullets) {
            auxBullet.render(g);
        }

        // Draw HUD details (messages from server and more)
        HUD.getInstance().render(g);
    }
    @Override
    public void pause() {
        setPaused(true);
        gameContext.setCurrentState(gameContext.getPauseState());
    }
    @Override
    public void gameOver() {
        gameContext.setCurrentState(gameContext.getGameOverState());
    }
    public void setPaused(Boolean paused){
        boss.setPaused(paused);
    }
    @Override
    public void play() {}
}