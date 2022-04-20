/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.userController;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ATENEA
 */
public final class User {

    String id;
    String username;
    String password;
    String role;
    Date created;
    Date edited;
    String dateFormat = "dd/MM/yyyy";

    public String getId() {
        return id;
    }

    public void genId() {
        this.id = userController.getInstance().genID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedToString() {
        return new SimpleDateFormat(dateFormat).format(created);
    }

    public void setCreated() {
        this.created = new Date();
    }

    public void setCreated(String date) {
        try {
            this.created = new SimpleDateFormat(dateFormat).parse(date);
        } catch (Exception e) {
            new Messages().errorMessage("User", "setCreated()", e);
        }
    }

    public Date getEdited() {
        return edited;
    }

    public String getEditedToString() {
        return new SimpleDateFormat(dateFormat).format(edited);
    }

    public void setEdited() {
        this.edited = new Date();
    }

    public void setEdited(String date) {
        try {
            this.edited = new SimpleDateFormat(dateFormat).parse(date);
        } catch (Exception e) {
            new Messages().errorMessage("User", "setEdited()", e);
        }
    }

    public User() {
        this.setCreated();
        this.setEdited();
    }

    public boolean isAdmin() {
        return (getRole().equals("Administrador"));
    }

    public boolean isRoot() {
        return (getRole().equals("Administrador") && (getUsername().equals("root")));
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", created=" + created + ", edited=" + edited + ", dateFormat=" + dateFormat + '}';
    }

}
