package com.csv.parser.readers;

import com.csv.parser.entity.Posting;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadPostings {
    public List<Posting> getCompletePostingList() {
        String filePath = "d:\\postings.csv";
        List<Posting> listPostings = null;
        try {
            listPostings = ParsePosting(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listPostings;
    }

    private static List<Posting> ParsePosting(String filePath) throws IOException {
        List<Posting> listPostings = new ArrayList<Posting>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(";");
            ArrayList<String> columnList = new ArrayList<>();
            for (int i = 0; i < splitedText.length; i++) {
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i].trim());
                }
            }
            Posting posting = new Posting();
            try {
                posting.setMatDoc(Long.parseLong(columnList.get(0)));
            } catch (NumberFormatException e) {
                continue;
            }
            posting.setItem(columnList.get(1));
            posting.setDocDate(columnList.get(2));
            posting.setPstngDate(columnList.get(3));
            posting.setMaterialDescription(columnList.get(4));
            posting.setQuantity(columnList.get(5));
            posting.setbUn(columnList.get(6));
            posting.setAmountLC(columnList.get(7));
            posting.setCrcy(columnList.get(8));
            posting.setUserName(columnList.get(9));
            listPostings.add(posting);
        }
        return listPostings;
    }

    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
