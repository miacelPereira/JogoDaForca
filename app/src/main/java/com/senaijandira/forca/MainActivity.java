package com.senaijandira.forca;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;


public class MainActivity extends Activity{

    /* ----------- BTN ----------- */
    Button btn[] = new Button[26];
    Button btnDica;
    TextView txtDica;
    TextView txtErro;
    TextView txtAcerto;
    TextToSpeech speak;
    int quantAcertos = 0;
    int quantErros = 0;
    ImageView imgForca;
    String textDicaAux;
    int palavraAleatoria = 0;


    int resposta[][] = {
            {9, 0, 21, 0}, /* GABARITO DE REPOSTA DA PALAVRA "JAVA" */
            {0, 12, 14, 17}, /* GABARITO DE REPOSTA DA PALAVRA "AMOR" */
            {2 , 0, 18, 0}, /* GABARITO DE REPOSTA DA PALAVRA "CASA" */
            {1, 14, 11, 0}, /* GABARITO DE REPOSTA DA PALAVRA "BOLA" */
            {2, 0, 5, 4}, /* GABARITO DE REPOSTA DA PALAVRA "CAFÉ" */
            {5, 14, 6, 14}, /* GABARITO DE REPOSTA DA PALAVRA "FOGO" */
            {6, 4, 11, 14}, /* GABARITO DE REPOSTA DA PALAVRA "GELO" */
            {14, 3, 8, 14}, /* GABARITO DE REPOSTA DA PALAVRA "ODIO" */
            {23, 1, 14, 23}, /* GABARITO DE REPOSTA DA PALAVRA "XBOX" */
            {2, 0, 12, 0}, /* GABARITO DE REPOSTA DA PALAVRA "CAMA" */
            {0, 17, 19, 4}, /* GABARITO DE REPOSTA DA PALAVRA "ARTE" */
            {9, 14, 6, 14}, /* GABARITO DE REPOSTA DA PALAVRA "JOGO" */
            {5, 17, 8, 14} /* GABARITO DE REPOSTA DA PALAVRA "FRIO" */
    };

String textDica [] = {"Liguagem de Programação",
        "Sentimento de afeto",
        "Moradia",
        "Futebol",
        "Bebida quente",
        "Queima",
        "Estado sólido da Água",
        "Sentimento de raiva",
        "Video-game",
        "Dormir",
        "Esse layout é uma obra de ...",
        "Partida esportiva",
        "Coração da morena"
};


    /******************************************************************************************/
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
    TextView txtResposta[] = new TextView[resposta[palavraAleatoria].length];
    int contadorAcertos = 0; /* ESSE CONTADOR VAI IR ATÉ O NÚMERO 4 */
    int contadorErro = 4;

    View.OnClickListener click = (View view) ->{

        int tag = (int) view.getTag();/* ARMAZENANDO A TAG DO BTN QUE FOI CLICADO */

        int verificar = 0; /* VERIFICADOR PARA VER SE O USUÁRIO ACERTOU*/

        for(int i = 0; i<resposta[palavraAleatoria].length; i++){
            if (tag == resposta[palavraAleatoria][i]) {
                verificar = 1;
                int id = getResources().getIdentifier("txtLetra" + i, "id", getPackageName());
                txtResposta[i] = findViewById(id);
                txtResposta[i].setText(btn[tag].getText());
                view.setEnabled(false);
                contadorAcertos++;
            }
            else if(tag != resposta[palavraAleatoria][i]){/* SETANDO O BTN PARA FALSE SEM COLOCAR O TXT NO TEXTVIEW, POIS O USUÁRIO NÃO ACERTOU */
                view.setEnabled(false);
            }
        }
        if(verificar != 0){
            view.setBackgroundColor(getResources().getColor(R.color.colorVerde));
        }else{

            view.setBackgroundColor(getResources().getColor(R.color.colorVermelho));
            contadorErro--;
            if(contadorErro == 4){
                imgForca.setImageResource(R.drawable.forca0);
            }
            if(contadorErro == 3){
                imgForca.setImageResource(R.drawable.forca1);
            }
            if(contadorErro == 2){
                imgForca.setImageResource(R.drawable.forca2);
            }
            if(contadorErro == 1){
                imgForca.setImageResource(R.drawable.forca3);
            }
            if(contadorErro == 0){
                imgForca.setImageResource(R.drawable.forca4);
            }
            if(contadorErro != 0) {
                Toast toast = Toast.makeText(this, "Você tem " + contadorErro + " chances", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
        /****************************************************************/
        if(contadorAcertos == 4){
            quantAcertos++;
            txtAcerto.setText(quantAcertos + "");
            alert("Opá, Consagrado(a)!", "Temos um Campeão aqui!", "btn");
        }
        if(contadorErro == 0){
            quantErros++;
            alert("Eita...", "Você errou", "btn");
            txtErro.setText(quantErros + "");
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
            builder.setCancelable(false);
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
            builder.setCancelable(false);
            builder.show();
        }

    }
    /* ---------------- REINICIAR JOGO ---------------- */
    private void reiniciarJogo() {
        contadorAcertos = 0;
        contadorErro = 5;
        int aux = 0;
        txtDica.setText("");
        btnDica.setEnabled(true);
        imgForca.setImageResource(R.drawable.forca);
        aleatorio();
        for (int i = 0; i < resposta[palavraAleatoria].length; i++) {

            int id = getResources().getIdentifier("txtLetra" + i, "id", getPackageName());
            txtResposta[i] = findViewById(id);
            txtResposta[i].setText("_");

        }

        gerarListener();
    }
    public void dica(View v){
        textDicaAux = textDica[palavraAleatoria];
        speak.speak(textDicaAux, TextToSpeech.QUEUE_FLUSH, null);
        btnDica.setEnabled(false);
        txtDica.setText(textDicaAux);

    }
    public void aleatorio(){
        Random gerador = new Random();
        palavraAleatoria = gerador.nextInt(13);
    }
    /* ----------- Oncreate ----------- */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDica = new TextView(this);
        txtDica = findViewById(R.id.txtDica);
        btnDica = new Button(this);
        btnDica = findViewById(R.id.btnDica);
        txtAcerto = findViewById(R.id.textAcerto);
        txtErro = findViewById(R.id.textErro);

        imgForca = new ImageView(this);
        imgForca = findViewById(R.id.imgForca);
        gerarListener();
        aleatorio();

        speak = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    //Setando o idioma para o português.
                    speak.setLanguage(new Locale("pt", "br"));
                }
            }
        });
    }
}
