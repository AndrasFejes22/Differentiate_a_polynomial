import java.math.BigInteger;

public class Big {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("0");
        System.out.println("add:"+bigInteger.add(new BigInteger("2")));
        System.out.println("pow: "+bigInteger.pow(-3));
        // v = v.add(((new BigInteger(coefficient)).multiply(new BigInteger(exponent))).multiply((new BigInteger(exponent).add(new BigInteger("-1"))).pow((int) x)));
        //v += Double.parseDouble(coefficients.get(i)) * Double.parseDouble(exponents.get(i)) * (Math.pow(x, (Double.parseDouble(exponents.get(i))) - 1));
        for (int i = 0; i < 3; i++) {
            System.out.println("i: "+i);
            bigInteger = bigInteger.add(((new BigInteger(String.valueOf(3-i))).multiply(new BigInteger(String.valueOf(2-i)))).multiply((new BigInteger(String.valueOf(3))).pow(2-i-1)));
            System.out.println("tmp: "+bigInteger);
        }
        System.out.println("result: "+bigInteger);
    }
}
