package com.Emi.IcodeV2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idP;



    private String label;
    private String text;
    private String input;
    private String ref;


    public Problem(){};
    public Problem(String label, String text, String input, String output) {
        this.label = label;
        this.text = text;
        this.input = input;
        this.ref = ref;
    }


    public void setIdP(Integer idP) {
        this.idP = idP;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setref(String ref) {
        this.ref = ref;
    }

    public Integer getIdP() {
        return idP;
    }

    public String getLabel() {
        return label;
    }

    public String getText() {
        return text;
    }

    public String getInput() {
        return input;
    }

    public String getref() {
        return ref;
    }



    @Override
    public String toString() {
        return "Problem{" +
                "idP=" + idP +
                ", label='" + label + '\'' +
                ", text='" + text + '\'' +
                ", input='" + input + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
