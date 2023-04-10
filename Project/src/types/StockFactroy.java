package types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class StockFactroy {
    public static Stocks readStockFile(String fileName){

        Stocks res = null;

        try{
            Stream<Stock> st = Files.lines(Path.of(fileName)).skip(1).map(StockFactroy::parseStock);
            res = new Stocks(st);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return res;

    }

    public static Stock parseStock(String line){
        return new Stock(line);
    }
}
