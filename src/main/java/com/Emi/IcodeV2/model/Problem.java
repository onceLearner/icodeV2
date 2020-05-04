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



    private String title;
    private String text;
    private String input;
    private String output;


    public Problem(){};
    public Problem(String title, String text, String input, String output) {
        this.title = title;
        this.text = text;
        this.input = input;
        this.output = output;
    }


    public void setIdP(Integer idP) {
        this.idP = idP;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Integer getIdP() {
        return idP;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }



    @Override
    public String toString() {
        return "Problem{" +
                "idP=" + idP +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
