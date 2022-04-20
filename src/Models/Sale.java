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
public class Sale {

    String id;
    String seller;
    double totalOfSale;
    String boxCut;
    Date created;

    String dateFormat = "dd/MM/yyyy";
    String timeFormat = "HH:mm";

    public String getId() {
        return id;
    }

    public void genId() {
        this.id = salesController.getInstance().genSaleID();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getTotalOfSale() {
        return totalOfSale;
    }

    public void setTotalOfSale(double totalOfSale) {
        this.totalOfSale = totalOfSale;
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

    public String getBoxCut() {
        return boxCut;
    }

    public void setBoxCut(String boxCut) {
        this.boxCut = boxCut;
    }

    public Sale() {
        setCreated();
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", seller=" + seller + ", totalOfSale=" + totalOfSale + ", created=" + created + '}';
    }

}
