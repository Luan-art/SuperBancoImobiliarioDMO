package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.model.CreditCard;
import br.edu.ifsp.dmos5.model.StarBank;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner textCard1;
    private Spinner textCard2;
    private Spinner textCard3;
    private Spinner textCard4;

    private EditText textValue;

    private EditText textValueOpe;
    private Button buttonOpe;
    private Button buttonTra;
    private Button buttonValor;
    private RadioGroup grupoOp;
    private TextView mostrarSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        textCard1 = findViewById(R.id.cartdsSpiner);
        textCard2 = findViewById(R.id.cartdsSpinerReceiver);
        textCard3 = findViewById(R.id.cartdsSpinerOper);
        textCard4 = findViewById(R.id.cartdsSpinerVal);
        textValue = findViewById(R.id.text_valor);
        textValueOpe = findViewById(R.id.textValor2);
        buttonOpe = findViewById(R.id.buttonOpe);
        buttonTra = findViewById(R.id.transference);
        buttonValor = findViewById(R.id.MostrarSaldo);
        grupoOp = findViewById(R.id.convert_options);
        mostrarSaldo = findViewById(R.id.text_view_converted);
        buttonOpe.setOnClickListener(this);
        buttonTra.setOnClickListener(this);
        buttonValor.setOnClickListener(this);

        StarBank.getInstance().startCreditCards();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cardId, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        textCard1.setAdapter(adapter);
        textCard2.setAdapter(adapter);
        textCard3.setAdapter(adapter);
        textCard4.setAdapter(adapter);


    }


    @Override
    public void onClick(View view) throws NumberFormatException {

        int cardId;
        double value;
        String textV;

        if(view == buttonOpe){

            textV = textValueOpe.getText().toString();
            cardId = textCard3.getSelectedItemPosition();

            value = Double.valueOf(textV);

            CreditCard card = StarBank.getInstance().getCard(cardId);

            switch (grupoOp.getCheckedRadioButtonId()){
                case R.id.pagar:

                        StarBank.getInstance().pay(card, value);
                        Toast.makeText(MainActivity.this, "Pagamento realizado.", Toast.LENGTH_SHORT).show();

                        textValueOpe.setText("");


                    break;

                case R.id.receber:

                        StarBank.getInstance().receive(card,value);
                        Toast.makeText(MainActivity.this, "Valor recebido.", Toast.LENGTH_SHORT).show();

                        textValueOpe.setText("");


                    break;
                case R.id.complet_rodad:

                        StarBank.getInstance().roundCompleted(card, value);
                        Toast.makeText(MainActivity.this, "Valor recebido.", Toast.LENGTH_SHORT).show();

                        textValueOpe.setText("");


                    break;

            }

        }
        if(view == buttonTra){

            int cardId2;

            textV = textValue.getText().toString();
            cardId = textCard1.getSelectedItemPosition();
            cardId2 = textCard2.getSelectedItemPosition();

            value = Double.valueOf(textV);

            CreditCard player = StarBank.getInstance().getCard(cardId);
            CreditCard receiver = StarBank.getInstance().getCard(cardId2);


            StarBank.getInstance().transfer(player, receiver, value);
            Toast.makeText(MainActivity.this, "Transferencia Realizada.", Toast.LENGTH_SHORT).show();

            textValue.setText("");


        }


        if(view == buttonValor){

            cardId = textCard4.getSelectedItemPosition();
            CreditCard cardValor = StarBank.getInstance().getCard(cardId);


            mostrarSaldo.setText(String.format("$ %.2f", cardValor.getBalance()));

        }

    }
    


}

