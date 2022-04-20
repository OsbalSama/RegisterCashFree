/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.productController;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ATENEA
 */
public class Product {

    String id;
    String description;
    String brand;
    String type;
    String container;
    boolean Bulk;
    double value;
    double inventory;
    double cant;
    Date created;
    Date edited;

    String dateFormat = "dd/MM/yyyy";

    //Get & Set
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public void restCant(double cant) {
        if (this.inventory - cant > 0) {
            this.inventory = inventory - cant;
        } else {
            this.inventory = 0;
        }
    }

    public double getCant() {
        return cant;
    }

    public void setCant(double cant) {
        this.cant = cant;
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

    public String getCreatedToString() {
        return new SimpleDateFormat(dateFormat).format(created);
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }

    public void setEdited() {
        this.edited = new Date();
    }

    public String getEditedToString() {
        return new SimpleDateFormat(dateFormat).format(edited);
    }

    public boolean isBulk() {
        return Bulk;
    }

    public void setBulk(boolean Bulk) {
        this.Bulk = Bulk;
    }

    public void genId() {
        setId(productController.getInstance().genId());
    }

    public Product() {
        this.setCreated();
        this.setEdited();
        setBulk(false);
        setCant(1);
    }

    public void addCant(float Cant) {
        this.cant = this.cant + Cant;
    }

    public void addCant() {
        this.cant++;
    }

    public void removeCant() {
        this.cant--;
    }

    public boolean haveCant() {
        return (cant > 0);
    }

    public boolean noInventory() {
        return (inventory == 0);
    }

    public boolean canBeAdded(double compare) {
        return (compare < inventory);
    }

    public void addCant(double cantidad) {
        setCant(getCant() + cantidad);
    }

    public void addInventory(double cant) {
        setInventory(getInventory() + cant);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", description=" + description + ", brand=" + brand + ", type=" + type + ", container=" + container + ", value=" + value + ", inventory=" + inventory + ", cant=" + cant + ", created=" + created + ", edited=" + edited + ", dateFormat=";
    }

}
