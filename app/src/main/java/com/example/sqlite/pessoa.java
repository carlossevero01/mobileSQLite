package com.example.sqlite;

import java.io.Serializable;

public class pessoa implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String curso;
    public pessoa(){
        this.id = 0;
        this.nome="";
        this.email="";
        this.curso="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
