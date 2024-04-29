/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.util.StringConverter;

/**
 *
 * @author msi
 */
public class SqlDateConverter extends StringConverter<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String toString(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

    @Override
    public Date fromString(String string) {
        if (string == null || string.trim().isEmpty()) {
            return null;
        }
        try {
            return new java.sql.Date(dateFormat.parse(string).getTime());
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}

