package model;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import java.awt.Color;

import org.openpdf.text.Document;
import org.openpdf.text.Rectangle;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class ReportsManagement {
	public ReportsManagement() {} 

	public boolean engagementReport(String[][] data) {
		Document document = new Document(new Rectangle(1000,595));
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Engagement_Reports.pdf"));
			document.open();
         
			Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 16);
			Font headerFont = FontFactory.getFont(
					FontFactory.HELVETICA, 
					18, 
					Font.BOLD, 
					new Color(213,98,51));

			LocalDate today = LocalDate.now();
			String month = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			String year = String.valueOf(today.getYear());
			String date = month + " " + year;

			Paragraph header = new Paragraph("Engagement Reports for the date of " + date, 
					headerFont);
			header.setSpacingAfter(20);

			PdfPTable table = new PdfPTable(9);

			table.addCell("ID");
			table.addCell("Name");
			table.addCell("Sex");
			table.addCell("Total Engagements");
			table.addCell("Total Player Engagements");
			table.addCell("Total Coach Engagements");
			table.addCell("Total Tournament Engagements");
			table.addCell("Average Engagements");
			table.addCell("Last Engagement");

			table.setHeaderRows(1);

			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < data[i].length; j++) {
					table.addCell(data[i][j]);
				}
			}

			table.setSpacingAfter(10);

			document.add(header);
			document.add(table);
			document.close();

			System.out.println("Succesfully generated PDF");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
