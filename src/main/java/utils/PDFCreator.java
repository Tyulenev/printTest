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

    public String addTextToPDF(String nameOfPDFFile, String newFileName) throws IOException {
        //Loading an existing document
        File file = new File(nameOfPDFFile);
//        PDDocument doc = Loader.loadPDF(file); //for pdfBox3.0
        PDDocument doc = PDDocument.load(file);
        //Retrieving the pages of the document
        PDPage page = doc.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        //Begin the Content stream
        contentStream.beginText();
        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
        //for pdfBox3.0
//        PDType1Font pdType1Font = new PDType1Font(Standard14Fonts.FontName.COURIER);
//        contentStream.setFont( pdType1Font, 14);
        //Setting the position for the line
        contentStream.newLineAtOffset(10, 650);
        String text = "TEST by AT. Q-SYS 10*650";
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        Date now = new Date();
        //Begin the Content stream
        contentStream.beginText();
        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);   //Почему то не работает, возможно проблема в версии библиотеки
//        PDType1Font pdType1Font2 = new PDType1Font(Standard14Fonts.FontName.COURIER);
//        contentStream.setFont( pdType1Font2, 16);
        //Setting the position for the line
        contentStream.newLineAtOffset(100, 550);
        String text2 = "Line2 TEST" + now;
        contentStream.showText(text2);
        //Ending the content stream
        contentStream.endText();

        System.out.println("New Text Content is added in the PDF Document.");

        //Closing the content stream
        contentStream.close();

        String newFilePath = newFileName + now.getTime() + ".pdf";
        //Saving the document
        doc.save(new File(newFilePath));

        //Closing the document
        doc.close();
        return newFilePath;
    }

}
