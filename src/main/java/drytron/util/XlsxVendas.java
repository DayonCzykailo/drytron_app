package drytron.util;

import drytron.dao.VendasRepository;
import drytron.dto.Vendas;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
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
public abstract class XlsxVendas {

    public static void GerarXlsx(String dir) throws IOException, ParseException {
        String[] coluna = {"ID", "NOME", "CPF", "TELEFONE", "E-MAIL", "LOCALIDADE", "BAIRRO", "UF"};
        try ( Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("VendasXlsx");
            org.apache.poi.ss.usermodel.Font fontCabecalho = wb.createFont();

            fontCabecalho.setBold(true);
            fontCabecalho.setFontHeightInPoints((short) 20);
            fontCabecalho.setColor(IndexedColors.BLACK1.getIndex());

            CellStyle headerCellStyle = wb.createCellStyle();
            headerCellStyle.setFont(fontCabecalho);

            Row rowCabecalho = sheet.createRow(0);

            ArrayList<Vendas> list;

            VendasRepository vendas = new VendasRepository();
            list = new ArrayList<>(vendas.listaTodos());

            for (int i = 0; i < coluna.length; i++) {
                Cell cell = rowCabecalho.createCell(i);
                cell.setCellValue(coluna[i]);
                cell.setCellStyle(headerCellStyle);
            }

            Cell cell = rowCabecalho.createCell(coluna.length);
            cell.setCellValue("Data: " + Util.getDataHoraAgora());
            int num = 1;
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);

            for (Vendas v : list) {
                Row row = sheet.createRow(num++);
                row.createCell(0).setCellValue(v.getId());
                row.createCell(1).setCellValue(v.getVendedor().getNome());
                row.createCell(2).setCellValue(v.getComprador().getNome());
                row.createCell(3).setCellValue(v.getProduto().getNome());
                row.createCell(4).setCellValue(String.valueOf(v.getQuantidade()));
                row.createCell(5).setCellValue(LocalDateTime.parse(v.getDataCompra().toString()).format(DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm:ss")));
                row.createCell(6).setCellValue(v.getPercDesconto() + "%");
                row.createCell(7).setCellValue("R$ " + String.format("%.2f",v.getValorFinal()));
            }

            for (int i = 0; i < coluna.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(dir + "\\VendasRelatorio.xlsx")) {
                wb.write(fos);
            }
        }
    }
}
