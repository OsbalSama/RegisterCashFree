/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFDocuments;

import Classes.Barcodes;
import Controllers.sessionController;
import Controllers.systemController;
import Models.Messages;
import Models.Session;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ARTEMISA
 */
public class LoginReportMiniPrinter {

    String Class = "LoginReportMiniPrinter";
    String LogoRoute = systemController.getInstance().getResources();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    systemController sysConf = systemController.getInstance();

    public void CreatePDFMiniPrint(String ruta, com.itextpdf.text.Rectangle tamaño, PdfPTable tabla) {
        try {
            int margenLeft = 5;
            int margenRigth = 5;
            int margenTop = 10;
            int margenBottom = 10;
            Document doc = new Document(tamaño, margenLeft, margenRigth, margenTop, margenBottom);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();
            doc.add(TableMiniprintHeader());
            doc.add(new Paragraph(" "));
            doc.add(tabla);
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            new Messages().errorMessage(Class, "CreatePDFMiniPrintBoxCut()", e);
        }
    }

    public PdfPTable TableMiniprintHeader() {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell celda;
        Paragraph p;
        int tamaño = 6;
        int border = 0;
        try {
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell();
            File f;
            if (new File(LogoRoute + "\\LOGO.png").exists()) {
                f = new File(LogoRoute + "\\LOGO.png");
            } else {
                f = new File(LogoRoute + "\\LOGO.png");
            }
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(f.getAbsolutePath());
            imagen.scaleToFit(70, 35);
            imagen.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(imagen);
            celda.setColspan(4);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell();
            String text = sysConf.getStoreName() + "\n";
            if (!sysConf.getRFC().equals("Sin RFC")) {
                text += "RFC:" + sysConf.getRFC().toUpperCase() + "\n";
            }
            text += "DIRECCION:" + sysConf.getAdress() + "\n";
            if (!sysConf.getPhone().equals("Sin Telefono")) {
                text += "TELEFONO:" + sysConf.getPhone() + "\n";
            }

            p = new Paragraph(text, FontFactory.getFont(fuente.toString(), tamaño));
            celda.setColspan(4);
            celda.setBorder(border);
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);
        } catch (BadElementException | IOException e) {
            new Messages().errorMessage(Class, "TableMiniprintHeader()", e);
        }
        return table;
    }

    public PdfPTable TableMiniprint(String idReport, Date From, Date To, List<Session> allLoggins) {
        int maxSize = 7;
        PdfPTable table = new PdfPTable(maxSize);
        table.setWidthPercentage(100);
        PdfPCell celda;
        Paragraph p;
        int tamaño = 6;
        int border = 0;
        try {
//            celda = new PdfPCell();
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("REPORTE DE INICIOS DE SESION", FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("DE " + dateFormat.format(From) + " AL " + dateFormat.format(To), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph("COD.", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell(new Paragraph("USR", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("F. REG", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("H. REG", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("F. OUT", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("H. OUT", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("LOGGS", FontFactory.getFont(fuente.toString(), tamaño - 2)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);
            if (true) {
                for (int i = 0; i < allLoggins.size(); i++) {
                    Session temp = allLoggins.get(i);

                    celda = new PdfPCell(new Phrase(temp.getId(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getUsername(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getFirstLoginToString(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getTimeFirstLogin(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getLastLoginToString(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getTimeLastLogin(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getNumberLoggins() + "", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);
                }
            } else {
                celda = new PdfPCell(new Phrase("SIN REGISTROS", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                celda.setColspan(maxSize);
                celda.setBorder(border);
                table.addCell(celda);
            }

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            Date now = new Date();
            p = new Paragraph("FECHA: " + new SimpleDateFormat("dd/MM/YYYY").format(now) + ", " + new SimpleDateFormat("HH:mm").format(now)
                    + "\nCREADO POR: " + sessionController.getInstance().getLogedUser().getUsername(),
                    FontFactory.getFont(fuente.toString(), tamaño));
            celda = new PdfPCell(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            p.setAlignment(Element.ALIGN_CENTER);
            table.addCell(celda);

            celda = new PdfPCell();
            celda.setColspan(maxSize);
            celda.setBorder(border);
            com.itextpdf.text.Image image;
            image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(idReport));
            celda.addElement(image);
            p = new Paragraph("FOLIO: " + idReport, FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(maxSize);
            celda.setBorder(2);
            table.addCell(celda);//CORTAR

        } catch (BadElementException | IOException e) {
            new Messages().errorMessage(Class, "TableMiniprint()", e);
        }
        return table;
    }
}
