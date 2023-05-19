import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Equation {

    public static BigInteger differentiate(String equation, long x) {

        System.out.println("equation: "+equation);
        System.out.println("x: "+x);

        String newStr = addOnesToAString(equation);
        List<List<String>> coefficientsAndExponentsLists = coefficientsAndExponentsLists(newStr);
        List<String> makeACompleteList = makeACompleteList(coefficientsAndExponentsLists.get(1));
        return derivated(coefficientsAndExponentsLists.get(0), makeACompleteList, x);
    }



    public static String addOnesToAString(String equation) { // "13x^4-x^3+4x^2-3x+2" --> "13x^4-1x^3+4x^2-3x^1+2x^0"
        System.out.println("LENGTH: "+equation.length());
        String splusOne = null;
        StringBuilder newStr = new StringBuilder();

        if (String.valueOf(equation.charAt(0)).equals("x")) {
            String s = String.valueOf(equation.charAt(0));
            String replaced = s.replace(s, "1x");
            newStr.append(replaced);
        } else {
            newStr.append(equation.charAt(0));
        }

        for (int i = 1; i < equation.length(); i++) {
            String s = String.valueOf(equation.charAt(i-1));
            if(i < equation.length()-1){
                splusOne = String.valueOf(equation.charAt(i+1));
            }
            // x, or px, or x^q cases:
            if (String.valueOf(equation.charAt(i)).equals("x") && ((s.equals("-")) || s.equals("+")) && ((splusOne.equals("-")) || splusOne.equals("+"))) {
                newStr.append("1x^1");
            }else if(String.valueOf(equation.charAt(i)).equals("x") && ((splusOne.equals("-")) || splusOne.equals("+"))) {
                newStr.append("x^1");
            }else if(String.valueOf(equation.charAt(i)).equals("x") && ((s.equals("-")) || s.equals("+"))) {
                newStr.append("1x");
            } else {
                newStr.append(equation.charAt(i));
            }

        }
        if(Character.isDigit(equation.charAt(equation.length()-1))) {
            newStr.append("x^0");
        }
        if(Character.isLetter(equation.charAt(equation.length()-1))) {
            newStr.append("^1");
        }
        return newStr.toString();
    }


    public static List<List<String>> coefficientsAndExponentsLists(String str){ // Make a coefficients And Exponents Lists //  arg: (String newStr)
        String[] split2 = str.split("[^\\^[-0-9]]");// majdnem jó, x-re nem jó, 1x-re jó
        List<String> coefficients = new ArrayList<>();
        List<String> exponents  = new ArrayList<>();
        List<List<String>> coefficientsAndExponents  = new ArrayList<>();
        System.out.println("splitted: "+ Arrays.toString(split2));
        for (int i = 0; i <split2.length ; i++) {
            if(split2[i].contains("^") && split2[i].contains("-")){
                String[] subSplit = split2[i].split("-");
                coefficients.add("-".concat(subSplit[1]));
                exponents.add(subSplit[0]);
            } else if (split2[i].contains("^")){
                exponents.add(split2[i]);

            } else if(!split2[i].isEmpty()){
                coefficients.add(split2[i]);
            }
        }
        if(!str.contains("^") || String.valueOf(str.charAt(str.length()-1)).equals("x")){//elsőfokú vagy hiányos
            exponents.add("^1");
        }
        coefficientsAndExponents.add(coefficients);
        coefficientsAndExponents.add(exponents);
        return coefficientsAndExponents;
    }


    public static  BigInteger derivated(List<String> coefficients, List<String> exponents, long x){ //coefficientsAndExponents.get(0), makeACompleteList(List<String> exponents)
        System.out.println("EXPs: "+exponents);
        BigInteger v = BigInteger.ZERO;
        BigInteger temp = BigInteger.ZERO;
        for (int i = 0; i < coefficients.size(); i++) { // teljes ned foku eq:coefficients.size()-1, hiányos: coefficients.size()
            String coefficient = coefficients.get(i);
            String exponent = exponents.get(i);
            // BigInteger not accepts negative exponent -->try/catch
            try {
                v = v.add(((new BigInteger(coefficient))).multiply(new BigInteger(exponent)).multiply((new BigInteger(String.valueOf(x))).pow((Integer.valueOf(exponent)) - 1)));
                System.out.println("v_temp: "+v);
                temp = v;
            } catch (ArithmeticException e) {
                e.printStackTrace();
            } finally {
                v = temp;
            }

        }
        System.out.println("result: " + v);
        return v;
    }

    public static List<String> makeACompleteList(List<String> exponents){ // coefficientsAndExponents.get(1)
        //List<String> tempList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < exponents.size(); i++) {
            resultList.add(exponents.get(i).substring(1));
        }
        Collections.sort(resultList,Collections.reverseOrder());
        return resultList;
    }
}
