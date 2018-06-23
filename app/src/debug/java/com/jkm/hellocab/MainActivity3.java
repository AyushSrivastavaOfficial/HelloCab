package com.jkm.hellocab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class MainActivity3 extends AppCompatActivity {
TextView txtv,txtv1;
PayPalConfiguration m_Pay;
String str="AeFV3HHQD7uQgSq9vptUs0f6ST6cpAKVcIj_PkV-yrtCH02fo27qeBAv8yqikYaWRbwB0VtxDbrNmmOT";
Intent intent;int s=999;String paymentAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle bundle=getIntent().getExtras();
        String cost=bundle.getString("cost");
        txtv=(TextView)findViewById(R.id.textView2);
        txtv.setText(cost);
        txtv1=(TextView)findViewById(R.id.textView);
        m_Pay=new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(str);
        intent=new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_Pay);
        startService(intent);
    }
    public void pay(View v) {
        if (v.getId()==R.id.button3)
        {
            //   Toast.makeText(BlankActivity.this,"Java is selected",Toast.LENGTH_LONG).show();
            paymentAmount = txtv.getText().toString();
            PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)),"USD","Your payment processed via paypal",PayPalPayment.PAYMENT_INTENT_SALE);
            //  PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(15),"$","Your payment processed via paypal",PayPalPayment.PAYMENT_INTENT_SALE);

            Intent intent1=new Intent(this,PaymentActivity.class);
            intent1.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_Pay);
            intent1.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
            startActivityForResult(intent1,s);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==s)
        {if (resultCode== Activity.RESULT_OK) {
            PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (paymentConfirmation != null) {
                String str1 = paymentConfirmation.getProofOfPayment().getState();
                if (str1.equals("approved"))
                    txtv1.setText("Payment approved");
                else
                    txtv1.setText("payment not approved:error message");
            } else
                txtv1.setText("Payment not confirmed");
        }}

    }
}

