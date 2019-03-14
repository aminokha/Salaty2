package com.example.amine.myapplication;

import java.util.Date;

public class Salat {
    private SalatName salatName = null;
    private  Date startAt = null;
    private Date endtAt = null;
    private EtatSalat etatSalat = null;


    public Salat(SalatName salatName, Date startAt, Date endtAt, EtatSalat etatSalat) {
        this.salatName = salatName;
        this.startAt = startAt;
        this.endtAt = endtAt;
        this.etatSalat = etatSalat;
    }

    public SalatName getSalatName() {
        return salatName;
    }

    public void setSalatName(SalatName salatName) {
        this.salatName = salatName;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndtAt() {
        return endtAt;
    }

    public void setEndtAt(Date endtAt) {
        this.endtAt = endtAt;
    }

    public EtatSalat getEtatSalat() {
        return etatSalat;
    }

    public void setEtatSalat(EtatSalat etatSalat) {
        this.etatSalat = etatSalat;
    }
}
