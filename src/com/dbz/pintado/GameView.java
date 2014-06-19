package com.dbz.pintado;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dbz.R;
import com.dbz.juego.Goku;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	
	private HiloPantalla hiloPantalla;
	private Goku goku;
	private  Bitmap bmpPadDireccion;
	private Rect flechaIzq, flechaDer, flechaArriba;

	public GameView(Context context) {
		super(context);
		getHolder().addCallback(this);
		bmpPadDireccion=BitmapFactory.decodeResource(getResources(), R.drawable.paddireccion);
		flechaIzq=new Rect(0, bmpPadDireccion.getHeight()/2, bmpPadDireccion.getHeight()/2, bmpPadDireccion.getHeight());
		flechaDer=new Rect(bmpPadDireccion.getHeight()/2, bmpPadDireccion.getHeight()/2, bmpPadDireccion.getHeight(), bmpPadDireccion.getHeight());
		flechaArriba=new Rect(bmpPadDireccion.getHeight()/4, 0, bmpPadDireccion.getHeight()*3/4, bmpPadDireccion.getHeight()/2);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		goku=new Goku(this);
		hiloPantalla=new HiloPantalla(this);
		hiloPantalla.setRunning(true);
		hiloPantalla.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry=true;
		hiloPantalla.setRunning(false);
		while(retry){
			try{
				hiloPantalla.join();
				retry=false;
			}catch(InterruptedException e){
			}
		}
	}
	
	public void onDraw(Canvas canvas){
		if(hiloPantalla.isRunning()){
			canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(bmpPadDireccion, 0, 0, null);
			goku.onDraw(canvas);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event){
		if(event.getAction()==MotionEvent.ACTION_UP){
			goku.setMovimiento(false);
			return true;
		}
		int x=(int) event.getX();
		int y=(int) event.getY();
		if(flechaDer.contains(x, y)){
			goku.setMovimiento(true);
			goku.setDirDer(true);
		}else if(flechaIzq.contains(x, y)){
			goku.setMovimiento(true);
			goku.setDirDer(false);
		}else if(flechaArriba.contains(x, y)){
			if(goku.saltoCompletado()){
				goku.setMovimiento(false);
				goku.setSaltar(true);
			}
		}else{
			goku.setMovimiento(false);
		}
		return true;
	}
}
