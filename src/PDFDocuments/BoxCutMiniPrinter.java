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
public class BoxCutMiniPrinter {

    String Class = "BoxCutMiniPrinter";
    String LogoRoute = systemController.getInstance().getResources();
    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    systemController sysConf = systemController.getInstance();
    salesController DataBase = salesController.getInstance();

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

    public PdfPTable TableMiniprint(BoxCut newBoxCut, List<Sale> allSales) {
        int maxSize = 5;
        PdfPTable table = new PdfPTable(maxSize);
        table.setWidthPercentage(100);
        PdfPCell celda;
        Paragraph p;
        int tamaño = 6;
        int border = 0;
        try {
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("CORTE DE CAJA", FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph("CODIGO", FontFactory.getFont(fuente.toString(), tamaño - 1)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph("VENDEDOR", FontFactory.getFont(fuente.toString(), tamaño - 1)));
            celda.setBorder(border);
            celda.setColspan(2);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase("TOTAL", FontFactory.getFont(fuente.toString(), tamaño - 1)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase("FECHA VENTA", FontFactory.getFont(fuente.toString(), tamaño - 1)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            if (allSales.size() > 0) {
                for (int i = 0; i < allSales.size(); i++) {

                    Sale temp = allSales.get(i);

                    celda = new PdfPCell(new Phrase(temp.getId(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getSeller(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setColspan(2);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(temp.getTotalOfSale()), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase(temp.getDateCreatedToString(), FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(celda);

                    List<SoldProduct> Solds = DataBase.findSoldProductsbySaleId(temp.getId());

                    celda = new PdfPCell(new Paragraph("PROD", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Paragraph("MARCA", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("PRECIO", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("CANT", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    celda = new PdfPCell(new Phrase("TOT", FontFactory.getFont(fuente.toString(), tamaño - 2)));
                    celda.setBorder(border);
                    celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);

                    if (Solds.size() > 0) {
                        for (int j = 0; j < Solds.size(); j++) {
                            SoldProduct soldProduct = Solds.get(j);
                            celda = new PdfPCell(new Phrase(soldProduct.getDescription(), FontFactory.getFont(fuente.toString(), tamaño - 3)));
                            celda.setBorder(border);
                            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(celda);

                            celda = new PdfPCell(new Phrase(soldProduct.getBrand(), FontFactory.getFont(fuente.toString(), tamaño - 3)));
                            celda.setBorder(border);
                            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(celda);

                            celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(soldProduct.getValue()), FontFactory.getFont(fuente.toString(), tamaño - 3)));
                            celda.setBorder(border);
                            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(celda);

                            if (soldProduct.isBulk()) {
                                celda = new PdfPCell(new Phrase(dataController.getInstance().getKgFormat(soldProduct.getCant()), FontFactory.getFont(fuente.toString(), tamaño - 3)));
                            } else {
                                celda = new PdfPCell(new Phrase(dataController.getInstance().getPzFormat(soldProduct.getCant()), FontFactory.getFont(fuente.toString(), tamaño - 3)));
                            }
                            celda.setBorder(border);
                            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(celda);

                            celda = new PdfPCell(new Phrase(dataController.getInstance().getMoneyFormat(soldProduct.getTotal()), FontFactory.getFont(fuente.toString(), tamaño - 3)));
                            celda.setBorder(border);
                            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(celda);
                        }
                    } else {
                        celda = new PdfPCell(new Paragraph("SIN PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño - -3)));
                        celda.setBorder(border);
                        celda.setColspan(maxSize);
                        celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(celda);
                    }
                }
            } else {
                celda = new PdfPCell(new Paragraph(" ", FontFactory.getFont(fuente.toString(), tamaño - 1)));
                celda.setBorder(border);
                celda.setColspan(maxSize);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);

                celda = new PdfPCell(new Paragraph("SIN PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño - 1)));
                celda.setBorder(border);
                celda.setColspan(maxSize);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);

                celda = new PdfPCell(new Paragraph(" ", FontFactory.getFont(fuente.toString(), tamaño - 1)));
                celda.setBorder(border);
                celda.setColspan(maxSize);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);
            }

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("En Caja: " + dataController.getInstance().getMoneyFormat(newBoxCut.getInCash()), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_RIGHT);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("Total de Ventas" + dataController.getInstance().getMoneyFormat(newBoxCut.getTotalOfSale()), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_RIGHT);
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

            celda = new PdfPCell();
            celda.setColspan(maxSize);
            celda.setBorder(border);
            com.itextpdf.text.Image image;
            image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(newBoxCut.getId()));
            celda.addElement(image);
            p = new Paragraph("FOLIO: " + newBoxCut.getId(), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(maxSize);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(celda);

            String texto = "Este documento fue realizado por el usuario " + sessionController.getInstance().getLogedUser().getUsername() + " el día " + newBoxCut.getDateCreatedToString() + " a las " + newBoxCut.getTimeCreatedToString() + " hrs";
            celda = new PdfPCell();
            p = new Paragraph(texto, FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            celda.setColspan(maxSize);
            celda.setBorder(border);
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
