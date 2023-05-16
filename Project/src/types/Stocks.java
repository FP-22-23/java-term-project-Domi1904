package types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import types.EmaIndicator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    public List<Stock> filterByUptrend(boolean uptrend) {
        List<Stock> filteredStocks = new ArrayList<>();
        for (Stock s : stocks) {
            if (s.getUptrend() == uptrend) {
                filteredStocks.add(s);
            }
        }
        return filteredStocks;
    }

    public Map<LocalDate, Integer> groupByDateAndCount() {
        Map<LocalDate, Integer> groupedStocks = new HashMap<>();
        for (Stock s : stocks) {
            LocalDate date = s.getDate();
            groupedStocks.put(date, groupedStocks.getOrDefault(date, 0) + 1);
        }
        return groupedStocks;
    }

    public Map<LocalDate, List<Stock>> groupByDate() {
        Map<LocalDate, List<Stock>> groupedStocks = new HashMap<>();
        for (Stock s : stocks) {
            LocalDate date = s.getDate();
            if (!groupedStocks.containsKey(date)) {
                groupedStocks.put(date, new ArrayList<>());
            }
            groupedStocks.get(date).add(s);
        }
        return groupedStocks;
    }

    public boolean existsVolumeAboveStream(Integer threshold) {
        return stocks.stream().anyMatch(s -> s.getVolume() > threshold);
    }

    public Double sumClosePriceStream() {
        return stocks.stream().mapToDouble(Stock::getClose).sum();
    }

    public List<Stock> filterByUptrendStream(boolean uptrend) {
        return stocks.stream().filter(s -> s.getUptrend() == uptrend).collect(Collectors.toList());
    }

    public Optional<Stock> maxClosePriceWithFilter(boolean uptrend) {
        return stocks.stream().filter(s -> s.getUptrend() == uptrend).max(Comparator.comparing(Stock::getClose));
    }

    public List<Stock> filterAndSortByClosePrice(boolean uptrend, boolean ascending) {
        return stocks.stream().filter(s -> s.getUptrend() == uptrend).sorted(ascending ? Comparator.comparing(Stock::getClose) : Comparator.comparing(Stock::getClose).reversed()).collect(Collectors.toList());
    }

    // Block II methods

    public Optional<Stock> minVolumeWithFilterStream(boolean uptrend) {
        return stocks.stream().filter(s -> s.getUptrend() == uptrend).min(Comparator.comparing(Stock::getVolume));
    }

    public Double averageClosePriceWithMappingCollector() {
        return stocks.stream().collect(Collectors.mapping(Stock::getClose, Collectors.averagingDouble(Double::doubleValue)));
    }

    public Map<LocalDate, Optional<Stock>> groupByDateAndMaxClosePrice() {
        return stocks.stream().collect(Collectors.groupingBy(Stock::getDate, Collectors.maxBy(Comparator.comparing(Stock::getClose))));
    }

    public Map<LocalDate, List<Stock>> groupByDateAndTopNClosePrices(int n) {
        return stocks.stream().collect(Collectors.groupingBy(Stock::getDate, Collectors.collectingAndThen(Collectors.toList(), list -> {
            list.sort(Comparator.comparing(Stock::getClose).reversed());
            return list.subList(0, Math.min(n, list.size()));
        })));
    }

    public Optional<LocalDate> findDateWithHighestAverageClosePrice() {
        return stocks.stream().collect(Collectors.groupingBy(Stock::getDate, Collectors.averagingDouble(Stock::getClose))).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stocks stocks1 = (Stocks) o;
        return Objects.equals(stocks, stocks1.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stocks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stocks{\n");
        for (Stock s : stocks) {
            sb.append("  ").append(s.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
