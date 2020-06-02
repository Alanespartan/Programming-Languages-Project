# How to run it

1.- Check the source tree code

2.- Open the Server.java file inside the Server package, there is where the main method of the server is located

3.- Run the class

4.- Open the RunGame.java file inside the Videogame.Main package, there is where the main method to run the game is located

5.- Run the class

6.- Once the game window is open, press the enter key

7.-Start playing

# Source Tree Code

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

|.........| Management |

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

|
