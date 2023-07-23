import utils.PDFCreator;
import utils.PrinterTaskCreator;

import javax.print.*;
import java.io.IOException;

public class Main {
    final static private String pdfPath = "pdf/blank1_template.pdf";
    final static private String pdfPathWithText = "pdf/blank1_test_withText.pdf";
    public static void main(String[] args) throws IOException, PrintException {
        PrinterTaskCreator printerTaskCreator = new PrinterTaskCreator();
//        printerTaskCreator.printTestPng("testPng.png");



        PDFCreator pdfCreator = new PDFCreator();
//        pdfCreator.createPDF(pdfPath);

        String pathToNewFile_1 = pdfCreator.addText1LineToPDF(pdfPath, pdfPathWithText, 0, 780);
        String pathToNewFile_2 = pdfCreator.addText1LineToPDF(pdfPathWithText, pdfPathWithText, 10, 770);
        String pathToNewFile_3 = pdfCreator.addText1LineToPDF(pdfPathWithText, pdfPathWithText, 20, 760);
        String pathToNewFile_4 = pdfCreator.addText1LineToPDF(pdfPathWithText, pdfPathWithText, 30, 750);

        pdfCreator.setFontSize(30);
        String[] strArr = {"MultiLine", "Line 0", "Line 1", "Line 2", "Line 3", "Line 4"};
        pdfCreator.addTextMultipleLines(pdfPathWithText, pdfPathWithText, strArr, 50, 650);

        //        printerTaskCreator.printTestPDF(pathToNewFile);
//        printerTaskCreator.printTestPDF(pdfPathWithText);
    }
}
