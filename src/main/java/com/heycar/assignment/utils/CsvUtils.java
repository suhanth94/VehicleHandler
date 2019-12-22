package com.heycar.assignment.utils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * This class is responsible for handling CSV Parsing and converting them to
 * POJO data objects.
 */
@Component
public class CsvUtils {
    private static final CsvMapper mapper = new CsvMapper();
    public static <T> List<T> read(Class<T> aClass, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(aClass).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(aClass).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
}

