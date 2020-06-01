# Programming-Languages-Project
Copyright (C) 2020 Juan Arturo Cruz Cardona

Final Project: 2D video game using a finite state machine, design patterns and threads.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.


## SRC tree
|
|  Server |
|.........|Server.java
|.........|ReadThread.java
|.........|WriteThread.java
|.........|UserThread.java
|Videogame|
|.........|  Factory  |
|.........|...........|Factory.java
|.........|  Figures  |
|.........|...........|MyGraphics.java
|.........|...........|Player.java
|.........|...........|Boss.java
|.........|...........|Thunder.java
|.........|...........|Bullet.java
|.........|    HUD    |
|.........|...........|HUD.java
|.........|ImageLoader|
|.........|...........|ImageLoader.java
|.........|    Main   |
|.........|...........|GamePanel.java
|.........|...........|RunGame.java
|.........| Managment |
|.........|...........|GameManager.java
|.........|  Observer |
|.........|...........|GameKeys.java
|.........|...........|Observer.java
|.........|...........|Subject.java
|.........| Resources |
|.........|...........|Backgrounds
|.........|...........|Gifs
|.........|...........|Images
|.........|...........|Music
|.........|   Sound   |
|.........|...........|MusicPlayer.java
|.........|   State   |
|.........|...........|GameContext.java
|.........|...........|State.java
|.........|...........|GameOverState.java
|.........|...........|PauseState.java
|.........|...........|PlayState.java
|.........|...........|MenuState.java
|  README.md