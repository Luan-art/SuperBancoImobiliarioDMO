package br.edu.ifsp.dmos5.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.view.MainActivity;

public class StarBank {

    private ArrayList<CreditCard> cards = new ArrayList<>();

    private static StarBank instance = null;

    private StarBank(){}

    public static StarBank getInstance() {
        if(instance == null){
            instance = new StarBank();
        }
        return instance;
    }

    public void startCreditCards(){

        for(int i = 0; i<6; i++){
            cards.add(new CreditCard());
        }

    }

    public CreditCard getCard( int id){
        CreditCard card;

        card = cards.get(id);

        return card;
    }


    public void roundCompleted(CreditCard card, double value){
        card.creditValue(value);
    }

    public boolean transfer (CreditCard player, CreditCard receiver, double value) throws NumberFormatException{

        double playerIncial = player.getBalance();
        double receiverInicial = receiver.getBalance();

        player.creditValue(value);
        receiver.debitValue(value);

        if( player.getBalance() == playerIncial && receiver.getBalance() == receiverInicial){
             return false;
        }

        return true;
    }

    public void receive(CreditCard card, double value){
        card.creditValue(value);
    }

    public boolean pay(CreditCard card, double value){

        double valoInicial = card.getBalance();
        card.debitValue(value);

        if(valoInicial != card.getBalance()){

            return true;
        }


        return false;
    }
}

