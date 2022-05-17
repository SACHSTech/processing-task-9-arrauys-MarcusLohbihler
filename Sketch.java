import processing.core.PApplet;

public class Sketch extends PApplet{ 
  int ballCount = 25;
  float playerX = 200;
  float playerY = 300;
  double playerRad = 15;
  float circleX;  
  float[] circleY = new float[ballCount];
  double circleRad = 12.5;
  double distance = 0;
  boolean[] ballHideStatus = new boolean[ballCount];
  int lifeCount = 3;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }
	

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    //Initializing locations of the snowballs in the array
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      ballHideStatus[i] = true;
    }
  }

  public void draw() {
    background(50);
    //for loop that controls snowball movement, collision detection and lives
    for (int i = 0; i < circleY.length; i++) {
      circleX = width * i / circleY.length;
      if(ballHideStatus[i] == true){
        stroke(0);
        fill(255);
        ellipse(circleX, circleY[i], 25, 25);
      }
      //initial movement speed of the circles
      circleY[i] += 2;
      //if up arrow is pressed, decreases snowball speed, if down arrow is pressed, increases snowball speed
      if(keyPressed){
        if(keyCode == UP){
          circleY[i] --;
        }else if(keyCode == DOWN){
          circleY[i] += 4;
        }
      }

      //if snowballs go offscreen, bring them back to the top, invisible circles reappear
      if (circleY[i] > height) {
        ballHideStatus[i] = true;
        circleY[i] = 0;
      }
  
      //if the player hits a snowball, the snowball disappears and the life count decreases by one, if the mouse is placed on top of a snowball, the snowball disappears
      if (ballHideStatus[i]) {
        if(playerX >= circleX - 12.5 && playerX <= circleX + 12.5 && playerY >= circleY[i] - 12.5 && playerY <= circleY[i] + 12.5){
          ballHideStatus[i] = false;
          lifeCount --;
        }else if(mouseX >= circleX - 12.5 && mouseX <= circleX + 12.5 && mouseY >= circleY[i] - 12.5 && mouseY <= circleY[i] + 12.5){
          ballHideStatus[i] = false;
        }
      }

      //shows how many lives are remaining, creates game over screen once the life count reaches zero or goes negative
      if(lifeCount == 3){
        fill(255, 0, 0);
        rect(310, 10, 20, 20);
        rect(340, 10, 20, 20);
        rect(370, 10, 20, 20);
      }else if(lifeCount == 2){
        fill(255, 0, 0);
        rect(340, 10, 20, 20);
        rect(370, 10, 20, 20);
      }else if(lifeCount == 1){
        fill(255, 0, 0);
        rect(370, 10, 20, 20);
      }else if(lifeCount <= 0){
        background(255);
        circleY[i] += 0;
        fill(0);
        String endGame = ("Game Over");
        textSize(50);
        text(endGame, 50, 200);
      }
    }
    //creates player
    fill(0,255,255);
    ellipse(playerX, playerY, 30, 30);

    //player movement controls
    if(keyPressed){
      if(key == 'a'){
        playerX -= 3;
      }else if(key == 'd'){
        playerX += 3;
      }else if(key == 'w'){
        playerY -= 3;
      }else if(key == 's'){
        playerY += 3;
      }
    }
    //if the player tries to go offscreen, bring them back to the starting point
    if(playerX > width || playerX < 0 || playerY > height || playerY < 0){
      playerX = 200;
      playerY = 300;
    }  
  }
}