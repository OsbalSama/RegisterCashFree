/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Messages;
import Models.Session;
import Models.User;
import Views.adminControlls.adminHome;
import Views.commondialogs.updateInCash;
import Views.userControlls.userHome;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author ARTEMISA
 */
public class sessionController {

    //Global Variables
    userController allUsers = userController.getInstance();
    String Class = "sessionController";
    String archivo = "source\\DBS_0006.dat";
    List<Session> DataBase;
    Gson g = new Gson();
    User LogedUser;
    adminHome adminHome;
    userHome userHome;

    public boolean existFile() {
        return new File(archivo).exists();
    }

    public JFrame getMainFrame() {
        JFrame resp = null;
        if (LogedUser != null && LogedUser.isAdmin()) {
            resp = adminHome;
        } else {
            resp = userHome;
        }
        return resp;
    }
    
    public JFrame getShowedFrame() {
        JFrame resp = null;
        
        return resp;
    }

    public void Loggin(User loggedUser) {
        setLogedUser(loggedUser);
        try {
            if (LogedUser != null) {
                if (LogedUser.isAdmin() || LogedUser.isRoot()) {
                    adminHome = new adminHome();
                    adminHome.setData();
                    adminHome.show();
                    if (systemController.getInstance().isUpCash()) {
                        updateInCash uic = new updateInCash(getMainFrame(), true);
                        uic.setData();
                        uic.show();
                    }
                } else {
                    userHome = new userHome();
                    userHome.setData();
                    userHome.show();
                    if (systemController.getInstance().isUpCash()) {
                        updateInCash uic = new updateInCash(getMainFrame(), true);
                        uic.setData();
                        uic.show();
                    }
                }
            } else {
                System.out.println("LogedUser = " + LogedUser);
            }

        } catch (Exception e) {
            new Messages().errorMessage(Class, "Loggin()", e);
        }

    }

