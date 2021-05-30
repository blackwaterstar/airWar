package mygame;


import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
//import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class Music {
	
	static void playMusic() {// 背景音乐播放
		 
		  try {
			 
			  //String  path=new URL("file:sound/bgm.wav").getPath();getClassLoader().getResource
			//  URL p=getClass().getClassLoader().getResource("sound/exp2.wav")；
		   AudioInputStream a = AudioSystem.getAudioInputStream(Music.class.getClassLoader().getResource("sound/bgm.wav"));//必须是绝对路径
		   AudioFormat b = a.getFormat();// 指定声音流中特定数据安排
		   final SourceDataLine sdl;// 获得一个源数据行，该行可用于以 AudioFormat 对象指定的格式回放音频数据
		   DataLine.Info d = new DataLine.Info(SourceDataLine.class, b);
		   sdl = (SourceDataLine) AudioSystem.getLine(d); // 从混频器获得源数据行
		   sdl.open(b);// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
		   sdl.start();// 允许数据行执行数据 I/O 
		 /* FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
		  //c可以用来设置音量，从0-2.0
		   double c = 2;
		   float dB = (float) (Math.log(c == 0.0 ? 0.0001 : c) / Math.log(10.0) * 20.0);
		   fc.setValue(dB);*/
		   int n = 0;
		   final int SIZE = 1024 * 64;
		   byte[] s = new byte[SIZE];
		   while (n != -1) {
		    n = a.read(s, 0, SIZE);// 从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中。返回读入缓冲区的总字节数；如果因已到达流末尾而不再有更多数据，则返回 -1 
		    sdl.write(s, 0, n);//实际写入的字节数 
		   }
		// sdl.stop();
		 
		  } catch (Exception e) {
		
		  }
		 }
	
	

}