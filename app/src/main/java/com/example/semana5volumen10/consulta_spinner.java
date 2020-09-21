package com.example.semana5volumen10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class consulta_spinner extends AppCompatActivity {

    private Spinner sp_options;
    private TextView tv_cod, tv_descripcion, tv_precio;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();
    @Override
        protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_consulta_spinner);

        sp_options = (Spinner)findViewById(R.id.sp_options);
        tv_cod=(TextView)findViewById(R.id.tv_cod);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_precio=(TextView)findViewById(R.id.tv_precio);

        conexion.consultaListaArticulos();

        ArrayAdapter<CharSequence>adaptador= new ArrayAdapter(this,android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());
        sp_options.setAdapter(adaptador);
        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (position!=0){
                    tv_cod.setText("Código: "+conexion.consultaListaArticulos().get(position-1).getCodigo());
                    tv_descripcion.setText("Descripción: "+conexion.consultaListaArticulos().get(position-1).getDescripcion());
                    tv_precio.setText("Precio: "+conexion.consultaListaArticulos().get(position-1).getPrecio());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
