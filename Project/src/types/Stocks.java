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

    public Double calcProfit(Double moneyInvested){
        double result = 0;
        for(Stock s : stocks){
            result += ((s.getChangeInPercentige())/100) * moneyInvested;
        }
        return result;
    }

    public String calcTrend(){
        Integer result = 0;
        String output;
        for(Stock s : stocks){
            if(s.getUptrend()){
                result += 1;
            }
            else{
                result -= 1;
            }
        }

        if(result > 0){
            output = "The trend for this stock is positive";
        }
        else{
            output = "The trend for this stock is negative";
        }

        return output;
    }

}
