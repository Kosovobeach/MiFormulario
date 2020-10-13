package com.fontanalautaro.miformulario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    EditText etTelefono;
    EditText etCumple;
    EditText etEmail;
    EditText etDescripicion;


    Button btnSiguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etCumple = (EditText) findViewById(R.id.etCumple);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDescripicion = (EditText) findViewById(R.id.etDescripcion);

        etCumple.setOnClickListener(this);

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etCumple:
                showDatePickerDialog();
                break;
            case R.id.btnSiguiente:
                guardarFomulario();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etCumple.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void guardarFomulario (){
        Intent miIntent = new Intent(this, DatosContacto.class);
        String nombre = etNombre.getText().toString();
        String nacimiento = etCumple.getText().toString();
        String telefono = etTelefono.getText().toString();
        String mail = etEmail.getText().toString();
        String descripcion = etDescripicion.getText().toString();
        miIntent.putExtra("NOMBRE",nombre);
        miIntent.putExtra("NACIMIENTO",nacimiento);
        miIntent.putExtra("TELEFONO",telefono);
        miIntent.putExtra("MAIL",mail);
        miIntent.putExtra("DESCRIPCION",descripcion);
        startActivity(miIntent);
    };
}

