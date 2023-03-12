package br.edu.ifsp.dmos5.model;

import android.widget.Toast;

public class CreditCard {

    private static int LAST_ID = 1;
    private int id;
    private double balance;

    public CreditCard(){
        this.setId(LAST_ID+1);
        LAST_ID++;
        this.setBalance(15000);
    }

    public void creditValue(double value){

        try{
            if (getBalance()  > 0){

                setBalance(getBalance() + value);
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("Valor invalido");
        }

    }

    public void debitValue(double value){
        try{
            if (value < getBalance() ){

               setBalance(getBalance() - value);
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("Saldo insuficiente");
        }

    }

    public int getId(){
        return id;
    }

    public double getBalance(){
        return balance;
    }

    public void setId( int id){
        this.id=id;
    }

    public void setBalance( double balance){
        this.balance = balance;
    }



}
