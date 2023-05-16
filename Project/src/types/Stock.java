package types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Stock implements Comparable<Stock> {

    private LocalDate date;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double adj_Close;
    private Integer volume;
    private Boolean uptrend;
    private Double changeInPercentige;

    

    public Stock(LocalDate date, Double open, Double close, Double high, Double low, Double adj_Close, Integer volume) {

        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.adj_Close = adj_Close;
        this.volume = volume;
        this.changeInPercentige = ((close/open)-1)*100;
        if(open <= close){
            this.uptrend = true;
        }
        else{
            this.uptrend = false;
        }
    }

    public Stock(String s){
        String[] parts = s.split(",");
        LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double open = Double.parseDouble(parts[1]);
        Double high = Double.parseDouble(parts[2]);
        Double low = Double.parseDouble(parts[3]);
        Double close = Double.parseDouble(parts[4]);
        Double adj_Close = Double.parseDouble(parts[5]);
        Integer volume = Integer.parseInt(parts[6]);

        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.adj_Close = adj_Close;
        this.volume = volume;
        this.changeInPercentige = ((close/open)-1)*100;
        if(open <= close){
            this.uptrend = true;
        }
        else{
            this.uptrend = false;
        }

    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Double getOpen() {
        return open;
    }
    public void setOpen(Double open) {
        this.open = open;
    }
    public Double getClose() {
        return close;
    }
    public Boolean getUptrend() {
        return uptrend;
    }

    public Double getChangeInPercentige() {
        return changeInPercentige;
    }

    public void setClose(Double close) {
        this.close = close;
    }
    public Double getHigh() {
        return high;
    }
    public void setHigh(Double high) {
        this.high = high;
    }
    public Double getLow() {
        return low;
    }
    public void setLow(Double low) {
        this.low = low;
    }
    public Double getAdj_Close() {
        return adj_Close;
    }
    public void setAdj_Close(Double adj_Close) {
        this.adj_Close = adj_Close;
    }
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
	public int hashCode() {
		return Objects.hash(date);
	}

    public int compareTo(Stock s) {
		int res = this.date.compareTo(s.date);
		if(res==0) {
			res = this.changeInPercentige.compareTo(s.changeInPercentige);
		}
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(date, other.date);
	}

    @Override
    public String toString() {
        return "Stock{" +
                "date=" + date +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", adj_Close=" + adj_Close +
                ", volume=" + volume +
                ", uptrend=" + uptrend +
                ", changeInPercentage=" + changeInPercentige +
                '}';
    }
	
    
}
