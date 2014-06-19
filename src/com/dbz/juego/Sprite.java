package com.dbz.juego;

import android.graphics.Bitmap;

public class Sprite {

	private int x, y;
	private int currentFrame=0;
	private int ancho, alto;
	private int filas, columnas;
	
	public Sprite(Bitmap bmp, int col){
		filas=2;
		columnas=col;
		ancho=bmp.getWidth()/columnas;
		alto=bmp.getHeight()/filas;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public int getColumnas() {
		return columnas;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
}
