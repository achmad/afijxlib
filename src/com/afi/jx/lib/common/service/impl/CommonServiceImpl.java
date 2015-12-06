/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.service.impl;

import com.afi.jx.lib.common.service.CommonService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author achmad.ha
 */
@Service("commonService")
@Transactional(readOnly = true)
public class CommonServiceImpl implements CommonService {

    public String amountInWords(Object num) {
        BigDecimal bd;
        try {
            bd = new BigDecimal(num.toString());
        } catch (NumberFormatException ex) {
            return "";
        }
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,###.00", dfs);
        String newNum = df.format(bd);
        String result = "";
        if (newNum == null || newNum.equals("0") || newNum.equals("")) {
            return "Nol ";
        }
        String[] numOrders = new String[]{"", " Ribu ", " Juta ", " Milyar ", " Triliun "};
        String[] decimal = newNum.split(",");
        String[] intAmount = decimal[0].split(Pattern.quote("."));
        for (int i = 0; i < intAmount.length; i++) {
            String tempresult = convertNumberToString(intAmount[i]) + numOrders[intAmount.length - (i + 1)];
            if (tempresult.equals("Satu Ribu ")) {
                tempresult = "Seribu ";
            }
            result += tempresult;
        }
        return result;
    }

    private String convertNumberToString(String num) {
        String[] numWords = new String[]{"", "Satu", "Dua", "Tiga", "Empat",
            "Lima", "Enam", "Tujuh", "Delapan", "Sembilan"};
        String result = "";
        Integer intNum = new Integer(num);
        num = intNum.toString();
        char[] numStr = num.toString().toCharArray();
        if (numStr.length == 3) {
            result = numWords[Integer.parseInt(Character.toString(numStr[0]))] + " Ratus ";
            if (result.equals("Satu Ratus ")) {
                result = "Seratus ";
            }
            if (numStr[1] != '0') {
                if (numStr[1] == '1') {
                    if (numStr[2] == '0') {
                        result += " Sepuluh";
                    } else if (numStr[2] == '1') {
                        result += " Sebelas";
                    } else {
                        result += numWords[Integer.parseInt(Character.toString(numStr[2]))] + " Belas";
                    }
                } else {
                    result += numWords[Integer.parseInt(Character.toString(numStr[1]))] + " Puluh " + numWords[Integer.parseInt(Character.toString(numStr[2]))];
                }
            } else {
                result += numWords[Integer.parseInt(Character.toString(numStr[2]))];
            }
            result = result.replace("Nol", "");
        } else if (numStr.length == 2) {
            if (numStr[0] == '1') {
                if (numStr[1] == '0') {
                    result += "Sepuluh";
                } else if (numStr[1] == '1') {
                    result += "Sebelas";
                } else {
                    result += numWords[Integer.parseInt(Character.toString(numStr[1]))] + " Belas";
                }
            } else {
                result += numWords[Integer.parseInt(Character.toString(numStr[0]))] + " Puluh " + numWords[Integer.parseInt(Character.toString(numStr[1]))];
            }
            result = result.replace("Nol", "");
        } else if (numStr.length == 1) {
            result += numWords[Integer.parseInt(Character.toString(numStr[0]))];
            result = result.replace("Nol", "");
        }
        return result;
    }
}
