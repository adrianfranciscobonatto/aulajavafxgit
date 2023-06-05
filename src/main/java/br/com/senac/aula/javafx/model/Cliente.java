package br.com.senac.aula.javafx.model;

public class Cliente {

    private int id;
    private String nome;
    private String documento;
    private double rg;
    private String email;
    private double telefone;

    public Cliente(){

    }
    public Cliente(int id, String nome, String documento, double rg, String email, double telefone) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.rg = rg;
        this.email = email;
        this.telefone = telefone;
    }

    public double getRg() {
        return rg;
    }

    public void setRg(double rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTelefone() {
        return telefone;
    }

    public void setTelefone(double telefone) {
        this.telefone = telefone;
    }

    public Cliente(int id, String nome, String documento) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
