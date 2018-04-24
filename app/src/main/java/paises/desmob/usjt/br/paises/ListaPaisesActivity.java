package br.usjt.desmob.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Matheus Ribeiro on 24/4/2018.
 * RA 816117880
 */

public class ListaPaisesActivity extends Activity {
    public static final String PAIS = "br.usjt.desmob.paises";
    Activity atividade;
    br.usjt.desmob.paises.Pais[] paises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paises);
        atividade = this;
        Intent intent = getIntent();

        paises = (br.usjt.desmob.paises.Pais[]) intent.getSerializableExtra(br.usjt.desmob.paises.MainActivity.PAISES);

        ListView listView = (ListView) findViewById(R.id.lista_paises);
        br.usjt.desmob.paises.PaisAdapter adapter = new br.usjt.desmob.paises.PaisAdapter(paises, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(atividade, br.usjt.desmob.paises.DetalhePaisActivity.class);
                intent.putExtra(PAIS, paises[position]);

                startActivity(intent);

            }

        });
    }
}
