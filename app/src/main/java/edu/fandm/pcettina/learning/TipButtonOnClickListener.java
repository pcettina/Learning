package edu.fandm.pcettina.learning;

import android.nfc.Tag;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipButtonOnClickListener implements View.OnClickListener{
    public static final String TAG = "TBOCL";
    public void onClick(View v){
        View parent = (View) v.getParent();
        Button tip_Button = (Button) v;
        EditText et = (EditText)parent.findViewById(R.id.total_bill_et);

        try {
            Double billAmt = Double.parseDouble(et.getText().toString());
            String S_tipAmt = tip_Button.getText().toString();

            int idx = S_tipAmt.indexOf('%');
            Double tipAmt = Double.parseDouble(S_tipAmt.substring(0,idx))/100;

            double totalAmt = billAmt + (billAmt * tipAmt) ;
            String s = String.format("Total Bill: $%.2f", totalAmt);

            TextView tv = (TextView) parent.findViewById(R.id.total_bill_tv);
            tv.setText(s);

        }
        catch (NumberFormatException NFE){
            String s = "Invalid Input";
            TextView tv = (TextView) parent.findViewById(R.id.total_bill_tv);
            tv.setText(s);
        }

    }
}
