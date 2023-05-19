import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        double x = 82 * (Math.pow(9063, 6));

        System.out.println(x);

        //differentiate("3x");
        //differentiate("x^2-x");
        //differentiate("x^3-x^2");
        //differentiate("13x^4-22x^3+4x^2-3x+2"); //22x-ből 2x lett
        //differentiate("1xx");

        System.out.println("pow: "+Math.pow(2,-3));

        String str = "-13x^4-1x^3+4x^2-3x+2";
        //CapitalizeFirstLetters2("valami");
        Equation.addOnesToAString("x^2-x");
        Equation.addOnesToAString("-13x^4-x^3+4x^2-3x+2");
        Equation.addOnesToAString("13x^4-x^3+4x^2-3x+2");
        Equation.addOnesToAString("13x^4-x^3+4x^2-x+2");
        Equation.addOnesToAString("-5x^2+10x+4");
        //String str2 = "x^3-x^2";
        System.out.println("------------------------------");
        //Equation.coefficientsAndExponentsLists(Equation.addOnesToAString("-13x^4-x^3+4x^2-3x+2"));
        Equation.coefficientsAndExponentsLists(Equation.addOnesToAString("-5x^2+10x+4"));
        Equation.coefficientsAndExponentsLists(Equation.addOnesToAString("-5x^2+10x"));
        List<String> coefficients1 = new ArrayList<>();
        coefficients1.add("-5");
        coefficients1.add("10");
        coefficients1.add("4");
        List<String> exponents1  = new ArrayList<>();
        exponents1.add("2");
        exponents1.add("1");
        exponents1.add("0");
        Equation.derivated(coefficients1, exponents1, 3);


        //String[] split = str.split("[^\\^0-9a-z]");// nem rossz
        String[] split2 = str.split("[^\\^[-0-9]]");// majdnem jó x-re nem jó 1x-re jó
        String[] split4 = str.split("-?\\d+");// nem rossz
        //String[] split3 = str.split("(?-)[0-9]");// nem rossz
        List<String> coefficients = new ArrayList<>();
        List<String> exponents  = new ArrayList<>();
        //System.out.println("splitted: "+ Arrays.toString(split2));
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
        //System.out.println("coefficients:"+coefficients);
        //System.out.println("exponents: "+exponents);

        /*
        Polynomial polynomial = new Polynomial(3,3);

        System.out.println("poly: "+polynomial.differentiate());

        Polynomial q1 = new Polynomial( 2, 3 );
        Polynomial q2 = new Polynomial( 3, 0 );
        Polynomial q = q1.plus( q2 ); // 3x^2 + 5

        System.out.println("q: "+q);
        System.out.println("q_deriv: "+q.differentiate());
        */




    }

    public static void differentiate(String equation) {
        List<String> coefficients = new ArrayList<>();
        List<String> exponents  = new ArrayList<>();

        if(equation.length() > 3){
            equation = equation.substring(0, equation.length()-2); //NEM jó mert 23456x is lehet a vége
        }
        //equation = equation.replace("x", "*x");

        System.out.println("subString: "+equation);

        if(String.valueOf(equation.charAt(0)).equals("x")){
            coefficients.add("1");
        }

        StringBuilder newStr = new StringBuilder();
        for (int i = 1; i < equation.length(); i++) {
            char charAtPosition = equation.charAt(i);
            if(String.valueOf(equation.charAt(i)).equals("x")){
                if(String.valueOf(equation.charAt(i-1)).equals("+") ) {
                    //newStr.append("1");
                    coefficients.add("1");

                }else if(String.valueOf(equation.charAt(i-1)).equals("-")){
                    coefficients.add("-1");
                } else {
                    newStr.append(equation.charAt(i - 1));
                    System.out.println("equation.charAt(i - 1): "+equation.charAt(i - 1));
                    coefficients.add(String.valueOf(equation.charAt(i - 1)));
                }

            }
            if(String.valueOf(equation.charAt(i)).equals("^")) {
                newStr.append(equation.charAt(i + 1));
                exponents.add(String.valueOf(equation.charAt(i + 1)));
                /*
                String s = String.valueOf(equation.charAt(i + 1));
                String replaced = s.replace(s, "Z");
                newStr.append(replaced);
                */
            }
        }

        //System.out.println("newStr:"+newStr.toString());
        //System.out.println("coefficients:"+coefficients);
        //System.out.println("exponents: "+exponents);
        //return null;
    }

    public static void CapitalizeFirstLetters2(String str) {
        // 2 substring és az 1 betűsnek toUpperCase() + concat egyszerűbb
        List<String> StringList = new ArrayList<String>();
        List<String> StringList2 = new ArrayList<String>();
        String[] strArray = str.split(" ");
        System.out.println("strArray: " + str);
        System.out.println("strArray.length: " + strArray.length);
        System.out.println("Arrays.toString: " + Arrays.toString(strArray));
        for (int i = 0; i < strArray.length; i++) {
            char[] ch = strArray[i].toCharArray();
            ch[0] = strArray[i].toUpperCase().charAt(0);
            strArray[i] = strArray[i].replace(strArray[i].charAt(0), ch[0]);
            StringList.add(strArray[i]);//List<String> be való feltöltés, hogy a joining-et lehessen használni/stringet csinál belőle/

        }
        System.out.println("StringList: " + StringList);
        //összefűzés, hogy a [lista]-ból rendes String legyen:
        String result1 = StringList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(" "));
        System.out.println("StringList1 összefűzve: " + result1);
    }

    public static class Polynomial {
        private int[] coef;  // coefficients
        private int deg;     // degree of polynomial (0 for the zero polynomial)


        // a * x^b
        public Polynomial(int a, int b) {
            coef = new int[b + 1];
            coef[b] = a;
            deg = degree();
        }

        // return the degree of this polynomial (0 for the zero polynomial)
        public int degree(){
            int d = 0;
            for( int i = 0; i < coef.length; i++ )
                if( coef[ i ] != 0 ) d = i;
            return d;
        }

        @Override
        // convert to string representation
        public String toString(){
            if( deg == 0 ) return "" + coef[ 0 ];
            if( deg == 1 ) return coef[ 1 ] + "x + " + coef[ 0 ];
            String s = coef[ deg ] + "x^" + deg;
            for( int i = deg - 1; i >= 0; i-- ){
                if( coef[ i ] == 0 ){
                    continue;
                }else if( coef[ i ] > 0 ){
                    s = s + " + " + ( coef[ i ] );
                }else if( coef[ i ] < 0 ) s = s + " - " + ( -coef[ i ] );
                if( i == 1 ){
                    s = s + "x";
                }else if( i > 1 ) s = s + "x^" + i;
            }
            return s;
        }

        public Polynomial differentiate(){
            if( deg == 0 ) return new Polynomial( 0, 0 );
            Polynomial deriv = new Polynomial( 0, deg - 1 );
            deriv.deg = deg - 1;
            for( int i = 0; i < deg; i++ )
                deriv.coef[ i ] = ( i + 1 ) * coef[ i + 1 ];
            return deriv;
        }

        // return c = a + b
        public Polynomial plus( Polynomial b ){
            Polynomial a = this;
            Polynomial c = new Polynomial( 0, Math.max( a.deg, b.deg ) );
            for( int i = 0; i <= a.deg; i++ ) c.coef[ i ] += a.coef[ i ];
            for( int i = 0; i <= b.deg; i++ ) c.coef[ i ] += b.coef[ i ];
            c.deg = c.degree();
            return c;
        }
    }




}
