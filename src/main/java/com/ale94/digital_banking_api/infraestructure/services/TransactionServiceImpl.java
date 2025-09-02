package com.ale94.digital_banking_api.infraestructure.services;

import java.io.IOException;
import java.util.List;
import java.awt.Color;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ale94.digital_banking_api.api.models.responses.TransactionResponse;
import com.ale94.digital_banking_api.domain.entities.TransactionEntity;
import com.ale94.digital_banking_api.domain.repositories.AccountRepository;
import com.ale94.digital_banking_api.domain.repositories.TransactionRepository;
import com.ale94.digital_banking_api.infraestructure.abstract_services.TransactionService;
import com.ale94.digital_banking_api.util.exceptions.IdNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> getAllTransactions(String accountNumber) {
        var account = this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IdNotFoundException("account"));
        var transactions = account.getTransactions();
        return transactions.stream().map(this::entityToResponse).toList();
    }

    @Override
    public byte[] generatePDF(String operationNumber) {
        var transaction = this.transactionRepository.findByOperationNumber(operationNumber)
                .orElseThrow(() -> new IdNotFoundException("transaction"));
        var account = transaction.getAccount();
        var userFrom = account.getUser();
        var destinationName = this.accountRepository
                .findByAccountNumber(transaction.getDestinationAccount())
                .orElseThrow(() -> new IdNotFoundException("account"))
                .getUser()
                .getName();

        try (PDDocument document = new PDDocument(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PDPage page = new PDPage(new PDRectangle(270, 600)); // Tamaño ticket
            document.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {

                float startY = page.getMediaBox().getHeight() - 30;
                float lineSpacing = 22;
                float marginX = 20;
                float maxWidth = page.getMediaBox().getWidth() - 2 * marginX;

                // Encabezado azul
                cs.setNonStrokingColor(new Color(30, 144, 255));
                cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
                cs.fill();

                // Título centrado
                cs.beginText();
                cs.setNonStrokingColor(Color.WHITE);
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Comprobante de Transferencia") / 1000
                        * 14;
                cs.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2,
                        page.getMediaBox().getHeight() - 28);
                cs.showText("Comprobante de Transferencia");
                cs.endText();

                startY -= 40;

                // Monto
                startY = writeWrappedText(cs, "Monto: $" + transaction.getAmount(), marginX, startY, 12, maxWidth,
                        lineSpacing, page);

                // Descripción
                if (transaction.getDescription() != null && !transaction.getDescription().isEmpty()) {
                    startY = writeWrappedText(cs, "Descripción: " + transaction.getDescription(), marginX, startY, 12,
                            maxWidth, lineSpacing, page);
                }

                // Desde (origen)
                startY = writeWrappedText(cs, "Cuenta origen: " + account.getAccountNumber(), marginX, startY, 12,
                        maxWidth, lineSpacing, page);
                startY = writeWrappedText(cs, "Titular: " + userFrom.getName(), marginX, startY, 12, maxWidth,
                        lineSpacing, page);

                // Hacia (destino)
                startY = writeWrappedText(cs, "Cuenta destino: " + transaction.getDestinationAccount(), marginX, startY,
                        12, maxWidth, lineSpacing, page);
                startY = writeWrappedText(cs, "Titular: " + destinationName, marginX, startY, 12, maxWidth, lineSpacing,
                        page);

                // Fecha
                startY = writeWrappedText(cs, "Fecha: " + transaction.getDate(), marginX, startY, 12, maxWidth,
                        lineSpacing, page);

                // Número de operación
                writeWrappedText(cs, "N° Operación: " + transaction.getOperationNumber(), marginX, startY, 12, maxWidth,
                        lineSpacing, page);
            }

            document.save(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    /**
     * Escribe texto ajustando tamaño si es largo y generando varias líneas si
     * excede el ancho
     */
    private float writeWrappedText(PDPageContentStream cs, String text, float x, float y, int fontSize,
            float maxWidth, float lineSpacing, PDPage page) throws IOException {

        PDType1Font font = PDType1Font.HELVETICA;
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String tempLine = line + (line.length() == 0 ? "" : " ") + words[i];
            float textWidth = font.getStringWidth(tempLine) / 1000 * fontSize;
            if (textWidth > maxWidth) {
                // Escribir línea actual
                cs.beginText();
                cs.setFont(font, fontSize);
                cs.setNonStrokingColor(Color.BLACK);
                cs.newLineAtOffset(x, y);
                cs.showText(line.toString());
                cs.endText();
                y -= lineSpacing;
                line = new StringBuilder(words[i]);
            } else {
                line = new StringBuilder(tempLine);
            }
        }
        // Escribir última línea
        if (line.length() > 0) {
            cs.beginText();
            cs.setFont(font, fontSize);
            cs.setNonStrokingColor(Color.BLACK);
            cs.newLineAtOffset(x, y);
            cs.showText(line.toString());
            cs.endText();
            y -= lineSpacing;
        }
        return y;
    }

    private TransactionResponse entityToResponse(TransactionEntity entity) {
        var response = new TransactionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
