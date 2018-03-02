package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        if (quantity > 10) {
            Toast.makeText(getApplicationContext(), "Too many coffees ordered!", Toast.LENGTH_SHORT).show();
            quantity = 10;
        } else {
            display(quantity);
        }


    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 1) {
            Toast.makeText(getApplicationContext(), "You must order at least one coffee.", Toast.LENGTH_SHORT).show();
            quantity = 1;
        } else {
            display(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    /**
     * The followin method calculates the price of the order.
     *
     * @return total price
     */

    private int calculatePrice(boolean addPriceWhippedCream, boolean addPriceChocolate) {
        int basePrice = 5;
        if (addPriceWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addPriceChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

//    public void onCheckboxClicked(View view) {
//        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
//        boolean checkWhippedCream = (WhippedCreamCheckBox.isChecked());
//    if (checkWhippedCream) info = "true";
//    else info = "false";}

    /**
     * @param price           is the total price for a coffee
     * @param addWhippedCream wheter or not addwhipped cream to the coffee
     * @param addChocolate    whether or not add chocolate to coffee
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String addName) {

        displayMessage("Name: " + addName + "\nAdd Whipped cream?  " + addWhippedCream + "\nAdd Chocolate?  " + addChocolate + "\nQuantity:  " + quantity + "\nTotal:  " + price + ",00  PLN" + "\nThank you very much!");
//        displayPrice(price);
        return addName;
    }

    /**
     * This method is called when in submitOrder, and passing the intent to e-mail.
     */


//    public void composeEmail(String subject) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
////        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    /**
     * This method is called when the order button is clicked.
     * Here the checkbox are checked and we get the name from the user.
     */
    public void submitOrder(View view) {

        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean checkWhippedCream = (WhippedCreamCheckBox.isChecked());

        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean checkChocolate = (ChocolateCheckBox.isChecked());

        int finalprice = calculatePrice(checkWhippedCream, checkChocolate);

        EditText name = (EditText) findViewById(R.id.name);
        String checkName = name.getText().toString();

        String message = "Name: " + checkName + "\nAdd Whipped cream?  " + checkWhippedCream + "\nAdd Chocolate?  " + checkChocolate + "\nQuantity:  " + quantity + "\nTotal:  " + finalprice + ",00  PLN" + "\nThank you very much!";


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order for "+ checkName);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//      createOrderSummary(finalprice, checkWhippedCream, checkChocolate, checkName);
    }


}