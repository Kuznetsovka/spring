package org.example.geekbrains.lesson7.config;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MyDateFormatter implements Formatter<Date>{

    private static final String PROPERTY_DATE_FORMAT = "date.format";
    private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("dd - mm <-> yyyy");

    private Map<Locale, SimpleDateFormat> cache = new HashMap<>();
    private final MessageSource messageSource;

    public MyDateFormatter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return getFormat(locale).parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return getFormat(locale).format(object);
    }

    private SimpleDateFormat getFormat(Locale locale){
        SimpleDateFormat resultFormat = cache.get(locale);
        if(resultFormat != null){
            return resultFormat;
        }

        String pattern = messageSource.getMessage(PROPERTY_DATE_FORMAT, null, locale);
        resultFormat = pattern == null ? DEFAULT_FORMAT : new SimpleDateFormat(pattern);
        cache.put(locale, resultFormat);
        return resultFormat;
    }
}
