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
import com.itextpdf.text.Rectangle;
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
public class TicketLetter {

    String Class = "ticketLetter";
    String LogoRoute = systemController.getInstance().getResources();
    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    systemController sysConf = systemController.getInstance();

    public void CreatePDFLetter(String ruta, Rectangle tamaño, PdfPTable tabla, PdfPTable tableFooter) {
        try {
            int margenLeft = 36;
            int margenRigth = 36;
            int margenTop = 18;
            int margenBottom = 18;
            Document doc = new Document(tamaño, margenLeft, margenRigth, margenTop, margenBottom);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();
            doc.add(TableLetterHeader());
            doc.add(new Paragraph(" "));
            doc.add(tabla);
            tableFooter.setTotalWidth(doc.right(doc.rightMargin()));
            tableFooter.writeSelectedRows(0, -1, margenLeft, tableFooter.getTotalHeight() + doc.bottom(doc.bottomMargin()), pdfw.getDirectContent());
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            new Messages().errorMessage(Class, "CreatePDFLetterSale()", e);
        }
    }

    public PdfPTable TableLetterHeader() {
        PdfPTable table = new PdfPTable(4);
        int tamaño = 9;
        table.setWidthPercentage(100);
        PdfPCell celda;
        try {
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);//SEPARADOR

            //ENCABEZADO
            File f;
            if (new File(LogoRoute + "\\LOGO.png").exists()) {
                f = new File(LogoRoute + "\\LOGO.png");
            } else {
                f = new File(LogoRoute + "\\LOGO.png");
            }
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(f.getAbsolutePath());
            imagen.scaleToFit(150, 75);
            imagen.setAlignment(Element.ALIGN_CENTER);
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(2);
            celda.addElement(imagen);
            table.addCell(celda);// LOGO  

            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setColspan(3);
            Paragraph p;
            String text = sysConf.getStoreName() + "\n";
            if (!sysConf.getRFC().equals("Sin RFC")) {
                text += "RFC:" + sysConf.getRFC().toUpperCase() + "\n";
            }
            text += "DIRECCION:" + sysConf.getAdress() + "\n";
            if (!sysConf.getPhone().equals("Sin Telefono")) {
                text += "TELEFONO:" + sysConf.getPhone() + "\n";
            }

            p = new Paragraph(text, FontFactory.getFont(fuente.toString(), 12));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);//DATOS EMPRESA

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);//SEPARADOR
        } catch (BadElementException | IOException e) {
            new Messages().errorMessage(Class, "TableLetterHeaderSale()", e);
        }
        return table;
    }

    public PdfPTable TableLetter(String idSale, List<Product> cart) {
        int maxSize = 5;
        PdfPTable table = new PdfPTable(maxSize);
        int tamaño = 9;
        table.setWidthPercentage(100);
        PdfPCell celda;
        try {
            Paragraph p;

            p = new Paragraph("PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño + 3));
            p.setAlignment(Element.ALIGN_CENTER);
            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            table.addCell(celda);

            table.addCell(new Phrase("Producto", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Marca", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Precio", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Cantidad", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Total", FontFactory.getFont(fuente.toString(), tamaño)));

            for (int i = 0; i < cart.size(); i++) {
                Product temp = cart.get(i);
                table.addCell(new Phrase(temp.getDescription(), FontFactory.getFont(fuente.toString(), tamaño)));
                table.addCell(new Phrase(temp.getBrand(), FontFactory.getFont(fuente.toString(), tamaño)));
                table.addCell(new Phrase(dataController.getInstance().getMoneyFormat(temp.getValue()), FontFactory.getFont(fuente.toString(), tamaño)));

                if (temp.isBulk()) {
                    table.addCell(new Phrase(dataController.getInstance().getKgFormat(temp.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                } else {
                    table.addCell(new Phrase(dataController.getInstance().getPzFormat(temp.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                }

                table.addCell(new Phrase(dataController.getInstance().getMoneyFormat(temp.getValue() * temp.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
            }

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(maxSize - 1);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase("Total: " + dataController.getInstance().getMoneyFormat(dataController.getInstance().getCartTotal(cart)), FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(celda);
        } catch (Exception e) {
            new Messages().errorMessage(Class, "TableLetter()", e);
        }
        return table;
    }

    public PdfPTable TableLetterFooter(String idSale) {
        PdfPTable table = new PdfPTable(4);
        int tamaño = 9;
        Paragraph p;
        table.setWidthPercentage(100);

        try {
            PdfPCell celda;
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);//SEPARADOR

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(2);
            table.addCell(celda);

            p = new Paragraph("Fecha: " + new SimpleDateFormat("dd-MM-YYYY").format(new Date()) + ", " + new SimpleDateFormat("HH:mm").format(new Date())
                    + "\nLe atendio: " + sessionController.getInstance().getLogedUser().getUsername()
                    + "\nForma de pago: EFECTIVO", FontFactory.getFont(fuente.toString(), tamaño));
            celda = new PdfPCell(p);
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(idSale));
            celda.addElement(image);
            p = new Paragraph("Folio: " + idSale, FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph(sysConf.getSaleTerms(), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(4);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);//SEPARADOR

        } catch (BadElementException | IOException e) {
            new Messages().errorMessage(Class, "TableLetterFooter()", e);
        }
        return table;
    }

}
