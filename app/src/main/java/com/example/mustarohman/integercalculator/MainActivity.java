package com.example.mustarohman.integercalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String inputNumber;
    private int firstInt;
    private int secondInt;
    private String operatorStr;
    private boolean operandSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = "0";
        firstInt = 0;
        secondInt = 0;
        operandSaved = false;

        textView = (TextView) findViewById(R.id.textView);

        updateNumberText();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickNumberBtn(View view) {

        Button numberBtn = (Button) view;

        if (inputNumber.equals("0")){
            inputNumber = "";
        };
        if (operandSaved ==  true && inputNumber.equals("")) {

            inputNumber = "";

        }
        inputNumber += numberBtn.getText().toString();
        updateNumberText();

    }

    public void updateNumberText(){

        textView.setText(inputNumber);

    }

    public void onClickCBtn(View view) {


        inputNumber = "0";
        firstInt = 0;
        secondInt = 0;
        operandSaved = false;
        updateNumberText();

    }


    /**
     * Performs calculation with 2 operands and the saved operator and updates screen
     * @param secondOperand Calculates this with the first saved operand
     */
    public void performOperation(int secondOperand){

        switch(operatorStr.charAt(0)){
            case '+':
                System.out.println(firstInt + " + "  + secondOperand);
                firstInt = firstInt + secondOperand;
                break;
            case  '-':
                System.out.println(firstInt + " -  " + secondOperand);
                firstInt = firstInt - secondOperand;
                break;
            case '/':
                System.out.println(firstInt + " -  " + secondOperand);
                firstInt = firstInt / secondOperand;
                break;
            case '*':
                firstInt = firstInt * secondOperand;
                break;
        }

        inputNumber = String.valueOf(firstInt);
        updateNumberText();
    }


    /**
     *
     * @param view
     */
    public void onClickOperator(View view) {
        Button opBtn = (Button) view;

        if (operandSaved == false) {
            //Save first operand
            firstInt = Integer.parseInt(inputNumber);
            //Notifies that operand has been saved
            operandSaved = true;
        } else {
            performOperation(Integer.parseInt(inputNumber));
            inputNumber = String.valueOf(firstInt);
            updateNumberText();

            operandSaved = false;
        }

        //Saves operator
        operatorStr = opBtn.getText().toString();
        inputNumber = "";


    }

    /**
     * Performs the calculation on the first integer saved and the number currently on
     * the screen. Then resets the saved data to default states, ready for another calculation
     * @param view
     */
    public void onClickEqual(View view) {

        if (operandSaved) {
            //Saves the current int on the screen
            secondInt = Integer.parseInt(textView.getText().toString());

            performOperation(secondInt);

//            firstInt = 0;
//            secondInt = 0;
            operandSaved = false;

//            inputNumber = "";

        }

    }
}
