package com.senaijandira.forca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;


public class InicioActivity extends Activity{

    int contImg = 0;
    ImageView imgInicio;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        imgInicio = new ImageView(this);
        imgInicio = findViewById(R.id.imgInicio);


    }

    public void iniciarJogo(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void mudarFoto(View v){
        contImg++;
        if(contImg >= 2){
            imgInicio.setImageResource(R.drawable.moe);
        }

    }
}
