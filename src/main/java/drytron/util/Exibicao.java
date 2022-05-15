package drytron.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dayon
 */
public class Exibicao {

    static Document doc;

    public static void GerarPdf(String dir) {
        try {
            doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(dir + "\\00_JogosRelatorio.pdf"));
            doc.open();
            getCabecalhoPdf("teste");
            getCorpoPdfJogos();
        } catch (Exception e) {
        } finally {
            doc.close();
            /*if(doc != null && doc.isOpen()){
            }*/
        }
    }

    public static void getCabecalhoPdf(String titulo) {
        try {
            Paragraph tituloPdf = new Paragraph();
            tituloPdf.setAlignment(Element.ALIGN_CENTER);
            tituloPdf.add(new Chunk(titulo, new Font(Font.FontFamily.HELVETICA, 24)));

            doc.add(tituloPdf);
            doc.add(new Paragraph(" "));

            Paragraph pgData = new Paragraph();
            pgData.setAlignment(Element.ALIGN_CENTER);
            pgData.add(new Chunk(LocalDate.now().format(DateTimeFormatter.ofPattern("d/MM/uuuu")), new Font(Font.FontFamily.HELVETICA, 10)));
            doc.add(pgData);
            doc.addAuthor("DRYTRON");

            //doc.add();
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

        } catch (DocumentException ex) {
        }
    }

    public static void getCorpoPdfJogos() throws DocumentException {
        PdfPTable pt = new PdfPTable(10);

        PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("NOME"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("GÊNERO"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("PLATAFORMA"));
        PdfPCell cell5 = new PdfPCell(new Paragraph("LANÇAMENTO"));
        PdfPCell cell6 = new PdfPCell(new Paragraph("DESENVOLVEDOR"));
        PdfPCell cell7 = new PdfPCell(new Paragraph("PUBLICADOR"));
        PdfPCell cell8 = new PdfPCell(new Paragraph("IDIOMA"));
        PdfPCell cell9 = new PdfPCell(new Paragraph("ESTOQUE"));
        PdfPCell cell10 = new PdfPCell(new Paragraph("PREÇO"));

        pt.addCell(cell1);
        pt.addCell(cell2);
        pt.addCell(cell3);
        pt.addCell(cell4);
        pt.addCell(cell5);
        pt.addCell(cell6);
        pt.addCell(cell7);
        pt.addCell(cell8);
        pt.addCell(cell9);
        pt.addCell(cell10);

        ArrayList<Jogos> list;

        JogosRepository jogo = new JogosRepository();
        list = new ArrayList<Jogos>(jogo.listaTodos());
        if (list == null) {
            System.out.println("LISTA VAZIA JOGOS");
        } else {
            System.out.println("Não vazia");
        }
        for (Jogos j : list) {
            cell1 = new PdfPCell(new Paragraph(j.getId()));
            cell2 = new PdfPCell(new Paragraph(j.getNome()));
            cell3 = new PdfPCell(new Paragraph(j.getGenero()));
            cell4 = new PdfPCell(new Paragraph(j.getPlataforma()));
            cell5 = new PdfPCell(new Paragraph(j.getLancamento().toString()));
            cell6 = new PdfPCell(new Paragraph(j.getDesenvolvedor()));
            cell7 = new PdfPCell(new Paragraph(j.getPublicador()));
            cell8 = new PdfPCell(new Paragraph(j.getIdioma()));
            cell9 = new PdfPCell(new Paragraph(j.getEstoque()));
            cell10 = new PdfPCell(new Paragraph(j.getPreco()));
            pt.addCell(cell1);
            pt.addCell(cell2);
            pt.addCell(cell3);
            pt.addCell(cell4);
            pt.addCell(cell5);
            pt.addCell(cell6);
            pt.addCell(cell7);
            pt.addCell(cell8);
            pt.addCell(cell9);
            pt.addCell(cell10);
        }
        doc.add(pt);

    }

    public static void getIcon() {

    }

    public static void GerarXlsx(String dir) throws IOException {
        String[] coluna = {"ID", "NOME", "GÊNERO", "PLATAFORMA", "LANÇAMENTO", "DESENVOLVEDOR", "PUBLICADOR", "IDIOMA", "ESTOQUE", "PREÇO"};
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("JogosXlsx");
        org.apache.poi.ss.usermodel.Font fontCabecalho = wb.createFont();

        fontCabecalho.setBold(true);
        fontCabecalho.setFontHeightInPoints((short) 20);
        fontCabecalho.setColor(IndexedColors.BLACK1.getIndex());

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(fontCabecalho);

        Row rowCabecalho = sheet.createRow(0);

        ArrayList<Jogos> list;

        JogosRepository jogo = new JogosRepository();
        list = new ArrayList<Jogos>(jogo.listaTodos());
        if (list == null) {
            System.out.println("LISTA VAZIA JOGOS");
        } else {
            System.out.println("Não vazia");
        }
        for (int i = 0; i < coluna.length; i++) {
            Cell cell = rowCabecalho.createCell(i);
            cell.setCellValue(coluna[i]);
            cell.setCellStyle(headerCellStyle);
        }
        Cell cell = rowCabecalho.createCell(coluna.length);
        cell.setCellValue("Data: "+LocalDate.now().format(DateTimeFormatter.ofPattern("d/MM/uuuu")));
        int num = 1;
        for (Jogos j : list) {
            Row row = sheet.createRow(num++);
            row.createCell(0).setCellValue(j.getId());
            row.createCell(1).setCellValue(j.getNome());
            row.createCell(2).setCellValue(j.getGenero());
            row.createCell(3).setCellValue(j.getPlataforma());
            row.createCell(4).setCellValue(j.getLancamento());
            row.createCell(5).setCellValue(j.getDesenvolvedor());
            row.createCell(6).setCellValue(j.getPublicador());
            row.createCell(7).setCellValue(j.getIdioma());
            row.createCell(8).setCellValue(j.getEstoque());
            row.createCell(9).setCellValue(j.getPreco());
        }
        for (int i = 0; i < coluna.length; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream fos = new FileOutputStream(dir + "\\00_JogosRelatorio.xlsx");
        wb.write(fos);
        fos.close();
        wb.close();
    }
}
