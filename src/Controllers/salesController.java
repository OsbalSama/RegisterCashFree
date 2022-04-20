/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BoxCut;
import Models.Messages;
import Models.Sale;
import Models.SoldProduct;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ARTEMISA
 */
public class salesController {

    String Class = "salesController";

    String archivo = "source\\DBS_0007.dat";
    String archivo2 = "source\\DBS_0008.dat";
    String archivo3 = "source\\DBS_0009.dat";

    public boolean existFile() {
        return new File(archivo).exists();
    }

    public boolean existFile2() {
        return new File(archivo2).exists();
    }

    public boolean existFile3() {
        return new File(archivo3).exists();
    }

    List<Sale> allSales;
    List<SoldProduct> allSoldProducts;
    List<BoxCut> allBoxCut;

    Gson g = new Gson();

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(String archivo2) {
        this.archivo2 = archivo2;
    }

    public String getArchivo3() {
        return archivo3;
    }

    public void setArchivo3(String archivo3) {
        this.archivo3 = archivo3;
    }

    public List<BoxCut> getAllBoxCut() {
        return allBoxCut;
    }

    public void setAllBoxCut(List<BoxCut> allBoxCut) {
        this.allBoxCut = allBoxCut;
    }

    public List<Sale> getAllSales() {
        return allSales;
    }

    public void setAllSales(List<Sale> allSales) {
        this.allSales = allSales;
    }

    public List<SoldProduct> getAllSoldProducts() {
        return allSoldProducts;
    }

    public void setAllSoldProducts(List<SoldProduct> allSoldProducts) {
        this.allSoldProducts = allSoldProducts;
    }

    //Singleton Instance;
    private static salesController instance;

    protected salesController() {
        updateMainList();
    }

    public static salesController getInstance() {
        if (instance == null) {
            instance = new salesController();
        }
        return instance;
    }

    //updateAll
    public void updateMainList() {
        updateSalesList();
        updateSoldProductsList();
        updateBoxCutList();
    }

    public void updateBoxCutList() {
        try {
            allBoxCut = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo3()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    BoxCut temp = g.fromJson(ln, BoxCut.class);
                    allBoxCut.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateBoxCutList()", e);
        }
    }

    public void updateSalesList() {
        try {
            allSales = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    Sale temp = g.fromJson(ln, Sale.class);
                    allSales.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSalesList()", e);
        }
    }

    public void updateSoldProductsList() {
        try {
            allSoldProducts = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo2()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String ln = new cypherController().DecryptData(line);
                    SoldProduct temp = g.fromJson(ln, SoldProduct.class);
                    allSoldProducts.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSoldProductsList()", e);
        }
    }

