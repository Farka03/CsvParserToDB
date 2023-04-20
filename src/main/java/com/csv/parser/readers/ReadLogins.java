package com.csv.parser.readers;

import com.csv.parser.entity.Login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadLogins {
    public List<Login> getCompleteLoginList() {
        String filePath = "d:\\logins.csv";
        List<Login> listOfLogins = null;
        try {
            listOfLogins = ParseLogin(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfLogins;
    }

    private static List<Login> ParseLogin(String filePath) throws IOException {
        List<Login> listOfLogins = new ArrayList<Login>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i].trim());
                }
            }
            Login login = new Login();
            login.setApplication(columnList.get(0));
            login.setAppAccountName(columnList.get(1));
            login.setActive(Boolean.parseBoolean(columnList.get(2)));
            login.setJobTitle(columnList.get(3));
            login.setDepartment(columnList.get(4));
            listOfLogins.add(login);
        }
        return listOfLogins;
    }
    public static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
