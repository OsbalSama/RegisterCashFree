/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Views.messages.ErrorMessage;
import Views.messages.YesNoMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author ARTEMISA
 */
public class Messages {

    public void closeMessage(String title, String message) {
        YesNoMessage msge = new YesNoMessage(null, true);
        msge.setData(title, message, 3);
        msge.show();
    }

    public void aceptMessage(String title, String message) {
        YesNoMessage msge = new YesNoMessage(null, true);
        msge.setData(title, message, 4);
        msge.show();
    }

    public boolean confirmMessage(String title, String message) {
        YesNoMessage msge = new YesNoMessage(null, true);
        msge.setData(title, message, 1);
        msge.show();
        return msge.isNext();
    }

    public boolean yesNoMessage(String title, String message) {
        YesNoMessage msge = new YesNoMessage(null, true);
        msge.setData(title, message, 0);
        msge.show();
        return msge.isNext();
    }

    public boolean continueCancelMessage(String title, String message) {
        YesNoMessage msge = new YesNoMessage(null, true);
        msge.setData(title, message, 2);
        msge.show();
        return msge.isNext();
    }

    public void errorMessage(String Class, String Method, Exception e) {
        try {
            ErrorMessage msge = new ErrorMessage(null, true);
            msge.setData(Class, Method, e);
            msge.show();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error in Message: " + ex);
        }

    }
}
