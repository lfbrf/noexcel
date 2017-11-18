/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author FELIPE
 */
@ViewScoped
@ManagedBean(name = "examinationFormBean")
public class ExaminationFormBean implements Serializable {

    private String comment;
    private String radioVal = "student";
    private boolean showMap;

    public boolean isShowMap() {
        return showMap;
    }

    public void map() {
        this.showMap = true;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRadioVal() {
        return radioVal;
    }

    public void setRadioVal(String radioVal) {
        this.radioVal = radioVal;
    }

}
