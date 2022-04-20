/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.sessionController;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ARTEMISA
 */
public class Session {

    String id;
    String username;
    double incash;
    Date firstLogin;
    String timeFirstLogin;
    Date lastLogin;
    String timeLastLogin;
    int numberLoggins;

    String dateFormat = "dd/MM/yyyy";
    String timeFormat = "HH:mm";

    public String getId() {
        return id;
    }

    public void genId() {
        this.id = sessionController.getInstance().genID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    public void setFirstLogin() {
        this.firstLogin = new Date();
    }

    public String getFirstLoginToString() {
        return new SimpleDateFormat(dateFormat).format(firstLogin);
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setLastLogin() {
        this.lastLogin = new Date();
    }

    public String getLastLoginToString() {
        return new SimpleDateFormat(dateFormat).format(lastLogin);
    }

    public String getTimeFirstLogin() {
        return timeFirstLogin;
    }

    public void setTimeFirstLogin() {
        this.timeFirstLogin = new SimpleDateFormat(timeFormat).format(firstLogin);
    }

    public String getTimeLastLogin() {
        return timeLastLogin;
    }

    public void setTimeLastLogin() {
        this.timeLastLogin = new SimpleDateFormat(timeFormat).format(lastLogin);
    }

    public int getNumberLoggins() {
        return numberLoggins;
    }

    public void setNumberLoggins(int numberLoggins) {
        this.numberLoggins = numberLoggins;
    }

    public Session() {
        this.setFirstLogin();
        setTimeFirstLogin();
        this.setLastLogin();
        setTimeLastLogin();
        resetNumberLoggins();
    }

    public void resetNumberLoggins() {
        numberLoggins = 0;
    }

    public void upNumberLoggins() {
        numberLoggins++;
    }
}
