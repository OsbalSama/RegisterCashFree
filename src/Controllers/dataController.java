/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BoxCut;
import Models.Messages;
import Models.Product;
import Models.Sale;
import Models.SoldProduct;
import Views.adminControlls.progressBarDialog;
import Views.adminControlls.setColumns;
import Views.commondialogs.paymentDialog;
import Views.commondialogs.printerService;
import Views.resetsteps.backFrame;
import Views.resetsteps.step_0;
import Views.resetsteps.step_1;
import Views.resetsteps.step_2;
import Views.resetsteps.step_3;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author OSBAL
 */
public class dataController {

    String Class = "dataController";
    salesController allSales = salesController.getInstance();

    File systemControllerFile = new File("source\\DBS_0001.dat");// systemController
    File userControllerFile = new File("source\\DBS_0002.dat");// userController
    File productControllerFile = new File("source\\DBS_0003.dat");// productController    
    File typeControllerFile = new File("source\\DBS_0004.dat");// typeContainerController 1
    File ContainerControllerFile = new File("source\\DBS_0005.dat");// typeContainerController 2    
    File sessionControllerFile = new File("source\\DBS_0006.dat");// sessionController    
    File salesControllerFile = new File("source\\DBS_0007.dat");// salesController 1
    File soldsControllerFile = new File("source\\DBS_0008.dat");// salesController 2
    File boxCutsControllerFile = new File("source\\DBS_0009.dat");// salesController 3

    File BoxCuts = new File("documents\\BoxCuts\\");
    File Reports = new File("documents\\Reports\\");
    File SaleTickets = new File("documents\\SaleTickets\\");
    //Instance configurations
    private static dataController instance;

    public static dataController getInstance() {
        if (instance == null) {
            instance = new dataController();
        }
        return instance;
    }

