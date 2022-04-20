/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Messages;
import Models.User;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ATENEA
 */
public class userController {

    //Global Variables
    String Class = "userController";
    String archivo = "source\\DBS_0002.dat";
    List<User> userList;
    Gson g = new Gson();

    public boolean existFile(){
        return new File(archivo).exists();
    }
    
    //Get & Set
    public List<User> getUserList() {
        return this.userList;
    }

    //Singleton Instance;
    private static userController instance;

    protected userController() {
        updateMainList();
    }

    public static userController getInstance() {
        if (instance == null) {
            instance = new userController();
        }
        return instance;
    }

    //Classs Config 
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    //Functions & Methods
    //Local List Controller
    public void updateMainList() {
        try {
            userList = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    User temp = g.fromJson(ln, User.class);
                    userList.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateMainList()", e);
        }
    }

    //Database Controllers
    public void resetDataBase() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            updateMainList();
            addRootUser();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetDataBase()", e);
        }
    }

    //Update file with new Info
    public boolean updateDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < userList.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(userList.get(i))));
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

    public void addRootUser() {
        try {
            User root = new User();
            root.setUsername("root");
            root.setPassword("root");
            root.setRole("Administrador");
            create(root);

        } catch (Exception e) {
            new Messages().errorMessage(Class, "addRootUser()", e);
        }
    }

    //Random Number
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //basic Controlls
    public String genID() {
        String resp = getRandomNumber(100000, 999999) + "";
        if (findUser(resp)) {
            genID();
        }
        return resp + "";
    }

    public boolean existUser(User User) {
        boolean resp = false;
        if (!userList.isEmpty()) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(User.getUsername())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    //CRUD
    //Create
    public boolean create(User newUser) {
        boolean resp = false;
        try {
            if (!existUser(newUser)) {
                userList.add(newUser);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "create()", e);
        }
        return resp;
    }

    public boolean findUser(String idUser) {
        boolean resp = false;
        try {
            System.out.println("userList.size(): " + userList.size());
            for (int i = 1; i < userList.size(); i++) {
                if (userList.get(i).getId().equals(idUser)) {
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findUser()", e);
        }
        return resp;
    }

    public User find(String idUser) {
        User resp = null;
        try {
            if (!userList.isEmpty()) {
                for (int i = 1; i < userList.size(); i++) {
                    if (userList.get(i).getId().equals(idUser)) {
                        resp = userList.get(i);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            new Messages().errorMessage(Class, "find()", e);
        }
        return resp;
    }

    public User findByName(String username) {
        User resp = null;
        try {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(username)) {
                    resp = userList.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findByName()", e);
        }
        return resp;
    }

    //Update
    public boolean update(User User) {
        boolean resp = false;
        try {
            for (int i = 1; i < userList.size(); i++) {
                if (userList.get(i).getId().equals(User.getId())) {
                    userList.set(i, User);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "update()", e);
        }
        return resp;
    }

    //Delete
    public boolean drop(User User) {
        try {
            userList.remove(User);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "drop()", e);
        }
        return false;
    }
}
