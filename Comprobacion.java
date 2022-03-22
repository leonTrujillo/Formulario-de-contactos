package com.mycompany.myapp;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.*;
import android.widget.*;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.app.*;
import java.util.*;
import java.util.Calendar;
import java.text.*;
import android.icu.util.*;
//import android.content.*;
import android.content.Intent;

public class Comprobacion extends AppCompatActivity {
	
	String nombre,telefono,email,fecha,coment;
	TextView datos;
	Button boton2;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprobacion);
		
		datos=findViewById(R.id.datos);
		boton2=(Button)findViewById(R.id.boton2);
		
		Bundle bn=getIntent().getExtras();
		nombre=bn.getString(getResources().getString(R.string.texNombre));
		telefono=bn.getString(getResources().getString(R.string.texTelefono));
		email=bn.getString(getResources().getString(R.string.texEmail));
		fecha=bn.getString(getResources().getString(R.string.fecha));
		coment=bn.getString(getResources().getString(R.string.coment));
		
		
		datos.setText("Nombre: "+nombre
	    +System.getProperty("line.separator")
		+" Fecha de nacimiento: "+fecha
		+System.getProperty("line.separator")
		+"Telefono: "+telefono
	    +System.getProperty("line.separator")
		+" Email: "+email
	    +System.getProperty("line.separator")
		+" Comentarios: "
	    +System.getProperty("line.separator")
		+coment);
		
		
		boton2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v){	
                     validaDatos(v);
				}
			});
		
		
		}
		
	public void validaDatos(View v){

		Intent in=new Intent(getApplicationContext(),MainActivity.class);
			in.putExtra(getResources().getString(R.string.texNombre),nombre);
			in.putExtra(getResources().getString(R.string.texTelefono),telefono);
			in.putExtra(getResources().getString(R.string.texEmail),email);
			in.putExtra(getResources().getString(R.string.fecha),fecha);
			in.putExtra(getResources().getString(R.string.coment),coment);
			setResult(RESULT_OK,in);
			finish();
		
	}
		
}
