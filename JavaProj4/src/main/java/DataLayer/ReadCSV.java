package DataLayer;

import BussinessLayer.BaseProduct;
import BussinessLayer.MenuItem;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class ReadCSV {
    public List<MenuItem> read(){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/products.csv"))) {
            return reader.lines()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(",")))
                    .map(line2-> new BaseProduct(line2.get(0),Double.parseDouble(line2.get(1)),Integer.parseInt(line2.get(2)),Integer.parseInt(line2.get(3)),Integer.parseInt(line2.get(4)),Integer.parseInt(line2.get(5)),Double.parseDouble(line2.get(6))))
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
