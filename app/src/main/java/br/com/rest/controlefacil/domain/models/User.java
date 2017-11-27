package br.com.rest.controlefacil.domain.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public class User extends RealmObject {

    @PrimaryKey
    private Long id;
    private String email;
    private String password;
    private String nome;
    private String photoUrl;

    public User(){
        id = 0L;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
