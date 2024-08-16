package books_api.books_api.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    // passing the received string to ISO_LOCAL_DATE in order to accept the inputs in db as YYYY-MM-DD and serialize as dd, MMMM YYYY

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{
        String dateString = jsonParser.getText();
        if(dateString.length() == 10){ // considering YYYY-MM-DD format
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            return date.atStartOfDay();
        }else{
            return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }
}
