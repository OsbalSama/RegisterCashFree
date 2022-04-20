package Controllers;

import Models.Container;
import Models.Messages;
import Models.Product;
import Models.Group;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author OSBAL
 */
public class importController extends SwingWorker<Integer, String> {

    /**
     * @return the boton
     */
    String Class = "importController";
    productController inventory = productController.getInstance();
    typeContainerController dbTypeContainer = typeContainerController.getInstance();

    public JLabel getLblDescripcion() {
        return lblDescripcion;
    }

    public void setLblDescripcion(JLabel lblDescripcion) {
        this.lblDescripcion = lblDescripcion;
    }

    public JTextArea getTxtAreaProceso() {
        return txtAreaProceso;
    }

    public void setTxtAreaProceso(JTextArea txtAreaProceso) {
        this.txtAreaProceso = txtAreaProceso;
    }

    public JProgressBar getBarraDeCarga() {
        return BarraDeCarga;
    }

    public void setBarraDeCarga(JProgressBar BarraDeCarga) {
        this.BarraDeCarga = BarraDeCarga;
    }

    public JButton getBotonCerrar() {
        return BotonCerrar;
    }

    public void setBotonCerrar(JButton BotonCerrar) {
        this.BotonCerrar = BotonCerrar;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public int[] getHeaders() {
        return Headers;
    }

    public void setHeaders(int[] Headers) {
        this.Headers = Headers;
    }

    public JButton getBotonDownload() {
        return BotonDownload;
    }

    public void setBotonDownload(JButton BotonDownload) {
        this.BotonDownload = BotonDownload;
    }

    private JLabel lblDescripcion;
    private JTextArea txtAreaProceso;
    private JProgressBar BarraDeCarga;
    private JButton BotonCerrar;
    private JButton BotonDownload;

    String Path;
    int[] Headers;
    dataController dataCont = dataController.getInstance();

    public importController(JLabel lblDescripcion, JTextArea txtAreaProceso, JProgressBar BarraDeCarga, JButton BotonCerrar, JButton BotonDownload, String Path, int[] Headers) {
        this.lblDescripcion = lblDescripcion;
        this.txtAreaProceso = txtAreaProceso;
        this.BarraDeCarga = BarraDeCarga;
        this.BotonCerrar = BotonCerrar;
        this.BotonDownload = BotonDownload;
        this.Path = Path;
        this.Headers = Headers;
    }

    public Product craftObject(String[] Data) {
        Product resp = null;
        try {
            resp = new Product();
            resp.setId(Data[0]);
            resp.setDescription(Data[1]);
            resp.setBrand(Data[2]);
            resp.setType(Data[3]);
            resp.setContainer(Data[4]);
            resp.setInventory(Float.parseFloat(Data[5]));
            resp.setValue(Double.parseDouble(Data[6]));
        } catch (Exception e) {
            new Messages().errorMessage(Class, "craftObject()", e);
        }
        return resp;
    }

    public String[] getRowContent(String Row) {
        String[] resp = Row.split(",");
        return resp;
    }

    public boolean evaluateObject(String[] pseudoObject) {
        boolean resp = false;
        try {
            double inventory = Double.parseDouble(pseudoObject[5]);
            double value = Double.parseDouble(pseudoObject[6]);
            resp = true;
        } catch (Exception e) {

        }
        return resp;
    }

    public String[] getObjectData(String[] Data) {
        String[] resp = new String[7];
        for (int i = 0; i < Headers.length; i++) {
            int header = Headers[i];
            if ((i == 0) || (i == 2) || (i == 3) || (i == 4)) {
                if (i == 0) {
                    if (header > 1) {
                        for (int j = 0; j < Data.length; j++) {
                            if (i == j) {
                                if (Data[header - 2] == null) {
                                    resp[i] = "NO DATA FOUNDED";
                                } else {
                                    resp[i] = Data[header - 2];
                                }
                                break;
                            }
                        }
                    } else {
                        resp[i] = inventory.genId();
                    }
                }
                if (i == 2) {
                    if (header > 1) {
                        for (int j = 0; j < Data.length; j++) {
                            if (i == j) {
                                if (Data[header - 2] == null) {
                                    resp[i] = "NO DATA FOUNDED";
                                } else {
                                    resp[i] = Data[header - 2];
                                }
                                break;
                            }
                        }
                    } else {
                        resp[i] = "SIN MARCA";
                    }
                }
                if (i == 3) {
                    if (header > 1) {
                        for (int j = 0; j < Data.length; j++) {
                            if (i == j) {
                                if (Data[header - 2] == null) {
                                    resp[i] = "NO DATA FOUNDED";
                                } else {
                                    resp[i] = Data[header - 2];
                                }
                                break;
                            }
                        }
                    } else {
                        resp[i] = "SIN CATEGORIA";
                    }
                }
                if (i == 4) {
                    if (header > 1) {
                        for (int j = 0; j < Data.length; j++) {
                            if (i == j) {
                                if (Data[header - 2] == null) {
                                    resp[i] = "NO DATA FOUNDED";
                                } else {
                                    resp[i] = Data[header - 2];
                                }
                                break;
                            }
                        }
                    } else {
                        resp[i] = "SIN MOSTRADOR";
                    }
                }

            } else {
                for (int j = 0; j < Data.length; j++) {
                    if (i == j) {
                        if (Data[header - 1] == null) {
                            resp[i] = "NO DATA FOUNDED";
                        } else {
                            resp[i] = Data[header - 1];
                        }
                        break;
                    }
                }
            }

        }
        return resp;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        getLblDescripcion().setText("IMPORTANDO BASE DE DATOS");
        getTxtAreaProceso().append("---IMPORTANDO BASE DE DATOS---");
        getTxtAreaProceso().append("\n");
        getTxtAreaProceso().append("**Favor de no apagar el equipo hasta terminar el proceso**");
        getTxtAreaProceso().append("\n");
        getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
        getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
        getBarraDeCarga().setIndeterminate(true);

        getLblDescripcion().setText("EXTRAYENDO DATOS DEL ARCHIVO");
        getTxtAreaProceso().append("\n");
        getTxtAreaProceso().append("***EXTRAYENDO DATOS DEL ARCHIVO***");
        getTxtAreaProceso().append("\n");
        getTxtAreaProceso().append("**Obteniendo las Filas guardadas en el archivo Seleccionado**");
        getTxtAreaProceso().append("\n");
        getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
        getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
        int extraidas = 0;
        int aprobadas = 0;
        int rechazadas = 0;
        int insertadas = 0;
        int noinsertadas = 0;
        try {

            List<String> Document = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(Path));
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("*Archivo seleccionado*");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> " + Path);
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("*Extrayendo contenido del archivo*");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            int cont = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().replace(",", "").length() > 0) {
                    Document.add(line);
                    getTxtAreaProceso().append("\n");
                    getTxtAreaProceso().append("--> Row {" + cont + "} Extraction: SUCCESSFULLY EXTRACTED");
                    getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                    extraidas++;
                    cont++;
                }
            }
            br.close();

            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> Proceso Terminado...");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> FILAS EXTRAIDAS [" + extraidas + "]");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getLblDescripcion().setText("EVALUANDO REGISTROS");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("***EVALUANDO REGISTROS***");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("**Con los datos recolectados, se crearán los Objetos individuales y evaluara si cumplen con las respectivas condiciones**");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getBarraDeCarga().setIndeterminate(false);

            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("*Evaluando Objetos*");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            cont = 0;
            List<Product> newProducts = new ArrayList<>();
            getBarraDeCarga().setMinimum(0);
            getBarraDeCarga().setMaximum(Document.size());

            for (int i = 0; i < Document.size(); i++) {
                String[] Row = getRowContent(Document.get(i));
                String[] pseudo = getObjectData(Row);
                if (evaluateObject(pseudo)) {
                    Product temp = craftObject(pseudo);
                    newProducts.add(temp);
                    aprobadas++;
                    cont++;
                    getTxtAreaProceso().append("\n");
                    getTxtAreaProceso().append("-> Object {" + cont + "} Evaluation: SUCCESSFULLY PASSED");
                    getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                    getBarraDeCarga().setValue(cont);

                } else {
                    rechazadas++;
                    cont++;
                    getTxtAreaProceso().append("\n");
                    getTxtAreaProceso().append("-> Object {" + cont + "} Evaluation: NOT PASSED");
                    getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                    getBarraDeCarga().setValue(cont);

                }
            }
            Document = new ArrayList<>();
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> Proceso Terminado...");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> FILAS EVALUADAS [" + cont + "]");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> FILAS APROBADAS [" + aprobadas + "]");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> FILAS RECHAZADAS [" + rechazadas + "]");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getTxtAreaProceso().append("\n");
            getLblDescripcion().setText("INYECTANDO PRODUCTOS AL INVENTARIO");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("***INYECTANDO PRODUCTOS AL INVENTARIO***");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("**Se inyectarán a la Base de Datos los objetos que hayan pasado la evaluación**");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("*Inyectando Productos*");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getBarraDeCarga().setMinimum(0);
            getBarraDeCarga().setMaximum(newProducts.size());

            cont = 0;
            if (newProducts.size() > 0) {
                inventory.resetDataBase();
                dbTypeContainer.resetTypes();
                dbTypeContainer.resetContainers();

                Group newType = new Group();
                newType.genId();
                newType.setDescription("SIN CATEGORIA");
                dbTypeContainer.createType(newType);

                Container newCont = new Container();
                newCont.genId();
                newCont.setDescription("MOSTRADOR GENERAL");
                dbTypeContainer.createCounter(newCont);

                for (int i = 0; i < newProducts.size(); i++) {
                    Product temp = newProducts.get(i);
                    if (inventory.create(temp)) {
                        if (!dbTypeContainer.existType(temp.getType())) {
                            newType = new Group();
                            newType.genId();
                            newType.setDescription(temp.getType());
                            dbTypeContainer.createType(newType);
                        }
                        if (!dbTypeContainer.existCounter(temp.getContainer())) {
                            newCont = new Container();
                            newCont.genId();
                            newCont.setDescription(temp.getContainer());
                            dbTypeContainer.createCounter(newCont);
                        }
                        insertadas++;
                        cont++;
                        getTxtAreaProceso().append("\n");
                        getTxtAreaProceso().append("-> Product {" + cont + "} Inyection: INYECTION SUCCESSFULLY");
                        getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                        getBarraDeCarga().setValue(cont);
                    } else {
                        noinsertadas++;
                        cont++;
                        if (inventory.existId(newProducts.get(i))) {
                            getTxtAreaProceso().append("\n");
                            getTxtAreaProceso().append("-> Product {" + cont + "} Inyection: FAIL ON INYECTION\n===>Details{Barcode [" + newProducts.get(i).getId() + "] Already Defined...}");
                            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

                        } else if (inventory.existProduct(newProducts.get(i))) {
                            getTxtAreaProceso().append("\n");
                            getTxtAreaProceso().append("-> Product {" + cont + "} Inyection: FAIL ON INYECTION \n===>Details{Product [" + newProducts.get(i).getDescription() + "] Already Defined...}");
                            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

                        } else {
                            getTxtAreaProceso().append("\n");
                            getTxtAreaProceso().append("-> Producto {" + cont + "} Inyection: FAIL ON INYECTION");
                            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                        }
                        getBarraDeCarga().setValue(cont);

                    }
                }

                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("-> Proceso Terminado...");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("-> PRODUCTOS PROCESADOS [" + cont + "]");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("-> PRODUCTOS INSERTADOS [" + insertadas + "]");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("-> PRODUCTOS RECHAZADOS [" + noinsertadas + "]");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            } else {
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("-> Sin Productos para Inyectar en Base de Datos, proceso anulado....");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            }
        } catch (Exception e) {
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("ERROR EN PROCESO!!!!");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append(e.toString());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("\n");
            getBarraDeCarga().setBackground(Color.RED);
        } finally {
//            getBarraDeCarga().setValue(inventory.size());
            getLblDescripcion().setText("PROCESO FINALIZADO");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("***PROCESO FINALIZADO***");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("**RESULTADOS DEL PROCESO**");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("Filas extraídas: " + extraidas);
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("Filas aprobadas: " + aprobadas);
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("Filas rechazadas: " + rechazadas);
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("Objetos Inyectados: " + insertadas);
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("Objetos Ignorados: " + noinsertadas);
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("[Relalizado Por el Usuario {" + sessionController.getInstance().getLogedUser().getUsername() + "}, el Dia {" + new Date() + "}]");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getBarraDeCarga().setIndeterminate(false);
            getBotonDownload().setEnabled(true);
            getBotonCerrar().setEnabled(true);
            getBotonCerrar().requestFocus();
        }
        return 0;
    }

}
