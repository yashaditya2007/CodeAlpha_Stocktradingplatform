# 📈 Stock Trading Platform

## 📌 Project Overview

The **Stock Trading Platform** is a console-based Java application developed as part of the **CodeAlpha Java Internship**.

This project simulates a basic stock trading environment where users can view market data, buy and sell stocks, manage their portfolio, track portfolio performance, and view transaction history.

The application demonstrates **Object-Oriented Programming (OOP)** concepts in Java and includes **File I/O** for saving transaction history.

---

## 🎯 Objectives

* Simulate a basic stock trading environment.
* Display available market data.
* Allow users to buy stocks.
* Allow users to sell stocks.
* Track the user's portfolio.
* Calculate portfolio value and profit/loss.
* Maintain transaction history.
* Use OOP to manage stocks, users, portfolios, and transactions.
* Save transaction history using File I/O.

---

## ✨ Features

### 📊 Market Data

Displays simulated stock information, including:

* Stock symbol
* Company name
* Current price

### 💰 Buy Stocks

Users can purchase stocks by entering the stock symbol and the number of shares.

The program checks whether sufficient funds are available before completing the purchase.

### 💵 Sell Stocks

Users can sell stocks they currently own.

The program verifies that the user owns enough shares before completing the transaction.

### 📁 Portfolio Management

Users can view:

* Stocks owned
* Number of shares
* Current stock value
* Available cash
* Total portfolio value
* Profit/Loss

### 📈 Portfolio Performance

The application calculates:

* Initial investment
* Current portfolio value
* Profit/Loss
* Return percentage
* Performance status

### 🧾 Transaction History

All buy and sell transactions are recorded during the session.

### 💾 File I/O

Transaction history can be saved to:

```text
transactions.txt
```

The file is automatically created when the user selects the **Save Transaction History** option.

---

## 🛠️ Technologies Used

* **Java**
* **Object-Oriented Programming (OOP)**
* **ArrayList**
* **HashMap**
* **Scanner**
* **FileWriter**
* **Exception Handling**
* **Console-based Interface**

---

## 🧠 OOP Concepts Used

The project contains several classes:

### `Stock`

Manages stock information such as symbol, company name, and price.

### `Transaction`

Stores information about buy and sell transactions.

### `Portfolio`

Manages:

* Cash
* Stock holdings
* Buying stocks
* Selling stocks
* Portfolio value
* Profit/Loss

### `User`

Stores the user's name and portfolio.

### `Main`

Controls the application, menu, user input, and trading operations.

---

## 📂 Project Structure

```text
StockTradingPlatform/
│
├── Main.java
├── README.md
└── transactions.txt
```

| File               | Description                                 |
| ------------------ | ------------------------------------------- |
| `Main.java`        | Main Java source code                       |
| `README.md`        | Project documentation                       |
| `transactions.txt` | Automatically generated transaction history |

> **Note:** `transactions.txt` does not need to be created manually. The program creates it when transaction history is saved.

---

## 💻 Requirements

To run this project, you need:

* **JDK 8 or higher**
* Java-supported IDE or terminal

Recommended IDEs:

* IntelliJ IDEA
* Eclipse
* Visual Studio Code
* NetBeans

---

## ▶️ How to Run

### 1. Open the Project

Open the `StockTradingPlatform` folder in your preferred Java IDE.

### 2. Open `Main.java`

Make sure the public class is:

```java
public class Main
```

### 3. Compile

Open a terminal in the project directory and run:

```bash
javac Main.java
```

### 4. Run

```bash
java Main
```

---

## 📋 Main Menu

The application provides the following options:

```text
==============================================
                    MENU
==============================================
1. Display Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. Portfolio Performance
6. Transaction History
7. Save Transaction History
8. Exit
==============================================
```

---

## 📊 Available Stocks

The application uses simulated stock prices for demonstration purposes.

| Symbol | Company   |   Price |
| ------ | --------- | ------: |
| AAPL   | Apple     | $195.50 |
| GOOGL  | Google    | $175.25 |
| MSFT   | Microsoft | $420.75 |
| AMZN   | Amazon    | $185.40 |
| TSLA   | Tesla     | $245.60 |
| META   | Meta      | $525.30 |

> **Disclaimer:** The stock prices used in this project are simulated and are not real-time market prices. This application is intended for educational purposes only.

---

## 🔐 Input Validation

The program validates:

* Menu choices
* Numeric input
* Initial investment amount
* Stock symbols
* Stock quantities
* Available funds
* Number of shares available for selling

This prevents invalid transactions and improves the user experience.

---

## 🚀 Future Improvements

Possible future enhancements include:

* Real-time stock market API
* User authentication
* Multiple user accounts
* Database integration
* JavaFX or Swing GUI
* Real-time price updates
* Stock watchlist
* Advanced portfolio analytics
* Persistent portfolio storage
* Transaction timestamps
* Stock search and filtering


## 👨‍💻 Author

**Yashaditya Singh**

Developed as part of the **CodeAlpha Java Internship**.

---

