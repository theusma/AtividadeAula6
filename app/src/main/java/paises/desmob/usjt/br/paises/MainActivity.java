package br.usjt.desmob.paises;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Matheus Ribeiro on 24/4/2018.
 * RA 816117912
 */

public class MainActivity extends Activity {
    Spinner spinnerContinente;
    String continente = "all";
    public static final String URL = "https://restcountries.eu/rest/v2/";
    public static final String PAISES = "br.usjt.desmob.paises";
    br.usjt.desmob.paises.Pais[] paises;
    Intent intent;
    ProgressBar timer;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerContinente = (Spinner) findViewById(R.id.spinnerContinente);
        spinnerContinente.setOnItemSelectedListener(new RegiaoSelecionada());
        timer = (ProgressBar)findViewById(R.id.timer);
        timer.setVisibility( View.INVISIBLE);
        contexto = this;
    }

    public void listarPaises(View view) {
        intent = new Intent(this, br.usjt.desmob.paises.ListaPaisesActivity.class);
        if(br.usjt.desmob.paises.PaisDataNetwork.isConnected(this)) {
            timer.setVisibility(View.VISIBLE);
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                paises = br.usjt.desmob.paises.PaisDataNetwork.buscarPaises(URL, continente);
                                br.usjt.desmob.paises.PaisDb db = new br.usjt.desmob.paises.PaisDb(contexto);
                                db.inserePaises(paises);
                                runOnUiThread(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      intent.putExtra(PAISES, paises);
                                                      startActivity(intent);
                                                      timer.setVisibility(View.INVISIBLE);
                                                  }
                                              }
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
        } else {
            Toast.makeText(this, "Rede inativa. Usando armazenamento local.",
                    Toast.LENGTH_SHORT).show();
            new CarregaPaisesDoBanco().execute("pais");
        }
    }

    private class RegiaoSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            continente = (String) parent.getItemAtPosition(position);
            if (continente.equals("Todos")) {
                continente = "all";
            } else {
                continente = "region/"+continente.toLowerCase();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class CarregaPaisesDoBanco extends AsyncTask<String, Void, br.usjt.desmob.paises.Pais[]> {

        @Override
        protected br.usjt.desmob.paises.Pais[] doInBackground(String... params) {
            br.usjt.desmob.paises.PaisDb db = new br.usjt.desmob.paises.PaisDb(contexto);
            br.usjt.desmob.paises.Pais[] paises = db.selecionaPaises();
            return paises;
        }

        public void onPostExecute(br.usjt.desmob.paises.Pais[] paises){
            intent.putExtra(PAISES, paises);
            startActivity(intent);
        }
    }
}
