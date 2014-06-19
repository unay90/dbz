package com.dbz.juego;

import com.dbz.R;
import com.dbz.pintado.GameView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Goku {

	private Bitmap bmpGokuAndar, bmpGokuParado, bmpGokuSaltar;
	private GameView gameView;
	private int xVelocidad, x, y, yVelocidad;
	private Sprite spriteGokuAndar, spriteGokuParado, spriteGokuSaltar;
	private boolean dirDer, movimiento, saltar, saltoComple;
	
	public Goku(GameView gv){
		gameView=gv;
		bmpGokuAndar=BitmapFactory.decodeResource(gameView.getResources(), R.drawable.gokuandar);
		bmpGokuParado=BitmapFactory.decodeResource(gameView.getResources(), R.drawable.gokuparado);
		bmpGokuSaltar=BitmapFactory.decodeResource(gameView.getResources(), R.drawable.gokusaltar);
		spriteGokuAndar=new Sprite(bmpGokuAndar, 8);
		spriteGokuAndar.setY(gameView.getHeight()-spriteGokuAndar.getAlto());
		spriteGokuParado=new Sprite(bmpGokuParado, 5);
		spriteGokuParado.setY(gameView.getHeight()-spriteGokuParado.getAlto());
		spriteGokuSaltar=new Sprite(bmpGokuSaltar, 7);
		spriteGokuSaltar.setY(gameView.getHeight()-spriteGokuSaltar.getAlto());
		dirDer=true;
		movimiento=false;
		saltar=false;
		saltoComple=true;
		x=0;
		y=gameView.getHeight()-spriteGokuSaltar.getAlto();
		xVelocidad=10;
		yVelocidad=40;
	}

	public void onDraw(Canvas canvas){
		if(movimiento && !saltar){
			int srcX=spriteGokuAndar.getCurrentFrame()*spriteGokuAndar.getAncho();
			int srcY;
			if(dirDer){
				srcY=0;
				if(x+xVelocidad<gameView.getWidth()*70/100-spriteGokuAndar.getAncho()){
					x=x+xVelocidad;
				}
			}else{
				srcY=1*spriteGokuAndar.getAlto();
				if(x-xVelocidad>0){
					x=x-xVelocidad;
				}
			}
			Rect src=new Rect(srcX, srcY, srcX+spriteGokuAndar.getAncho(), srcY+spriteGokuAndar.getAlto());//aqui cojo solo una figura de la imagen
			Rect dst=new Rect(x, gameView.getHeight()-spriteGokuAndar.getAlto(), x+spriteGokuAndar.getAncho(), gameView.getHeight());
			canvas.drawBitmap(bmpGokuAndar, src, dst, null);
			siguienteFrame(spriteGokuAndar);
		}else if(!movimiento && saltar){
			saltoComple=false;
			int srcX=spriteGokuSaltar.getCurrentFrame()*spriteGokuSaltar.getAncho();
			int srcY;
			if(dirDer){
				srcY=0;
			}else{
				srcY=1*spriteGokuAndar.getAlto();
			}
			switch (spriteGokuSaltar.getCurrentFrame()) {
			case 0: case 1: case 2:
				y=y-yVelocidad;
				break;
			case 3: case 4: case 5:
				y=y+yVelocidad;
				break;
			}
			Rect src=new Rect(srcX, srcY, srcX+spriteGokuSaltar.getAncho(), srcY+spriteGokuSaltar.getAlto());
			Rect dst=new Rect(x, y, x+spriteGokuSaltar.getAncho(), y+spriteGokuSaltar.getAlto());
			canvas.drawBitmap(bmpGokuSaltar, src, dst, null);
			siguienteFrame(spriteGokuSaltar);
			if(spriteGokuSaltar.getCurrentFrame()==0){
				saltar=false;
				saltoComple=true;
			}
		}else if(movimiento && saltar){
			saltoComple=false;
			int srcX=spriteGokuSaltar.getCurrentFrame()*spriteGokuSaltar.getAncho();
			int srcY;
			if(dirDer){
				srcY=0;
				if(spriteGokuSaltar.getCurrentFrame()!=6 && spriteGokuSaltar.getCurrentFrame()!=7){
					if(x+xVelocidad<gameView.getWidth()*70/100-spriteGokuAndar.getAncho()){
						x=x+xVelocidad*2;
					}
				}
			}else{
				srcY=1*spriteGokuAndar.getAlto();
				if(spriteGokuSaltar.getCurrentFrame()!=6 && spriteGokuSaltar.getCurrentFrame()!=7){
					if(x-xVelocidad>0){
						x=x-xVelocidad*2;
					}
				}
			}
			switch (spriteGokuSaltar.getCurrentFrame()) {
			case 0: case 1: case 2:
				y=y-yVelocidad;
				break;
			case 3: case 4: case 5:
				y=y+yVelocidad;
				break;
			}
			Rect src=new Rect(srcX, srcY, srcX+spriteGokuSaltar.getAncho(), srcY+spriteGokuSaltar.getAlto());
			Rect dst=new Rect(x, y, x+spriteGokuSaltar.getAncho(), y+spriteGokuSaltar.getAlto());
			canvas.drawBitmap(bmpGokuSaltar, src, dst, null);
			siguienteFrame(spriteGokuSaltar);
			if(spriteGokuSaltar.getCurrentFrame()==0){
				saltar=false;
				saltoComple=true;
			}
		}else{
			int srcX=spriteGokuParado.getCurrentFrame()*spriteGokuParado.getAncho();
			int srcY;
			if(dirDer){
				srcY=0;
			}else{
				srcY=1*spriteGokuParado.getAlto();
			}
			Rect src=new Rect(srcX, srcY, srcX+spriteGokuParado.getAncho(), srcY+spriteGokuParado.getAlto());
			Rect dst=new Rect(x, gameView.getHeight()-spriteGokuParado.getAlto(), x+spriteGokuParado.getAncho(), gameView.getHeight());
			canvas.drawBitmap(bmpGokuParado, src, dst, null);
			siguienteFrame(spriteGokuParado);
		}
	}
	
	private void siguienteFrame(Sprite sprite){
		sprite.setCurrentFrame(sprite.getCurrentFrame()+1);
		sprite.setCurrentFrame(sprite.getCurrentFrame()%sprite.getColumnas());
	}

	public void setDirDer(boolean dirDer) {
		this.dirDer = dirDer;
	}
	
	public void setSaltar(boolean saltar) {
		this.saltar = saltar;
	}

	public void setMovimiento(boolean movimiento) {
		this.movimiento = movimiento;
	}

	public boolean saltoCompletado() {
		return saltoComple;
	}
}
