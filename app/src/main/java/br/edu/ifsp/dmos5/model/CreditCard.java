package br.edu.ifsp.dmos5.model;

import android.widget.Toast;

public class CreditCard {

    private static int LAST_ID = 0;
    private int id;
    private double balance;

    public CreditCard(){
        this.id = LAST_ID + 1;
        LAST_ID++;
        this.balance = 15000;
    }

    public void creditValue(double value){

        try{
            if (value > 0){

                balance += value;
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("Valor invalido");
        }

    }




    public void debitValue(double value){
        try{
            if (value > balance){

                balance -= value;
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("Valor invalido");
        }

    }


}
