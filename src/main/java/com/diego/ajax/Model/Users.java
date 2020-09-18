/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.ajax.Model;

/**
 *
 * @author Diego
 */
public class Users {

    private int id;
    private String name;
    private String password;
    private String access_level;

    public Users(int id, String name, String password, String access_level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.access_level = access_level;
    }

    public Users() {
 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }
}
