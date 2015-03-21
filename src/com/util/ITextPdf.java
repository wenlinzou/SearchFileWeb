package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextPdf {
	public void writePdf2() throws DocumentException, IOException {

		OutputStream file = new FileOutputStream(new File("f:\\Test.pdf"));

		Document document = new Document();
		PdfWriter.getInstance(document, file);
		document.open();
		document.add(new Paragraph("Hello Kiran"));
		document.add(new Paragraph(new Date().toString()));

		document.close();
		file.close();
	}

	public static void writePdf(File readFile) throws DocumentException,
			IOException {

		OutputStream os = new FileOutputStream(new File("f:\\Test.pdf"));
		FileInputStream fis = new FileInputStream(readFile);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(fis, "gbk"));

		Document document = new Document();
		PdfWriter.getInstance(document, os);
		document.open();

		String tempStr = null;
		while ((tempStr = br.readLine()) != null) {
			System.out.println(tempStr);
			document.add(new Paragraph(tempStr));
		}
		document.add(new Paragraph(new Date().toString()));

		document.close();

		fis.close();
		br.close();
		os.close();

	}

	public static void main(String[] args) {
		try {
//			writePdf(new File("f:/dellll1.txt"));
			htmlTransPdf();
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	//HTML to PDF 
	public static void htmlTransPdf() throws IOException, Exception {
		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document,new FileOutputStream("f://testpdf1.pdf"));
		document.open();
		HTMLWorker htmlWorker = new HTMLWorker(document);
		htmlWorker.parse(new StringReader("<h1>This is a test!</h1>"));
		document.close();
	}
	//Barcode QRCode
	/*public static void barcodePdf(){
		Document doc = new Document(PageSize.LETTER);
		String myString = "http://www.google.com";

		Barcode128 code128 = new Barcode128();
		code128.setCode(myString.trim());
		code128.setCodeType(Barcode128.CODE128);
		Image code128Image = code128.createImageWithBarcode(cb, null, null);
		code128Image.setAbsolutePosition(10,700);
		code128Image.scalePercent(125);
		doc.add(code128Image);

		BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);
		Image qrcodeImage = qrcode.getImage();
		qrcodeImage.setAbsolutePosition(10,600);
		qrcodeImage.scalePercent(200);
		doc.add(qrcodeImage);

	}*/
}
