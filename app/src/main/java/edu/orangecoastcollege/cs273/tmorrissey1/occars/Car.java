package edu.orangecoastcollege.cs273.tmorrissey1.occars;

/**
 * Represents a model of a car loan
 * Created by Travis on 9/18/2016.
 */
public class Car {

    /**
     * Sales tax rate of Costa Mesa
     */
    private final double TAX_RATE = 0.08;
    /**
     * Interest rate in decimal form for a 3 year loan
     */
    private final double INTEREST_RATE_3_YR = 0.0462;
    /**
     * Interest rate in decimal form for a 4 year loan
     */
    private final double INTEREST_RATE_4_YR = 0.0416;
    /**
     * Interest rate in decimal form for a 5 year loan
     */
    private final double INTEREST_RATE_5_YR = 0.0419;
    /**
     * Down payment of this Car
     */
    private double mDownPayment;
    /**
     * Sticker price of this car
     */
    private double mPrice;

    /**
     * Length of the loan in years
     */
    private int mLoanTerm;

    /**
     *  Gets down payment of this Car
     * @return down payment of this Car
     */
    public double getDownPayment() {
        return mDownPayment;
    }

    /**
     *  Sets down payment of this Car
     *  @param downPayment The new down payment of this Car
     */
    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    /**
     *  Gets price of this Car
     * @return price of this Car
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Sets price of this Car
     * @param price the new price of this Car
     */
    public void setPrice(double price) {
        mPrice = price;
    }

    /**
     *  Gets loan term of this Car
     * @return loan term of this Car in years
     */
    public int getLoanTerm() {
        return mLoanTerm;
    }

    /**
     * Sets loan term of this Car
     * @param loanTerm new loan term of this Car in years
     */
    public void setLoanTerm(int loanTerm) {
        mLoanTerm = loanTerm;
    }

    /**
     * Calculates the monthly payment of this Car
     * @return the monthly payment of this car
     */
    public double calculateMonthlyPayment() {
        return (calculateBorrowedAmount() + calculateInterestAmount()) / (mLoanTerm * 12);
    }

    /**
     * Calculates the borrowed amount of this Car
     * @return the borrowed amount of this Car
     */
    public double calculateBorrowedAmount() {
        return mPrice - mDownPayment + calculateTaxAmount();
    }

    /**
     * Calculates the interest amount of this Car
     * @return the interest amount of this Car
     */
    public double calculateInterestAmount() {
        return calculateBorrowedAmount() * getCorrectTaxRate();
    }

    /**
     * Calculates the tax amount of this Car
     * @return the tax amount of this Car
     */
    public double calculateTaxAmount() {
        return  mPrice * TAX_RATE;
    }

    /**
     * Calculates the total cost of this Car
     * @return the total cost of this Car
     */
    public double calculateTotalCost() {
        return mPrice + calculateTaxAmount();
    }

    /**
     * Helper method that determines the correct interest rate constant to use based on the loan
     * term.
     * @return the correct interest rate in decimal form.
     */
    private double getCorrectTaxRate() {
        return mLoanTerm == 5 ? INTEREST_RATE_5_YR : mLoanTerm == 4
                ? INTEREST_RATE_4_YR : INTEREST_RATE_3_YR;
    }
}
