/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.salesController;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ARTEMISA
 */
public class SoldProduct {

    String id;
    String idSale;
    String description;
    String brand;
    boolean isBulk = false;
    double value;
    double cant;
    double total;
    Date created;

    String dateFormat = "dd/MM/yyyy";

    public String getId() {
        return id;
    }

    public void genId() {
        this.id = salesController.getInstance().genSoldProductID();
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isBulk() {
        return isBulk;
    }

    public void setBulk(boolean isBulk) {
        this.isBulk = isBulk;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCant() {
        return cant;
    }

    public void setCant(double cant) {
        this.cant = cant;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedToString() {
        return new SimpleDateFormat(dateFormat).format(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreated() {
        this.created = new Date();
    }

    public SoldProduct() {
        setCreated();
    }

}
