package com.pstech.tutorials.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

//import com.payumoney.core.PayUmoneySdkInitializer;
//import com.payumoney.core.entity.TransactionResponse;
//import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.pstech.tutorials.R;

public class PaymentPayUActivity extends AppCompatActivity {

    private String TAG = "PGExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pay_u);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

//    public void startPayment() {
//        PayUmoneySdkInitializer.PaymentParam.Builder builder = new
//                PayUmoneySdkInitializer.PaymentParam.Builder();
//        builder.setAmount(amount)                          // Payment amount
//                .setTxnId(txnId)                                             // Transaction ID
//                .setPhone(phone)                                           // User Phone number
//                .setProductName(productName)                   // Product Name or description
//                .setFirstName(firstName)                              // User First name
//                .setEmail(email)                                            // User Email ID
//                .setsUrl(appEnvironment.surl())                    // Success URL (surl)
//                .setfUrl(appEnvironment.furl())                     //Failure URL (furl)
//                .setUdf1(udf1)
//                .setUdf2(udf2)
//                .setUdf3(udf3)
//                .setUdf4(udf4)
//                .setUdf5(udf5)
//                .setUdf6(udf6)
//                .setUdf7(udf7)
//                .setUdf8(udf8)
//                .setUdf9(udf9)
//                .setUdf10(udf10)
//                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
//                .setKey(“enter merchant key”)                        // Merchant key
//                .setMerchantId(“enter merchant ID”);             // Merchant ID
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result Code is -1 send from Payumoney activity
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
//        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
//            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
//            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
//                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
//                    //Success Transaction
//                } else {
//                    //Failure Transaction
//                }
//                    // Response from Payumoney
//                String payuResponse = transactionResponse.getPayuResponse();
//                    // Response from SURl and FURL
//                String merchantResponse = transactionResponse.getTransactionDetails();
//            } else if (resultModel != null && resultModel.getError() != null) {
//                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
//            } else {
//                Log.d(TAG, "Both objects are null!");
//            }
//        }
    }
}
