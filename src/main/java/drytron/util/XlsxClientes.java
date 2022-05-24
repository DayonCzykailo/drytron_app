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
import drytron.dao.ClientesRepository;
import drytron.dao.JogosRepository;
import drytron.dto.Clientes;
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
public class XlsxClientes {

    public static void GerarXlsx(String dir) throws IOException {
        String[] coluna = {"ID", "NOME", "CPF", "TELEFONE", "E-MAIL", "LOCALIDADE", "BAIRRO", "UF"};
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("ClientesXlsx");
        org.apache.poi.ss.usermodel.Font fontCabecalho = wb.createFont();

        fontCabecalho.setBold(true);
        fontCabecalho.setFontHeightInPoints((short) 20);
        fontCabecalho.setColor(IndexedColors.BLACK1.getIndex());

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(fontCabecalho);

        Row rowCabecalho = sheet.createRow(0);

        ArrayList<Clientes> list;

        ClientesRepository clientes = new ClientesRepository();
        list = new ArrayList<Clientes>(clientes.listaTodos());

        for (int i = 0; i < coluna.length; i++) {
            Cell cell = rowCabecalho.createCell(i);
            cell.setCellValue(coluna[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        Cell cell = rowCabecalho.createCell(coluna.length);
        cell.setCellValue("Data: " + Util.getDataHoraAgora());
        int num = 1;
        
        for (Clientes c : list) {
            Row row = sheet.createRow(num++);
            row.createCell(0).setCellValue(c.getId());
            row.createCell(1).setCellValue(c.getNome());
            row.createCell(2).setCellValue(c.getCpf());
            row.createCell(3).setCellValue(c.getTelefone());
            row.createCell(4).setCellValue(c.getEmail());
            row.createCell(5).setCellValue(c.getEndCli().getLocalidade());
            row.createCell(6).setCellValue(c.getEndCli().getBairro());
            row.createCell(7).setCellValue(c.getEndCli().getUf());
        }
        
        for (int i = 0; i < coluna.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        FileOutputStream fos = new FileOutputStream(dir + "\\ClientesRelatorio.xlsx");
        wb.write(fos);
        fos.close();
        wb.close();
    }
}
