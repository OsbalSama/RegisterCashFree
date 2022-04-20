/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Product;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author OSBAL
 */
public class exportController extends SwingWorker<Integer, String> {

    /**
     * @return the lblDescripcion
     */
    public JLabel getLblDescripcion() {
        return lblDescripcion;
    }

    /**
     * @param lblDescripcion the lblDescripcion to set
     */
    public void setLblDescripcion(JLabel lblDescripcion) {
        this.lblDescripcion = lblDescripcion;
    }

    /**
     * @return the txtAreaProceso
     */
    public JTextArea getTxtAreaProceso() {
        return txtAreaProceso;
    }

    /**
     * @param txtAreaProceso the txtAreaProceso to set
     */
    public void setTxtAreaProceso(JTextArea txtAreaProceso) {
        this.txtAreaProceso = txtAreaProceso;
    }

    /**
     * @return the BarraDeCarga
     */
    public JProgressBar getBarraDeCarga() {
        return BarraDeCarga;
    }

    /**
     * @param BarraDeCarga the BarraDeCarga to set
     */
    public void setBarraDeCarga(JProgressBar BarraDeCarga) {
        this.BarraDeCarga = BarraDeCarga;
    }

    /**
     * @return the BotonCerrar
     */
    public JButton getBotonCerrar() {
        return BotonCerrar;
    }

    /**
     * @param BotonCerrar the BotonCerrar to set
     */
    public void setBotonCerrar(JButton BotonCerrar) {
        this.BotonCerrar = BotonCerrar;
    }

    public JButton getBotonDownload() {
        return BotonDownload;
    }

    public void setBotonDownload(JButton BotonDownload) {
        this.BotonDownload = BotonDownload;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    /**
     * @param destino the destino to set
     */
    private JLabel lblDescripcion;
    private JTextArea txtAreaProceso;
    private JProgressBar BarraDeCarga;
    private JButton BotonCerrar;
    private JButton BotonDownload;

    private String Path;

    public exportController(JLabel lblDescripcion, JTextArea txtAreaProceso, JProgressBar BarraDeCarga, JButton BotonCerrar, JButton BotonDownload, String Path) {
        this.lblDescripcion = lblDescripcion;
        this.txtAreaProceso = txtAreaProceso;
        this.BarraDeCarga = BarraDeCarga;
        this.BotonCerrar = BotonCerrar;
        this.BotonDownload = BotonDownload;
        this.Path = Path;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        List<Product> inventory = productController.getInstance().getInventory();
        int exportadas = 0;
        try {
            getLblDescripcion().setText("EXPORTANDO BASE DE DATOS");
            getTxtAreaProceso().append("---EXPORTANDO BASE DE DATOS---");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("**Favor de no apagar el equipo hasta terminar el proceso**");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getBarraDeCarga().setIndeterminate(true);

            getLblDescripcion().setText("CREANDO ARCHIVOS DE DESTINO");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("***CREANDO ARCHIVOS DE DESTINO***");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("Ruta Seleccionada:");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append(getPath());
            if (new File(getPath()).exists()) {
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("--> El Archivo  especificado ya existe...");
                new File(getPath()).delete();
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("--> Archivo Eliminado Correctamente...");

                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("--> Creando Nuevo Archivo...");
                new File(getPath()).createNewFile();
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("--> Archivo Creado Correctamente...");

            } else {
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("--> Creando Nuevo Archivo...");
                new File(getPath()).createNewFile();
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("--> Archivo Creado Correctamente...");
            }
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> Proceso Terminado...");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getBarraDeCarga().setIndeterminate(false);

            getLblDescripcion().setText("EXTRAYENDO PRODUCTOS DE INVENTARIO");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("***EXTRAYENDO PRODUCTOS DE INVENTARIO***");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("**Obteniendo los Productos en el Inventario y guardandolos en el nuevo Archivo**");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getBarraDeCarga().setMinimum(0);
            getBarraDeCarga().setMaximum(inventory.size());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("*Inyectando contenido al Archivo*");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            FileWriter w = new FileWriter(getPath());
            BufferedWriter bw = new BufferedWriter(w);
            bw.write("Codigo,Descripcion,Marca,Tipo,Mostrador,Precio,Cantidad,Creado,Editado");
            bw.newLine();
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> Encabezado agregado correctamente...");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            for (int i = 0; i < inventory.size(); i++) {
                Product temp = inventory.get(i);
                String toWrite = temp.getId() + ","
                        + temp.getDescription() + ","
                        + temp.getDescription() + ","
                        + temp.getBrand() + ","
                        + temp.getType() + ","
                        + temp.getContainer() + ","
                        + temp.getValue() + ","
                        + temp.getCreatedToString() + ","
                        + temp.getEditedToString() + ",";
                bw.write(toWrite);
                bw.newLine();
                exportadas++;
                getTxtAreaProceso().append("\n");
                getTxtAreaProceso().append("-> Producto [" + (i + 1) + "] exportado correctamente...");
                getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            }
            bw.close();

            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> Proceso Terminado...");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("-> FILAS EXPORTADAS [" + exportadas + "]");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

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
            getBarraDeCarga().setValue(inventory.size());
            getLblDescripcion().setText("PROCESO FINALIZADO");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("***PROCESO FINALIZADO***");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("**RESULTADOS DEL PROCESO**");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());
            getTxtAreaProceso().append("Productos exportados: " + exportadas);
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("[Relalizado Por el Usuario {" + sessionController.getInstance().getLogedUser().getUsername() + "}, el Dia {" + new Date() + "}]");
            getTxtAreaProceso().append("\n");
            getTxtAreaProceso().append("----------------------------------------------------------------------------------------------------");
            getTxtAreaProceso().setCaretPosition(getTxtAreaProceso().getDocument().getLength());

            getBotonDownload().setEnabled(true);
            getBotonCerrar().setEnabled(true);
            getBotonCerrar().requestFocus();
        }
        return 0;
    }

}
