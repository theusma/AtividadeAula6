package br.usjt.desmob.paises;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

/**
 * Created by Matheus Ribeiro on 24/4/2018.
 * RA 816117912
 */

public class SectionIndexBuilder {
    //cria um array de cabeçalhos de seção; países devem estar ordenados por nome
    public static Object[] buildSectionHeaders(br.usjt.desmob.paises.Pais[] paises){
        ArrayList<String> resultado = new ArrayList<>();
        TreeSet<String> usados = new TreeSet<>();
        for(br.usjt.desmob.paises.Pais pais:paises){
            String letra = pais.getNome().substring(0,1);
            if((!usados.contains(letra))){
                resultado.add(letra);
            }
            usados.add(letra);
        }
        return resultado.toArray(new Object[0]);
    }
    //cria uma mapa para responder: posicao --> secao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> buildSectionForPositionMap(br.usjt.desmob.paises.Pais[] paises){
        Hashtable<Integer, Integer> resultados = new Hashtable<>();
        TreeSet<String> usados = new TreeSet<>();

        int secao = -1;

        for(int i = 0; i < paises.length; i++){
            String letra = paises[i].getNome().substring(0,1);

            if(!usados.contains(letra)){
                secao++;
                usados.add(letra);
            }
            resultados.put(i, secao);
        }
        return resultados;
    }

    //cria uma mapa para responder: secao --> posicao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> buildPositionForSectionMap(br.usjt.desmob.paises.Pais[] paises){
        Hashtable<Integer, Integer> resultados = new Hashtable<>();
        TreeSet<String> usados = new TreeSet<>();

        int secao = -1;

        for(int i = 0; i < paises.length; i++){
            String letra = paises[i].getNome().substring(0,1);

            if(!usados.contains(letra)){
                secao++;
                usados.add(letra);
                resultados.put(secao, i);
            }
        }
        return resultados;
    }
}
