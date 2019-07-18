PImage how, menu, l1, l2, l3, cl1, cl2, cl3;
// For How to play image
float howstartTime;
boolean howTimer = false;
int n = 0;
PFont f;   // For Font
// For Level 1
float l1startTime;
boolean l1Timer = false;
boolean Level1 = false;
float x1 = 150;
float y1 = 150;
float speedX1 = random(3, 5);
float speedY1 = random(3, 5);
int lC = 128;
int rC = 128;
int rectSize1 = 150;
int Score1 = 0;
// For Level 2
float l2startTime;
boolean l2Timer = false;
boolean Level2 = false;
float x2 = 150;
float y2 = 150;
float speedX2 = random(3, 5);
float speedY2 = random(3, 5);
int lC2 = 128;
int rC2 = 128;
int rectSize2 = 150;
int Score2 = 0;
// For Level 3
float l3startTime;
boolean l3Timer = false;
boolean Level3 = false;
float x3 = 150;
float y3 = 150;
float speedX3 = random(3, 5);
float speedY3 = random(3, 5);
int lC3 = 128;
int rC3 = 128;
int rectSize3 = 150;
int Score3 = 0;
// For Sound
Maxim maxi;
AudioPlayer player;
AudioPlayer player2;
AudioPlayer player3;

void setup(){
  size(585,480);
  noStroke();
  smooth();
  ellipseMode(CENTER);
  rectMode(CORNER);
  maxi = new Maxim(this);
  player = maxi.loadFile("shot.wav");
  player.setLooping(false);
  player2 = maxi.loadFile("game.wav");
  player2.setLooping(false);
  player3 = maxi.loadFile("clap.wav");
  player3.setLooping(false);
  f = loadFont( "NeonPixel-7-32.vlw" );
  l1 = loadImage("Level1.jpg");
  l2 = loadImage("Level2.jpg");
  l3 = loadImage("Level3.jpg");
  how = loadImage("how.jpg");
  menu = loadImage("menu.jpg");
  cl1 = loadImage("compl1.jpg");
  cl2 = loadImage("compl2.jpg");
  cl3 = loadImage("compl3.jpg");
}
void draw(){
  // For how to play image
  if(howTimer) {
    float howTime = millis();
    // the how to play image hold for 3 seconds
    if(howTime - howstartTime > 3000.0f) {
      howTimer = false;
      n = 0;
    } 
  }
  // the level 1 completed image hold for 3 seconds
  if(l1Timer) {
    float l1Time = millis();
    if(l1Time - l1startTime > 3000.0f) {
      l1Timer = false;
      Level1 = false;
      Level2 = true;
    } 
  }
  // the level 2 completed image hold for 3 seconds
  if(l2Timer) {
    float l2Time = millis();
    if(l2Time - l2startTime > 3000.0f) {
      l2Timer = false;
      Level2 = false;
      Level3 = true;
    } 
  }
  // the level 3 completed image hold for 3 seconds
  if(l3Timer) {
    float l3Time = millis();
    if(l3Time - l3startTime > 3000.0f) {
      l3Timer = false;
      Level1 = false;
      Level2 = false;
      Level3 = false;
      n = 0;
    } 
  }
  if(n == 0){
    image(menu,0,0);
    n = 1;
  }
  // level 1 function
  if(Level1){
  Level_1();
  }
  // level 2 function
  if(Level2){
  Level_2();
  }
  // level 3 function
  if(Level3){
  Level_3();
  }
  //level 1 completed
  if(Score1 == 10){
      Score1 = 0;
      l1startTime = millis();
      l1Timer = true;
      image(cl1,0,0);
   }
   //level 2 completed
   if(Score2 == 20){
      Score2 = 0;
      l2startTime = millis();
      l2Timer = true;
      image(cl2,0,0);
   }
   //level 3 completed
   if(Score3 == 20){
      Score3 = 0;
      l3startTime = millis();
      l3Timer = true;
      image(cl3,0,0);
   }
}
void Level_1(){
    textFont(f,24);
    fill(0);
    image(l1,0,0);
    text ( "Score = "+ Score1, 470, 45);
    fill(255);
    ellipse(x1, y1, 30, 30);
    fill(lC);
    rect(0, 0, width, 20);
    fill(rC);
    rect(mouseX-rectSize1/2, height-30, rectSize1, 10);
    x1 = x1 + speedX1;
    y1 = y1 + speedY1;
 
    // if ball hits down pad, invert Y direction and apply effects
    if ( y1 > height-40 && y1 < height+20 && x1 > mouseX-rectSize1/2 && x1 < mouseX+rectSize1/2 ) {
      Score1++;
      player.play();
      speedY1 = speedY1 * -1;
      y1 = y1 + speedY1;
      rC = 0;
      rectSize1 = rectSize1-5;
      rectSize1 = constrain(rectSize1, 10,150);     
    }
    
    // if ball hits upper pad, change direction of Y
    if (y1 < 30) {
      player.play();
      speedY1 = speedY1 * -1.1;
      y1 = y1 + speedY1;
      lC = 0;
    }
    // if ball hits right or left, change direction of X
    else if (x1 < 10 || x1 > width-10) {
      player.play();
      speedX1 = speedX1 * -1.1;
      x1 = x1 + speedX1;
    }
    else {    
      lC = 128;
      rC = 128;
    }
    
    // user win Level 1
    if(Score1 == 10){
      player3.play();
      Level1 = false;
      x1 = 150;
      y1 = 150;
      speedX1 = random(3, 5);
      speedY1 = random(3, 5);
      rectSize1 = 150;
    }
   
    // user lose the game
    if ( y1 > height-10 ) {
       Score1 = 0;
       n = 0;
       player2.play();
       Level1 = false;
       x1 = 150;
       y1 = 150;
       speedX1 = random(3, 5);
       speedY1 = random(3, 5);
       rectSize1 = 150;
     }
}
void Level_2(){
    textFont(f,24);
    fill(0);
    image(l2,0,0);
    text ( "Score = "+ Score2, 455, 35);
    fill(255);
    ellipse(x2, y2, 30, 30);
    fill(lC2);
    rect(0, 0, 20, height);
    fill(rC2);
    rect(width-30, mouseY-rectSize2/2, 10, rectSize2);
    x2 = x2 + speedX2;
    y2 = y2 + speedY2;
 
    // if ball hits right pad, invert X direction and apply effects
    if ( x2 > width-40 && x2 < width +20 && y2 > mouseY-rectSize2/2 && y2 < mouseY+rectSize2/2 ) {
      Score2++;
      player.play();
      speedX2 = speedX2 * -1;
      x2 = x2 + speedX2;
      rC2 = 0;
      rectSize2 = rectSize2-5;
      rectSize2 = constrain(rectSize2, 10,150);     
    }
    // if ball hits left pad, change direction of X
    else if (x2 < 25) {
      player.play();
      speedX2 = speedX2 * -1.1;
      x2 = x2 + speedX2;
      lC2 = 0;
    }
    else {
      lC2 = 128;
      rC2 = 128;
    }
    // user win Level 2
    if(Score2 == 20){
      player3.play();
      Level2 = false;
      x2 = 150;
      y2 = 150;
      speedX2 = random(3, 5);
      speedY2 = random(3, 5);
      rectSize2 = 150;
    }
    // user lose the game
    if (x2 > width) {
      n = 0;
      Score2 = 0;
      player2.play();
      Level2 = false;
      x2 = 150;
      y2 = 150;
      speedX2 = random(3, 5);
      speedY2 = random(3, 5);
      rectSize2 = 150;
    }
    // if ball hits up or down, change direction of Y  
    if ( y2 > height-10 || y2 < 10 ) {
      player.play();
      speedY2 = speedY2 * -1;
      y2 = y2 + speedY2;
    }
}
void Level_3(){
    textFont(f,24);
    fill(0);  
    image(l3,0,0);
    text ( "Score = "+ Score3, 455, 35);
    fill(255);
    ellipse(x3, y3, 30, 30);
    fill(lC3);
    rect(20, mouseY-rectSize3/2, 10, rectSize3);
    fill(rC3);
    rect(width-30, mouseY-rectSize3/2, 10, rectSize3);
    x3 = x3 + speedX3;
    y3 = y3 + speedY3;
 
    // if ball hits right pad, invert X direction and apply effects
    if ( x3 > width-40 && x3 < width +20 && y3 > mouseY-rectSize3/2 && y3 < mouseY+rectSize3/2 ) {
      Score3++;
      player.play();
      speedX3 = speedX3 * -1;
      x3 = x3 + speedX3;
      rC3 = 0;
      rectSize3 = rectSize3-5;
      rectSize3 = constrain(rectSize3, 10,150);     
    }
    // if ball hits left pad, invert X direction and apply effects
    else if ( x3 < 40 && x3 < width-20 && y3 > mouseY-rectSize3/2 && y3 < mouseY+rectSize3/2) {
      player.play();
      speedX3 = speedX3 * -1.1;
      x3 = x3 + speedX3;
      lC3 = 0;
    }
    else {    
      lC3 = 128;
      rC3 = 128;
    }
    // user win Level 3
    if(Score3 == 20){
      player3.play();
      Level3 = false;
      x3 = 150;
      y3 = 150;
      speedX3 = random(3, 5);
      speedY3 = random(3, 5);
      rectSize3 = 150;
    }
    // user lose the game
    if (x3 < 0 || x3 > width) {
      Score3 = 0;
      n = 0;
      player2.play();
      Level3 = false;
      x3 = 150;
      y3 = 150;
      speedX3 = random(3, 5);
      speedY3 = random(3, 5);
      rectSize3 = 150;
    }
   // if ball hits up or down, change direction of Y  
    if ( y3 > height-10 || y3 < 10 ) {
      speedY3 = speedY3 * -1;
      y3 = y3 + speedY3;
    }
}
void mouseClicked(){
    // if user click on play
    if(mouseX > 255 && mouseX < 255+82 && mouseY < 280+38 && mouseY > 280){
      Level1 = true;
    }
    // if user click on how to play
    if(mouseX > 175 && mouseX < 175+240 && mouseY < 325+38 && mouseY > 325){
      howstartTime = millis();
      howTimer = true;
      image(how,0,0);
    }
    // if user click on exit
    if(mouseX > 255 && mouseX < 255+75 && mouseY < 370+38 && mouseY > 370){
      exit();
    }
}
