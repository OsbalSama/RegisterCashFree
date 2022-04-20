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
import Models.Session;
import PDFDocuments.BoxCutLetter;
import PDFDocuments.BoxCutMiniPrinter;
import PDFDocuments.BoxCutsReportLetter;
import PDFDocuments.BoxCutsReportMiniPrinter;
import PDFDocuments.LoginReportLetter;
import PDFDocuments.LoginReportMiniPrinter;
import PDFDocuments.SaleReportLetter;
import PDFDocuments.SaleReportMiniPrinter;
import PDFDocuments.TicketLetter;
import PDFDocuments.TicketMiniPrinter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import java.awt.HeadlessException;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author ARTEMISA
 */
public class ticketController {

    String Class = "ticketController";
    Date dateReport = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMYYHHmm");

    systemController sysConf = systemController.getInstance();
    salesController allSales = salesController.getInstance();
    String TicketRepository = "documents\\SaleTickets\\";
    String BoxCutRepository = "documents\\BoxCuts\\";
    String ReportRepository = "documents\\Reports\\";

    public void printSaleTicket(String saleId, List<Product> cart, String SelectedPrinter) {
        String ruta = TicketRepository + saleId + ".pdf";
        try {
            switch (sysConf.getPaperSize()) {
                case 0:
                    new TicketMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(150, 792), new TicketMiniPrinter().TableMiniprint(saleId, cart));
                    PrintDocument(ruta, SelectedPrinter);
                    break;
                case 1:
                    new TicketMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(200, 792), new TicketMiniPrinter().TableMiniprint(saleId, cart));
                    PrintDocument(ruta, SelectedPrinter);
                    break;
                case 2:
                    new TicketLetter().CreatePDFLetter(ruta, PageSize.LETTER, new TicketLetter().TableLetter(saleId, cart), new TicketLetter().TableLetterFooter(saleId));
                    PrintDocument(ruta, SelectedPrinter);
                    break;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "printSaleTicket()", e);
            if (new Messages().yesNoMessage("Error al Imprimir", "¿Desea imprimirlo como PDF?")) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
        }
    }

    public void printBoxCut(BoxCut newBoxCut, String SelectedPrinter) {
        String ruta = BoxCutRepository + newBoxCut.getId() + ".pdf";
        List<Sale> Sales = allSales.findSalesbyBoxCutId(newBoxCut.getId());
        try {
            switch (sysConf.getPaperSize()) {
                case 0:
                    new BoxCutMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(150, 792), new BoxCutMiniPrinter().TableMiniprint(newBoxCut, Sales));
                    PrintDocument(ruta, SelectedPrinter);
                    break;
                case 1:
                    new BoxCutMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(200, 792), new BoxCutMiniPrinter().TableMiniprint(newBoxCut, Sales));
                    PrintDocument(ruta, SelectedPrinter);
                    break;
                case 2:
                    new BoxCutLetter().CreatePDFLetter(ruta, PageSize.LETTER, new BoxCutLetter().TableLetter(newBoxCut, Sales), new BoxCutLetter().TableLetterFooter(newBoxCut));
                    PrintDocument(ruta, SelectedPrinter);
                    break;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "printBoxCut()", e);
            if (new Messages().yesNoMessage("Error al Imprimir", "¿Desea imprimirlo como PDF?")) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
        }
    }

    public boolean printLogginsReport(Date From, Date To, List<Session> allLoggins, String SelectedPrinter) {
        boolean resp = false;
        String idReport = "LogRpt-" + dateFormat.format(dateReport);
        String ruta = ReportRepository + idReport + ".pdf";
        try {
            switch (sysConf.getPaperSize()) {
                case 0:
                    new LoginReportMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(150, 792), new LoginReportMiniPrinter().TableMiniprint(idReport, From, To, allLoggins));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 1:
                    new LoginReportMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(200, 792), new LoginReportMiniPrinter().TableMiniprint(idReport, From, To, allLoggins));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 2:
                    new LoginReportLetter().CreatePDFLetter(ruta, PageSize.LETTER, new LoginReportLetter().TableLetter(idReport, From, To, allLoggins), new LoginReportLetter().TableLetterFooter(idReport));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "printBoxCut()", e);
            if (new Messages().yesNoMessage("Error al Imprimir", "¿Desea imprimirlo como PDF?")) {
                PrintDocument(ruta, "Imprimir como PDF");
                resp = true;
            }
        }
        return resp;
    }

    public boolean printSalesReport(Date From, Date To, List<Sale> allSales, String SelectedPrinter) {
        boolean resp = false;
        String idReport = "SalesRpt-" + dateFormat.format(dateReport);
        String ruta = ReportRepository + idReport + ".pdf";
        try {
            switch (sysConf.getPaperSize()) {
                case 0:
                    new SaleReportMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(150, 792), new SaleReportMiniPrinter().TableMiniprint(idReport, From, To, allSales));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 1:
                    new SaleReportMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(200, 792), new SaleReportMiniPrinter().TableMiniprint(idReport, From, To, allSales));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 2:
                    new SaleReportLetter().CreatePDFLetter(ruta, PageSize.LETTER, new SaleReportLetter().TableLetter(idReport, From, To, allSales), new SaleReportLetter().TableLetterFooter(idReport));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "printBoxCut()", e);
            if (new Messages().yesNoMessage("Error al Imprimir", "¿Desea imprimirlo como PDF?")) {
                PrintDocument(ruta, "Imprimir como PDF");
                resp = true;
            }
        }
        return resp;
    }

    public boolean printBoxCutsReport(Date From, Date To, List<BoxCut> allBoxCuts, String SelectedPrinter) {
        boolean resp = false;
        String idReport = "BoxCutRpt-" + dateFormat.format(dateReport);
        String ruta = ReportRepository + idReport + ".pdf";
        try {
            switch (sysConf.getPaperSize()) {
                case 0:
                    new BoxCutsReportMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(150, 792), new BoxCutsReportMiniPrinter().TableMiniprint(idReport, From, To, allBoxCuts));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 1:
                    new BoxCutsReportMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(200, 792), new BoxCutsReportMiniPrinter().TableMiniprint(idReport, From, To, allBoxCuts));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 2:
                    new BoxCutsReportLetter().CreatePDFLetter(ruta, PageSize.LETTER, new BoxCutsReportLetter().TableLetter(idReport, From, To, allBoxCuts), new BoxCutsReportLetter().TableLetterFooter(idReport));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "printBoxCut()", e);
            if (new Messages().yesNoMessage("Error al Imprimir", "¿Desea imprimirlo como PDF?")) {
                PrintDocument(ruta, "Imprimir como PDF");
                resp = true;
            }
        }
        return resp;
    }

    public void PrintDocument(String ruta, String impresora) {
        switch (impresora) {
            case "No Imprimir":
                System.out.println("No Imprimir");
                break;
            case "Imprimir como PDF":
                DigitalPrint(ruta);
                break;
            default:
                PrintTicket(ruta, impresora);
                break;
        }
    }

    public void DigitalPrint(String ruta) {
        Path origen = FileSystems.getDefault().getPath(ruta);
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de PDF", "PDF", "pdf");
        chooser.setFileFilter(filtro);
        chooser.setSelectedFile(new File(new File(ruta).getName()));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                Path destino = FileSystems.getDefault().getPath(chooser.getSelectedFile().getAbsolutePath() + ".pdf");
                if (new File(destino.toString()).exists()) {
                    if (new Messages().yesNoMessage("Archivo ya existe", "Desea Reemplazarlo?")) {
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    }
                } else {
                    Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (HeadlessException | IOException e) {
                new Messages().errorMessage(Class, "DigitalPrint()", e);
            }
        }
    }

    public void PrintTicket(String ruta, String printer) {
        try {
            File arch = new File(ruta);
            PDDocument document = PDDocument.load(arch);
            PrintService myPrintService = this.getPrintDevice(printer);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPageable(new PDFPageable(document));
            printerJob.setPrintService(myPrintService);
            printerJob.print();
        } catch (PrinterException | IOException | NullPointerException e) {
            if (new Messages().yesNoMessage("Error al Imprimir", "Desea guardarlo como PDF")) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
        }
    }

    public PrintService getPrintDevice(String nombre) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService impresora : printServices) {
            if (impresora.getName().contentEquals(nombre)) {
                return impresora;
            }
        }
        return null;
    }

    public boolean rePrintSale(String idReg, String SelectedPrinter) {
        String ruta = TicketRepository + idReg + ".pdf";
        if (new File(ruta).exists()) {
            PrintDocument(ruta, SelectedPrinter);
            return true;
        } else {
            return false;

        }
    }

    public boolean existBoxCur(BoxCut boxcut) {
        String ruta = BoxCutRepository + boxcut.getId() + ".pdf";
        if (new File(ruta).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean rePrintBoxCut(BoxCut boxcut, String SelectedPrinter) {
        String ruta = BoxCutRepository + boxcut.getId() + ".pdf";
        if (new File(ruta).exists()) {
            PrintDocument(ruta, SelectedPrinter);
            return true;
        } else {
            return false;
        }
    }

    public boolean createBoxCutPDF(BoxCut newBoxCut, String SelectedPrinter) {
        boolean resp = false;
        String ruta = BoxCutRepository + newBoxCut.getId() + ".pdf";
        List<Sale> Sales = allSales.findSalesbyBoxCutId(newBoxCut.getId());
        try {
            switch (sysConf.getPaperSize()) {
                case 0:
                    new BoxCutMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(150, 792), new BoxCutMiniPrinter().TableMiniprint(newBoxCut, Sales));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 1:
                    new BoxCutMiniPrinter().CreatePDFMiniPrint(ruta, new Rectangle(200, 792), new BoxCutMiniPrinter().TableMiniprint(newBoxCut, Sales));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
                case 2:
                    new BoxCutLetter().CreatePDFLetter(ruta, PageSize.LETTER, new BoxCutLetter().TableLetter(newBoxCut, Sales), new BoxCutLetter().TableLetterFooter(newBoxCut));
                    PrintDocument(ruta, SelectedPrinter);
                    resp = true;
                    break;
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "printBoxCut()", e);
            if (new Messages().yesNoMessage("Error al Imprimir", "¿Desea imprimirlo como PDF?")) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
        }
        return resp;
    }

//    public boolean createBoxCutPDF(BoxCut boxcut, String SelectedPrinter) {
//        boolean resp = false;
//        try {
//            String ruta = BoxCutRepository + boxcut.getId() + ".pdf";
//            PrintDocument(ruta, SelectedPrinter);
//            resp = true;
//        } catch (Exception e) {
//            new Messages().errorMessage(Class, "DigitalPrint()", e);
//        }
//        return resp;
//    }
}
