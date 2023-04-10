package types;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stocks{
    private Set<Stock> stocks;

    public Stocks(){
        stocks = new HashSet<>();
    }

    public Stocks(Stream<Stock> s){
        stocks = s.collect(Collectors.toSet());
    }

    public Integer getSizeOfStocks(){
        return stocks.size();
    }

    public Double calcProfit(){
        for(s in stocks){
            System.out.println("test");
        }
    }

}