    public boolean exitFromReset() {
        boolean resp = false;
        try {
            if (LogedUser.isAdmin() || LogedUser.isRoot()) {
                adminHome.dispose();
                adminHome = null;
                LogedUser = null;
            } else {
                userHome.dispose();
                userHome = null;
                LogedUser = null;
            }
            systemController.getInstance().startSystem();
            resp = true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "Loggout()", e);
        }
        return resp;
    }

    public boolean Loggout() {
        boolean resp = false;
        try {
            String text = "<html>"
                    + "<center>"
                    + "<b>"
                    + "¡SE PERDERÁN TODOS LOS DATOS QUE NO SE HAYAN GUARDADO!"
                    + "</b>"
                    + "<br>"
                    + "<br>"
                    + "¿Desea continuar?"
                    + "</center>"
                    + "</html>";
            if (new Messages().yesNoMessage("¡Cerrar Sesion!", text)) {
                dataController.getInstance().genBoxCut();
                if (LogedUser.isAdmin() || LogedUser.isRoot()) {
                    adminHome.dispose();
                    adminHome = null;
                    LogedUser = null;
                } else {
                    userHome.dispose();
                    userHome = null;
                    LogedUser = null;
                }
                systemController.getInstance().startSystem();
                resp = true;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "Loggout()", e);
        }
        return resp;
    }

    public boolean checkLevelAccess(String username, String password) {
        boolean resp = false;
        try {
            User finded = allUsers.findByName(username);
            if (finded != null) {
                if (finded.getUsername().equals(username) && finded.getPassword().equals(password)) {
                    resp = true;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "checkLevelAccess()", e);
        }
        return resp;
    }

    public int tryLoggin(String username, String Password) {
        //0 todo OK
//        //1 Usuario no encontrado
//        //2 Contraseñas no coinciden
        int resp = 0;
        try {
            User finded = allUsers.findByName(username);
            if (finded != null) {
                if (finded.getPassword().equals(Password)) {
                    resp = 0;
                } else {
                    resp = 2;
                }
            } else {
                resp = 1;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "tryLogin()", e);
        }
        return resp;
    }

    public void exitApp() {
        if (new Messages().yesNoMessage("¡Usted está a punto de salir!", "¿Está seguro que desea cerrar la aplicación?")) {
            System.exit(0);
        }
    }

    public void closeApp() {
        String text = "<html>"
                + "<center>"
                + "<b>"
                + "¡SE PERDERÁN TODOS LOS DATOS QUE NO SE HAYAN GUARDADO!"
                + "</b>"
                + "<br>"
                + "<br>"
                + "¿Desea salir?"
                + "</center>"
                + "</html>";
        if (new Messages().yesNoMessage("¡Salir del Sistema!", text)) {
            dataController.getInstance().genBoxCut();
            System.exit(0);
        }
    }

    //Get & Set
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public List<Session> getDataBase() {
        return DataBase;
    }

    public void setDataBase(List<Session> DataBase) {
        this.DataBase = DataBase;
    }

    public User getLogedUser() {
        return LogedUser;
    }

    public void setLogedUser(User LogedUser) {
        this.LogedUser = LogedUser;
    }

    //Singleton Instance;
    private static sessionController instance;

    protected sessionController() {
        updateMainList();
    }

    public static sessionController getInstance() {
        if (instance == null) {
            instance = new sessionController();
        }
        return instance;
    }

    //Metodos y Funciones
    //Database Controllers
    public void resetDataBase() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            updateMainList();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetDataBase()", e);
        }
    }

    //Reset Local Inventory
    public void updateMainList() {
        try {
            DataBase = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    Session temp = g.fromJson(ln, Session.class);
                    DataBase.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateMainList()", e);
        }
    }

    //Update file with new Info
    public boolean updateDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < DataBase.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(DataBase.get(i))));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            updateMainList();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateDataBase()", e);
        }
        return resp;
    }

    //Random Number
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //basic Controlls
    public String genID() {
        String resp = String.valueOf(getRandomNumber(100000, 999999));
        if (findbyID(resp) != null) {
            genID();
        }
        return resp;
    }

    //Find a Login by ID
    public Session findbyID(String id) {
        Session resp = null;
        try {
            for (int i = 0; i < DataBase.size(); i++) {
                if (DataBase.get(i).getId().equals(id)) {
                    resp = DataBase.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findbyID()", e);
        }
        return resp;
    }

    //Find a Session
    public Session findSesion(String username, String LoginDate) {
        Session resp = null;
        try {
            for (int i = 0; i < DataBase.size(); i++) {
                if ((DataBase.get(i).getUsername().equals(username)) && (DataBase.get(i).getFirstLoginToString().equals(LoginDate))) {
                    resp = DataBase.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSesion()", e);
        }
        return resp;
    }

    //Check if a Login Exist
    public boolean existSession(String username, String LoginDate) {
        boolean resp = false;
        try {
            for (int i = 0; i < DataBase.size(); i++) {
                if ((DataBase.get(i).getUsername().equals(username)) && (DataBase.get(i).getFirstLoginToString().equals(LoginDate))) {
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "existSession()", e);
        }
        return resp;
    }

    //CRUD
    //Create
    public boolean create(Session newReg) {
        boolean resp = false;
        try {
            if (!existSession(newReg.getUsername(), newReg.getFirstLoginToString())) {
                DataBase.add(newReg);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "create()", e);
        }
        return resp;
    }

    //Update Session Login Count, Last Login Date and time
    public boolean updateSession(Session session) {
        boolean resp = false;
        try {
            for (int i = 0; i < DataBase.size(); i++) {
                if (DataBase.get(i).getId().equals(session.getId())) {
                    session.setLastLogin();
                    session.setTimeLastLogin();
                    session.upNumberLoggins();
                    DataBase.set(i, session);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSession()", e);
        }
        return resp;
    }

    public List<Session> findRegbyDateRange(Date From, Date To) {
        List<Session> resp = new ArrayList<>();
        try {
            if (From.equals(To)) {
                From.setHours(0);
                From.setMinutes(0);
                From.setSeconds(0);
                To.setHours(23);
                To.setMinutes(59);
                To.setSeconds(59);
            }

            for (int i = 0; i < DataBase.size(); i++) {
                Session temp = DataBase.get(i);
                if (((temp.getFirstLogin().after(From)) && (temp.getLastLogin().before(To))) || ((temp.getFirstLogin().equals(From)) && (temp.getLastLogin().equals(To)))) {
                    resp.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findRegbyDateRange()", e);
        }
        return resp;
    }

    public void saveNewLogin(User User) {
        try {
            Date Now = new Date();
            Session registro = new Session();
            if (existSession(User.getUsername(), registro.getFirstLoginToString())) {
                registro = findSesion(User.getUsername(), registro.getFirstLoginToString());
                updateSession(registro);
            } else {
                registro.genId();
                registro.setUsername(User.getUsername());
                registro.upNumberLoggins();
                create(registro);
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "saveNewLogin()", e);
        }
    }
}
