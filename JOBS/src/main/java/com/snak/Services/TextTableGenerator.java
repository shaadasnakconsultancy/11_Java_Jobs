package com.snak.Services;

import java.util.ArrayList;
import java.util.List;

import com.snak.dto.excelJobTrackerDTO;

public class TextTableGenerator {

    // Column widths
    private static final int COL_BATCH_ID = 10;
    private static final int COL_FILE_NAME = 20;
    private static final int COL_FULL_PATH = 40;
    private static final int COL_PROCESSED_AT = 20;
    private static final int COL_SHEET = 15;
    private static final int COL_INSERTED = 10;
    private static final int COL_DELETED = 10;
    private static final int COL_ERRORS = 30;

    public String convertToTextTable(List<excelJobTrackerDTO> list) {

        StringBuilder sb = new StringBuilder();

        // Header
        sb.append(String.format(
                "%-" + COL_BATCH_ID + "s %-" + COL_FILE_NAME + "s %-" + COL_FULL_PATH + "s %-" +
                COL_PROCESSED_AT + "s %-" + COL_SHEET + "s %-" + COL_INSERTED + "s %-" +
                COL_DELETED + "s %-" + COL_ERRORS + "s%n",
                "BatchID", "FileName", "FullPath", "ProcessedAt",
                "Sheet Name", "Inserted", "Deleted", "Errors"
        ));

        // Separator
        sb.append(new String(new char[160]).replace("\0", "-")).append("\n");

        // Data rows
        for (excelJobTrackerDTO obj : list) {

            List<String> batchId = wrapText(String.valueOf(obj.getBatchID()), COL_BATCH_ID);
            List<String> fileName = wrapText(obj.getFileName(), COL_FILE_NAME);
            List<String> fullPath = wrapText(obj.getFullPath(), COL_FULL_PATH);
            List<String> processedAt = wrapText(String.valueOf(obj.getProcessedAt()), COL_PROCESSED_AT);
            List<String> sheet = wrapText(obj.getSheetName(), COL_SHEET);
            List<String> inserted = wrapText(String.valueOf(obj.getInsertedRows()), COL_INSERTED);
            List<String> deleted = wrapText(String.valueOf(obj.getDeletedRows()), COL_DELETED);
            List<String> errors = wrapText(obj.getErrorMessages(), COL_ERRORS);

            int maxLines = getMaxLines(batchId, fileName, fullPath, processedAt, sheet, inserted, deleted, errors);

            for (int i = 0; i < maxLines; i++) {
                sb.append(String.format(
                        "%-" + COL_BATCH_ID + "s %-" + COL_FILE_NAME + "s %-" + COL_FULL_PATH + "s %-" +
                        COL_PROCESSED_AT + "s %-" + COL_SHEET + "s %-" + COL_INSERTED + "s %-" +
                        COL_DELETED + "s %-" + COL_ERRORS + "s%n",

                        getLine(batchId, i),
                        getLine(fileName, i),
                        getLine(fullPath, i),
                        getLine(processedAt, i),
                        getLine(sheet, i),
                        getLine(inserted, i),
                        getLine(deleted, i),
                        getLine(errors, i)
                ));
            }
        }

        return sb.toString();
    }

    // 🔹 Wrap text into fixed width
    private List<String> wrapText(String text, int width) {
        List<String> lines = new ArrayList<>();

        if (text == null) text = "";

        while (text.length() > width) {
            lines.add(text.substring(0, width));
            text = text.substring(width);
        }
        lines.add(text);

        return lines;
    }

    // 🔹 Get max lines among all columns
    private int getMaxLines(List<String>... lists) {
        int max = 0;
        for (List<String> list : lists) {
            if (list.size() > max) {
                max = list.size();
            }
        }
        return max;
    }

    // 🔹 Get line safely
    private String getLine(List<String> list, int index) {
        if (index < list.size()) {
            return list.get(index);
        }
        return "";
    }
}