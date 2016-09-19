package edu.orangecoastcollege.cs273.tmorrissey1.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;

/**
 * Controller for OC Cars. Manages inputs/outputs between activity_purchase.xml and Car.java
 * @author Travis Morrissey
 */
public class PurchaseActivity extends Activity {

    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioButton threeYearsRadioButton;
    private RadioButton fourYearsRadioButton;
    private RadioButton fiveYearsRadioButton;
    private Button loanReportButton;
    private NumberFormat currencyFormat;

    private Car currentCar;

    private String monthlyPaymentText;
    private String loanSummaryText;

    /**
     * Perform initialization of all fragments and loaders
     * @param savedInstanceState Last saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        carPriceEditText = (EditText) findViewById(R.id.carPriceEditText);
        downPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);
        fourYearsRadioButton = (RadioButton) findViewById(R.id.fourYearsRadioButton);
        fiveYearsRadioButton = (RadioButton) findViewById(R.id.fiveYearsRadioButton);
        loanReportButton = (Button) findViewById(R.id.loanReportButton);
        currencyFormat = NumberFormat.getCurrencyInstance();
        currentCar = new Car();

        loanReportButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * @param v the View that was clicked
             */
            @Override
            public void onClick(View v) {
                activateLoanReport(v);
            }
        });

    }

    /**
     * Updates the Car class with new input from user. Creates a new intent
     * and starts LoanSummaryActivity.
     * @param view the View that was clicked
     */
    public void activateLoanReport(View view) {
        double price, downPayment;

        try {
            price = Double.parseDouble(carPriceEditText.getText().toString());
            downPayment = Double.parseDouble(
                    downPaymentEditText.getText().toString());
        }
        catch (NumberFormatException e) {
            downPayment = price = 0.0;
        }

        int loanTerm = fiveYearsRadioButton.isChecked() ? 5 :
                fourYearsRadioButton.isChecked() ? 4 : 3;

        currentCar.setPrice(price);
        currentCar.setDownPayment(downPayment);
        currentCar.setLoanTerm(loanTerm);

        constructLoanSummaryText();

        // Create the intent to share data with LoanSummaryActivity
        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivity.class);
        loanSummaryIntent.putExtra("MonthlyPayment", monthlyPaymentText);
        loanSummaryIntent.putExtra("LoanSummary", loanSummaryText);
        // Start new activity
        startActivity(loanSummaryIntent);
    }

    /**
     * Builds the formatted strings to be output on LoanSummaryActivity View
     */
    private void constructLoanSummaryText() {
        monthlyPaymentText = getString(R.string.report_line1) +
                currencyFormat.format(currentCar.calculateMonthlyPayment());

        loanSummaryText = getString(R.string.report_line2) +
                currencyFormat.format(currentCar.getPrice()) +
                getString(R.string.report_line3) + currencyFormat.format(currentCar.getDownPayment())
                + getString(R.string.report_line5) + currencyFormat.format(currentCar.calculateTaxAmount())
                + getString(R.string.report_line6) + currencyFormat.format(currentCar.calculateTotalCost())
                + getString(R.string.report_line7) + currencyFormat.format(currentCar.calculateBorrowedAmount())
                + getString(R.string.report_line8) + currencyFormat.format(currentCar.calculateInterestAmount())
                + getString(R.string.report_line4) + (fiveYearsRadioButton.isChecked() ? getString(R.string.years5) :
                fourYearsRadioButton.isChecked() ? getString(R.string.years4) : getString(R.string.years3))
                + getString(R.string.report_line9) + getString(R.string.report_line10)
                + getString(R.string.report_line11);

    }
}
