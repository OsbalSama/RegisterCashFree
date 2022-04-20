/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.typeContainerController;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ATENEA
 */
public class Group {

    String id;
    String description;
    Date created;
    Date edited;

    String dateFormat = "dd/MM/yyyy";

    //Get & Set
    public String getId() {
        return id;
    }
    
    public void genId(){
        this.id = typeContainerController.getInstance().genTypeID();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Group() {
        this.setCreated();
        this.setEdited();
    }

}
