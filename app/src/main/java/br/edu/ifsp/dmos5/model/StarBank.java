package br.edu.ifsp.dmos5.model;

import java.util.List;

public class StarBank {

    private static StarBank instance = null;

    private StarBank(){}

    public static StarBank getInstance() {
        if(instance == null){
            instance = new StarBank();
        }
        return instance;
    }

    public void startCreditCards(){

        List<CreditCard>cards = null;
        CreditCard card = null;

        for(int i = 0; i<6; i++){
            card = new CreditCard();
            cards.add(card);
            card = null;
        }


    }

    public void roundCompleted(CreditCard card, double value){

    }

    public boolean transfer (CreditCard card1, CreditCard card2, double value){
        return true;
    }

    public void receive(CreditCard card, double value){

    }

    public boolean pay(CreditCard card, double value){
        return true;
    }
}

