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
import drytron.dao.ClientesRepository;
import drytron.dto.Clientes;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class PdfClientes {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static void gerar(String dir) {
        try {
            Document document = new Document();
            document.setPageSize(PageSize.LETTER.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(dir + "\\ClientesRelatorio.pdf"));
            document.open();
            addMetaDado(document);
            addContent(document);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            Mensagens.mensagemExcessao("Excessão", "Erro no arquivo", e);
        }
    }

    private static void addMetaDado(Document document) {
        document.addTitle("Drytron - Clientes");
        document.addSubject("Area: Clientes");
        document.addKeywords("Drytron, Clientes");
        document.addAuthor("Drytron - Sistema de gerenciamento de produtos e clientes");
        document.addCreator("Drytron - Sistema de gerenciamento de produtos e clientes");
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Area Clientes", catFont);
        anchor.setName("Area Clientes");

        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph prefacio = new Paragraph();
        addLinhaVazia(prefacio, 1);
        prefacio.add(new Paragraph("Clientes", catFont));
        addLinhaVazia(prefacio, 1);
        prefacio.add(new Paragraph(
                "Autor: " + System.getProperty("user.name") + ", " + Util.getDataHoraAgora() + ".",
                smallBold));
        addLinhaVazia(prefacio, 3);

        document.add(prefacio);

        Paragraph subPara = new Paragraph("Tabela Clientes \n\n", subFont);
        Section subCatPart = catPart.addSection(subPara);

        criarTabela(subCatPart);

        addLinhaVazia(subPara, 2);
        document.add(catPart);

    }

    private static void criarTabela(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(8);

        int fonte = 10;

        PdfPCell c1 = new PdfPCell(new Paragraph("ID", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("NOME", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("CPF", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("TELEFONE", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("E-MAIL", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("LOCALIDADE", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("BAIRRO", new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Paragraph("UF", new Font(Font.FontFamily.TIMES_ROMAN, fonte, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        ArrayList<Clientes> list;

        ClientesRepository clientes = new ClientesRepository();
        list = new ArrayList<>(clientes.listaTodos());

        int fonteDados = 12;
        for (int i = 0; i < list.size(); i++) {

            table.addCell(new Phrase(Element.ALIGN_LEFT, Long.toString(list.get(i).getId()),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getNome(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getCpf(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getTelefone(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getEmail(),
                    new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getEndCli().getLocalidade(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getEndCli().getBairro(),
                    new Font(Font.FontFamily.TIMES_ROMAN, fonteDados, Font.BOLD)));

            table.addCell(new Phrase(Element.ALIGN_LEFT, list.get(i).getEndCli().getUf(),
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
