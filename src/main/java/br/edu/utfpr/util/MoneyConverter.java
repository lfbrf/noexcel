/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Roni
 */
@FacesConverter(value = "moneyConverter", forClass = Long.class)
public class MoneyConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String s) {        
        return Long.valueOf(s.replace(".", "").replace(",", ""));        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return MoneyUtil.getFormatted((Long)o);
    }
    
}
