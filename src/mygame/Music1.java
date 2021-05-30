package mygame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

//音效
public class Music1 {
	 public void Music2()//敌人爆炸音效
	 {
	  try
	  {
	 URL p=getClass().getClassLoader().getResource("sound/exp4.wav"); //file:\\+绝对地址 
	   AudioClip a=Applet.newAudioClip(p);
	   a.play();        
	  }
	  catch(Exception e)
	  {
	   
	  }
	 }
	 public  void Music3()//英雄爆炸音效
	 {
	  try
	  {
		  URL p=getClass().getClassLoader().getResource("sound/exp2.wav"); //file:\\+绝对地址 
	   AudioClip a=Applet.newAudioClip(p);
	   a.play();        
	   
	  }
	  catch(Exception e)
	  {
	   
	  }
	 }
	 public  void Music4()//Redskill爆炸音效
	 {
	  try
	  {
	 URL p=getClass().getClassLoader().getResource("sound/exp6.wav"); //file:\\+绝对地址 
	   AudioClip a=Applet.newAudioClip(p);
	   a.play();        
	   
	  }
	  catch(Exception e)
	  {
	   
	  }
	 }
	 public  void Music5()//Blueskill爆炸音效
	 {
	  try
	  {
	 URL p=getClass().getClassLoader().getResource("sound/exp7.wav"); //file:\\+绝对地址 
	   AudioClip a=Applet.newAudioClip(p);
	   a.play();        
	   
	  }
	  catch(Exception e)
	  {
	   
	  }
	 }
	 public  void Music6()//按键音效
	 {
	  try
	  {
		  URL p=getClass().getClassLoader().getResource("sound/exp8.wav"); //file:\\+绝对地址 
	   AudioClip a=Applet.newAudioClip(p);
	   a.play();        
	   
	  }
	  catch(Exception e)
	  {
	   
	  }
	 }
	/*public  void Music7()//陨石爆炸音效
	 {
	  try
	  {
	 URL p=new URL("file:\\C:\\flyingmusic\\exp9.wav"); //file:\\+绝对地址 
	   AudioClip a=Applet.newAudioClip(p);
	   a.play();        
	   
	  }
	  catch(Exception e)
	  {
	   
	  }
	 }*/
}
