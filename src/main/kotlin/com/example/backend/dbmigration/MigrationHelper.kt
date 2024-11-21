package com.example.backend.dbmigration

import com.example.backend.documents.TarotCard
import com.example.backend.dto.tarot.TarotCardType
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.file.Files
import java.util.*

@Component
class MigrationHelper(
    private val dbMigrationProps: DbMigrationProps,
) {

    val TYPE_INSERT_ROW: String = "INSERT INTO tarot_card_type"
    val CARD_INSERT_ROW: String = "INSERT INTO tarot_card"

    @Throws(IOException::class)
    fun recreateJsonDbInitFileFromSqlFlyWayFile() {
        val lines = Files.readAllLines(dbMigrationProps.initDbSqlFile!!.file.toPath())
        var isCardTypeBlockProcessing = false
        var isCardsBlockProcessing = false
        val cardTypes: MutableMap<String, TarotCardType> = HashMap()
        val cards: MutableList<TarotCard> = ArrayList()

        for (i in lines.indices) {
            var line = lines[i]
            if (TYPE_INSERT_ROW == line) {
                isCardTypeBlockProcessing = true
                continue
            }

            if (isCardTypeBlockProcessing) {
                var typesData = StringBuilder()
                var j = i
                while (true) {
                    line = lines[j]
                    line = line.replace("VALUES".toRegex(), "").trim { it <= ' ' }
                    typesData.append(line)
                    if (line.endsWith("),") || line.endsWith(";")) {
                        val typeArgs = typesData.substring(2, typesData.length - 1)
                            .split("',".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val id = typeArgs[0].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val name = typeArgs[1].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val desc = typeArgs[2].substring(1, typeArgs[2].length - 1).trim { it <= ' ' }
                        cardTypes[id] = TarotCardType(name, desc)
                        typesData = StringBuilder()
                        if (line.endsWith(";")) {
                            break
                        }
                    }
                    j++
                }
                isCardTypeBlockProcessing = false
            }

            if (CARD_INSERT_ROW == line) {
                isCardsBlockProcessing = true
                continue
            }

            if (isCardsBlockProcessing) {
                var cardData = StringBuilder()
                var j = i
                while (true) {
                    line = lines[j]
                    line = line.replace("VALUES".toRegex(), "").trim { it <= ' ' }
                    cardData.append(line)
                    if (line.endsWith("),") || line.endsWith(";")) {
                        val cardArgs = cardData.substring(2, cardData.length - 3)
                            .split("',".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val id = cardArgs[0].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val typeId = cardArgs[1].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val name = cardArgs[2].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val desc = cardArgs[3].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val rDesc = cardArgs[4].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        val advice = cardArgs[5].replaceFirst("'".toRegex(), "").trim { it <= ' ' }
                        cards.add(TarotCard(UUID.randomUUID(), cardTypes[typeId], name, desc, rDesc, advice, null))
                        cardData = StringBuilder()
                        if (line.endsWith(";")) {
                            break
                        }
                    }
                    j++
                }
                isCardsBlockProcessing = false
            }
        }


        val objectMapper = ObjectMapper()
        //todo add check that output file exists
        objectMapper.writeValue(dbMigrationProps.initDbJsonFile!!.file, cards)
    }
}