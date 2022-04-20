/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Container;
import Models.Messages;
import Models.Group;
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
public class typeContainerController {

    //Global Variables
    String Class = "typeContainerController";

    String archivo = "source\\DBS_0004.dat";
    String archivo2 = "source\\DBS_0005.dat";
    
    public boolean existFile(){
        return new File(archivo).exists();
    }
    
    public boolean existFile2(){
        return new File(archivo2).exists();
    }
    List<Group> types;
    List<Container> counters;
    Gson g = new Gson();

    //Get & Set
    public List<Group> getTypes() {
        return types;
    }

    public List<Container> getCounters() {
        return counters;
    }

    public String getArchivo() {
        return archivo;
    }

    public String getArchivo2() {
        return archivo2;
    }

    //Singleton Instance;
    private static typeContainerController instance;

    protected typeContainerController() {
        updateMainList();
    }

    public static typeContainerController getInstance() {
        if (instance == null) {
            instance = new typeContainerController();
        }
        return instance;
    }

    //Metodos y Funciones
    //Database Controllers
    public void resetTypes() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            updateMainList();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetCats()", e);
        }
    }

    public void resetContainers() {
        try {
            new File(getArchivo2()).delete();
            new File(getArchivo2()).createNewFile();
            updateMainList();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetProvs()", e);
        }
    }

    //Reset Local Databases
    public void updateMainList() {
        updateTypesList();
        updateCountersList();
    }

    public void updateTypesList() {
        try {
            types = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    Group temp = g.fromJson(ln, Group.class);
                    types.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateTypesList()", e);
        }
    }

    public void updateCountersList() {
        try {
            counters = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo2()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    Container temp = g.fromJson(ln, Container.class);
                    counters.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateCountersList()", e);
        }
    }

    //Update file with new Info    
    public void updateDataBase() {
        updateTypesDataBase();
        updateCountersDataBase();
    }

    public boolean updateTypesDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < types.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(types.get(i))));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateTypesDataBase()", e);
        }
        return resp;
    }

    public boolean updateCountersDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo2());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < counters.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(counters.get(i))));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateCountersDataBase()", e);
        }
        return resp;
    }

    //Random Number
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

//TYPE CONTROLLS
    //basic Controlls
    public String genTypeID() {
        int resp = getRandomNumber(100000, 999999);
        if (findTypebyID(resp) != null) {
            genTypeID();
        }
        return resp + "";
    }

    //If exist Product Desc
    public boolean existType(Group type) {
        boolean resp = false;
        if (!types.isEmpty()) {
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).getDescription().equals(type.getDescription())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    public boolean existType(String desc) {
        boolean resp = false;
        if (!types.isEmpty()) {
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).getDescription().equals(desc)) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    //CREATE
    public boolean createType(Group newType) {
        boolean resp = false;
        try {
            if (!existType(newType)) {
                types.add(newType);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createType()", e);
        }
        return resp;
    }

    //READ
    //Find a type by ID
    public Group findTypebyID(int idCounter) {
        Group resp = null;
        try {
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).getId().equals(idCounter)) {
                    resp = types.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findTypebyID()", e);
        }
        return resp;
    }

    public Group findTypebyDesc(String Desc) {
        Group resp = null;
        try {
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).getDescription().equals(Desc)) {
                    resp = types.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findTypebyID()", e);
        }
        return resp;
    }

    //Find a Product by Description
    public Group findypebyTDesc(String desc) {
        Group resp = null;
        try {
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).getDescription().equals(desc)) {
                    resp = types.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findypebyTDesc()", e);
        }
        return resp;
    }

    //UPDATE
    public boolean updateType(Group Type) {
        boolean resp = false;
        try {
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).getId().equals(Type.getId())) {
                    types.set(i, Type);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateType()", e);
        }
        return resp;
    }

    //DELETE
    public boolean deleleType(Group Type) {
        try {
            types.remove(Type);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "deleleType()", e);
        }
        return false;
    }

//COUNTERS CONTROLLS
    //basic Controlls
    public String genCounterID() {
        int resp = getRandomNumber(100000, 999999);
        if (findCounterbyID(resp) != null) {
            genCounterID();
        }
        return resp + "";
    }

    //If exist Product Desc
    public boolean existCounter(Container counter) {
        boolean resp = false;
        if (!counters.isEmpty()) {
            for (int i = 0; i < counters.size(); i++) {
                if (counters.get(i).getDescription().equals(counter.getDescription())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    public boolean existCounter(String Desc) {
        boolean resp = false;
        if (!counters.isEmpty()) {
            for (int i = 0; i < counters.size(); i++) {
                if (counters.get(i).getDescription().equals(Desc)) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    //CREATE
    public boolean createCounter(Container newCounter) {
        boolean resp = false;
        try {
            if (!existCounter(newCounter)) {
                counters.add(newCounter);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createCounter()", e);
        }
        return resp;
    }

    //READ
    //Find a Product by ID
    public Container findCounterbyID(int idCounter) {
        Container resp = null;
        try {
            for (int i = 0; i < counters.size(); i++) {
                if (counters.get(i).getId().equals(idCounter)) {
                    resp = counters.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findCounterbyID()", e);
        }
        return resp;
    }

    //Find a Product by Description
    public Container findCounterbyDesc(String desc) {
        Container resp = null;
        try {
            for (int i = 0; i < counters.size(); i++) {
                if (counters.get(i).getDescription().equals(desc)) {
                    resp = counters.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findCounterbyDesc()", e);
        }
        return resp;
    }

    //UPDATE
    public boolean updateCounter(Container Counter) {
        boolean resp = false;
        try {
            for (int i = 0; i < counters.size(); i++) {
                if (counters.get(i).getId().equals(Counter.getId())) {
                    counters.set(i, Counter);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateCounter()", e);
        }
        return resp;
    }

    //DELETE
    public boolean deleleCounter(Container Counter) {
        try {
            counters.remove(Counter);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "deleleCounter()", e);
        }
        return false;
    }
}
