/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grecius.testcreatiweb.test;

import com.grecius.testcreatiweb.logic.CalcolaAcquaRaccolta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author grecius
 */
public class CalcolaAcquaRaccoltaTest {
    static CalcolaAcquaRaccolta calcola;
    private Integer[] interiArr = null;
    private List<Integer> interiList = null;
    @BeforeClass
    public static void testSetup() {
        calcola = new CalcolaAcquaRaccolta();
    }
    @Test
    public void testNullArgumentArray() {
      calcola.calcolaBlocchiAquaRaccolta(interiArr);
      Assert.assertEquals(new Integer(0),calcola.getTotAcqua());
    }
    @Test
    public void testNullArgumentList() {
      calcola.calcolaBlocchiAquaRaccolta(interiList);
      Assert.assertEquals(new Integer(0),calcola.getTotAcqua());
    }
    @Test
    public void testCalcolo1Arr() {
       //{5,2,3,4,5,4,1,3,1}
      Integer[] interiArr = {new Integer(5),new Integer(2),new Integer(3),new Integer(4),new Integer(5),new Integer(4),new Integer(1),new Integer(3),new Integer(1)};
      calcola.calcolaBlocchiAquaRaccolta(interiArr);
      Assert.assertEquals(new Integer(8),calcola.getTotAcqua());
    }
    @Test
    public void testCalcolo1List() {
      Integer[] interiArr = {new Integer(5),new Integer(2),new Integer(3),new Integer(4),new Integer(5),new Integer(4),new Integer(1),new Integer(3),new Integer(1)};
      calcola.calcolaBlocchiAquaRaccolta(new ArrayList<Integer>(Arrays.asList(interiArr)));
      Assert.assertEquals(new Integer(8),calcola.getTotAcqua());
    }
    @Test
    public void testCalcolo2() {
       //{5,5,3,6,2,3,5,10,5,9,11,11,11,1}
      Integer[] interiArr = {new Integer(5),new Integer(5),new Integer(3),new Integer(6),new Integer(2),new Integer(3),new Integer(5),new Integer(10),new Integer(5), new Integer(9),new Integer(11), new Integer(11), new Integer(11),new Integer(1)};
      calcola.calcolaBlocchiAquaRaccolta(interiArr);
      Assert.assertEquals(new Integer(16),calcola.getTotAcqua());
    }
     @Test
    public void testCalcolo3() {
       //{2,2,3,7,4,3,2,4,4,4,2,3,4}
      Integer[] interiArr = {new Integer(5),new Integer(5),new Integer(3),new Integer(6),new Integer(2),new Integer(3),new Integer(5),new Integer(10),new Integer(5), new Integer(9),new Integer(11), new Integer(11), new Integer(11),new Integer(1)};
      calcola.calcolaBlocchiAquaRaccolta(interiArr);
      Assert.assertEquals(new Integer(16),calcola.getTotAcqua());
    }
    /*@Test
    public void testCalcolo3MainArgs() {
      String args = "{2,2,3,7,4,3,2,4,4,4,2,3,4}";
      calcola.calcolaBlocchiAquaRaccolta(args);
      Assert.assertEquals(new Integer(16),calcola.getTotAcqua());
    }*/
    @Test
    public void testErroreInput1() {
      String args = "2,2,3,7,4,3,2,4,4,4,2,3,4}";
      calcola.calcolaBlocchiAquaRaccolta(args);
      Assert.assertTrue(calcola.getErr().containsKey(new Integer(1)));
    }
    @Test
    public void testErroreInput2() {
      String args = "{2,32001,3,7,4,3,2,4,4,4,2,3,4}";
      calcola.calcolaBlocchiAquaRaccolta(args);
      Assert.assertTrue(calcola.getErr().containsKey(new Integer(2)));
    }
    @Test
    public void testErroreInput3() {
      String args = "{2,A,3,7,4,3,2,4,4,4,2,3,4}";
      calcola.calcolaBlocchiAquaRaccolta(args);
      Assert.assertTrue(calcola.getErr().containsKey(new Integer(3)));
    }   
}
