/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.util;

/**
 *
 * @author Roni
 */
public class MoneyUtil {
    
    /**
     * 
     * Retorna a parte em reais, sem os centavos
     * 
     * @param value
     * @return 
     */
    public static long getReal(long value){
        return value/100;
    }
    
    /**
     * 
     * Retorna a parte em centavos.
     * 
     * @param value
     * @return 
     */
    public static long getCents(long value){
        return value%100;
    }
    
    /**
     * 
     * Retorna o valor formatado. Ex: 4,56
     * 
     * @param value
     * @return 
     */
    public static String getFormatted(long value){
        return getReal(value) + "," + getCents(value);
    }
    
}
