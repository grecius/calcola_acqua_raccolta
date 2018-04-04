/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grecius.testcreatiweb.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author grecius
 */
public class CalcolaAcquaRaccoltaMain {

    public static void main(String[] args){

        if(args[0].length() == 0){
           System.out.println("richiesto parametro input"); 
        }
        CalcolaAcquaRaccolta acquaRaccolta = new CalcolaAcquaRaccolta();
        acquaRaccolta.calcolaBlocchiAquaRaccolta(args[0]);
        if(!acquaRaccolta.getErr().isEmpty()){
          System.out.println("errore Input " +acquaRaccolta.getErr().values());
        }
        System.out.println(acquaRaccolta.getTotAcqua().toString());
 }
}
