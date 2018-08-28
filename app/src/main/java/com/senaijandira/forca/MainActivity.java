package com.senaijandira.forca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity{

    /* ----------- BTN ----------- */
    Button btn[] = new Button[26];
    int quantAcertos = 0;
    int quantErros = 0;


    private void gerarListener(){
        int i;
        for (i=0; i < btn.length; i++) {
            int id = getResources().getIdentifier("btn" + i, "id", getPackageName());
            btn[i] = findViewById(id);
            btn[i].setTag(i);
            btn[i].setEnabled(true);
            btn[i].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            btn[i].setOnClickListener(click);
        }
    }

    /*********************************************************************************************/

    int resposta[] = {9, 0, 21, 0}; /* GABARITO DE REPOSTA DA PALAVRA "JAVA" */
    TextView txtResposta[] = new TextView[resposta.length];
    int contadorAcertos = 0; /* ESSE CONTADOR VAI IR ATÉ O NÚMERO 4 */
    int contadorErro = 20;

    View.OnClickListener click = (View view) ->{

        int tag = (int) view.getTag();/* ARMAZENANDO A TAG DO BTN QUE FOI CLICADO */

        int verificar = 0; /* VERIFICADOR PARA VER SE O USUÁRIO ACERTOU*/

        for(int i = 0; i<resposta.length; i++){
            if (tag == resposta[i]) {
                verificar = 1;
                int id = getResources().getIdentifier("txtLetra" + i, "id", getPackageName());
                txtResposta[i] = findViewById(id);
                txtResposta[i].setText(btn[tag].getText());
                view.setEnabled(false);
                contadorAcertos++;
            }
            else if(tag != resposta[i]){/* SETANDO O BTN PARA FALSE SEM COLOCAR O TXT NO TEXTVIEW, POIS O USUÁRIO NÃO ACERTOU */
                view.setEnabled(false);
                contadorErro--;
            }
            if(verificar != 0){
                view.setBackgroundColor(getResources().getColor(R.color.colorVerde));
            }else{
                view.setBackgroundColor(getResources().getColor(R.color.colorVermelho));
            }
        }
        /****************************************************************/
        if(contadorAcertos == 4){
            quantAcertos++;
            alert("Opá, Consagrado(a)!", "Temos um Campeão aqui!", "btn");
            alert("Resultados:","Você acertou "+quantAcertos+" e errou "+quantErros, "null");
        }
        if(contadorErro == 0){
            quantErros++;
            alert("Eita...", "Você errou", "btn");
            alert("Resultados:","Você acertou "+quantAcertos+" e errou "+quantErros, "null");
        }

        /***********************************************************/
    };
    private void alert(String titulo, String messagem, String aux){

        if(aux.equals("btn")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(titulo);
            builder.setMessage(messagem);
            builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    reiniciarJogo();
                }
            });
            builder.create();
            builder.show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(titulo);
            builder.setMessage(messagem);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            builder.create();
            builder.show();
        }
    }
    /* ---------------- REINICIAR JOGO ---------------- */
    private void reiniciarJogo() {
        contadorAcertos = 0;
        contadorErro = 20;

        for (int i = 0; i < resposta.length; i++) {

            int id = getResources().getIdentifier("txtLetra" + i, "id", getPackageName());
            txtResposta[i] = findViewById(id);
            txtResposta[i].setText("_");

        }

        gerarListener();
    }


    /* ----------- Oncreate ----------- */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gerarListener();
    }
}