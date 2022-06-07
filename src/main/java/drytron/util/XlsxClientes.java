package drytron.util;

import drytron.repository.ClientesRepository;
import drytron.dto.Clientes;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public abstract class XlsxClientes {

    public static void GerarXlsx(String dir) throws IOException {
        String[] coluna = {"ID", "NOME", "CPF", "TELEFONE", "E-MAIL", "LOCALIDADE", "BAIRRO", "UF"};
        try (Workbook wb = new XSSFWorkbook()) {
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
            list = new ArrayList<>(clientes.listaTodos());
            
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
            
            try (FileOutputStream fos = new FileOutputStream(dir + "\\ClientesRelatorio.xlsx")) {
                wb.write(fos);
            }
        }
    }
}
