package com.example.backend.dbmigration;

import com.example.backend.domain.tarot.TarotCard;
import com.example.backend.dto.tarot.TarotCardType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Component
@AllArgsConstructor
public class MigrationHelper {

    private static final String TYPE_INSERT_ROW = "INSERT INTO tarot_card_type";
    private static final String CARD_INSERT_ROW = "INSERT INTO tarot_card";

    private final DbMigrationProps dbMigrationProps;


    public void recreateJsonDbInitFileFromSqlFlyWayFile() throws IOException {
        List<String> lines = Files.readAllLines(dbMigrationProps.getInitDbSqlFile().getFile().toPath());
        boolean isCardTypeBlockProcessing = false;
        boolean isCardsBlockProcessing = false;
        Map<String, TarotCardType> cardTypes = new HashMap<>();
        List<TarotCard> cards = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (TYPE_INSERT_ROW.equals(line)) {
                isCardTypeBlockProcessing = true;
                continue;
            }

            if (isCardTypeBlockProcessing) {
                StringBuilder typesData = new StringBuilder();
                for (int j = i; ; j++) {
                    line = lines.get(j);
                    line = line.replaceAll("VALUES", "").trim();
                    typesData.append(line);
                    if (line.endsWith("),") || line.endsWith(";")) {
                        String[] typeArgs = typesData.substring(2, typesData.length() - 1)
                                .split("',");
                        String id = typeArgs[0].replaceFirst("'", "").trim();
                        String name = typeArgs[1].replaceFirst("'", "").trim();
                        String desc = typeArgs[2].substring(1, typeArgs[2].length() - 1).trim();
                        cardTypes.put(id, new TarotCardType(name, desc));
                        typesData = new StringBuilder();
                        if (line.endsWith(";")) {
                            break;
                        }
                    }
                }
                isCardTypeBlockProcessing = false;
            }

            if (CARD_INSERT_ROW.equals(line)) {
                isCardsBlockProcessing = true;
                continue;
            }

            if (isCardsBlockProcessing) {
                StringBuilder cardData = new StringBuilder();
                for (int j = i; ; j++) {
                    line = lines.get(j);
                    line = line.replaceAll("VALUES", "").trim();
                    cardData.append(line);
                    if (line.endsWith("),") || line.endsWith(";")) {
                        String[] cardArgs = cardData.substring(2, cardData.length() - 3)
                                .split("',");
                        String id = cardArgs[0].replaceFirst("'", "").trim();
                        String typeId = cardArgs[1].replaceFirst("'", "").trim();
                        String name = cardArgs[2].replaceFirst("'", "").trim();
                        String desc = cardArgs[3].replaceFirst("'", "").trim();
                        String rDesc = cardArgs[4].replaceFirst("'", "").trim();
                        String advice = cardArgs[5].replaceFirst("'", "").trim();
                        cards.add(new TarotCard(UUID.randomUUID(), cardTypes.get(typeId), name, desc, rDesc, advice, null));
                        cardData = new StringBuilder();
                        if (line.endsWith(";")) {
                            break;
                        }
                    }
                }
                isCardsBlockProcessing = false;
            }
        }


        ObjectMapper objectMapper = new ObjectMapper();
        //todo add check that output file exists
        objectMapper.writeValue(dbMigrationProps.getInitDbJsonFile().getFile(), cards);
    }

}
