import utils.PDFCreator;
import utils.PrinterTaskCreator;

import javax.print.*;
import java.io.IOException;
import java.util.Date;

public class Main {
//    final static private String pdfPath = "pdf/templates/blank2_template.pdf";
//    final static private String pdfPath = "pdf/templates/template_77_smile.pdf";
    final static private String pdfPath = "pdf/templates/template_80_lines.pdf";
    final static private String pdfPathWithText = "pdf/ticket_";

    public static void main(String[] args) throws IOException, PrintException {
        PrinterTaskCreator printerTaskCreator = new PrinterTaskCreator();
//        printerTaskCreator.printTestPng("testPng.png");

        PDFCreator pdfCreator = new PDFCreator();
//        pdfCreator.createPDF(pdfPath);

        String pdfResultTicketName = generateTicketName(pdfPathWithText);

//        One Line TEXT
//        pdfCreator.addText1LineToPDF(pdfPath, pdfResultTicketName, 0, 780);
        pdfCreator.setFontSize(8);
        pdfCreator.addText1LineToPDF(pdfPath, pdfResultTicketName, 5, 385, "filename: " +pdfResultTicketName);
        pdfCreator.addText1LineToPDF(pdfResultTicketName, pdfResultTicketName, 5, 375
                , (new Date()).toString());
//        pdfCreator.addText1LineToPDF(pdfResultTicketName, pdfResultTicketName, 5, 365
//                , "Русский текс ТЕСТ");


        pdfCreator.setFontSize(60);
        pdfCreator.addText1LineToPDF(pdfResultTicketName, pdfResultTicketName, 40, 30, "A002");


        //MULTILINE TEXT
        pdfCreator.setFontSize(20);
        String[] strArr1 = {"MultiLine1", "Line 0", "Line 1", "Line 2", "Line 3", "Line 4"};
        String[] strArr2 = {"MultiLine2", "Line 0", "Line 1", "Line 2", "Line 3"};
        pdfCreator.addTextMultipleLines(pdfResultTicketName, pdfResultTicketName, strArr1, 30, 250);
        pdfCreator.addTextMultipleLines(pdfResultTicketName, pdfResultTicketName, strArr2, 70, 250);
        pdfCreator.setFontSize(13);
        pdfCreator.addText1LineToPDF(pdfResultTicketName, pdfResultTicketName, 10, 250, "abrakadabra123");

        //        printerTaskCreator.printTestPDF(pathToNewFile);
//        printerTaskCreator.printTestPDF(pdfPathWithText);
        System.out.println("Created file: " + pdfResultTicketName);
    }

    public static String generateTicketName(String templateName) {
        Date now = new Date();
        return templateName + now.getTime() + ".pdf";
    }
}
