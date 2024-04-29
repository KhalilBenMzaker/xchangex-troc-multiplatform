/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import utils.MyDB;

/**
 *
 * @author asus
 */
public class Pdf2 {

    Connection cnx;

    public Pdf2() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void add(String file, String N1, String N2, String N3, String N4) throws FileNotFoundException, DocumentException {

        Document my_pdf_report = new Document();
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
        my_pdf_report.open();

        // Add content to the PDF using paragraphs
        my_pdf_report.add(new Paragraph("Nom event: " + N1));
        my_pdf_report.add(new Paragraph("Type: " + N2));
        my_pdf_report.add(new Paragraph("Description: " + N3));
        my_pdf_report.add(new Paragraph("Date: " + N4));

        my_pdf_report.close();
    }

}
