/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.salesController;
import Controllers.systemController;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ARTEMISA
 */
public class BoxCut {

    String id;
    String user;
    double inCash;
    double TotalOfSale;
    Date created;

    String dateFormat = "dd/MM/yyyy";
    String timeFormat = "HH:mm";

    public String getId() {
        return id;
    }

    public void genId() {
        this.id = salesController.getInstance().genBoxCutID();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getInCash() {
        return inCash;
    }

    public void setInCash(double inCash) {
        this.inCash = inCash;
    }

    public double getTotalOfSale() {
        return TotalOfSale;
    }

    public void setTotalOfSale(double TotalOfSale) {
        this.TotalOfSale = TotalOfSale;
    }

    public void saveInCash() {
        setInCash(systemController.getInstance().getInCash());
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreated() {
        this.created = new Date();
    }

    public String getDateCreatedToString() {
        return new SimpleDateFormat(dateFormat).format(created);
    }

    public String getTimeCreatedToString() {
        return new SimpleDateFormat(timeFormat).format(created);
    }

    public BoxCut() {
        setCreated();
    }

    @Override
    public String toString() {
        return "BoxCut{" + "id=" + id + ", user=" + user + ", created=" + created + '}';
    }

}
