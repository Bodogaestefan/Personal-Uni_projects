package model;

public class Monome {
    double coef;
    int pow;

    public Monome(double coef, int pow, char sign) {
        this.coef = coef;
        this.pow = pow;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }


}
