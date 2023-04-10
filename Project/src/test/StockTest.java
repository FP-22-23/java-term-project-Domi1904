package test;

import types.StockFactroy;
import types.Stocks;

public class StockTest {

    public static void main(String[] args) {
        Stocks s = StockFactroy.readStockFile("data/AMZNtest.csv");
        System.out.println(s.getSizeOfStocks());
    }
}
