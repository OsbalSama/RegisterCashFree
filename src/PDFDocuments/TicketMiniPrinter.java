/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFDocuments;

import Classes.Barcodes;
import Controllers.dataController;
import Controllers.sessionController;
import Controllers.systemController;
import Models.Messages;
import Models.Product;
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
public class TicketMiniPrinter {

    String Class = "ticketController";
    String LogoRoute = systemController.getInstance().getResources();
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
            new Messages().errorMessage(Class, "CreatePDFMiniPrintSale()", e);
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

    public PdfPTable TableMiniprint(String idSale, List<Product> cart) {
        int maxSize = 5;
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
            p = new Paragraph("PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            table.addCell(celda);

//            celda = new PdfPCell();
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph("PROD", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Paragraph("MARCA", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("PRECIO", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("CANT", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("TOTAL", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);

            for (int i = 0; i < cart.size(); i++) {
                Product temp = cart.get(i);
                celda = new PdfPCell(new Phrase(temp.getDescription().toUpperCase(), FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);

                celda = new PdfPCell(new Phrase(temp.getBrand().toUpperCase(), FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);

                celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(temp.getValue()), FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);

                if (temp.isBulk()) {
                    celda = new PdfPCell(new Phrase(dataController.getInstance().getKgFormat(temp.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                } else {
                    celda = new PdfPCell(new Phrase(dataController.getInstance().getPzFormat(temp.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                }
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);

                celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(temp.getValue() * temp.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);
            }//añadir productos

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(maxSize - 1);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase("Total: " + dataController.getInstance().getMoneyFormat(dataController.getInstance().getCartTotal(cart)), FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            Date now = new Date();
            p = new Paragraph("FECHA: " + new SimpleDateFormat("dd/MM/YYYY").format(now) + ", " + new SimpleDateFormat("HH:mm").format(now)
                    + "\nLE ATENDIO: " + sessionController.getInstance().getLogedUser().getUsername()
                    + "\nFORMA DE PAGO: EFECTIVO", FontFactory.getFont(fuente.toString(), tamaño));
            celda = new PdfPCell(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            p.setAlignment(Element.ALIGN_CENTER);
            table.addCell(celda);

            celda = new PdfPCell();
            celda.setColspan(maxSize);
            celda.setBorder(border);
            com.itextpdf.text.Image image;
            image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(idSale));
            celda.addElement(image);
            p = new Paragraph("FOLIO: " + idSale, FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph(sysConf.getSaleTerms().toUpperCase(), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
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
            new Messages().errorMessage(Class, "TableMiniprintSale()", e);
        }
        return table;
    }
}