    public void resetDocumentsFolder() {
        try {
            File[] files;

            files = BoxCuts.listFiles();
            for (File file : files) {
                file.delete();
            }
            files = Reports.listFiles();
            for (File file : files) {
                file.delete();
            }
            files = SaleTickets.listFiles();
            for (File file : files) {
                file.delete();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetDocumentsFolder()", e);
        }
    }

    public boolean resetAllApp() {
        boolean resp = false;
        try {
            resetDocumentsFolder();
            systemController.getInstance().resetSystemData();
            userController.getInstance().resetDataBase();
            productController.getInstance().resetDataBase();
            typeContainerController.getInstance().resetTypes();
            typeContainerController.getInstance().resetContainers();
            sessionController.getInstance().resetDataBase();
            salesController.getInstance().resetSales();
            salesController.getInstance().resetSoldProducts();
            salesController.getInstance().resetBoxCuts();
            if (configSystem()) {
                resp = true;
            }

        } catch (Exception e) {
            new Messages().errorMessage(Class, "resetAllApp()", e);
        }
        return resp;
    }

    public boolean configSystem() {
        boolean resp = false;
        try {
            backFrame bc = new backFrame();
            bc.show();
            step_0 step_0 = new step_0(bc, true);
            step_0.setWellcomeData();
            step_0.show();
            if (step_0.isNext()) {
                step_1 step_1 = new step_1(bc, true);
                step_1.setData();
                step_1.show();
                if (step_1.isNext()) {
                    step_2 step_2 = new step_2(bc, true);
                    step_2.show();
                    if (step_2.isNext()) {
                        step_3 step_3 = new step_3(bc, true);
                        step_3.setData();
                        step_3.show();
                        if (step_3.isNext()) {
                            step_0 step_4 = new step_0(bc, true);
                            step_4.setFinishData();
                            step_4.show();
                            bc.dispose();
                            systemController.getInstance().disableResetMode();
                            resp = true;
                        } else {
                            System.exit(0);
                        }
                    } else {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "configSystem()", e);
        }

        return resp;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getNumericFormat(double Value) {
        String resp = "";
        try {
            DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
            DecimalFormat.getNumberInstance(Locale.US);
            resp = df.format(Value);
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getNumericFormat()", e);
        }
        return resp;
    }

    public String getKgFormat(double Value) {
        String resp = "";
        try {
            DecimalFormat df = new DecimalFormat("###,###,###,##0.00kg");
            DecimalFormat.getNumberInstance(Locale.US);
            resp = df.format(Value);
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getKgFormat()", e);
        }
        return resp;
    }

    public String getPzFormat(double Value) {
        String resp = "";
        try {
            DecimalFormat df = new DecimalFormat("###,###,###,##0.00pz");
            DecimalFormat.getNumberInstance(Locale.US);
            resp = df.format(Value);
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getPzFormat()", e);
        }
        return resp;
    }

    public String getMoneyFormat(double Value) {
        String resp = "";
        try {
            DecimalFormat df = new DecimalFormat("$###,###,###,##0.00");
            DecimalFormat.getNumberInstance(Locale.US);
            resp = df.format(Value);
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getMoneyFormat()", e);
        }
        return resp;
    }

    //Import & Export Classess
    public void importDatabase() {
        try {
            String text = "<html>"
                    + "<center>"
                    + "<b>"
                    + "¡ESTA ACCIÓN ES IRREVERSIBLE!"
                    + "</b>"
                    + "<br>"
                    + "<br>"
                    + "¿Desea continuar?"
                    + "</center>"
                    + "</html>";
            if (new Messages().continueCancelMessage("Importar Inventario", text)) {
                JFileChooser frame = new JFileChooser();
                frame.setDialogTitle("Importar Inventario");
                int c = 0;
                do {
                    frame.setFileFilter(new FileNameExtensionFilter("Archivo CSV", "csv", "CSV"));
                    frame.setAcceptAllFileFilterUsed(false);
                    int returnVal = frame.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String Path = frame.getSelectedFile().getAbsolutePath();
                        if (isOpened(Path)) {
                            if (!isEmpty(Path)) {
                                try {
                                    setColumns sc = new setColumns(null, true);
                                    sc.setData(getHeadersCSVFile(Path));
                                    sc.show();
                                    if (sc.Next()) {
                                        int[] headers = sc.getHeaders();
                                        progressBarDialog pbd = new progressBarDialog(null, true);
                                        pbd.SetImportData(Path, headers);
                                        pbd.stariImportData();
                                        pbd.show();

//                                        new Messages().alertMessage("Importar Inventario", "Importar Database");
                                    }
                                } catch (Exception e) {
                                    new Messages().errorMessage(Class, "importDatabase()", e);
                                } finally {
                                    c = 1;
                                }
                            } else {
                                new Messages().aceptMessage("Importar Inventario", "El archivo no contiene ningun dato...<br>Verifiquelo antes de continuar");
                            }

                        } else {
                            new Messages().aceptMessage("Importar Inventario", "El archivo esta abierto...<br>Cierrelo antes de continuar");
                        }
                    } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                        c = 1;
                    }
                } while (c == 0);

            }

        } catch (HeadlessException e) {
            new Messages().errorMessage(Class, "importDatabase()", e);
        }
    }

    public String[] getHeadersCSVFile(String ruta) {
        String[] resp = null;
        try {
            List<String> Data = getCSVFileContent(ruta);
            resp = Data.get(0).split(",");
        } catch (Exception e) {
            new Messages().errorMessage(Class, "getHeadersCSVFile()", e);
        }
        return resp;

    }

    public List<String> getCSVFileContent(String Path) {
        List<String> resp = new ArrayList<>();
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(Path));
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 0) {
                    resp.add(line);
                }
            }
            br.close();
        } catch (IOException e) {
            new Messages().errorMessage(Class, "getCSVFileContent()", e);
        }
        return resp;
    }

    public boolean isEmpty(String selecterFile) {
        boolean resp = true;
        try {
            int cont = 0;
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(selecterFile));
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 0) {
                    cont++;
                }
            }
            br.close();
            if (cont > 1) {
                resp = false;
            }
        } catch (IOException e) {
            new Messages().errorMessage(Class, "isEmpty()", e);
        }
        return resp;
    }

    public boolean isOpened(String selecterFile) {
        boolean resp = false;
        try {
            File file = new File(selecterFile);
            File sameFileName = new File(selecterFile);
            if (file.renameTo(sameFileName)) {
                resp = true;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "isOpened()", e);
        }
        return resp;
    }

    public void exportDatabase() {
        try {
            if (productController.getInstance().getInventory().size() > 0) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Exportar Inventario");
                int c = 0;
                do {
                    chooser.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo CSV (Delimitado por comas)", "csv", "CSV");
                    chooser.setFileFilter(filtro);
                    chooser.setSelectedFile(new File("RC-Free Inventario " + new SimpleDateFormat("dd-MM-YYY").format(new Date())));
                    int retrival = chooser.showSaveDialog(null);
                    if (retrival == JFileChooser.APPROVE_OPTION) {
                        String path = chooser.getSelectedFile().getAbsolutePath() + ".csv";
                        if (new File(path).exists()) {
                            if (new Messages().yesNoMessage("¡El Archivo ya Existe!", "¿Desea reemplazarlo?")) {
                                c = 1;
                                fillExportedFile(path);
                            }
                        } else {
                            c = 1;
                            fillExportedFile(path);
                        }
                    } else if (retrival == JFileChooser.CANCEL_OPTION) {
                        c = 1;
                    }

                } while (c == 0);

            } else {
                new Messages().closeMessage("Exportar Inventario", "No hay productos a Exportar");
            }
        } catch (HeadlessException e) {
            new Messages().errorMessage(Class, "exportDatabase()", e);
        }
    }

    public void fillExportedFile(String Path) {
        try {
            progressBarDialog pbd = new progressBarDialog(null, true);
            pbd.SetExportFile(Path);
            pbd.startExportData();
            pbd.show();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "fillExportedFile()", e);
        }
    }

    public void createExportCSVFile(String Path) {
        try {
            File target = new File(Path);
            if (target.createNewFile()) {
                FileWriter w = new FileWriter(target);
                BufferedWriter bw = new BufferedWriter(w);
                bw.write("Codigo,Descripcion,Marca,Categoria,Mostrador,Precio,Cantidad,Creado,Editado");
                bw.newLine();
                bw.close();
            }
        } catch (IOException e) {
            new Messages().errorMessage(Class, "createExportCSVFile()", e);
        }
    }

    public boolean downloadReport(String ReportName, String Report) {
        boolean resp = false;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Descargar Reporte");
        int c = 0;
        do {
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto (.txt)", "txt", "TXT");
            chooser.setFileFilter(filtro);
            chooser.setSelectedFile(new File(ReportName));
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = chooser.getSelectedFile().getAbsolutePath() + ".txt";
                    if (new File(path).exists()) {
                        if (new Messages().yesNoMessage("¡El Archivo ya Existe!", "¿Desea reemplazarlo?")) {
                            c = 1;
                            createTXTFile(path, Report);
                            resp = true;
                        }
                    } else {
                        c = 1;
                        createTXTFile(path, Report);
                        resp = true;
                    }

                } catch (Exception e) {
                    new Messages().errorMessage(Class, "downloadReport()", e);
                }
            } else if (retrival == JFileChooser.CANCEL_OPTION) {
                c = 1;
            }
        } while (c == 0);
        return resp;
    }

    public boolean downloadLayout() {
        boolean resp = false;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Descargar Plantilla");
        int c = 0;
        do {
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo CSV (Delimitado por comas)", "csv", "CSV");
            chooser.setFileFilter(filtro);
            chooser.setSelectedFile(new File("RC-Free Plantilla Inventario"));
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = chooser.getSelectedFile().getAbsolutePath() + ".csv";
                    if (new File(path).exists()) {
                        if (new Messages().yesNoMessage("¡El Archivo ya Existe!", "¿Desea reemplazarlo?")) {
                            c = 1;
                            new File(path).delete();
                            createLayoutCSVFile(path);
                            resp = true;
                        }
                    } else {
                        c = 1;
                        createLayoutCSVFile(path);
                        resp = true;
                    }
                } catch (Exception e) {
                    new Messages().errorMessage(Class, "downloadLayout()", e);
                }
            } else if (retrival == JFileChooser.CANCEL_OPTION) {
                c = 1;
            }
        } while (c == 0);

        return resp;
    }

    public void createTXTFile(String Path, String Content) {
        try {
            File Document = new File(Path);
            if (Document.createNewFile()) {
                FileWriter w = new FileWriter(Document);
                BufferedWriter bw = new BufferedWriter(w);
                bw.write(Content);
                bw.newLine();
                bw.close();
            }
        } catch (IOException e) {
            new Messages().errorMessage(Class, "createTXTFile()", e);
        }
    }

    public void createLayoutCSVFile(String Path) {
        try {
            File target = new File(Path);
            if (target.createNewFile()) {
                FileWriter w = new FileWriter(target);
                BufferedWriter bw = new BufferedWriter(w);
                bw.write("Codigo,Descripcion,Marca,Categoria,Mostrador,Precio,Cantidad");
                bw.newLine();
                bw.close();
            }
        } catch (IOException e) {
            new Messages().errorMessage(Class, "createLayoutCSVFile()", e);
        }
    }

    public void printBoxCut(BoxCut newBoxCut) {
        printerService selectPrinter = new printerService(sessionController.getInstance().getMainFrame(), true);
        selectPrinter.setData();
        selectPrinter.show();
        if (selectPrinter.isNext()) {
            String SelectedPrinter = selectPrinter.getSelectedPrinter();
            new ticketController().printBoxCut(newBoxCut, SelectedPrinter);
            new Messages().aceptMessage("Imprimir Documento", "Corte Z Impreso Correctamente");
        }
    }

    public void genBoxCut() {
        try {
            if (sessionController.getInstance().getLogedUser().isAdmin()) {
                if (salesController.getInstance().findSalesNoBoxBut().size() > 0) {
                    String text = "<html>"
                            + "<center>"
                            + "<b>"
                            + "¡VENTAS REGISTRADAS SIN PROCESAR!"
                            + "</b>"
                            + "<br>"
                            + "<br>"
                            + "Se guardarán todos los movimientos realizados en esta Sesión... ¿Desea continuar?"
                            + "</center>"
                            + "</html>";
                    if (new Messages().yesNoMessage("Generar Corte Z", text)) {
                        BoxCut newBoxCut = new BoxCut();
                        newBoxCut.genId();
                        newBoxCut.setUser(sessionController.getInstance().getLogedUser().getUsername());
                        newBoxCut.saveInCash();
                        updateSales(newBoxCut);
                        newBoxCut.setTotalOfSale(getTotalOfSales(newBoxCut));
                        if (salesController.getInstance().createBoxCut(newBoxCut)) {
                            systemController.getInstance().enableUpCash();
                            printBoxCut(newBoxCut);
                        }
                    }
                } else {
                    String text = "<html>"
                            + "<center>"
                            + "<b>"
                            + "¡SIN VENTAS PARA PROCESAR!"
                            + "</b>"
                            + "<br>"
                            + "<br>"
                            + "No hay movimientos realizados en esta Sesión... ¿Desea continuar?"
                            + "</center>"
                            + "</html>";
                    if (new Messages().yesNoMessage("Generar Corte Z", text)) {
                        BoxCut newBoxCut = new BoxCut();
                        newBoxCut.genId();
                        newBoxCut.setUser(sessionController.getInstance().getLogedUser().getUsername());
                        newBoxCut.saveInCash();
                        newBoxCut.setTotalOfSale(getTotalOfSales(newBoxCut));
                        if (salesController.getInstance().createBoxCut(newBoxCut)) {
                            systemController.getInstance().enableUpCash();
                            printBoxCut(newBoxCut);
                        }
                    }
                }
            } else {
                BoxCut newBoxCut = new BoxCut();
                newBoxCut.genId();
                newBoxCut.setUser(sessionController.getInstance().getLogedUser().getUsername());
                newBoxCut.saveInCash();
                updateSales(newBoxCut);
                newBoxCut.setTotalOfSale(getTotalOfSales(newBoxCut));
                if (salesController.getInstance().createBoxCut(newBoxCut)) {
                    systemController.getInstance().enableUpCash();
                    printBoxCut(newBoxCut);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "genBoxCut()", e);
        }
    }

    public void updateSales(BoxCut newBoxCut) {
        List<Sale> AllSales = this.allSales.getAllSales();
        for (int i = 0; i < AllSales.size(); i++) {
            Sale temp = AllSales.get(i);
            if (temp.getBoxCut() == null) {
                AllSales.get(i).setBoxCut(newBoxCut.getId());
            }
        }
        this.allSales.updateBoxCutDataBase();
    }

    public double getTotalOfSales(BoxCut newBoxCut) {
        double resp = 0;
        List<Sale> AllSales = this.allSales.getAllSales();
        for (int i = 0; i < AllSales.size(); i++) {
            Sale temp = AllSales.get(i);
            if (temp.getBoxCut().equals(newBoxCut.getId())) {
                resp += temp.getTotalOfSale();
            }
        }
        return resp;
    }

    public boolean saveSale(List<Product> cart) {

        boolean resp = false;
        paymentDialog pay = new paymentDialog(null, true);
        pay.setData(getCartTotal(cart));
        pay.show();
        if (pay.isNext()) {
            Sale newSale = new Sale();
            newSale.genId();
            newSale.setSeller(sessionController.getInstance().getLogedUser().getUsername());
            newSale.setTotalOfSale(getCartTotal(cart));
            allSales.createSale(newSale);
            for (int i = 0; i < cart.size(); i++) {
                Product inCart = cart.get(i);
                SoldProduct sold = new SoldProduct();
                sold.genId();
                sold.setIdSale(newSale.getId());
                sold.setDescription(inCart.getDescription());
                sold.setBrand(inCart.getBrand());
                sold.setBulk(inCart.isBulk());
                sold.setValue(inCart.getValue());
                sold.setCant(inCart.getCant());
                sold.setTotal(inCart.getCant() * inCart.getValue());
                allSales.createSoldProduct(sold);
            }
            productController.getInstance().restCartInInventory(cart);
            new ticketController().printSaleTicket(newSale.getId(), cart, pay.getSelectedPrinter());
            resp = true;
        }
        return resp;
    }

    public double getCartTotal(List<Product> cart) {
        double resp = 0;
        try {
            if (!cart.isEmpty()) {
                for (int i = 0; i < cart.size(); i++) {
                    Product temp = cart.get(i);
                    resp += (temp.getValue() * temp.getCant());
                }
            }

        } catch (Exception e) {
            new Messages().errorMessage(Class, "getCartTotal()", e);
        }
        return resp;
    }
}
