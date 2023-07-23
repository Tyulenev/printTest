package utils;

//import org.apache.pdfbox.Loader;

//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PDFCreator {
    public PDFCreator() {
        this.fontSize = 14;
        this.fontType = PDType1Font.COURIER_OBLIQUE;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    private int fontSize;
    private PDType1Font fontType;

    public void createPDF(String nameOfPDFFile) throws IOException {
        //Creating PDF document object
        PDDocument doc = new PDDocument();

        PDPage blankPage = new PDPage();
        //Saving the document
        doc.addPage(blankPage);
        doc.save( nameOfPDFFile);
        System.out.println("PDF created");
        //Closing the document
        doc.close();
    }

    public String addText1LineToPDF(String sourcePathFile, String newSavedFileName, int tx, int ty) throws IOException {

        File file = new File(sourcePathFile);
//        PDDocument doc = Loader.loadPDF(file); //for pdfBox3.0
        PDDocument doc = PDDocument.load(file);
        PDPage page = doc.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true); //Append mode

        contentStream.beginText();
        contentStream.setFont(fontType, fontSize);
        //for pdfBox3.0
//        PDType1Font pdType1Font = new PDType1Font(Standard14Fonts.FontName.COURIER);
//        contentStream.setFont( pdType1Font, 14);
        Date now = new Date();
        contentStream.newLineAtOffset(tx, ty);
        String text = "TEST by AT. Q-SYS " + tx + "*" + ty + " (tx*ty). Time: " + now.toString();
        contentStream.showText(text);
        contentStream.endText();
        System.out.println("New Text Content is added in the PDF Document.");
        contentStream.close();
        doc.save(new File(newSavedFileName));
        doc.close();
        return newSavedFileName;
    }

    public String addTextMultipleLines(String sourcePathFile, String newSavedFileName
            , String[] multipleStrText, int tx, int ty) throws IOException {
        File file = new File(sourcePathFile);
        PDDocument doc = PDDocument.load(file);
        PDPage page = doc.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);

        contentStream.beginText();
        contentStream.setFont(fontType, fontSize);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(tx, ty);

        for (String str :multipleStrText) {
            contentStream.showText(str);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();
        doc.save(new File(newSavedFileName));
        doc.close();

        System.out.println("Multiple Text Content is added in the PDF Document.");
        return newSavedFileName;
    }

}
