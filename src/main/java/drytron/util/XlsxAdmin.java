package drytron.util;

import drytron.dao.FuncionariosRepository;
import drytron.dto.Funcionarios;
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
public abstract class XlsxAdmin {

    public static void GerarXlsx(String dir) throws IOException {
        String[] coluna = {"ID", "NOME", "CPF", "CARGO", "E-MAIL", "LOCALIDADE", "BAIRRO", "UF"};
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("AdministradorXlsx");
        org.apache.poi.ss.usermodel.Font fontCabecalho = wb.createFont();

        fontCabecalho.setBold(true);
        fontCabecalho.setFontHeightInPoints((short) 20);
        fontCabecalho.setColor(IndexedColors.BLACK1.getIndex());

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(fontCabecalho);

        Row rowCabecalho = sheet.createRow(0);

        ArrayList<Funcionarios> list;

        FuncionariosRepository fr = new FuncionariosRepository();
        list = new ArrayList<>(fr.listaTodos());

        for (int i = 0; i < coluna.length; i++) {
            Cell cell = rowCabecalho.createCell(i);
            cell.setCellValue(coluna[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        Cell cell = rowCabecalho.createCell(coluna.length);
        cell.setCellValue("Data: " + Util.getDataHoraAgora());
        int num = 1;
        
        for (Funcionarios f : list) {
            Row row = sheet.createRow(num++);
            row.createCell(0).setCellValue(f.getId());
            row.createCell(1).setCellValue(f.getNome());
            row.createCell(2).setCellValue(f.getCpf());
            row.createCell(3).setCellValue(Dicionario.getCargo(f.getCargo()));
            row.createCell(4).setCellValue(f.getEmail());
            row.createCell(5).setCellValue(f.getEndFun().getLocalidade());
            row.createCell(6).setCellValue(f.getEndFun().getBairro());
            row.createCell(7).setCellValue(f.getEndFun().getUf());
        }
        
        for (int i = 0; i < coluna.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        FileOutputStream fos = new FileOutputStream(dir + "\\AdminRelatorio.xlsx");
        wb.write(fos);
        fos.close();
        wb.close();
    }
}
