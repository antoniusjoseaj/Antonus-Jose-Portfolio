import pandas as pd
import matplotlib.pyplot as plt

# Loading the dataset
aapl = pd.read_csv(r"C:\Users\josea\Downloads\AAPL.csv")

# Understanding the Dataset
print(aapl.dtypes)
print(aapl.info)

# 1. Data Cleaning Process
# Dropping all null and duplicate values
aapl = aapl.dropna()
aapl = aapl.drop_duplicates(subset='Date', keep='first')

# Ensuring all columns with integers can be read 
all_numeric_cols = ['Open', 'High', 'Low', 'Close', 'Adj Close', 'Volume']
aapl[all_numeric_cols] = aapl[all_numeric_cols].apply(pd.to_numeric, errors='coerce')

# Conversion to date
aapl['Date'] = pd.to_datetime(aapl['Date']).dt.date

aapl = aapl.sort_values('Date')
aapl = aapl.reset_index(drop=True)

# 2. Calculations

# Calculate daily returns (percentage change)
aapl['Daily_Return'] = aapl['Adj Close'].pct_change()

# Calculate moving averages (rolling means)
aapl['MA_7'] = aapl['Adj Close'].rolling(window=7).mean()    # 7-day moving average
aapl['MA_21'] = aapl['Adj Close'].rolling(window=21).mean()  # 21-day moving average

# Calculate rolling volatility (standard deviation)
aapl['Volatility_MA_21'] = aapl['Daily_Return'].rolling(window=21).std()

# Dropping all NaN rows created by rolling calculations
aapl = aapl.dropna()

print(aapl.head())

# 3. Graphs
# Price & Moving Averages over Time
plt.figure(figsize=(12,6))
plt.plot(aapl['Date'], aapl['Adj Close'], label='Adjusted Close', linewidth=2)
plt.plot(aapl['Date'], aapl['MA_7'], label='7-Day MA', linestyle='--')
plt.plot(aapl['Date'], aapl['MA_21'], label='21-Day MA', linestyle='--')

plt.title('AAPL Stock Price with Moving Averages')
plt.xlabel('Date')
plt.ylabel('Price ($)')
plt.legend()
plt.grid(True)

plt.gcf().autofmt_xdate()
plt.xticks(aapl['Date'][::5])  
plt.show()

# Daily Return Distribution
plt.figure(figsize=(8,5))
plt.hist(aapl['Daily_Return'], bins=50, color='skyblue', edgecolor='black')

plt.title('Distribution of Daily Returns')
plt.xlabel('Daily Return')
plt.ylabel('Frequency')
plt.grid(True)

plt.gcf().autofmt_xdate()
plt.show()

# Rolling votality over time
plt.figure(figsize=(12,6))
plt.plot(aapl['Date'], aapl['Volatility_MA_21'], 
color='orange', label='21-Day Rolling Volatility')

plt.title('AAPL Rolling Volatility (21 days)')
plt.xlabel('Date')
plt.ylabel('Volatility')
plt.legend()

plt.grid(True)
plt.gcf().autofmt_xdate()
plt.xticks(aapl['Date'][::5])  
plt.show()
