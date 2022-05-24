package drytron.util;

/**
 *
 * @author dayon
 */
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;

public class PdfJogos {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static void gerar(String dir) {
        try {
            Document document = new Document();
            document.setPageSize(PageSize.LETTER.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(dir + "\\JogosRelatorio.pdf"));
            document.open();
            addMetaDado(document);
            addContent(document);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addMetaDado(Document document) {
        document.addTitle("Drytron - Jogos");
        document.addSubject("Area: Produtos");
        document.addKeywords("Drytron, Produtos, Jogos");
        document.addAuthor("Drytron - Sistema de gerenciamento de produtos e clientes");
        document.addCreator("Drytron - Sistema de gerenciamento de produtos e clientes");
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Produtos Jogos", catFont);
        anchor.setName("Produtos Jogos");

        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph prefacio = new Paragraph();
        addLinhaVazia(prefacio, 1);
        prefacio.add(new Paragraph("Jogos", catFont));
        addLinhaVazia(prefacio, 1);
        prefacio.add(new Paragraph(
                "Autor: " + System.getProperty("user.name") + ", " + Util.getDataHoraAgora() + ".",
                smallBold));
        addLinhaVazia(prefacio, 3);

        document.add(prefacio);

        Paragraph subPara = new Paragraph("Tabela Jogos \n\n", subFont);
        Section subCatPart = catPart.addSection(subPara);

        criarTabela(subCatPart);

        addLinhaVazia(subPara, 2);
        document.add(catPart);

    }

    private static void criarTabela(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(10);

        int fonte = 10;

        PdfPCell c1 = new PdfPCell(new Paragraph("ID", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("NOME", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("GÊNERO", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("PLATAFORMA", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("LANÇAMENTO", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("DESENVOLVEDOR", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("PUBLICADOR", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("IDIOMA", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("ESTOQUE", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("PREÇO", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        ArrayList<Jogos> list;

        JogosRepository jogo = new JogosRepository();
        list = new ArrayList<>(jogo.listaTodos());

        int fonteDados = 12;
        for (int i = 0; i < list.size(); i++) {

            table.addCell(new Phrase(Element.ALIGN_LEFT, Integer.toString(list.get(i).getId()),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getNome(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getGenero(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getPlataforma(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getDesenvolvedor(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getPublicador(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getIdioma(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, Integer.toString(list.get(i).getEstoque()),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, Double.toString(list.get(i).getPreco()),
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
