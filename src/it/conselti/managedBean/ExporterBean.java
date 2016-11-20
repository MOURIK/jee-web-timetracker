/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;


/**
 * @author onofr
 *
 */

@Component(value="exporterBean")
@Scope(value="view")
public class ExporterBean extends AbstractManagedBean implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1821471110304616830L;
	
	
	@Autowired
	private VisualizzaRegistrazioneBean visualizzaRegistrazioneBean;
	
	private Date from, to;	
	private Integer mese, anno;
	
	
	
	
	
	
	
	@PostConstruct
	public void init() {
		refreshList();
	}
	
	
	@Override
	protected void injectBean() {
		try {
			from = visualizzaRegistrazioneBean.getVisualizza().getDateFrom();
			to = visualizzaRegistrazioneBean.getVisualizza().getDateTo();
			mese = visualizzaRegistrazioneBean.getVisualizza().getMese();
			anno = visualizzaRegistrazioneBean.getVisualizza().getAnno();
			
			if(this.from != null && this.to != null) {
				if(this.mese != null && this.anno != null) {
					logger.info("Bean injection: ExporterBean.java [" + this.from + ", " + this.to + ", " + this.mese + ", " + this.anno + "]");
				}
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * @param document the xls document.
	 */
	public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short)10);
        font.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(font);
         
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
     
	
	
	/**
	 * 
	 * @param document the input document.
	 * @throws IOException if fails.
	 * @throws BadElementException if fails.
	 * @throws DocumentException if fails.
	 */
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
      
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imgs" + File.separator + "logo.png";
        
        pdf.add(Image.getInstance(logo));
        
        Paragraph p = new Paragraph();
        p.setSpacingAfter(50);
        
        pdf.add(p);
         
    }
    
    
    
    /**
	 * 
	 * @param document the input document.
	 * @throws IOException if fails.
	 * @throws BadElementException if fails.
	 * @throws DocumentException if fails.
	 */
    public void preProcessVisualizzaRegistrazionePDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        
        injectBean();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imgs" + File.separator + "logo.png";
        
        pdf.add(Image.getInstance(logo));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Paragraph p = new Paragraph();
        p.setSpacingAfter(50);

        if(from == null && to == null) {
        	pdf.add(new Paragraph("Elenco completo delle Registrazioni."));
            pdf.add(p);
             
        } else if (from != null & to != null) {
            pdf.add(new Paragraph("Elenco delle Registrazioni dal " + sdf.format(this.from) + " al " + sdf.format(this.to) + "."));
            pdf.add(p);
        }
        
        refreshList();
    }
    
    
    
    /**
     * 
     * @param document the document.
     * @throws IOException the IO exception.
     * @throws BadElementException the BadElement exception.
     * @throws DocumentException the Document exception.
     */
    public void preProcessAnagraficaPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
      
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imgs" + File.separator + "logo.png";
        
        pdf.add(Image.getInstance(logo));
        
        Paragraph p = new Paragraph();
        p.setSpacingAfter(50);
        
        pdf.add(new Paragraph("Elenco completo delle Anagrafiche utenti."));
        pdf.add(p);
         
    }
    
    
    
    
    /**
     * 
     * @param document the document.
     * @throws IOException the IO exception.
     * @throws BadElementException the BadElement exception.
     * @throws DocumentException the Document exception.
     */
    public void preProcessVisualizzaRegistrazionePerPeriodoPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
      
        injectBean();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imgs" + File.separator + "logo.png";
        
        pdf.add(Image.getInstance(logo));
        
        Paragraph p = new Paragraph();
        p.setSpacingAfter(50);
        
        
        if(mese == null || mese.intValue() == 0) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni dell'anno " + this.anno + "."));
        	pdf.add(p);
        	
        } else if (mese.equals(1)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Gennaio " + this.anno + "."));
        	pdf.add(p);
             
        } else if (mese.equals(2)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Febbraio " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(3)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Marzo " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(4)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Aprile " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(5)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Maggio " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(6)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Giugno " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(7)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Luglio " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(8)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Agosto " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(9)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Settembre " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(10)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Ottobre " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(11)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Novembre " + this.anno + "."));
            pdf.add(p);
        	
        } else if (mese.equals(12)) {
        	pdf.add(new Paragraph("Elenco delle Registrazioni di Dicembre " + this.anno + "."));
            pdf.add(p);
        }
        
        refreshList();
    }

    
    
	/* (non-Javadoc)
	 * @see it.conselti.managedBean.AbstractManagedBean#refreshList()
	 */
	@Override
	protected void refreshList() {
		from = to = null;
		mese = anno = null;
	}


	
	
	
	
	
	
	
	/* Getters and Setters */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public VisualizzaRegistrazioneBean getVisualizzaRegistrazioneBean() {
		return visualizzaRegistrazioneBean;
	}


	public Date getFrom() {
		return from;
	}


	public Date getTo() {
		return to;
	}


	public Integer getMese() {
		return mese;
	}


	public Integer getAnno() {
		return anno;
	}


	public void setVisualizzaRegistrazioneBean(VisualizzaRegistrazioneBean visualizzaRegistrazioneBean) {
		this.visualizzaRegistrazioneBean = visualizzaRegistrazioneBean;
	}


	public void setFrom(Date from) {
		this.from = from;
	}


	public void setTo(Date to) {
		this.to = to;
	}


	public void setMese(Integer mese) {
		this.mese = mese;
	}


	public void setAnno(Integer anno) {
		this.anno = anno;
	}



}
