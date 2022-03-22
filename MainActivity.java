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
import android.content.*;

public class MainActivity extends AppCompatActivity{

	private TextInputLayout nombre,telefono,email,parFechaNac,parentComent;
	private Button miBoton;
	private String contenidoNombre,contenidoTelefono,contenidoMail, contenidoFecha;
	private EditText fechaNac,editName,editPhone,editMail;
	private DatePickerDialog eligeFecha;
	private int miDia,miMes,miAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		nombre=findViewById(R.id.parentName);
		telefono=findViewById(R.id.parentPhone);
		email=findViewById(R.id.parentMail);
		fechaNac=findViewById(R.id.FechaNac);
		miBoton=(Button)findViewById(R.id.boton);
		editName=findViewById(R.id.EditName);
		editPhone=findViewById(R.id.EditPhone);
		editMail=findViewById(R.id.EditMail);
		parFechaNac=findViewById(R.id.parFechaNac);
		parentComent=findViewById(R.id.parentComent);
		
		telefono.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		
		miBoton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v){	
					validaDatos(v);
				}
			});
			
		fechaNac.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1){
					
					seleccionFecha();			
                
				}
			});
			
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
			nombre.getEditText().setText(data.getStringExtra(getResources().getString(R.string.texNombre)));
			telefono .getEditText().setText( data.getStringExtra(getResources().getString(R.string.texTelefono)));
			email .getEditText().setText( data.getStringExtra(getResources().getString(R.string.texEmail)));
			parFechaNac .getEditText().setText( data.getStringExtra(getResources().getString(R.string.fecha)));
			parentComent .getEditText().setText( data.getStringExtra(getResources().getString(R.string.coment)));
		}
	}

	
	
	
	private boolean compruebaMail(){
	 contenidoMail=email.getEditText().getText().toString();
		if(!contenidoMail.contains("@")||!contenidoMail.contains(".")
			|| contenidoMail.isEmpty()){
			email.setError("Debes introducir una direccion valida");
			return false;
		
		}else{
			email.setError(null);
			return true;
		}
	}
	
	private boolean compruebaNombre(){
		 contenidoNombre=nombre.getEditText().getText().toString();
		if(contenidoNombre.isEmpty()){
			nombre.setError("Debes rellenar este campo");
			return false;
		}else{ 
		nombre.setError(null);
		return true;
		}
	}
	
	private boolean compruebaTelefono(){
		contenidoTelefono=telefono.getEditText().getText().toString().trim();
		if(contenidoTelefono.length()<7){
			telefono.setError("Debes introducir un numero de telefono");
			return false;
		}else{
			telefono.setError(null);
			return true;
		}	
		
	}
	
	
	
	private boolean compruebaNacimiento(){
		contenidoFecha=parFechaNac.getEditText().getText().toString();
		if(contenidoFecha.isEmpty()){
			parFechaNac.setError("Debes introducir una fecha de nacimiento");
			return false;
		}else{
			parFechaNac.setError(null);
			return true;
		}	

	}
	
	private void seleccionFecha(){
		try{
		Calendar cal=Calendar.getInstance();
		miDia=cal.get(Calendar.DAY_OF_MONTH);
		miMes=cal.get(Calendar.MONTH);
		miAno=cal.get(Calendar.YEAR);
		
	DatePickerDialog.OnDateSetListener recogefecha=
			new DatePickerDialog.OnDateSetListener(){

			@Override
			public void onDateSet(DatePicker p1, int p2, int p3, int p4){
				p3=p3+1;
				fechaNac.setText("       "+p4+" / "+p3+" / "+p2);
				
			}

	};
		
	eligeFecha=new DatePickerDialog(MainActivity.this,
	android.R.style.Theme_Holo_Light_Dialog_MinWidth,recogefecha,miAno,miMes,miDia);
	
     	eligeFecha.show();	
		}catch(Exception e){
		
			fechaNac.setText(e.toString());
		}
	}
	
	public void validaDatos(View v){
		if(!compruebaNombre() | !compruebaMail() | !compruebaTelefono()|!compruebaNacimiento()){
			Toast.makeText(getApplicationContext(),"putas",Toast.LENGTH_SHORT).show();
			return;
		}else{
			
			Intent in=new Intent(MainActivity.this,Comprobacion.class);
			in.putExtra(getResources().getString(R.string.texNombre),editName.getText().toString());
			in.putExtra(getResources().getString(R.string.texTelefono),editPhone.getText().toString());
			in.putExtra(getResources().getString(R.string.texEmail),editMail.getText().toString());
			in.putExtra(getResources().getString(R.string.fecha),fechaNac.getText().toString());
			in.putExtra(getResources().getString(R.string.coment),parentComent.getEditText().getText().toString());
			startActivityForResult(in,1);
		}
	}
    
}
