package drytron.util;

import drytron.repository.JogosRepository;
import drytron.dto.Jogos;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public abstract class XlsxJogos {

    public static void GerarXlsx(String dir) throws IOException {
        String[] coluna = {"ID", "NOME", "GÊNERO", "PLATAFORMA", "LANÇAMENTO", "DESENVOLVEDOR", "PUBLICADOR", "IDIOMA", "ESTOQUE", "PREÇO"};
        try (Workbook wb = new XSSFWorkbook()) {
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
            list = new ArrayList<>(jogo.listaTodos());

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
            try (FileOutputStream fos = new FileOutputStream(dir + "\\00_JogosRelatorio.xlsx")) {
                wb.write(fos);
            }
        }
    }
}
