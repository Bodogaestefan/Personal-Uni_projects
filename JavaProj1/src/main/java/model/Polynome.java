package model;

import java.util.ArrayList;

public class Polynome{
    ArrayList<Monome> monomes;

    public Polynome() {
    }
    public Polynome(ArrayList<Monome> monomes) {
        this.monomes = monomes;
    }

    public ArrayList<Monome> getMonomes() {
        return monomes;
    }

    public void setMonomes(ArrayList<Monome> monomes) {
        this.monomes = monomes;
    }

    public boolean checkZero(){
        boolean zero=false;
        for (Monome m : this.monomes){
            if (m.coef != 0){
                zero = true;
            }
        }
        if(zero == true){
            return true;
        }
        return false;
    }
    public void deleteZero(){
        ArrayList<Monome> result = new ArrayList<Monome>();
        for(Monome m: this.getMonomes()){
            if(m.coef != 0){
                result.add(m);
            }
        }
        this.setMonomes(result);
    }
}

