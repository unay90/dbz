package com.dbz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView tvJugar, tvSonido, tvSalir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//deja la aplicacion a toda la pantalla y en vertical
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		tvJugar=(TextView)findViewById(R.id.tvJugar);
		tvSonido=(TextView)findViewById(R.id.tvSonido);
		tvSalir=(TextView)findViewById(R.id.tvSalir);
	
	tvJugar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			empezarJuego();
		}
	});
	tvSonido.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	});
	tvSalir.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			System.exit(0);
		}
	});
}

private void empezarJuego(){
	Intent i=new Intent(this, dbzJuego.class);
	this.startActivity(i);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
