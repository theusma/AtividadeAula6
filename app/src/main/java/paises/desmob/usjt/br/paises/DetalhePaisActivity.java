package br.usjt.desmob.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Matheus Ribeiro on 24/4/2018.
 * RA 816117912
 */

public class DetalhePaisActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pais);
        TextView txtPais = (TextView)findViewById(R.id.txtPais);
        Intent intent = getIntent();
        br.usjt.desmob.paises.Pais pais = (br.usjt.desmob.paises.Pais)intent.getSerializableExtra(br.usjt.desmob.paises.ListaPaisesActivity.PAIS);
        txtPais.setText(pais.toString());
    }
}
