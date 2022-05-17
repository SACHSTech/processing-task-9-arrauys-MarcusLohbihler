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
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      ballHideStatus[i] = true;
    }
  }

  public void draw() {
    background(50);
  
    for (int i = 0; i < circleY.length; i++) {
      circleX = width * i / circleY.length;
      if(ballHideStatus[i] == true){
        stroke(0);
        fill(255);
        ellipse(circleX, circleY[i], 25, 25);
      }

      circleY[i] += 2;
      
      if(keyPressed){
        if(keyCode == UP){
          circleY[i] --;
        }else if(keyCode == DOWN){
          circleY[i] += 4;
        }
      }
      
      if (circleY[i] > height) {
        ballHideStatus[i] = true;
        circleY[i] = 0;
      }
  
      
      if (ballHideStatus[i]) {
        if(playerX >= circleX - 12.5 && playerX <= circleX + 12.5 && playerY >= circleY[i] - 12.5 && playerY <= circleY[i] + 12.5){
          ballHideStatus[i] = false;
          lifeCount --;
        }else if(mouseX >= circleX - 12.5 && mouseX <= circleX + 12.5 && mouseY >= circleY[i] - 12.5 && mouseY <= circleY[i] + 12.5){
          ballHideStatus[i] = false;
        }
      }
  
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
    
    fill(0,255,255);
    ellipse(playerX, playerY, 30, 30);
    
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
    if(playerX > width || playerX < 0 || playerY > height || playerY < 0){
      playerX = 200;
      playerY = 300;
    }  
  }
}