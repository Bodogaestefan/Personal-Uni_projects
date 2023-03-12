package model;

import view.Calc;

import javax.swing.*;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvPoli {

    public static boolean isDouble(String s) {
        try
        {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }

    }
        public static Monome splitMonome(String mon) {
            Monome monome = new Monome(0,0,'-');
            double coef = 0;
            int pow = 0;
            int i = 0;
            int sign=-1;
            if(mon.charAt(i) != '-'){
                sign = 1;
                if(mon.charAt(i) == '+')
                    i++;
            }
            else{
                i++;
            }
            if(isDouble(mon+i)){
                try {
                    coef = Double.parseDouble(mon.substring(i,mon.length()));
                }
                catch(Exception e){

                }

            }
            else{
                int j = i;
                while(isDouble(mon.substring(i,j+1))){
                    j++;
                }
                if(j>0 && isDouble(mon.substring(i,j)))
                    coef = Double.parseDouble(mon.substring(i,j));
                else if(mon.substring(i,i+1).equals("x"))
                    coef = 1;

                if(mon.length() > j+2 && mon.substring(j,j+2).equals("x^")){
                    i = j+2;
                    j = i;
                    while(j<mon.length() && isDouble(mon.substring(i,j+1))){
                        j++;
                    }
                    pow = Integer.parseInt(mon.substring(i,j));
                }
                else if(mon.length() >= j && mon.substring(j,j+1).equals("x")){
                    pow = 1;
                }
            }


            System.out.println(coef + " "+pow);
            monome.setCoef(sign*coef);
            monome.setPow(pow);
            return monome;
        }
       public static Polynome convertP(String exp) {
           Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
           Matcher matcher = pattern.matcher(exp);
           ArrayList<Monome> monomes = new ArrayList<Monome>();
           int x = 0;
           Monome m;
           int bigP = 0;
           while (matcher.find()) {
               m = splitMonome(matcher.group(1));
               if(m.pow>bigP)
                   bigP = m.pow;
               monomes.add(m);
               x = x + 1;
           }

           Polynome polynome = new Polynome(monomes);
           return polynome;
       }


}
