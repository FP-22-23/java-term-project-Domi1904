# Amazon Stocks

## Introduction

The Project is build around the Amazon Stock Market prices and can Calculate different Informations like:

- Percentage change between Days (open/close)
- Utrend or Downtrend (open > close)
- Calculate the Profit if a certain amount of money is Invested into the Stock
- Give you the overall Up-Downtrend on all Datasets (Days)


### Tests/Examples

On the test directory you will find some examples of all funtionalities.
Keep in mind that the Testdata will use the AMZNtest.csv dataset file.

## The Stock class

It is used to store all the relevant Informations with all the attributes needed to add further funtions.

### Fields:

date (LocalDate): The date of the stock.
open (Double): The opening price of the stock.
close (Double): The closing price of the stock.
high (Double): The highest price the stock reached during the day.
low (Double): The lowest price the stock reached during the day.
adj_Close (Double): The adjusted closing price of the stock.
volume (Integer): The number of shares traded during the day.
uptrend (Boolean): True if the stock opened and closed higher, false otherwise.
changeInPercentige (Double): The percentage change in the stock price from open to close.

### Constructors
There are two constructors for this class:

Stock(LocalDate date, Double open, Double close, Double high, Double low, Double adj_Close, Integer volume): This constructor takes the stock's information as separate parameters and sets the data fields accordingly.

Stock(String s): This constructor takes a string in the format "yyyy-MM-dd,open,high,low,close,adj_close,volume" and parses it to create a Stock object.

### Methods

This class has getters and setters for each data field. It also implements the Comparable interface to allow for sorting of Stock objects by date and percentage change. The hashCode and equals methods are also implemented for use in hash tables.

### Indicators

EmaIndicator: Calculates the Exponential Moving Average (EMA) for a given period.

MacdPricePredictor: Predicts the future price based on MACD and its signal line.
This method is using the EMAIndictor to predict Prices. You need to insert 3 Parameters:
- Shorttime period
- Longtime period
- Signal length

