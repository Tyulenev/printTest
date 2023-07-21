import utils.PDFCreator;
import utils.PrinterTaskCreator;

import javax.print.*;
import javax.print.attribute.HashPrintServiceAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    final static private String pdfPath = "pdf/blank1_test.pdf";
    final static private String pdfPathWithText = "pdf/blank1_test_withText";
    public static void main(String[] args) throws IOException, PrintException {
        PrinterTaskCreator printerTaskCreator = new PrinterTaskCreator();
//        printerTaskCreator.printTestPng("testPng.png");



        PDFCreator pdfCreator = new PDFCreator();
//        pdfCreator.createPDF(pdfPath);
        String pathToNewFile = pdfCreator.addTextToPDF(pdfPath, pdfPathWithText);
//        printerTaskCreator.printTestPDF(pathToNewFile);
        printerTaskCreator.printTestPDF(pathToNewFile);
    }
}
