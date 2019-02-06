package org.arpico.groupit.receipt.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class CurrencyFormat {
	public CurrencyFormat() {
    }

   private static final String[] majorNames = {
    "",
    " Thousand",
    " Million",
    " Billion",
    " Trillion",
    " Quadrillion",
    " Quintillion"
    };

  private static final String[] tensNames = {
    "",
    " Ten",
    " Twenty",
    " Thirty",
    " Forty",
    " Fifty",
    " Sixty",
    " Seventy",
    " Eighty",
    " Ninety"
    };

  private static final String[] numNames = {
    "",
    " One",
    " Two",
    " Three",
    " Four",
    " Five",
    " Six",
    " Seven",
    " Eight",
    " Nine",
    " Ten",
    " Eleven",
    " Twelve",
    " Thirteen",
    " Fourteen",
    " Fifteen",
    " Sixteen",
    " Seventeen",
    " Eighteen",
    " Nineteen"
    };   
  public String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
        soFar = numNames[number % 100];
        number /= 100;
       }
    else {
        soFar = numNames[number % 10];
        number /= 10;

        soFar = tensNames[number % 10] + soFar;
        number /= 10;
       }
    if (number == 0) {
            return soFar;
        }
    return numNames[number] + " Hundred " + soFar;
}

    public String convert(int number) {
        /* special case */
        if (number == 0) { return "Zero"; }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "negative";
          }

        String soFar = "";
        int place = 0;

        do {
          int n = number % 1000;
          if (n != 0){
             String s = convertLessThanOneThousand(n);
             soFar = s + majorNames[place] + soFar;
            }
          place++;
          number /= 1000;
          } while (number > 0);
        
        if (soFar.endsWith(" and")) {
             soFar = soFar.substring(0,soFar.length()-4)  ;
           }
        if (soFar.endsWith(" and Thousand")) {
                  soFar = soFar.substring(0,soFar.length()-12)  ;
                  soFar += " Thousand";
                }
        
        return (prefix + soFar).trim();
    }
    
    public String numberToWords(double prm_dblAmount){
        
      int m_intWhole = 0;
      int m_intDecimal = 0;
      String m_strValue;
      String m_strWhole;
      String m_strDecimal;
        try {
            
            m_strValue = String.valueOf(NumberFormat.getInstance().format(prm_dblAmount)).replace(",","");   
            m_strValue = m_strValue.replace(".",",");
            m_intWhole = Integer.parseInt(m_strValue.split(",")[0]);
            String strSplitInDec[] = m_strValue.split(",");
            if(strSplitInDec.length > 1) { 
             m_strDecimal = strSplitInDec[1];
            }else{
             m_strDecimal = "0";               
            }
            
            m_strDecimal = "." + m_strDecimal + "0";
            BigDecimal m_bgdDecimalPoint = new BigDecimal(m_strDecimal);
            
            BigDecimal m_bdgDecimal = m_bgdDecimalPoint.multiply(new BigDecimal("100"));
                       
            m_intDecimal = m_bdgDecimal.intValue();
            
            m_strWhole = convert(m_intWhole);
            m_strDecimal = convert(m_intDecimal);
            
            if(m_strDecimal.equalsIgnoreCase("Zero")){
                
              return m_strWhole + " Only";  
            } 
            else{
               return m_strWhole + " and " + " cents " + m_strDecimal + " Only";
            }
            
        } catch (NumberFormatException ex) {
            Logger log = Logger.getLogger(""); log.log(Level.WARNING, "App Error", ex);
            return new String("");
        }
      
    }

}
