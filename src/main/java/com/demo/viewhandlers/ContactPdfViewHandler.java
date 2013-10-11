package com.demo.viewhandlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.demo.constants.ApplicationConstants;
import com.demo.dtos.ContactDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class ContactPdfViewHandler extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		@SuppressWarnings("unchecked")
		List<ContactDTO> contactDataList = (List<ContactDTO>) model.get(ApplicationConstants.CONTACT_MODEL);

		Table table = new Table(5);
		table.addCell(ContactDTO.ID.toUpperCase());
		table.addCell(ContactDTO.NAME.toUpperCase());
		table.addCell(ContactDTO.EMAIL.toUpperCase());
		table.addCell(ContactDTO.MOBILE.toUpperCase());
		table.addCell(ContactDTO.MESSAGE.toUpperCase());

		for (ContactDTO contact : contactDataList) {
			table.addCell(String.valueOf(contact.getId()));
			table.addCell(contact.getName());
			table.addCell(contact.getEmail());
			table.addCell(contact.getMobile());
			table.addCell(contact.getMessage());
		}

		document.add(table);

	}
}
