package com.dbz.pintado;

import android.graphics.Canvas;

public class HiloPantalla extends Thread {

	private GameView view;
	private boolean running=false;
	static final long FPS=7;//como mucho hare 7 frames por segundo
	
	public HiloPantalla(GameView view){
		this.view=view;
	}
	
	public void setRunning(boolean run){
		running=run;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void run(){
		long ticksPS=1000/FPS;//cada 142 milisegundos hago un frame
		long startTime;
		long sleepTime;
		while(running){
			Canvas c=null;
			startTime=System.currentTimeMillis();
			try{
				c=view.getHolder().lockCanvas();
				synchronized (view.getHolder()) {
					view.onDraw(c);
				}
			}finally{
				if(c!=null){
					view.getHolder().unlockCanvasAndPost(c);
				}
			}
			sleepTime=ticksPS-(System.currentTimeMillis()-startTime);
			try{
				if(sleepTime>0){
					sleep(sleepTime);
				}
			}catch(Exception e){
			}
		}
	}
}
