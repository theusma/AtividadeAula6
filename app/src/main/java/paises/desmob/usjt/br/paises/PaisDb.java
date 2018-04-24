package br.usjt.desmob.paises;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;


import static br.usjt.desmob.paises.PaisContract.PaisEntry.*;
/**
 * Created by Matheus Ribeiro on 24/4/2018.
 * RA 816117880
 */

public class PaisDb {
    br.usjt.desmob.paises.PaisDbHelper dbHelper;

    public PaisDb(Context contexto){
        dbHelper = new br.usjt.desmob.paises.PaisDbHelper(contexto);
    }

    public void inserePaises(br.usjt.desmob.paises.Pais[] paises){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete( TABLE_NAME, null, null);

        for(br.usjt.desmob.paises.Pais pais:paises){
            ContentValues values = new ContentValues();
            values.put( COLUMN_NAME_NOME, pais.getNome());
            values.put( COLUMN_NAME_REGIAO, pais.getRegiao());
            values.put( COLUMN_NAME_CAPITAL, pais.getCapital());
            values.put( COLUMN_NAME_BANDEIRA, pais.getBandeira());
            values.put( COLUMN_NAME_CODIGO3, pais.getCodigo3());
            values.put( COLUMN_NAME_DEMONIMO, pais.getDemonimo());
            values.put( COLUMN_NAME_SUBREGIAO, pais.getSubRegiao());
            values.put( COLUMN_NAME_POPULACAO, pais.getPopulacao());
            values.put( COLUMN_NAME_GINI, pais.getGini());
            values.put( COLUMN_NAME_IDIOMAS, String.valueOf( pais.getIdiomas() ) );
            values.put( COLUMN_NAME_FRONTEIRAS, String.valueOf( pais.getFronteiras() ) );
            values.put( COLUMN_NAME_FUSOS, String.valueOf( pais.getFusos() ) );
            values.put( COLUMN_NAME_MOEDAS, String.valueOf( pais.getMoedas() ) );
            values.put( COLUMN_NAME_DOMINIOS, String.valueOf( pais.getDominios() ) );
            values.put( COLUMN_NAME_LATITUDE, pais.getLatitude());
            values.put( COLUMN_NAME_LONGITUDE, pais.getLongitude());
            values.put( COLUMN_NAME_AREA, pais.getArea());

            db.insert( TABLE_NAME, null, values);
        }
    }

    public br.usjt.desmob.paises.Pais[] selecionaPaises(){
        ArrayList<br.usjt.desmob.paises.Pais> paises = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = { COLUMN_NAME_NOME,
                COLUMN_NAME_REGIAO,
                COLUMN_NAME_CAPITAL,
                COLUMN_NAME_BANDEIRA,
                COLUMN_NAME_CODIGO3,
                COLUMN_NAME_LONGITUDE ,
                COLUMN_NAME_LATITUDE,
                COLUMN_NAME_GINI,
                COLUMN_NAME_SUBREGIAO,
                COLUMN_NAME_POPULACAO,
                COLUMN_NAME_DEMONIMO,
                COLUMN_NAME_MOEDAS,
                COLUMN_NAME_IDIOMAS,
                COLUMN_NAME_FUSOS,
                COLUMN_NAME_FRONTEIRAS,
                COLUMN_NAME_DOMINIOS,
                COLUMN_NAME_AREA};
        String ordem = COLUMN_NAME_NOME;

        Cursor c = db.query( TABLE_NAME, colunas, null, null,
                ordem, null, null);
        while(c.moveToNext()) {
            Pais pais = new Pais();
            pais.setNome(c.getString(c.getColumnIndex( COLUMN_NAME_NOME)));
            pais.setRegiao(c.getString(c.getColumnIndex( COLUMN_NAME_REGIAO)));
            pais.setCapital(c.getString(c.getColumnIndex( COLUMN_NAME_CAPITAL)));
            pais.setBandeira(c.getString(c.getColumnIndex( COLUMN_NAME_BANDEIRA)));
            pais.setCodigo3(c.getString(c.getColumnIndex( COLUMN_NAME_CODIGO3)));
            pais.setLongitude(c.getDouble(c.getColumnIndex( COLUMN_NAME_LONGITUDE ) ));
            pais.setLatitude(c.getDouble(c.getColumnIndex( COLUMN_NAME_LATITUDE ) ));
            pais.setGini(c.getDouble(c.getColumnIndex( COLUMN_NAME_GINI ) ));
            pais.setSubRegiao(c.getString(c.getColumnIndex( COLUMN_NAME_SUBREGIAO)));
            pais.setPopulacao(c.getInt(c.getColumnIndex( COLUMN_NAME_POPULACAO ) ));
            pais.setDemonimo(c.getString(c.getColumnIndex( COLUMN_NAME_DEMONIMO)));
            pais.setArea(c.getInt(c.getColumnIndex( COLUMN_NAME_AREA ) ));

            ArrayList<String> moedas = new ArrayList<>();
            moedas.add(c.getString(c.getColumnIndex((String.valueOf(PaisContract.PaisEntry.COLUMN_NAME_MOEDAS)))));
            pais.setMoedas(moedas);

            ArrayList<String> fronteiras = new ArrayList<>();
            fronteiras.add(c.getString(c.getColumnIndex((String.valueOf(PaisContract.PaisEntry.COLUMN_NAME_FRONTEIRAS)))));
            pais.setFronteiras(fronteiras);

            ArrayList<String> fusos = new ArrayList<>();
            fusos.add(c.getString(c.getColumnIndex((String.valueOf(PaisContract.PaisEntry.COLUMN_NAME_FUSOS)))));
            pais.setFusos(fusos);

            ArrayList<String> dominios = new ArrayList<>();
            dominios.add(c.getString(c.getColumnIndex((String.valueOf(PaisContract.PaisEntry.COLUMN_NAME_DOMINIOS)))));
            pais.setDominios(dominios);

            ArrayList<String> idiomas = new ArrayList<>();
            idiomas.add(c.getString(c.getColumnIndex((String.valueOf(PaisContract.PaisEntry.COLUMN_NAME_IDIOMAS)))));
            pais.setIdiomas(idiomas);

            paises.add(pais);
        }
        c.close();
        if(paises.size()> 0) {
            return paises.toArray(new Pais[0]);
        } else {
            return new Pais[0];
        }

        }
    }

