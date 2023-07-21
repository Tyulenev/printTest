package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.*;

public class PrinterTaskCreator {


    public void printTestPng(String docName) throws FileNotFoundException, PrintException {
        DocFlavor flavorPNG = DocFlavor.INPUT_STREAM.PNG;
//        HashPrintServiceAttributeSet  //м.б. пемещен в атрибуты для сервиса
        DocAttributeSet docAttributeSet = new HashDocAttributeSet();
        docAttributeSet.add(OrientationRequested.LANDSCAPE);


        PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavorPNG, docAttributeSet);
        InputStream inputStream = new FileInputStream(docName);
        Doc doc = new SimpleDoc(inputStream, flavorPNG, null);
        if (printService.length > 0) {
            DocPrintJob job = printService[0].createPrintJob();
            job.print(doc, null);
        }
    }

//    public void printTestPDF_2(String docName)  {
//        //Создать объект PdfViewer
//        PdfViewer viewer = new PdfViewer();
//        //Открыть исходный PDF-файл
//        viewer.bindPdf(dataDir + "Test.pdf");
//        //Распечатать PDF-документ
//        viewer.printDocument();
//        //Закрыть PDF-файл
//        viewer.close();
//    }

    public void printTestPDF(String docName)  {
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        if (ps == null)
        {
            JOptionPane.showMessageDialog(null,
                    "Принтер не обнаружен",
                    "Печать",
                    JOptionPane.PLAIN_MESSAGE);
            return;
        }
        //
        PrinterJob job = PrinterJob.getPrinterJob();
        try
        {
            job.setPrintService(ps);
        }
        catch (PrinterException e1)
        {
            e1.printStackTrace();
        }

        PageFormat pageFormat = job.defaultPage();
        double margin = 36;//12.5
        Paper paper = new Paper();
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()- margin * 2);
        pageFormat.setOrientation(PageFormat.PORTRAIT);//PORTRAIT
        pageFormat.setPaper(paper);
        PageFormat validatePage = job.validatePage(pageFormat);

        job.setPrintable(new Printable()
        {
            public int print(Graphics g, PageFormat pf, int pageNumber)throws PrinterException
            {
                if (pageNumber != 0)
                {
                    return Printable.NO_SUCH_PAGE;
                }

                BufferedImage img = null;
                try
                {
                    File file = new File(docName);
                    PDDocument document = PDDocument.load(file);
                    PDFRenderer renderer = new PDFRenderer(document);
                    img = renderer.renderImage(0);
                    document.close();
                }
                catch (IOException e) {}

                Graphics2D g2 = (Graphics2D) g;
                g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
                double pageWidth = pf.getImageableWidth();
                double pageHeight = pf.getImageableHeight();
                double imageWidth = img.getWidth();
                double imageHeight = img.getHeight();
                double scaleX = pageWidth / imageWidth * 1.04;
                double scaleY = pageHeight / imageHeight * 1.04;
                double scaleFactor = Math.min(scaleX, scaleY);
                AffineTransform at = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
                g2.drawImage(img, at, null);

                return Printable.PAGE_EXISTS;
            }
        }, validatePage);

        try
        {
            job.print();
        }
        catch (PrinterException ex) {}
    }
}
