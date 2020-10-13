package com.fontanalautaro.miformulario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatosContacto extends AppCompatActivity {

    private TextView etNombre;
    private TextView etTelefono;
    private TextView etCumple;
    private TextView etEmail;
    private TextView etDescripicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);

        // Get the Intent that started this activity and extract the string
        Intent miIntent = getIntent();

        String nombre = miIntent.getStringExtra("NOMBRE");
        String nacimiento = miIntent.getStringExtra("NACIMIENTO");
        String telefono = miIntent.getStringExtra("TELEFONO");
        String mail = miIntent.getStringExtra("MAIL");
        String descripcion = miIntent.getStringExtra("DESCRIPCION");

        etNombre = (TextView) findViewById(R.id.tvNombreContacto2);
        etCumple = (TextView) findViewById(R.id.tvNacimiento2);
        etTelefono = (TextView) findViewById(R.id.tvTelefono2);
        etEmail = (TextView) findViewById(R.id.tvEmail2);
        etDescripicion = (TextView) findViewById(R.id.tvDescripcion2);

        etNombre.setText(nombre);
        etCumple.setText(nacimiento);
        etTelefono.setText(telefono);
        etEmail.setText(mail);
        etDescripicion.setText(descripcion);



    }

    public void editarDatos (){
        finish();
    }

    public void llamarContacto (View v){
        String url = etTelefono.getText().toString();




        if (Build.VERSION.SDK_INT > 22) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(DatosContacto.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ url)));
        } else {

            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ url)));
        }

    }

    public void enviarMail (View v){
        String email = etEmail.getText().toString();
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email "));
    }
}