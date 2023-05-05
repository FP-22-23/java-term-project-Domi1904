package types;

import java.util.List;

public class EmaIndicator {
    private final int period;
    private final double smoothingFactor;

    public EmaIndicator(int period) {
        this.period = period;
        this.smoothingFactor = 2.0 / (period + 1);
    }

    public double calculate(List<Double> prices) {
        if (prices == null || prices.size() < period) {
            throw new IllegalArgumentException("Not enough data to calculate the EMA");
        }

        double ema = calculateSma(prices.subList(0, period));

        for (int i = period; i < prices.size(); i++) {
            double price = prices.get(i);
            ema = calculateEma(ema, price);
        }

        return ema;
    }

    public int getPeriod(){
        return this.period;
    }

    private double calculateSma(List<Double> prices) {
        double sum = 0;

        for (double price : prices) {
            sum += price;
        }

        return sum / period;
    }

    private double calculateEma(double previousEma, double price) {
        return (price - previousEma) * smoothingFactor + previousEma;
    }
}