/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Messages;
import Models.Product;
import Views.commondialogs.addInventory;
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
public class productController {

    //Global Variables
    String Class = "productController";
    String archivo = "source\\DBS_0003.dat";
    List<Product> inventory = new ArrayList<>();
    Gson g = new Gson();

    typeContainerController dbTypeContainer = typeContainerController.getInstance();

    public boolean existFile(){
        return new File(archivo).exists();
    }
    
    //Get & Set
    public List<Product> getInventory() {
        return this.inventory;
    }

    public String getArchivo() {
        return archivo;
    }

    //Singleton Instance;
    private static productController instance;

    protected productController() {
        updateMainList();
    }

    public static productController getInstance() {
        if (instance == null) {
            instance = new productController();
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
            inventory = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    Product temp = g.fromJson(ln, Product.class);
                    inventory.add(temp);
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
            for (int i = 0; i < inventory.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(inventory.get(i))));
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
    //gen new Id genID
    public String genId() {
        String resp = String.valueOf(getRandomNumber(100000, 999999));
        if (findbyID(resp) != null) {
            genId();
        }
        return resp;
    }

    //If exist Product Desc
    public boolean existId(Product product) {
        boolean resp = false;
        if (!inventory.isEmpty()) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getId().equals(product.getId())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    //If exist Product Desc
    public boolean existProduct(Product product) {
        boolean resp = false;
        if (!inventory.isEmpty()) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getDescription().equals(product.getDescription())
                        && (inventory.get(i).getBrand().equals(product.getBrand()))
                        && (inventory.get(i).getType().equals(product.getType()))
                        && (inventory.get(i).getContainer().equals(product.getContainer()))) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    //CRUD
    //Create
    public boolean create(Product newProd) {
        boolean resp = false;
        try {
            if (!existId(newProd)) {
                inventory.add(newProd);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "create()", e);
        }
        return resp;
    }

    //Find a Product by ID
    public Product findbyID(String id) {
        Product resp = null;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getId().equals(id)) {
                    resp = inventory.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findbyID()", e);
        }
        return resp;
    }

    //Find a Product by Description
    public Product findbyDesc(String desc) {
        Product resp = null;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getDescription().equals(desc)) {
                    resp = inventory.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findbyDesc()", e);
        }
        return resp;
    }

    public boolean noProductInventory(Product product) {
        boolean resp = false;
        if (sessionController.getInstance().getLogedUser().isAdmin()) {
            if (new Messages().yesNoMessage("Inventario Agotado", "Inventario Agregado...<br>Desea agregar mas?")) {
                addInventory ai = new addInventory(null, true);
                ai.setData(product);
                ai.show();
                if (ai.isNext()) {
                    for (int i = 0; i < inventory.size(); i++) {
                        if (inventory.get(i).getId().equals(product.getId())) {
                            inventory.get(i).addInventory(ai.getResp());
                            updateDataBase();
                            new Messages().closeMessage("Inventario", "Inventario Agregado... Vuelva a intentarlo...");
                            resp = true;
                            break;
                        }
                    }
                }
            }
        } else {
            new Messages().closeMessage("Inventario Agotado", "Existencias Agotadas...<br>Informe a su administrador...");
        }
        return resp;
    }

    public boolean maxProductInventory(Product product) {
        boolean resp = false;
        if (sessionController.getInstance().getLogedUser().isAdmin()) {
            if (new Messages().yesNoMessage("Inventario al Maximo", "Ya tienes en tu carrito todas las existencias de tu producto...<br>Desea agregar mas?")) {
                addInventory ai = new addInventory(null, true);
                ai.setData(product);
                ai.show();
                if (ai.isNext()) {
                    for (int i = 0; i < inventory.size(); i++) {
                        if (inventory.get(i).getId().equals(product.getId())) {
                            inventory.get(i).addInventory(ai.getResp());
                            updateDataBase();
                            new Messages().closeMessage("Inventario", "Inventario Agregado... Vuelva a intentarlo...");
                            resp = true;
                            break;
                        }
                    }
                }
            }
        } else {
            new Messages().closeMessage("Inventario al Maximo", "Existencias al maximo, no se pueden agregar mas...<br>Informe a su administrador...");
        }
        return resp;
    }

    //Update
    public boolean update(Product product) {
        boolean resp = false;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getId().equals(product.getId())) {
                    inventory.set(i, product);
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

    public void restCartInInventory(List<Product> cart) {
        try {
            for (int i = 0; i < cart.size(); i++) {
                for (int j = 0; j < inventory.size(); j++) {
                    if (inventory.get(j).getId().equals(cart.get(i).getId())) {
                        inventory.get(j).restCant(cart.get(i).getCant());
                    }
                }
            }
            updateDataBase();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "restCartInInventory()", e);
        }
    }
    //Delete

    public boolean delele(Product product) {
        try {
            inventory.remove(product);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "delele()", e);
        }
        return false;
    }

    //Special Controlls
    //Browser
    public List<Product> getDBProdbyIndex(String Index) {
        Index = Index.toLowerCase();
        List<Product> busqueda = new ArrayList();
        try {
            for (int i = 0; i < inventory.size(); i++) {
                Product temp = inventory.get(i);
                int intIndex0 = temp.getId().toLowerCase().indexOf(Index);
                int intIndex1 = temp.getDescription().toLowerCase().indexOf(Index);
                int intIndex2 = temp.getBrand().toLowerCase().indexOf(Index);
                int intIndex3 = temp.getType().toLowerCase().indexOf(Index);
                int intIndex4 = temp.getContainer().toLowerCase().indexOf(Index);
                if (intIndex0 != - 1 || intIndex1 != - 1 || intIndex2 != - 1 || intIndex3 != - 1 || intIndex4 != - 1) {
                    busqueda.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getDBProdbyIndex()", e);
        }
        return busqueda;
    }
}
