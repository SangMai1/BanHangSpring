package com.learncode.models;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Phaser;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.codec.Utf8;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPage;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

public class SanphamdamuaPDFExporter {

	Optional<Kho> listKho;
	
	public SanphamdamuaPDFExporter(Optional<Kho> k) {
		this.listKho = k;
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		
		cell.setPhrase(new Phrase("Quantity"));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Price"));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		Optional<Kho> kho = listKho;
		table.addCell(String.valueOf(kho.get().getBilldetails_id().getBilldetail_quantity()));
		table.addCell(String.valueOf(kho.get().getBilldetails_id().getBilldetail_price()));
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("TrendFashion", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font1.setSize(18);
		font1.setColor(Color.BLACK);
		
		Paragraph h = new Paragraph("PHIEU XUAT KHO", font1);
		h.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(h);
		
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
//		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();
	}
}
