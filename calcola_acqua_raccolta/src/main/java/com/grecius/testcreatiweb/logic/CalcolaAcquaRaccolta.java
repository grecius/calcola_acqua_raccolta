/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grecius.testcreatiweb.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author grecius
 */

public class CalcolaAcquaRaccolta {
    private Integer totAcqua = new Integer(0);
    private List<Integer> bloccoAltezze = new ArrayList<Integer>(0);
    private List<Integer> listaAcqua = new ArrayList<Integer>(0);
    private Integer livelloAcqua = new Integer(0);
    private List<Integer> altezze = null;
    private int posizione = 0;
    private String andamentoValue = "d";
    private Integer[] altezzeArr = null;
    private Map<Integer,String> err = new HashMap<Integer,String>();
    
    public void inizializza(){
        totAcqua = 0;
        bloccoAltezze = new ArrayList<Integer>(0);
        listaAcqua = new ArrayList<Integer>(0);
        livelloAcqua = new Integer(0);
    }
    
    
    public void calcolaBlocchiAquaRaccolta(String altezze) {
        controllaArgomenti(altezze);
        if(err.isEmpty()){
            calcolaBlocchiAquaRaccolta(altezzeArr);
        }
    }

    
    public void calcolaBlocchiAquaRaccolta(Integer[] altezze){
        if(altezze == null || altezze.length == 0){
           return; 
        }
        calcolaBlocchiAquaRaccolta(Arrays.asList(altezze));
    }
    public void calcolaBlocchiAquaRaccolta(List<Integer> altezze){
        //inizializza
        inizializza();
        this.altezze = altezze;
        if(this.altezze == null || this.altezze.isEmpty()){
            return; 
        }
        while(this.altezze.size()> 0){
            posizione = 0;
            for(Integer altezzaValue : this.altezze){
                posizione++;
                if(bloccoAltezze.isEmpty()){
                    bloccoAltezze.add(altezzaValue);
                    continue;
                }
                //valore minore del precedente e andamento down
                if(altezzaValue.compareTo(bloccoAltezze.get(bloccoAltezze.size()-1)) < 0 && andamentoValue.equals("d")){
                    bloccoAltezze.add(altezzaValue);
                //valore minore del precedente e andamento up
                }else if(altezzaValue.compareTo(bloccoAltezze.get(bloccoAltezze.size()-1)) < 0 && andamentoValue.equals("u")){
                    andamentoValue = "d";
                    calcolaBloccoAcquaRaccolta();
                    break;
                //valore maggiore del precedente e andamento down
                }else if(altezzaValue.compareTo(bloccoAltezze.get(bloccoAltezze.size()-1)) > 0 && andamentoValue.equals("d")){
                    andamentoValue = "u";
                    bloccoAltezze.add(altezzaValue);
                //valore maggiore del precedente e andamento up  
                }else if(altezzaValue.compareTo(bloccoAltezze.get(bloccoAltezze.size()-1)) > 0 && andamentoValue.equals("u")){
                    bloccoAltezze.add(altezzaValue);
                }
           }
        }
    }
    
    public Integer getTotAcqua() {
        return totAcqua;
    }

    public List<Integer> getListaAcqua() {
        return listaAcqua;
    }
    
    public Map<Integer, String> getErr() {
        return err;
    }
    
    private void calcolaBloccoAcquaRaccolta(){
        livelloAcqua = Integer.min(bloccoAltezze.get(0), bloccoAltezze.get(bloccoAltezze.size()-1));
        for(Integer bloccoAltezzaValue : bloccoAltezze){
            if(bloccoAltezzaValue.compareTo(livelloAcqua) < 0){
                listaAcqua.add(new Integer(livelloAcqua.intValue() -  bloccoAltezzaValue.intValue()));
                totAcqua+= new Integer(livelloAcqua.intValue() -  bloccoAltezzaValue.intValue());
            }else{
               listaAcqua.add(0); 
            } 
        }
        clearBloccoAltezze();
    }
    
    private void clearBloccoAltezze(){
        altezze = altezze.subList(posizione-2, altezze.size());
        if(altezze.size()== 2){
            altezze = new ArrayList<Integer>(0);
        }
        bloccoAltezze.clear();
    }
    
    private void controllaArgomenti(String altezze){
        Integer numeroSequenza = null;
        int contatoreLunghezza = 0;
        if(altezze.charAt(0) != '{' || altezze.charAt(altezze.length()-1) != '}'){
           err.put(new Integer(1),"formato input consentito {1,2,3,4,5}");
          return;
        }
           String sequenza = altezze.substring(1, altezze.length()-1);
           StringTokenizer st = new StringTokenizer(sequenza, ",");
           altezzeArr = new Integer[st.countTokens()];
           while (st.hasMoreTokens()) {
                try{
                    numeroSequenza = new Integer(st.nextToken());

                    if(numeroSequenza.intValue() > 32000){
                        err.put(new Integer(2),"valore numerico massimo consentito 32000");
                        break;
                    }
                    
                }catch(NumberFormatException nEx){
                    err.put(new Integer(3),"solo valori numerici consentiti");
                }
                altezzeArr[contatoreLunghezza] = numeroSequenza;
                contatoreLunghezza++;
            }
            if(contatoreLunghezza > 32000){
                err.put(new Integer(4),"massimo numero valori consentiti 32000");
                return;
            }
    }

}
