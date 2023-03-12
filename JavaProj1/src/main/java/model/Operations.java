package model;

import javax.swing.*;
import java.security.cert.PolicyNode;
import java.sql.PreparedStatement;
import java.util.*;

public class Operations {

    public static String printP(Polynome p){
        ArrayList<Monome> m = p.getMonomes();
        int i=0;
        String result="";
        while(i < m.size()){
            if(m.get(i).getPow()==0 && m.get(i).getCoef()!=0.0){  // 4
                if(result!="" && m.get(i).getCoef()>0) //...+4
                    result = result+'+';
                result = result + m.get(i).getCoef();
            }
            else if(m.get(i).getPow() == 1 && m.get(i).getCoef()!=0.0){ //x
                if(result!="" && m.get(i).getCoef()>0) ///+2x
                    result = result+'+';
                if(m.get(i).getCoef()==1.0)
                    result = result +"x";
                else if(m.get(i).getCoef()==-1.0)
                    result = result+"-x";
                else result = result + m.get(i).getCoef()+"x";
            }
            else if (m.get(i).getCoef()!=0.0){
                if (result != "" && m.get(i).getCoef()>0)
                    result = result + '+';
                if(m.get(i).getCoef()!=1 && m.get(i).getCoef()!=-1)
                    result = result + m.get(i).getCoef();
                else if(m.get(i).getCoef()==-1.0)
                    result = result +"-";
                result = result + "x^" + m.get(i).getPow();
            }
            if(result.equals(""))
                result = "0";
            i++;
        }
        return result;
    }

       public static Polynome add(Polynome p1, Polynome p2){
            ArrayList<Monome> m1 = p1.getMonomes();
            ArrayList<Monome> m2 = p2.getMonomes();
            ArrayList<Monome> mr = new ArrayList<Monome>();
            Monome m = new Monome(0,0,'+');
            int i=0,j=0;

           while (i<m1.size() && j<m2.size()){

               if(m1.get(i).pow == m2.get(j).pow){
                   m = new Monome(0,0,'+');
                   m.coef = m1.get(i).coef+m2.get(j).coef; //power is the same for both polynomials
                   m.pow = m1.get(i).pow;
                   mr.add(m);
                   i++;
                   j++;
               }
               else if(m1.get(i).pow>m2.get(j).pow){//m1 has bigger power at the current item
                   m = new Monome(0,0,'+');
                   m.coef = m1.get(i).coef;
                   m.pow = m1.get(i).pow;
                   mr.add(m);
                   i++;
               }
               else if(m1.get(i).pow<m2.get(j).pow){//m2 has bigger power at the current item
                   m = new Monome(0,0,'+');
                   m.coef = m2.get(j).coef;
                   m.pow = m2.get(j).pow;
                   mr.add(m);
                   j++;
               }
               System.out.println(m.coef);
           }

           while(i<m1.size()){
               m = new Monome(0,0,'+');
               m.coef = m1.get(i).coef; //remaining m1 monomes
               m.pow = m1.get(i).pow;
               mr.add(m);
               i++;
               System.out.println(m.coef);
           }
           while(j<m2.size()){
               m = new Monome(0,0,'+');
               m.coef = m2.get(j).coef;   //remaining m2 monomes
               m.pow = m2.get(j).pow;
               mr.add(m);
               j++;
               System.out.println(m.coef);
           }
            Polynome result=new Polynome(mr);
            return result;
        }

    public static Polynome sub(Polynome p1, Polynome p2){
        ArrayList<Monome> m1 = p1.getMonomes();
        ArrayList<Monome> m2 = p2.getMonomes();
        ArrayList<Monome> mr = new ArrayList<Monome>();
        Monome m = new Monome(0,0,'+');
        int i=0,j=0;

        while (i<m1.size() && j<m2.size()){

            if(m1.get(i).pow == m2.get(j).pow){
                m = new Monome(0,0,'+');
                m.coef = m1.get(i).coef-m2.get(j).coef; //power is the same for both polynomials
                m.pow = m1.get(i).pow;
                mr.add(m);
                i++;
                j++;
            }
            else if(m1.get(i).pow>m2.get(j).pow){//m1 has bigger power at the current item
                m = new Monome(0,0,'+');
                m.coef = m1.get(i).coef;
                m.pow = m1.get(i).pow;
                mr.add(m);
                i++;
            }
            else if(m1.get(i).pow<m2.get(j).pow){//m2 has bigger power at the current item
                m = new Monome(0,0,'+');
                m.coef = -m2.get(j).coef;
                m.pow = m2.get(j).pow;
                mr.add(m);
                j++;
            }
            System.out.println(m.coef);
        }

        while(i<m1.size()){
            m = new Monome(0,0,'+');
            m.coef = m1.get(i).coef;   //remaining m1 monomes
            m.pow = m1.get(i).pow;
            mr.add(m);
            i++;
            System.out.println(m.coef);
        }
        while(j<m2.size()){
            m = new Monome(0,0,'+');
            m.coef = -m2.get(j).coef;    //remaining m2 monomes
            m.pow = m2.get(j).pow;
            mr.add(m);
            j++;
            System.out.println(m.coef);
        }
        Polynome result=new Polynome(mr);
        return result;
    }
    public static ArrayList<Monome> sortMonomes(ArrayList<Monome> m){
        int i=0,j;
        while(i<m.size()-1){
            j = i+1;
            while(j<m.size()) {
                if(m.get(i).pow<m.get(j).pow){
                    Collections.swap(m,i,j);
                }
                j++;
            }
            i++;
        }

        return m;
    }

