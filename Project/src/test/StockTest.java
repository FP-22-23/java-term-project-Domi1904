package test;

import types.StockFactroy;
import types.Stocks;

public class StockTest {

    public static void main(String[] args) {
        //Input for Price perdictor
        int shortTermPeriod = 12;
        int longTermPeriod = 20;
        int signalPeriod = 9;

        Stocks s = StockFactroy.readStockFile("data/AMZNtrain.csv");
        System.out.println(s.getSizeOfStocks());
        System.out.println(s.calcProfit(1000.00));
        System.out.println(s.calcTrend());

        System.out.println(s.calcEMA(14));
        System.out.println(s.toString());
        System.out.println(s.getPrediction(shortTermPeriod, longTermPeriod, signalPeriod));
    }
}
