package drytron.util;

/**
 *
 * @author dayon
 */
import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import drytron.dao.VendasRepository;
import drytron.dto.Vendas;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public abstract class PdfVendas {

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static void gerar(String dir) throws ParseException {
        try {
            Document document = new Document();
            document.setPageSize(PageSize.LETTER.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(dir + "\\VendasRelatorio.pdf"));
            document.open();
            addMetaDado(document);
            addContent(document);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
        }
    }

    private static void addMetaDado(Document document) {
        document.addTitle("Drytron - Vendas");
        document.addSubject("Area: Vendas");
        document.addKeywords("Drytron, Vendas");
        document.addAuthor("Drytron - Sistema de gerenciamento de produtos e Vendas");
        document.addCreator("Drytron - Sistema de gerenciamento de produtos e Vendas");
    }

    private static void addContent(Document document) throws DocumentException, ParseException {
        Anchor anchor = new Anchor("Area Vendas", catFont);
        anchor.setName("Area Vendas");

        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph prefacio = new Paragraph();
        addLinhaVazia(prefacio, 1);
        prefacio.add(new Paragraph("Vendas", catFont));
        addLinhaVazia(prefacio, 1);
        prefacio.add(new Paragraph(
                "Autor: " + System.getProperty("user.name") + ", " + Util.getDataHoraAgora() + ".",
                smallBold));
        addLinhaVazia(prefacio, 3);

        document.add(prefacio);

        Paragraph subPara = new Paragraph("Tabela Vendas \n\n", subFont);
        Section subCatPart = catPart.addSection(subPara);

        criarTabela(subCatPart);

        addLinhaVazia(subPara, 2);
        document.add(catPart);

    }

    private static void criarTabela(Section subCatPart)
            throws BadElementException, ParseException {
        PdfPTable table = new PdfPTable(8);

        int fonte = 6;

        PdfPCell c1 = new PdfPCell(new Paragraph("ID", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("VENDEDOR", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("COMPRADOR", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("PRODUTO", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("QUANTIDADE", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("DATA", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("DESCONTO %", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("VALOR FINAL", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        ArrayList<Vendas> list;

        VendasRepository clientes = new VendasRepository();
        list = new ArrayList<>(clientes.listaTodos());

        int fonteDados = 8;
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        for (int i = 0; i < list.size(); i++) {

            table.addCell(new Phrase(Element.ALIGN_LEFT, Long.toString(list.get(i).getId()),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getVendedor().getNome(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getComprador().getNome(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getProduto().getNome(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, String.valueOf(list.get(i).getQuantidade()),
                    new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, LocalDateTime.parse(list.get(i).getDataCompra().toString()).format(DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm:ss")),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, String.valueOf(list.get(i).getPercDesconto()) + "%",
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, "R$ " +String.format("%.2f",list.get(i).getValorFinal()),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));
        }
        subCatPart.add(table);
    }

    private static void addLinhaVazia(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
