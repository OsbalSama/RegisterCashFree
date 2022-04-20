/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFDocuments;

import Classes.Barcodes;
import Controllers.dataController;
import Controllers.salesController;
import Controllers.sessionController;
import Controllers.systemController;
import Models.BoxCut;
import Models.Messages;
import Models.Sale;
import Models.SoldProduct;
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
import java.util.List;

/**
 *
 * @author ARTEMISA
 */
public class BoxCutLetter {

    String Class = "BoxCutLetter";
    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    systemController sysConf = systemController.getInstance();
    salesController DataBase = salesController.getInstance();
    String LogoRoute = systemController.getInstance().getResources();

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

    public PdfPTable TableLetter(BoxCut newBoxCut, List<Sale> allSales) {
        int maxSize = 5;
        PdfPTable table = new PdfPTable(maxSize);
        int tamaño = 9;
        table.setWidthPercentage(100);
        PdfPCell celda;
        try {
            Paragraph p;

            p = new Paragraph("CORTE DE CAJA", FontFactory.getFont(fuente.toString(), tamaño + 3));
            p.setAlignment(Element.ALIGN_CENTER);
            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            table.addCell(celda);

            if (allSales.size() > 0) {
                for (int i = 0; i < allSales.size(); i++) {
                    Sale temp = allSales.get(i);

                    p = new Paragraph(" ", FontFactory.getFont(fuente.toString(), tamaño));
                    p.setAlignment(Element.ALIGN_CENTER);
                    celda = new PdfPCell();
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celda.addElement(p);
                    celda.setColspan(maxSize);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("VENTA CODIGO: " + temp.getId(), FontFactory.getFont(fuente.toString(), tamaño + 3)));
                    celda.setColspan(maxSize);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    celda.setColspan(2);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Vendedor", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Total", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Fecha de Venta", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setColspan(2);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getSeller(), FontFactory.getFont(fuente.toString(), tamaño)));
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(temp.getTotalOfSale()), FontFactory.getFont(fuente.toString(), tamaño)));
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getDateCreatedToString(), FontFactory.getFont(fuente.toString(), tamaño)));
                    table.addCell(celda);

                    List<SoldProduct> Solds = DataBase.findSoldProductsbySaleId(temp.getId());

                    celda = new PdfPCell(new Phrase("Productos Vendidos", FontFactory.getFont(fuente.toString(), tamaño + 3)));
                    celda.setColspan(maxSize);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Producto Vendido", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Marca", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Precio", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Cantidad", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("Total", FontFactory.getFont(fuente.toString(), tamaño)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    for (int j = 0; j < Solds.size(); j++) {
                        if (Solds.size() > 0) {
                            SoldProduct soldProduct = Solds.get(j);

                            celda = new PdfPCell(new Phrase(soldProduct.getDescription(), FontFactory.getFont(fuente.toString(), tamaño)));
                            table.addCell(celda);

                            celda = new PdfPCell(new Phrase(soldProduct.getBrand(), FontFactory.getFont(fuente.toString(), tamaño)));
                            table.addCell(celda);

                            celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(soldProduct.getValue()), FontFactory.getFont(fuente.toString(), tamaño)));
                            table.addCell(celda);

                            if (soldProduct.isBulk()) {
                                celda = new PdfPCell(new Phrase(dataController.getInstance().getKgFormat(soldProduct.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                            } else {
                                celda = new PdfPCell(new Phrase(dataController.getInstance().getPzFormat(soldProduct.getCant()), FontFactory.getFont(fuente.toString(), tamaño)));
                            }
                            table.addCell(celda);

                            celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(soldProduct.getTotal()), FontFactory.getFont(fuente.toString(), tamaño)));
                            table.addCell(celda);

                        } else {
                            celda = new PdfPCell(new Phrase("SIN PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño)));
                            table.addCell(celda);
                        }
                    }
                }
            } else {

                celda = new PdfPCell(new Paragraph("SIN PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setColspan(maxSize);
                table.addCell(celda);

            }
            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(maxSize);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(maxSize - 1);
            table.addCell(celda);

            p = new Paragraph("En Caja: " + dataController.getInstance().getMoneyFormat(newBoxCut.getInCash()), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_RIGHT);
            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(maxSize - 1);
            table.addCell(celda);

            p = new Paragraph("Total en Ventas: " + dataController.getInstance().getMoneyFormat(newBoxCut.getTotalOfSale()), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_RIGHT);
            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            table.addCell(celda);

        } catch (Exception e) {
            new Messages().errorMessage(Class, "TableLetterSale()", e);
        }
        return table;
    }

    public PdfPTable TableLetterFooter(BoxCut newBoxCut) {
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

            p = new Paragraph("Fecha: " + newBoxCut.getDateCreatedToString() + ", " + newBoxCut.getTimeCreatedToString()
                    + "\nCreado Por: " + sessionController.getInstance().getLogedUser().getUsername(),
                    FontFactory.getFont(fuente.toString(), tamaño));
            celda = new PdfPCell(p);
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(newBoxCut.getId()));
            celda.addElement(image);
            p = new Paragraph("Folio: " + newBoxCut.getId(), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

        } catch (BadElementException | IOException e) {
            new Messages().errorMessage(Class, "TableLetterFooterSale()", e);
        }
        return table;
    }

}
