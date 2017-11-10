package work.geodoo.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.icu.text.NumberFormat;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean whippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        if (quantity == 0) return;
        String message = setMessage();
        Intent gmail = new Intent(Intent.ACTION_SENDTO);
        gmail.setData(Uri.parse("mailto:"));
        gmail.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + getUserName());
        gmail.putExtra(Intent.EXTRA_TEXT, message);
        if (gmail.resolveActivity(getPackageManager()) != null) {
            startActivity(gmail);
        }
    }

    public void checkWhippedCream(View view) {
        whippedCream = !whippedCream;
    }

    private String getUserName() {
        EditText name = (EditText) findViewById(R.id.name);
        return name.getText().toString();
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String setMessage() {
        return "Welcome, " + getUserName() + "\nTotal: " + NumberFormat.getCurrencyInstance().format(quantity * 5) + "\nAdd whipped cream too? " + whippedCream + "\nThank you!";
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
        }
        display(quantity);
    }

}