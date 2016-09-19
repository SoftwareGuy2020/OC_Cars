package edu.orangecoastcollege.cs273.tmorrissey1.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Controller for OC Cars
 */
public class LoanSummaryActivity extends Activity {


    /**
     * Perform initialization of all fragments and loaders
     * @param savedInstanceState Last saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        TextView monthlyPaymentTextView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        TextView loanReportTextView = (TextView) findViewById(R.id.loanReportTextView);
        Button returnButton = (Button) findViewById(R.id.returnButton);

        Intent intentFromPurchaseActivity = getIntent();
        String monthlyPaymentText = intentFromPurchaseActivity.getStringExtra("MonthlyPayment");
        String loanReportText = intentFromPurchaseActivity.getStringExtra("LoanSummary");

        monthlyPaymentTextView.setText(monthlyPaymentText);
        loanReportTextView.setText(loanReportText);

        returnButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * @param v the View that was clicked
             */
            @Override
            public void onClick(View v) {
                returnToDataEntry(v);
            }
        });

    }

    /**
     * Finishes the activity and returns to PurchaseActivity
     * @param view the View that was clicked
     */
    public void returnToDataEntry(View view) {
        this.finish();
    }
}
