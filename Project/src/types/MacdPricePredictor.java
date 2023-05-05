package types;

import java.util.ArrayList;
import java.util.List;
import types.EmaIndicator;

public class MacdPricePredictor {
    private final EmaIndicator shortTermEma;
    private final EmaIndicator longTermEma;
    private final EmaIndicator signalEma;

    public MacdPricePredictor(int shortTermPeriod, int longTermPeriod, int signalPeriod) {
        this.shortTermEma = new EmaIndicator(shortTermPeriod);
        this.longTermEma = new EmaIndicator(longTermPeriod);
        this.signalEma = new EmaIndicator(signalPeriod);
    }

    public double predict(List<Double> prices) {
        if (prices == null || prices.size() < longTermEma.getPeriod()) {
            throw new IllegalArgumentException("Not enough data points to make a prediction.");
        }

        double shortTermEmaValue = shortTermEma.calculate(prices);
        double longTermEmaValue = longTermEma.calculate(prices);

        double macd = shortTermEmaValue - longTermEmaValue;

        List<Double> macdHistory = new ArrayList<>();
        for (int i = 0; i < prices.size() - longTermEma.getPeriod(); i++) {
            double shortEma = shortTermEma.calculate(prices.subList(0, longTermEma.getPeriod() + i));
            double longEma = longTermEma.calculate(prices.subList(0, longTermEma.getPeriod() + i));
            macdHistory.add(shortEma - longEma);
        }

        double signal = signalEma.calculate(macdHistory);

        if (macd > signal) {
            return prices.get(prices.size() - 1) * 1.01;
        } else {
            return prices.get(prices.size() - 1) * 0.99;
        }
    }
}