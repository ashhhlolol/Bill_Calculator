package sg.edu.rp.c346.id22000765.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmountInput;
    EditText etNumOfPax;
    ToggleButton SVS;
    ToggleButton GST;
    EditText etDiscountInput;
    RadioGroup paymentMethod;
    Button SplitButton;
    Button ResetButton;
    TextView TotalBill;
    TextView eachPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmountInput = findViewById(R.id.EditTextAmount);
        etNumOfPax = findViewById(R.id.EditTextPax);
        SVS = findViewById(R.id.SVS);
        GST = findViewById(R.id.GST);
        etDiscountInput = findViewById(R.id.EditTextDiscount);
        paymentMethod = findViewById(R.id.rgPaymentMethod);
        SplitButton=findViewById(R.id.SplitButton);
        ResetButton=findViewById(R.id.ResetButton);
        TotalBill=findViewById(R.id.totalBillAmt);
        eachPay=findViewById(R.id.EachPay);

        SplitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalAmount = etAmountInput.getText().toString();
                int NumOfPax = Integer.parseInt(etNumOfPax.getText().toString());
                String discount = etDiscountInput.getText().toString();
                double newAmount=0.0;

                if(totalAmount.trim().length()!=0 && NumOfPax!=0){
                    if(!SVS.isChecked()&&!GST.isChecked()){
                        newAmount=Double.parseDouble(totalAmount);
                    } else if (SVS.isChecked() && !GST.isChecked()){
                        newAmount = Double.parseDouble(totalAmount)*1.1;
                    } else if(!SVS.isChecked()&& GST.isChecked()){
                        newAmount=Double.parseDouble(totalAmount)*1.07;
                    } else{
                        newAmount *= 1-Double.parseDouble(totalAmount)*1.17;
                    }
                    // The discount
                    if(discount.trim().length()!=0){
                        newAmount *= 1-Double.parseDouble(discount)/100;
                    }
                    TotalBill.setText("Total Bill:$ " + (newAmount));

                    if(NumOfPax != 1)
                        eachPay.setText("Each Pays:$ "+ newAmount/NumOfPax);
                    else
                        eachPay.setText("Each Pays:$ "+newAmount);
                }



            }
        });


}

}

//