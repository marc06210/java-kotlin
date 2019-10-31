package com.afklm.tecc.article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Extensions {

    private static DateTimeFormatter englishDateFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .appendLiteral(" ")
            .appendPattern("yyyy")
            .toFormatter(Locale.ENGLISH);
    
    public static String format(LocalDateTime ldt) {
        return ldt.format(englishDateFormatter);
    }

    public static String toSlug(String s) {
        StringJoiner sj = new StringJoiner("-");
        Stream.of(s.toLowerCase()
                    .replaceAll("\n", " ")
                    .replaceAll("\\+", "-")
                    .split(" "))
                .forEach(sj::add);
        return sj.toString().toLowerCase();
    }

}
