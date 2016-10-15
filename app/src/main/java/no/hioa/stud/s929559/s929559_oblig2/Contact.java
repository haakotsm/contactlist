package no.hioa.stud.s929559.s929559_oblig2;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {

    private int id;
    private String fornavn, etternavn;
    private String telefon;
    private Date bursdag;

    @SuppressLint("SimpleDateFormat")
    public Contact(int id, String fornavn, String etternavn, String telefon, String bursdag) {
        this.id = id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefon = telefon;
        try {
            this.bursdag = new SimpleDateFormat("dd/MM/yyyy").parse(bursdag);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getBursdag() {
        return bursdag;
    }

    public void setBursdag(Date bursdag) {
        this.bursdag = bursdag;
    }

    public String toString() {
        return etternavn;
    }
}
