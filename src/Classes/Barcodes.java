/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Models.Messages;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author terra
 */
public class Barcodes {

    String Class = "Barcodes";

    public boolean genBC128PDF(String cod, int cant) {
        try {
            JFileChooser JFCO = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de PDF", "PDF", "pdf");
            JFCO.setFileFilter(filtro);
            JFCO.setAcceptAllFileFilterUsed(false);
            JFCO.setSelectedFile(new File(cod + "-128"));
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Codigos de barras: " + cod + " , barcode-128"));
            celdaFinal.setColspan(8);
            table.addCell(celdaFinal);
            int returnVal = JFCO.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (new File(JFCO.getSelectedFile() + ".pdf").exists()) {
                    String text = "<html>"
                            + "<center>"
                            + "<b>"
                            + "¡ESTE ARCHIVO YA EXISTE!"
                            + "</b>"
                            + "<br>"
                            + "<br>"
                            + "¿Desea reemplazarlo?"
                            + "</center>"
                            + "</html>";
                    if (new Messages().confirmMessage("¡Archivo Existe!", text)) {
                        Document doc = new Document(PageSize.LETTER, 18, 18, 18, 18);
                        PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(JFCO.getSelectedFile() + ".pdf"));
                        doc.open();
                        int cont = 0;
                        Barcode128 code = new Barcode128();
                        code.setCode(cod);
                        Image img = code.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                        for (int i = 0; i < cant; i++) {
                            table.addCell(img);
                            cont++;
                            if (cont == 8) {
                                cont = 0;
                            }
                        }
                        if (cont < 8) {
                            for (int i = cont; i < 8; i++) {
                                table.addCell("empty");
                            }
                        }
                        doc.add(table);
                        doc.close();
                    }
                } else {
                    Document doc = new Document(PageSize.LETTER, 18, 18, 18, 18);
                    PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(JFCO.getSelectedFile() + ".pdf"));
                    doc.open();
                    int cont = 0;
                    Barcode128 code = new Barcode128();
                    code.setCode(cod);
                    Image img = code.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                    for (int i = 0; i < cant; i++) {
                        PdfPCell celda = new PdfPCell();
                        img.setAlignment(Element.ALIGN_CENTER);
                        celda.addElement(new Paragraph("    "));
                        celda.addElement(img);
                        celda.addElement(new Paragraph("    "));
                        celda.setVerticalAlignment(Element.ALIGN_CENTER);
                        table.addCell(img);
                        cont++;
                        if (cont == 8) {
                            cont = 0;
                        }
                    }
                    if (cont < 8) {
                        for (int i = cont; i < 8; i++) {
                            table.addCell("empty");
                        }
                    }
                    doc.add(table);
                    doc.close();
                    return true;
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "genBC128PDF()", e);
        }
        return false;
    }

    public boolean ImpDocument(String cod, int cant, String printer) {
        try {
            String dir = "docs\\temp";
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Caatalogo codigos de barras: " + cod + " , barcode-128"));
            celdaFinal.setColspan(8);
            table.addCell(celdaFinal);
            File arch = new File(dir + "\\impress.pdf");
            Document doc = new Document(PageSize.LETTER, 18, 18, 18, 18);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(arch.getAbsolutePath()));
            doc.open();
            int cont = 0;
            Barcode128 code = new Barcode128();
            code.setCode(cod);
            Image img = code.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            for (int i = 0; i < cant; i++) {
                table.addCell(img);
                cont++;
                if (cont == 8) {
                    cont = 0;
                }
            }
            if (cont < 8) {
                for (int i = cont; i < 8; i++) {
                    table.addCell("empty");
                }
            }
            doc.add(table);
            doc.close();
            PDDocument document = PDDocument.load(arch);
            PrintService myPrintService = this.impresora(printer);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPageable(new PDFPageable(document));
            printerJob.setPrintService(myPrintService);
            printerJob.print();
            return true;
        } catch (Exception e) {
            new Messages().errorMessage(Class, "genBC128PDF()", e);
        }
        return false;
    }

    public PrintService impresora(String nombre) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null); //Obtenemos los servicios de impresion del sistema 
        for (PrintService impresora : printServices) { //Recorremos el array de servicios de impresion
            if (impresora.getName().contentEquals(nombre)) { // Si el nombre del servicio es el mismo que el que buscamos
                return impresora; // Nos devuelve el servicio 
            }
        }
        return null;
    }

    public byte[] ImgBC128(String cod) {
        byte[] resp = null;
        try {
            Barcode128 code128ext = new Barcode128();
            code128ext.setCode(cod);
            code128ext.setStartStopText(false);
            code128ext.setExtended(true);
            java.awt.Image rawImage = code128ext.createAwtImage(Color.BLACK, Color.WHITE);
            BufferedImage outImage = new BufferedImage(rawImage.getWidth(null), rawImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            outImage.getGraphics().drawImage(rawImage, 0, 0, null);
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ImageIO.write(outImage, "png", bytesOut);
            bytesOut.flush();
            resp = bytesOut.toByteArray();
        } catch (IOException e) {
            new Messages().errorMessage(Class, "ImgBC128()", e);
        }
        return resp;
    }

}