    //Database Controllers
    public void resetSales() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            updateMainList();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetSales()", e);
        }
    }

    public void resetSoldProducts() {
        try {
            new File(getArchivo2()).delete();
            new File(getArchivo2()).createNewFile();
            updateMainList();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetSoldProducts()", e);
        }
    }

    public void resetBoxCuts() {
        try {
            new File(getArchivo3()).delete();
            new File(getArchivo3()).createNewFile();
            updateMainList();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetBoxCuts()", e);
        }
    }

    //Update SoldProducts && Sales Database
    public void updateDataBase() {
        updateSalesDataBase();
        updateSoldProductsDataBase();
        updateBoxCutDataBase();
    }

    public boolean updateSalesDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < allSales.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(allSales.get(i))));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSalesDataBase()", e);
        }
        return resp;
    }

    public boolean updateSoldProductsDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo2());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < allSoldProducts.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(allSoldProducts.get(i))));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSoldProductsDataBase()", e);
        }
        return resp;
    }

    public boolean updateBoxCutDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo3());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < allBoxCut.size(); i++) {
                bw.write(new cypherController().EncryptData(g.toJson(allBoxCut.get(i))));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateBoxCutDataBase()", e);
        }
        return resp;
    }

    //Random Number
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //Sales Controlls
    public String genSaleID() {
        String resp = String.valueOf(getRandomNumber(10000000, 99999999));
        if (findSalebyID(resp) != null) {
            genSaleID();
        }
        return resp;
    }

    public boolean existSale(Sale sale) {
        boolean resp = false;
        if (!allSales.isEmpty()) {
            for (int i = 0; i < allSales.size(); i++) {
                if (allSales.get(i).getId().equals(sale.getId())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    public String genSoldProductID() {
        String resp = String.valueOf(getRandomNumber(100000, 999999));
        if (findSalebyID(resp) != null) {
            genSaleID();
        }
        return resp;
    }

    public boolean existSoldProducts(SoldProduct sold) {
        boolean resp = false;
        if (!allSoldProducts.isEmpty()) {
            for (int i = 0; i < allSoldProducts.size(); i++) {
                if (allSoldProducts.get(i).getId().equals(sold.getId())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    public String genBoxCutID() {
        String resp = "BoxCut-" + String.valueOf(getRandomNumber(100000, 999999));
        if (findBoxCutbyID(resp) != null) {
            genSaleID();
        }
        return resp;
    }

    public boolean existBoxCut(BoxCut BoxCut) {
        boolean resp = false;
        if (!allSales.isEmpty()) {
            for (int i = 0; i < allSales.size(); i++) {
                if (allSales.get(i).getId().equals(BoxCut.getId())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    //CREATE
    public boolean createSale(Sale newSale) {
        boolean resp = false;
        try {
            if (!existSale(newSale)) {
                allSales.add(newSale);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createSale()", e);
        }
        return resp;
    }

    public boolean createSoldProduct(SoldProduct newSoldProduct) {
        boolean resp = false;
        try {
            if (!existSoldProducts(newSoldProduct)) {
                allSoldProducts.add(newSoldProduct);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createSoldProduct()", e);
        }
        return resp;
    }

    public boolean createBoxCut(BoxCut newBoxCut) {
        boolean resp = false;
        try {
            if (!existBoxCut(newBoxCut)) {
                allBoxCut.add(newBoxCut);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "createBoxCut()", e);
        }
        return resp;
    }

    //READ
    //Find a Sale by ID
    public Sale findSalebyID(String idSale) {
        Sale resp = null;
        try {
            for (int i = 0; i < allSales.size(); i++) {
                if (allSales.get(i).getId().equals(idSale)) {
                    resp = allSales.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSalebyID()", e);
        }
        return resp;
    }

    public List<Sale> findSalesbyDateRange(Date From, Date To) {
        List<Sale> resp = new ArrayList<>();
        try {
            From.setHours(0);
            From.setMinutes(0);
            From.setSeconds(0);
            To.setHours(23);
            To.setMinutes(59);
            To.setSeconds(59);

            for (int i = 0; i < allSales.size(); i++) {
                Sale temp = allSales.get(i);
                if (((temp.getCreated().after(From)) && (temp.getCreated().before(To))) || ((temp.getCreated().equals(From)) && (temp.getCreated().equals(To)))) {
                    resp.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSalebyID()", e);
        }
        return resp;
    }
    
    public List<Sale> findSalesNoBoxBut() {
        List<Sale> resp = new ArrayList<>();
        try {
            for (int i = 0; i < allSales.size(); i++) {
                Sale temp = allSales.get(i);
                if ((temp.getBoxCut() == null)) {
                    resp.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSalesNoBoxBut()", e);
        }
        return resp;
    }

    public List<Sale> findSalesbyBoxCutId(String BoxCutId) {
        List<Sale> resp = new ArrayList<>();
        try {
            for (int i = 0; i < allSales.size(); i++) {
                Sale temp = allSales.get(i);
                if ((temp.getBoxCut() != null) && (temp.getBoxCut().equals(BoxCutId))) {
                    resp.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSalebyID()", e);
        }
        return resp;
    }

    public List<Sale> findSalesbyDate(Date Now) {
        List<Sale> resp = new ArrayList<>();
        try {
            Date From = new Date(Now.toString());
            From.setHours(0);
            From.setMinutes(0);
            From.setSeconds(0);

            Date To = new Date(Now.toString());
            To.setHours(23);
            To.setMinutes(59);
            To.setSeconds(59);

            for (int i = 0; i < allSales.size(); i++) {
                Sale temp = allSales.get(i);
                if ((temp.getCreated().after(From) && (temp.getCreated().before(To)))) {
                    resp.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSalebyID()", e);
        }
        return resp;
    }

    public List<SoldProduct> findSoldProductsbySaleId(String idSale) {
        List<SoldProduct> resp = new ArrayList<>();
        try {
            for (int i = 0; i < allSoldProducts.size(); i++) {
                if (allSoldProducts.get(i).getIdSale().equals(idSale)) {
                    resp.add(allSoldProducts.get(i));
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSoldProductbyID()", e);
        }
        return resp;
    }

    public SoldProduct findSoldProductbyID(int idSoldProduct) {
        SoldProduct resp = null;
        try {
            for (int i = 0; i < allSoldProducts.size(); i++) {
                if (allSoldProducts.get(i).getId().equals(idSoldProduct)) {
                    resp = allSoldProducts.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findSoldProductbyID()", e);
        }
        return resp;
    }

    public BoxCut findBoxCutbyID(String idBozCut) {
        BoxCut resp = null;
        try {
            for (int i = 0; i < allBoxCut.size(); i++) {
                if (allBoxCut.get(i).getId().equals(idBozCut)) {
                    resp = allBoxCut.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findBoxCutbyID()", e);
        }
        return resp;
    }

    public List<BoxCut> findBoxCutbyDateRange(Date From, Date To) {
        List<BoxCut> resp = new ArrayList<>();
        try {
            From.setHours(0);
            From.setMinutes(0);
            From.setSeconds(0);
            To.setHours(23);
            To.setMinutes(59);
            To.setSeconds(59);
            for (int i = 0; i < allBoxCut.size(); i++) {
                BoxCut temp = allBoxCut.get(i);
                if (((temp.getCreated().after(From)) && (temp.getCreated().before(To))) || ((temp.getCreated().equals(From)) && (temp.getCreated().equals(To)))) {
                    resp.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findBoxCutbyDateRange()", e);
        }
        return resp;
    }

    //UPDATE
    public boolean updateSale(Sale sale) {
        boolean resp = false;
        try {
            for (int i = 0; i < allSales.size(); i++) {
                if (allSales.get(i).getId().equals(sale.getId())) {
                    allSales.set(i, sale);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSale()", e);
        }
        return resp;
    }

    public boolean updateSoldProduct(SoldProduct sold) {
        boolean resp = false;
        try {
            for (int i = 0; i < allSoldProducts.size(); i++) {
                if (allSoldProducts.get(i).getId().equals(sold.getId())) {
                    allSoldProducts.set(i, sold);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSoldProduct()", e);
        }
        return resp;
    }

    public boolean updateBoxCut(BoxCut BoxCut) {
        boolean resp = false;
        try {
            for (int i = 0; i < allBoxCut.size(); i++) {
                if (allBoxCut.get(i).getId().equals(BoxCut.getId())) {
                    allBoxCut.set(i, BoxCut);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateBoxCut()", e);
        }
        return resp;
    }

    //DELETE
    public boolean dropSale(Sale sale) {
        try {
            allSales.remove(sale);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "dropSale()", e);
        }
        return false;
    }

    public boolean dropSoldProduct(SoldProduct sold) {
        try {
            allSoldProducts.remove(sold);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "dropSale()", e);
        }
        return false;
    }

    public boolean dropBoxCut(BoxCut BoxCut) {
        try {
            allBoxCut.remove(BoxCut);
            updateDataBase();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "dropBoxCut()", e);
        }
        return false;
    }
}
