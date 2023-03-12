package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.model.CreditCard;
import br.edu.ifsp.dmos5.model.StarBank;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText textCard1;
    private EditText textCard2;
    private EditText textCard3;
    private EditText textCard4;

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

        textCard1 = findViewById(R.id.textCard1);
        textCard2 = findViewById(R.id.textCard2);
        textCard3 = findViewById(R.id.textCard3);
        textCard4 = findViewById(R.id.textCard4);
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


    }


    @Override
    public void onClick(View view) {

        int cardId;
        double value;
        String textV;
        String textID;

        if(view == buttonOpe){

            textV = textValueOpe.getText().toString();
            textID = textCard3.getText().toString();

            cardId = Integer.valueOf(textID);
            value = Double.valueOf(textV);

            CreditCard card = StarBank.getInstance().getCard(cardId);

            switch (grupoOp.getCheckedRadioButtonId()){
                case R.id.pagar:

                        StarBank.getInstance().pay(card, value);
                        Toast.makeText(MainActivity.this, "Pagamento realizado.", Toast.LENGTH_SHORT).show();
                        textValueOpe.setText("");
                        textCard3.setText("");

                    break;

                case R.id.receber:

                        StarBank.getInstance().receive(card,value);
                        Toast.makeText(MainActivity.this, "Valor recebido.", Toast.LENGTH_SHORT).show();
                        textValueOpe.setText("");
                        textCard3.setText("");


                    break;
                case R.id.complet_rodad:

                        StarBank.getInstance().roundCompleted(card, value);
                        Toast.makeText(MainActivity.this, "Valor recebido.", Toast.LENGTH_SHORT).show();
                        textValueOpe.setText("");
                        textCard3.setText("");


                    break;

            }

        }
        if(view == buttonTra){

            String textId2;
            int cardId1;

            textV = textValue.getText().toString();
            textID = textCard1.getText().toString();
            textId2 = textCard2.getText().toString();

            cardId = Integer.valueOf(textID);
            value = Double.valueOf(textV);
            cardId1 = Integer.valueOf(textId2);

            CreditCard player = StarBank.getInstance().getCard(cardId);
            CreditCard receiver = StarBank.getInstance().getCard(cardId1);


            StarBank.getInstance().transfer(player, receiver, value);
            Toast.makeText(MainActivity.this, "Transferencia Realizada.", Toast.LENGTH_SHORT).show();

            textValue.setText("");
            textCard1.setText("");
            textCard2.setText("");


        }


        if(view == buttonValor){

            textID = textCard4.getText().toString();
            cardId = Integer.valueOf(textID);
            CreditCard cardValor = StarBank.getInstance().getCard(cardId);


            mostrarSaldo.setText(String.format("$ %.2f", cardValor.getBalance()));

            textCard4.setText("");



        }


    }
    


}

