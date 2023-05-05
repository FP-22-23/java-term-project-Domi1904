package types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import types.EmaIndicator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stocks{
    private Set<Stock> stocks;
    private List<Double> allPrices = new ArrayList<>();

    public Stocks(){
        stocks = new HashSet<>();
    }

    public Stocks(Stream<Stock> s){
        stocks = s.collect(Collectors.toSet());
        for(Stock stockPrice : stocks) {
            allPrices.add(stockPrice.getClose());
        }
    }

    public void addStock(Stock s){
        stocks.add(s);
    }

    public void addStocks(Collection<Stock> data){
        stocks.addAll(data);
    }

    public void removeStock(Stock s){
        stocks.remove(s);
    }

    public Double calcEMA(int period){
        EmaIndicator emaPeriod = new EmaIndicator(period);
        return emaPeriod.calculate(allPrices);
    }

    public Double getPrediction(int period1, int period2, int period3){
        MacdPricePredictor predictor = new MacdPricePredictor(period1,period2,period3);
        return predictor.predict(allPrices);
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
