package work.geodoo.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
        String message = setMessage();
        displayMessage(message);
    }

    public void checkWhippedCream(View view) {
        whippedCream = !whippedCream;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String setMessage() {
        if (whippedCream) {
            return "Total: " + NumberFormat.getCurrencyInstance().format(quantity * 5) + "\nAdd whipped cream too? Yes" + "\nThank you!";
        }
        return "Total: " + NumberFormat.getCurrencyInstance().format(quantity * 5) + "\nAdd whipped cream too? No" + "\nThank you!";
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
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