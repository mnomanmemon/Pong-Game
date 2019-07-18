import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.ArrayList; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.BufferedInputStream; 
import java.net.MalformedURLException; 
import java.net.URL; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Final_Project extends PApplet {

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

public void setup(){
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
public void draw(){
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
public void Level_1(){
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
      speedY1 = speedY1 * -1.1f;
      y1 = y1 + speedY1;
      lC = 0;
    }
    // if ball hits right or left, change direction of X
    else if (x1 < 10 || x1 > width-10) {
      player.play();
      speedX1 = speedX1 * -1.1f;
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
public void Level_2(){
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
      speedX2 = speedX2 * -1.1f;
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
public void Level_3(){
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
      speedX3 = speedX3 * -1.1f;
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
public void mouseClicked(){
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
//The MIT License (MIT)

//Copyright (c) 2013 Mick Grierson, Matthew Yee-King, Marco Gillies

//Permission is hereby granted, free of charge, to any person obtaining a copy\u2028of this software and associated documentation files (the "Software"), to deal\u2028in the Software without restriction, including without limitation the rights\u2028to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\u2028copies of the Software, and to permit persons to whom the Software is\u2028furnished to do so, subject to the following conditions:
//The above copyright notice and this permission notice shall be included in\u2028all copies or substantial portions of the Software.

//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\u2028IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\u2028FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\u2028AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\u2028LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\u2028OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\u2028THE SOFTWARE.

















 
 
 
 

//import android.content.res.Resources;
// import android.app.Activity; 
// import android.os.Bundle; 
// import android.media.*;
// import android.media.audiofx.Visualizer;
// import android.content.res.AssetFileDescriptor;
// import android.hardware.*;


public class Maxim {

    private float sampleRate;

    public final float[] mtof = {
	0f, 8.661957f, 9.177024f, 9.722718f, 10.3f, 10.913383f, 11.562325f, 12.25f, 12.978271f, 13.75f, 14.567617f, 15.433853f, 16.351599f, 17.323914f, 18.354048f, 19.445436f, 20.601723f, 21.826765f, 23.124651f, 24.5f, 25.956543f, 27.5f, 29.135235f, 30.867706f, 32.703197f, 34.647827f, 36.708096f, 38.890873f, 41.203445f, 43.65353f, 46.249302f, 49.f, 51.913086f, 55.f, 58.27047f, 61.735413f, 65.406395f, 69.295654f, 73.416191f, 77.781746f, 82.406891f, 87.30706f, 92.498604f, 97.998856f, 103.826172f, 110.f, 116.540939f, 123.470825f, 130.81279f, 138.591309f, 146.832382f, 155.563492f, 164.813782f, 174.61412f, 184.997208f, 195.997711f, 207.652344f, 220.f, 233.081879f, 246.94165f, 261.62558f, 277.182617f, 293.664764f, 311.126984f, 329.627563f, 349.228241f, 369.994415f, 391.995422f, 415.304688f, 440.f, 466.163757f, 493.883301f, 523.25116f, 554.365234f, 587.329529f, 622.253967f, 659.255127f, 698.456482f, 739.988831f, 783.990845f, 830.609375f, 880.f, 932.327515f, 987.766602f, 1046.502319f, 1108.730469f, 1174.659058f, 1244.507935f, 1318.510254f, 1396.912964f, 1479.977661f, 1567.981689f, 1661.21875f, 1760.f, 1864.655029f, 1975.533203f, 2093.004639f, 2217.460938f, 2349.318115f, 2489.015869f, 2637.020508f, 2793.825928f, 2959.955322f, 3135.963379f, 3322.4375f, 3520.f, 3729.31f, 3951.066406f, 4186.009277f, 4434.921875f, 4698.63623f, 4978.031738f, 5274.041016f, 5587.651855f, 5919.910645f, 6271.926758f, 6644.875f, 7040.f, 7458.620117f, 7902.132812f, 8372.018555f, 8869.84375f, 9397.272461f, 9956.063477f, 10548.082031f, 11175.303711f, 11839.821289f, 12543.853516f, 13289.75f
    };

    private AudioThread audioThread;
    private PApplet processing;

    public Maxim (PApplet processing) {
	this.processing = processing;
	sampleRate = 44100f;
	audioThread = new AudioThread(sampleRate, 4096, false);
	audioThread.start();
	    
    }

    public float[] getPowerSpectrum() {
	return audioThread.getPowerSpectrum();
    }

    /** 
     *  load the sent file into an audio player and return it. Use
     *  this if your audio file is not too long want precision control
     *  over looping and play head position
     * @param String filename - the file to load
     * @return AudioPlayer - an audio player which can play the file
     */
    public AudioPlayer loadFile(String filename) {
	// this will load the complete audio file into memory
	AudioPlayer ap = new AudioPlayer(filename, sampleRate, processing);
	audioThread.addAudioGenerator(ap);
	// now we need to tell the audiothread
	// to ask the audioplayer for samples
	return ap;
    }

    /**
     * Create a wavetable player object with a wavetable of the sent
     * size. Small wavetables (<128) make for a 'nastier' sound!
     * 
     */
    public WavetableSynth createWavetableSynth(int size) {
	// this will load the complete audio file into memory
	WavetableSynth ap = new WavetableSynth(size, sampleRate);
	audioThread.addAudioGenerator(ap);
	// now we need to tell the audiothread
	// to ask the audioplayer for samples
	return ap;
    }
    // /**
    //  * Create an AudioStreamPlayer which can stream audio from the
    //  * internet as well as local files.  Does not provide precise
    //  * control over looping and playhead like AudioPlayer does.  Use this for
    //  * longer audio files and audio from the internet.
    //  */
    // public AudioStreamPlayer createAudioStreamPlayer(String url) {
    //     AudioStreamPlayer asp = new AudioStreamPlayer(url);
    //     return asp;
    // }
}




/**
 * This class can play audio files and includes an fx chain 
 */
public class AudioPlayer implements Synth, AudioGenerator {
    private FXChain fxChain;
    private boolean isPlaying;
    private boolean isLooping;
    private boolean analysing;
    private FFT fft;
    private int fftInd;
    private float[] fftFrame;
    private float[] powerSpectrum;

    //private float startTimeSecs;
    //private float speed;
    private int length;
    private short[] audioData;
    private float startPos;
    private float readHead;
    private float dReadHead;
    private float sampleRate;
    private float masterVolume;

    float x1, x2, y1, y2, x3, y3;

    public AudioPlayer(float sampleRate) {
	fxChain = new FXChain(sampleRate);
	this.sampleRate = sampleRate;
    }

    public AudioPlayer (String filename, float sampleRate, PApplet processing) {
	//super(filename);
	this(sampleRate);
	try {
	    // how long is the file in bytes?
	    //long byteCount = getAssets().openFd(filename).getLength();
	    File f = new File(processing.dataPath(filename));
	    long byteCount = f.length();
	    //System.out.println("bytes in "+filename+" "+byteCount);

	    // check the format of the audio file first!
	    // only accept mono 16 bit wavs
	    //InputStream is = getAssets().open(filename); 
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));

	    // chop!!

	    int bitDepth;
	    int channels;
	    boolean isPCM;
	    // allows us to read up to 4 bytes at a time 
	    byte[] byteBuff = new byte[4];

	    // skip 20 bytes to get file format
	    // (1 byte)
	    bis.skip(20);
	    bis.read(byteBuff, 0, 2); // read 2 so we are at 22 now
	    isPCM = ((short)byteBuff[0]) == 1 ? true:false; 
	    //System.out.println("File isPCM "+isPCM);

	    // skip 22 bytes to get # channels
	    // (1 byte)
	    bis.read(byteBuff, 0, 2);// read 2 so we are at 24 now
	    channels = (short)byteBuff[0];
	    //System.out.println("#channels "+channels+" "+byteBuff[0]);
	    // skip 24 bytes to get sampleRate
	    // (32 bit int)
	    bis.read(byteBuff, 0, 4); // read 4 so now we are at 28
	    sampleRate = bytesToInt(byteBuff, 4);
	    //System.out.println("Sample rate "+sampleRate);
	    // skip 34 bytes to get bits per sample
	    // (1 byte)
	    bis.skip(6); // we were at 28...
	    bis.read(byteBuff, 0, 2);// read 2 so we are at 36 now
	    bitDepth = (short)byteBuff[0];
	    //System.out.println("bit depth "+bitDepth);
	    // convert to word count...
	    bitDepth /= 8;
	    // now start processing the raw data
	    // data starts at byte 36
	    int sampleCount = (int) ((byteCount - 36) / (bitDepth * channels));
	    audioData = new short[sampleCount];
	    int skip = (channels -1) * bitDepth;
	    int sample = 0;
	    // skip a few sample as it sounds like shit
	    bis.skip(bitDepth * 4);
	    while (bis.available () >= (bitDepth+skip)) {
		bis.read(byteBuff, 0, bitDepth);// read 2 so we are at 36 now
		//int val = bytesToInt(byteBuff, bitDepth);
		// resample to 16 bit by casting to a short
		audioData[sample] = (short) bytesToInt(byteBuff, bitDepth);
		bis.skip(skip);
		sample ++;
	    }

	    float secs = (float)sample / (float)sampleRate;
	    //System.out.println("Read "+sample+" samples expected "+sampleCount+" time "+secs+" secs ");      
	    bis.close();


	    // unchop
	    readHead = 0;
	    startPos = 0;
	    // default to 1 sample shift per tick
	    dReadHead = 1;
	    isPlaying = false;
	    isLooping = true;
	    masterVolume = 1;
	} 
	catch (FileNotFoundException e) {

	    e.printStackTrace();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void setAnalysing(boolean analysing_) {
	this.analysing = analysing_;
	if (analysing) {// initialise the fft
	    fft = new FFT();
	    fftInd = 0;
	    fftFrame = new float[1024];
	    powerSpectrum = new float[fftFrame.length/2];
	}
    }

    public float getAveragePower() {
	if (analysing) {
	    // calc the average
	    float sum = 0;
	    for (int i=0;i<powerSpectrum.length;i++){
		sum += powerSpectrum[i];
	    }
	    sum /= powerSpectrum.length;
	    return sum;
	}
	else {
	    System.out.println("call setAnalysing to enable power analysis");
	    return 0;
	}
    }
    public float[] getPowerSpectrum() {
	if (analysing) {
	    return powerSpectrum;
	}
	else {
	    System.out.println("call setAnalysing to enable power analysis");
	    return null;
	}
    }

    /** 
     *convert the sent byte array into an int. Assumes little endian byte ordering. 
     *@param bytes - the byte array containing the data
     *@param wordSizeBytes - the number of bytes to read from bytes array
     *@return int - the byte array as an int
     */
    private int bytesToInt(byte[] bytes, int wordSizeBytes) {
	int val = 0;
	for (int i=wordSizeBytes-1; i>=0; i--) {
	    val <<= 8;
	    val |= (int)bytes[i] & 0xFF;
	}
	return val;
    }

    /**
     * Test if this audioplayer is playing right now
     * @return true if it is playing, false otherwise
     */
    public boolean isPlaying() {
	return isPlaying;
    }

    /**
     * Set the loop mode for this audio player
     * @param looping 
     */
    public void setLooping(boolean looping) {
	isLooping = looping;
    }

    /**
     * Move the start pointer of the audio player to the sent time in ms
     * @param timeMs - the time in ms
     */
    public void cue(int timeMs) {
	//startPos = ((timeMs / 1000) * sampleRate) % audioData.length;
	//readHead = startPos;
	//System.out.println("AudioPlayer Cueing to "+timeMs);
	if (timeMs >= 0) {// ignore crazy values
	    readHead = (((float)timeMs / 1000f) * sampleRate) % audioData.length;
	    //System.out.println("Read head went to "+readHead);
	}
    }

    /**
     *  Set the playback speed,
     * @param speed - playback speed where 1 is normal speed, 2 is double speed
     */
    public void speed(float speed) {
	//System.out.println("setting speed to "+speed);
	dReadHead = speed;
    }

    /**
     * Set the master volume of the AudioPlayer
     */

    public void volume(float volume) {
	masterVolume = volume;
    }

    /**
     * Get the length of the audio file in samples
     * @return int - the  length of the audio file in samples
     */
    public int getLength() {
	return audioData.length;
    }
    /**
     * Get the length of the sound in ms, suitable for sending to 'cue'
     */
    public float getLengthMs() {
	return ((float) audioData.length / sampleRate * 1000f);
    }

    /**
     * Start playing the sound. 
     */
    public void play() {
	isPlaying = true;
    }

    /**
     * Stop playing the sound
     */
    public void stop() {
	isPlaying = false;
    }

    /**
     * implementation of the AudioGenerator interface
     */
    public short getSample() {
	if (!isPlaying) {
	    return 0;
	}
	else {
	    short sample;
	    readHead += dReadHead;
	    if (readHead > (audioData.length - 1)) {// got to the end
		//% (float)audioData.length;
		if (isLooping) {// back to the start for loop mode
		    readHead = readHead % (float)audioData.length;
		}
		else {
		    readHead = 0;
		    isPlaying = false;
		}
	    }

	    // linear interpolation here
	    // declaring these at the top...
	    // easy to understand version...
	    //      float x1, x2, y1, y2, x3, y3;
	    x1 = floor(readHead);
	    x2 = x1 + 1;
	    y1 = audioData[(int)x1];
	    y2 = audioData[(int) (x2 % audioData.length)];
	    x3 = readHead;
	    // calc 
	    y3 =  y1 + ((x3 - x1) * (y2 - y1));
	    y3 *= masterVolume;
	    sample = fxChain.getSample((short) y3);
	    if (analysing) {
		// accumulate samples for the fft
		fftFrame[fftInd] = (float)sample / 32768f;
		fftInd ++;
		if (fftInd == fftFrame.length - 1) {// got a frame
		    powerSpectrum = fft.process(fftFrame, true);
		    fftInd = 0;
		}
	    }

	    //return sample;
	    return (short)y3;
	}
    }

    public void setAudioData(short[] audioData) {
	this.audioData = audioData;
    }

    public short[] getAudioData() {
	return audioData;
    }

    public void setDReadHead(float dReadHead) {
	this.dReadHead = dReadHead;
    }

    ///
    //the synth interface
    // 

    public void ramp(float val, float timeMs) {
	fxChain.ramp(val, timeMs);
    } 



    public void setDelayTime(float delayMs) {
	fxChain.setDelayTime( delayMs);
    }

    public void setDelayFeedback(float fb) {
	fxChain.setDelayFeedback(fb);
    }

    public void setFilter(float cutoff, float resonance) {
	fxChain.setFilter( cutoff, resonance);
    }
}

/**
 * This class can play wavetables and includes an fx chain
 */
public class WavetableSynth extends AudioPlayer {

    private short[] sine;
    private short[] saw;
    private short[] wavetable;
    private float sampleRate;

    public WavetableSynth(int size, float sampleRate) {
	super(sampleRate);
	sine = new short[size];
	for (float i = 0; i < sine.length; i++) {
	    float phase;
	    phase = TWO_PI / size * i;
	    sine[(int)i] = (short) (sin(phase) * 32768);
	}
	saw = new short[size];
	for (float i = 0; i<saw.length; i++) {
	    saw[(int)i] = (short) (i / (float)saw.length *32768);
	}

	this.sampleRate = sampleRate;
	setAudioData(sine);
	setLooping(true);
    }

    public void setFrequency(float freq) {
	if (freq > 0) {
	    //System.out.println("freq freq "+freq);
	    setDReadHead((float)getAudioData().length / sampleRate * freq);
	}
    }

    public void loadWaveForm(float[] wavetable_) {
	if (wavetable == null || wavetable_.length != wavetable.length) {
	    // only reallocate if there is a change in length
	    wavetable = new short[wavetable_.length];
	}
	for (int i=0;i<wavetable.length;i++) {
	    wavetable[i] = (short) (wavetable_[i] * 32768);
	}
	setAudioData(wavetable);
    }
}

public interface Synth {
    public void volume(float volume);
    public void ramp(float val, float timeMs);  
    public void setDelayTime(float delayMs);  
    public void setDelayFeedback(float fb);  
    public void setFilter(float cutoff, float resonance);
    public void setAnalysing(boolean analysing);
    public float getAveragePower();
    public float[] getPowerSpectrum();
}

public class AudioThread extends Thread
{
    private int minSize;
    //private AudioTrack track;
    private short[] bufferS;
    private byte[] bOutput;
    private ArrayList audioGens;
    private boolean running;

    private FFT fft;
    private float[] fftFrame;
    private SourceDataLine sourceDataLine;
    private int blockSize;

    public AudioThread(float samplingRate, int blockSize) {
	this(samplingRate, blockSize, false);
    }

    public AudioThread(float samplingRate, int blockSize, boolean enableFFT)
    {
	this.blockSize = blockSize;
	audioGens = new ArrayList();
	// we'll do our dsp in shorts
	bufferS = new short[blockSize];
	// but we'll convert to bytes when sending to the sound card
	bOutput = new byte[blockSize * 2];
	AudioFormat audioFormat = new AudioFormat(samplingRate, 16, 1, true, false);
	DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
	    
	sourceDataLine = null;
	// here we try to initialise the audio system. try catch is exception handling, i.e. 
	// dealing with things not working as expected
	try {
	    sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	    sourceDataLine.open(audioFormat, bOutput.length);
	    sourceDataLine.start();
	    running = true;
	} catch (LineUnavailableException lue) {
	    // it went wrong!
	    lue.printStackTrace(System.err);
	    System.out.println("Could not initialise audio. check above stack trace for more info");
	    //System.exit(1);
	}


	if (enableFFT) {
	    try {
		fft = new FFT();
	    }
	    catch(Exception e) {
		System.out.println("Error setting up the audio analyzer");
		e.printStackTrace();
	    }
	}
    }

    // overidden from Thread
    public void run() {
	running = true;
	while (running) {
	    //System.out.println("AudioThread : ags  "+audioGens.size());
	    for (int i=0;i<bufferS.length;i++) {
		// we add up using a 32bit int
		// to prevent clipping
		int val = 0;
		if (audioGens.size() > 0) {
		    for (int j=0;j<audioGens.size(); j++) {
			AudioGenerator ag = (AudioGenerator)audioGens.get(j);
			val += ag.getSample();
		    }
		    val /= audioGens.size();
		}
		bufferS[i] = (short) val;
	    }
	    // send it to the audio device!
	    sourceDataLine.write(shortsToBytes(bufferS, bOutput), 0, bOutput.length);
	}
    }
	
    public void addAudioGenerator(AudioGenerator ag) {
	audioGens.add(ag);
    }

    /**
     * converts an array of 16 bit samples to bytes
     * in little-endian (low-byte, high-byte) format.
     */
    private byte[] shortsToBytes(short[] sData, byte[] bData) {
	int index = 0;
	short sval;
	for (int i = 0; i < sData.length; i++) {
	    //short sval = (short) (fData[j][i] * ShortMaxValueAsFloat);
	    sval = sData[i];
	    bData[index++] = (byte) (sval & 0x00FF);
	    bData[index++] = (byte) ((sval & 0xFF00) >> 8);
	}
	return bData;
    }

    /**
     * Returns a recent snapshot of the power spectrum 
     */
    public float[] getPowerSpectrum() {
	// process the last buffer that was calculated
	if (fftFrame == null) {
	    fftFrame = new float[bufferS.length];
	}
	for (int i=0;i<fftFrame.length;i++) {
	    fftFrame[i] = ((float) bufferS[i] / 32768f);
	}
	return fft.process(fftFrame, true);
	//return powerSpectrum;
    }
}

/**
 * Implement this interface so the AudioThread can request samples from you
 */
public interface AudioGenerator {
    /** AudioThread calls this when it wants a sample */
    public short getSample();
}


public class FXChain  {
    private float currentAmp;
    private float dAmp;
    private float targetAmp;
    private boolean goingUp;
    private Filter filter;

    private float[] dLine;   

    private float sampleRate;

    public FXChain(float sampleRate_) {
	sampleRate = sampleRate_;
	currentAmp = 1;
	dAmp = 0;
	// filter = new MickFilter(sampleRate);
	filter = new RLPF(sampleRate);

	//filter.setFilter(0.1, 0.1);
    }

    public void ramp(float val, float timeMs) {
	// calc the dAmp;
	// - change per ms
	targetAmp = val;
	dAmp = (targetAmp - currentAmp) / (timeMs / 1000 * sampleRate);
	if (targetAmp > currentAmp) {
	    goingUp = true;
	}
	else {
	    goingUp = false;
	}
    }


    public void setDelayTime(float delayMs) {
    }

    public void setDelayFeedback(float fb) {
    }

    public void volume(float volume) {
    }


    public short getSample(short input) {
	float in;
	in = (float) input / 32768;// -1 to 1

	in =  filter.applyFilter(in);
	if (goingUp && currentAmp < targetAmp) {
	    currentAmp += dAmp;
	}
	else if (!goingUp && currentAmp > targetAmp) {
	    currentAmp += dAmp;
	}  

	if (currentAmp > 1) {
	    currentAmp = 1;
	}
	if (currentAmp < 0) {
	    currentAmp = 0;
	}  
	in *= currentAmp;  
	return (short) (in * 32768);
    }

    public void setFilter(float f, float r) {
	filter.setFilter(f, r);
    }
}


// /**
//  * Represents an audio source is streamed as opposed to being completely loaded (as WavSource is)
//  */
// public class AudioStreamPlayer {
// 	/** a class from the android API*/
// 	private MediaPlayer mediaPlayer;
// 	/** a class from the android API*/
// 	private Visualizer viz; 
// 	private byte[] waveformBuffer;
// 	private byte[] fftBuffer;
// 	private byte[] powerSpectrum;

// 	/**
// 	 * create a stream source from the sent url 
// 	 */
// 	public AudioStreamPlayer(String url) {
// 	    try {
// 		mediaPlayer = new MediaPlayer();
// 		//mp.setAuxEffectSendLevel(1);
// 		mediaPlayer.setLooping(true);

// 		// try to parse the URL... if that fails, we assume it
// 		// is a local file in the assets folder
// 		try {
// 		    URL uRL = new URL(url);
// 		    mediaPlayer.setDataSource(url);
// 		}
// 		catch (MalformedURLException eek) {
// 		    // couldn't parse the url, assume its a local file
// 		    AssetFileDescriptor afd = getAssets().openFd(url);
// 		    //mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
// 		    mediaPlayer.setDataSource(afd.getFileDescriptor());
// 		    afd.close();
// 		}

// 		mediaPlayer.prepare();
// 		//mediaPlayer.start();
// 		//System.out.println("Created audio with id "+mediaPlayer.getAudioSessionId());
// 		viz = new Visualizer(mediaPlayer.getAudioSessionId());
// 		viz.setEnabled(true);
// 		waveformBuffer = new byte[viz.getCaptureSize()];
// 		fftBuffer = new byte[viz.getCaptureSize()/2];
// 		powerSpectrum = new byte[viz.getCaptureSize()/2];
// 	    }
// 	    catch (Exception e) {
// 		System.out.println("StreamSource could not be initialised. Check url... "+url+ " and that you have added the permission INTERNET, RECORD_AUDIO and MODIFY_AUDIO_SETTINGS to the manifest,");
// 		e.printStackTrace();
// 	    }
// 	}

// 	public void play() {
// 	    mediaPlayer.start();
// 	}

// 	public int getLengthMs() {
// 	    return mediaPlayer.getDuration();
// 	}

// 	public void cue(float timeMs) {
// 	    if (timeMs >= 0 && timeMs < getLengthMs()) {// ignore crazy values
// 		mediaPlayer.seekTo((int)timeMs);
// 	    }
// 	}

// 	/**
// 	 * Returns a recent snapshot of the power spectrum as 8 bit values
// 	 */
// 	public byte[] getPowerSpectrum() {
// 	    // calculate the spectrum
// 	    viz.getFft(fftBuffer);
// 	    short real, imag;
// 	    for (int i=2;i<fftBuffer.length;i+=2) {
// 		real = (short) fftBuffer[i];
// 		imag = (short) fftBuffer[i+1];
// 		powerSpectrum[i/2] = (byte) ((real * real)  + (imag * imag));
// 	    }
// 	    return powerSpectrum;
// 	}

// 	/**
// 	 * Returns a recent snapshot of the waveform being played 
// 	 */
// 	public byte[] getWaveForm() {
// 	    // retrieve the waveform
// 	    viz.getWaveForm(waveformBuffer);
// 	    return waveformBuffer;
// 	}
// } 

/**
 * Use this class to retrieve data about the movement of the device
 */
public class Accelerometer  {
    //private SensorManager sensorManager;
    //private Sensor accelerometer;
    private float[] values;

    public Accelerometer() {
	//sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	//accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	//sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	values = new float[3];
	System.out.println("Java accelerometer will generate values of zero!");
    }

    public float[] getValues() {
	return values;
    }

    public float getX() {
	return values[0];
    }

    public float getY() {
	return values[1];
    }

    public float getZ() {
	return values[2];
    }

}

public interface Filter {
    public void setFilter(float f, float r);
    public float applyFilter(float in);
}

/** https://github.com/supercollider/supercollider/blob/master/server/plugins/FilterUGens.cpp */

public class RLPF implements Filter {
    float a0, b1, b2, y1, y2;
    float freq;
    float reson;
    float sampleRate;
    boolean changed;

    public RLPF(float sampleRate_) {
	this.sampleRate = sampleRate_;
	reset();
	this.setFilter(sampleRate / 4, 0.01f);
    }
    private void reset() {
	a0 = 0.f;
	b1 = 0.f;
	b2 = 0.f;
	y1 = 0.f;
	y2 = 0.f;
    }
    /** f is in the range 0-sampleRate/2 */
    public void setFilter(float f, float r) {
	// constrain 
	// limit to 0-1 
	f = constrain(f, 0, sampleRate/4);
	r = constrain(r, 0, 1);
	// invert so high r -> high resonance!
	r = 1-r;
	// remap to appropriate ranges
	f = map(f, 0f, sampleRate/4, 30f, sampleRate / 4);
	r = map(r, 0f, 1f, 0.005f, 2f);

	System.out.println("rlpf: f "+f+" r "+r);

	this.freq = f * TWO_PI / sampleRate;
	this.reson = r;
	changed = true;
    }

    public float applyFilter(float in) {
	float y0;
	if (changed) {
	    float D = tan(freq * reson * 0.5f);
	    float C = ((1.f-D)/(1.f+D));
	    float cosf = cos(freq);
	    b1 = (1.f + C) * cosf;
	    b2 = -C;
	    a0 = (1.f + C - b1) * .25f;
	    changed = false;
	}
	y0 = a0 * in + b1 * y1 + b2 * y2;
	y2 = y1;
	y1 = y0;
	if (Float.isNaN(y0)) {
	    reset();
	}
	return y0;
    }
}

/** https://github.com/micknoise/Maximilian/blob/master/maximilian.cpp */

class MickFilter implements Filter {

    private float f, res;
    private float cutoff, z, c, x, y, out;
    private float sampleRate;

    MickFilter(float sampleRate) {
	this.sampleRate = sampleRate;
    }

    public void setFilter(float f, float r) {
	f = constrain(f, 0, 1);
	res = constrain(r, 0, 1);
	f = map(f, 0, 1, 25, sampleRate / 4);
	r = map(r, 0, 1, 1, 25);
	this.f = f;
	this.res = r;    

	//System.out.println("mickF: f "+f+" r "+r);
    }
    public float applyFilter(float in) {
	return lores(in, f, res);
    }

    public float lores(float input, float cutoff1, float resonance) {
	//cutoff=cutoff1*0.5;
	//if (cutoff<10) cutoff=10;
	//if (cutoff>(sampleRate*0.5)) cutoff=(sampleRate*0.5);
	//if (resonance<1.) resonance = 1.;

	//if (resonance>2.4) resonance = 2.4;
	z=cos(TWO_PI*cutoff/sampleRate);
	c=2-2*z;
	float r=(sqrt(2.0f)*sqrt(-pow((z-1.0f), 3.0f))+resonance*(z-1))/(resonance*(z-1));
	x=x+(input-y)*c;
	y=y+x;
	x=x*r;
	out=y;
	return out;
    }
}


/*
 * This file is part of Beads. See http://www.beadsproject.net for all information.
 * CREDIT: This class uses portions of code taken from MPEG7AudioEnc. See readme/CREDITS.txt.
 */

/**
 * FFT performs a Fast Fourier Transform and forwards the complex data to any listeners. 
 * The complex data is a float of the form float[2][frameSize], with real and imaginary 
 * parts stored respectively.
 * 
 * @beads.category analysis
 */
public class FFT {

    /** The real part. */
    protected float[] fftReal;

    /** The imaginary part. */
    protected float[] fftImag;

    private float[] dataCopy = null;
    private float[][] features;
    private float[] powers;
    private int numFeatures;

    /**
     * Instantiates a new FFT.
     */
    public FFT() {
	features = new float[2][];
    }

    /* (non-Javadoc)
     * @see com.olliebown.beads.core.UGen#calculateBuffer()
     */
    public float[] process(float[] data, boolean direction) {
	if (powers == null) powers = new float[data.length/2];
	if (dataCopy==null || dataCopy.length!=data.length)
	    dataCopy = new float[data.length];
	System.arraycopy(data, 0, dataCopy, 0, data.length);

	fft(dataCopy, dataCopy.length, direction);
	numFeatures = dataCopy.length;
	fftReal = calculateReal(dataCopy, dataCopy.length);
	fftImag = calculateImaginary(dataCopy, dataCopy.length);
	features[0] = fftReal;
	features[1] = fftImag;
	// now calc the powers
	return specToPowers(fftReal, fftImag, powers);
    }

    public float[] specToPowers(float[] real, float[] imag, float[] powers) {
	float re, im;
	double pow;
	for (int i=0;i<powers.length;i++) {
	    //real = spectrum[i][j].re();
	    //imag = spectrum[i][j].im();
	    re = real[i];
	    im = imag[i];
	    powers[i] = (re*re + im * im);
	    powers[i] = (float) Math.sqrt(powers[i]) / 10;
	    // convert to dB
	    pow = (double) powers[i];
	    powers[i] = (float)(10 *  Math.log10(pow * pow)); // (-100 - 100)
	    powers[i] = (powers[i] + 100) * 0.005f; // 0-1
	}
	return powers;
    }

    /**
     * The frequency corresponding to a specific bin 
     * 
     * @param samplingFrequency The Sampling Frequency of the AudioContext
     * @param blockSize The size of the block analysed
     * @param binNumber 
     */
    public  float binFrequency(float samplingFrequency, int blockSize, float binNumber)
    {    
	return binNumber*samplingFrequency/blockSize;
    }

    /**
     * Returns the average bin number corresponding to a particular frequency.
     * Note: This function returns a float. Take the Math.round() of the returned value to get an integral bin number. 
     * 
     * @param samplingFrequency The Sampling Frequency of the AudioContext
     * @param blockSize The size of the fft block
     * @param freq  The frequency
     */

    public  float binNumber(float samplingFrequency, int blockSize, float freq)
    {
	return blockSize*freq/samplingFrequency;
    }

    /** The nyquist frequency for this samplingFrequency 
     * 
     * @params samplingFrequency the sample
     */
    public  float nyquist(float samplingFrequency)
    {
	return samplingFrequency/2;
    }

    /*
     * All of the code below this line is taken from Holger Crysandt's MPEG7AudioEnc project.
     * See http://mpeg7audioenc.sourceforge.net/copyright.html for license and copyright.
     */

    /**
     * Gets the real part from the complex spectrum.
     * 
     * @param spectrum
     *            complex spectrum.
     * @param length 
     *       length of data to use.
     * 
     * @return real part of given length of complex spectrum.
     */
    protected  float[] calculateReal(float[] spectrum, int length) {
	float[] real = new float[length];
	real[0] = spectrum[0];
	real[real.length/2] = spectrum[1];
	for (int i=1, j=real.length-1; i<j; ++i, --j)
	    real[j] = real[i] = spectrum[2*i];
	return real;
    }

    /**
     * Gets the imaginary part from the complex spectrum.
     * 
     * @param spectrum
     *            complex spectrum.
     * @param length 
     *       length of data to use.
     * 
     * @return imaginary part of given length of complex spectrum.
     */
    protected  float[] calculateImaginary(float[] spectrum, int length) {
	float[] imag = new float[length];
	for (int i=1, j=imag.length-1; i<j; ++i, --j)
	    imag[i] = -(imag[j] = spectrum[2*i+1]);
	return imag;
    }

    /**
     * Perform FFT on data with given length, regular or inverse.
     * 
     * @param data the data
     * @param n the length
     * @param isign true for regular, false for inverse.
     */
    protected  void fft(float[] data, int n, boolean isign) {
	float c1 = 0.5f; 
	float c2, h1r, h1i, h2r, h2i;
	double wr, wi, wpr, wpi, wtemp;
	double theta = 3.141592653589793f/(n>>1);
	if (isign) {
	    c2 = -.5f;
	    four1(data, n>>1, true);
	} 
	else {
	    c2 = .5f;
	    theta = -theta;
	}
	wtemp = Math.sin(.5f*theta);
	wpr = -2.f*wtemp*wtemp;
	wpi = Math.sin(theta);
	wr = 1.f + wpr;
	wi = wpi;
	int np3 = n + 3;
	for (int i=2,imax = n >> 2, i1, i2, i3, i4; i <= imax; ++i) {
	    /** @TODO this can be optimized */
	    i4 = 1 + (i3 = np3 - (i2 = 1 + (i1 = i + i - 1)));
	    --i4; 
	    --i2; 
	    --i3; 
	    --i1; 
	    h1i =  c1*(data[i2] - data[i4]);
	    h2r = -c2*(data[i2] + data[i4]);
	    h1r =  c1*(data[i1] + data[i3]);
	    h2i =  c2*(data[i1] - data[i3]);
	    data[i1] = (float) ( h1r + wr*h2r - wi*h2i);
	    data[i2] = (float) ( h1i + wr*h2i + wi*h2r);
	    data[i3] = (float) ( h1r - wr*h2r + wi*h2i);
	    data[i4] = (float) (-h1i + wr*h2i + wi*h2r);
	    wr = (wtemp=wr)*wpr - wi*wpi + wr;
	    wi = wi*wpr + wtemp*wpi + wi;
	}
	if (isign) {
	    float tmp = data[0]; 
	    data[0] += data[1];
	    data[1] = tmp - data[1];
	} 
	else {
	    float tmp = data[0];
	    data[0] = c1 * (tmp + data[1]);
	    data[1] = c1 * (tmp - data[1]);
	    four1(data, n>>1, false);
	}
    }

    /**
     * four1 algorithm.
     * 
     * @param data
     *            the data.
     * @param nn
     *            the nn.
     * @param isign
     *            regular or inverse.
     */
    private  void four1(float data[], int nn, boolean isign) {
	int n, mmax, istep;
	double wtemp, wr, wpr, wpi, wi, theta;
	float tempr, tempi;

	n = nn << 1;        
	for (int i = 1, j = 1; i < n; i += 2) {
	    if (j > i) {
		// SWAP(data[j], data[i]);
		float swap = data[j-1];
		data[j-1] = data[i-1];
		data[i-1] = swap;
		// SWAP(data[j+1], data[i+1]);
		swap = data[j];
		data[j] = data[i]; 
		data[i] = swap;
	    }      
	    int m = n >> 1;
	    while (m >= 2 && j > m) {
		j -= m;
		m >>= 1;
	    }
	    j += m;
	}
	mmax = 2;
	while (n > mmax) {
	    istep = mmax << 1;
	    theta = 6.28318530717959f / mmax;
	    if (!isign)
		theta = -theta;
	    wtemp = Math.sin(0.5f * theta);
	    wpr = -2.0f * wtemp * wtemp;
	    wpi = Math.sin(theta);
	    wr = 1.0f;
	    wi = 0.0f;
	    for (int m = 1; m < mmax; m += 2) {
		for (int i = m; i <= n; i += istep) {
		    int j = i + mmax;
		    tempr = (float) (wr * data[j-1] - wi * data[j]);  
		    tempi = (float) (wr * data[j]   + wi * data[j-1]);  
		    data[j-1] = data[i-1] - tempr;
		    data[j]   = data[i] - tempi;
		    data[i-1] += tempr;
		    data[i]   += tempi;
		}
		wr = (wtemp = wr) * wpr - wi * wpi + wr;
		wi = wi * wpr + wtemp * wpi + wi;
	    }
	    mmax = istep;
	}
    }
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "Final_Project" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