    public static Polynome Compress(Polynome p){
        ArrayList<Monome> m1 = p.getMonomes();
        ArrayList<Monome> mr = new ArrayList<Monome>();
        Monome m = new Monome(0,-1,'+');
        m1 = sortMonomes(m1);

        for(Monome mi1:m1){
            if(m.getPow()!=mi1.getPow()){
                    if(m.getPow()==-1){
                        m.setPow(mi1.getPow());
                        m.setCoef(mi1.getCoef());
                    }
                    else{
                        mr.add(m);
                        m = new Monome(mi1.getCoef(),mi1.getPow(),'+');
                    }
            }
            else{
                m.setCoef(m.getCoef()+mi1.getCoef());
            }
        }
        mr.add(m);
        Polynome result = new Polynome(mr);
        return result;
    }

    public static Polynome Multiply(Polynome p1, Polynome p2){
        ArrayList<Monome> m1 = p1.getMonomes();
        ArrayList<Monome> m2 = p2.getMonomes();
        ArrayList<Monome> mr = new ArrayList<Monome>();
        Monome m = new Monome(0,0,'+');
        int i=0,j=0;

        for(Monome mi1 : m1){
            for(Monome mi2:m2){
                m = new Monome(0,0,'+');
                m.pow = mi1.pow + mi2.pow;
                m.coef = mi1.coef * mi2.coef;
                mr.add(m);
            }
        }
        Polynome result = new Polynome(mr);
        result.deleteZero();
        return Compress(result);

    }

    public static Polynome Derivate(Polynome p1){
        ArrayList<Monome> m1 = p1.getMonomes();
        ArrayList<Monome> mr = new ArrayList<Monome>();
        Monome m = new Monome(0,0,'+');
        for(Monome mi1 : m1){
            m = new Monome(0,0,'+');
            m.coef = mi1.getCoef()*mi1.getPow();
            m.pow = mi1.getPow()-1;
            mr.add(m);
        }
        Polynome result = new Polynome(mr);
        return result;
    }

    public static Polynome Integrate(Polynome p1){
        ArrayList<Monome> m1 = p1.getMonomes();
        ArrayList<Monome> mr = new ArrayList<Monome>();
        Monome m = new Monome(0,0,'+');
        for(Monome mi1 : m1){
            m = new Monome(0,0,'+');
            m.pow = mi1.getPow()+1;
            m.coef = mi1.getCoef()/(mi1.getPow()+1);
            mr.add(m);
        }
        Polynome result = new Polynome(mr);
        return result;
    }

    public static Monome divideMon(Monome m1, Monome m2){
        Monome m = new Monome(0,0,'+');
        if(m2.coef!=0){
            m.setCoef(m1.coef/m2.coef);
        }
        m.setPow(m1.pow-m2.pow);
        return m;
    }
    public static ArrayList<Monome> multiplyMonomes(ArrayList<Monome> am, Monome m,int sign){
        for (Monome m1:am){
            m1.setCoef(sign*m1.getCoef()*m.getCoef());
            m1.setPow(m1.getPow()+m.getPow());
        }

        return am;
    }

    public static Polynome Divide(Polynome p1, Polynome p2){
        ArrayList<Monome> m1 = p1.getMonomes();
        ArrayList<Monome> m2 = p2.getMonomes();
        ArrayList<Monome> mr = new ArrayList<Monome>();
        ArrayList<Monome> mq = new ArrayList<Monome>();
        ArrayList<Monome> md = new ArrayList<Monome>();
        ArrayList<Monome> m0;
        Polynome r = new Polynome(m1);
        Polynome d = new Polynome(m2);
        Polynome q = new Polynome(mq);

        Polynome t = new Polynome(md);
        Polynome mul;
        int pt;
        double ct;
      //  Monome rest = new Monome(0,0,'+');
        Monome m = new Monome(0,0,'+');
       // m = divideMon(m1.get(0),m2.get(0));
        mr.add(m);
        if(p2.checkZero() == false){
            JOptionPane.showMessageDialog(null,"Division by 0");
            throw new RuntimeException("Division by 0");
        }
        if(m1.get(0).pow>=m2.get(0).pow)
        {
            r=p1;
            d=p2;
        }
        else{
            r=p2;
            d=p1;
        }
        Monome tt;
        while(r.checkZero() && r.getMonomes().get(0).pow >= d.getMonomes().get(0).pow){
            m0 = new ArrayList<Monome>();
            pt= r.getMonomes().get(0).pow-d.getMonomes().get(0).pow;
            ct =  r.getMonomes().get(0).coef/d.getMonomes().get(0).coef;
            tt = new Monome(ct,pt,'-');
            t = new Polynome(m0);
            t.monomes.add(tt);
            q= add(q,t);
            mul = Multiply(t,d);
            r = sub(r,mul);
            r.deleteZero();
            d.deleteZero();
        }
        if(r.checkZero()!=false){
            JOptionPane.showMessageDialog(null,"Remainder: "+printP(r));
        }



        return q;

    }

}


