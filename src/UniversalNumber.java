public class UniversalNumber {

    private boolean isNegative;
    private int integerPart;
    private double fractionalPart;
    private int numbersAfterDot;
    private static char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                                        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                                        'U', 'V', 'W', 'X', 'Y', 'Z'};

    public UniversalNumber(String number, int base){
        assert (isNumber(number, base));
        if(number.charAt(0) == '-')
            isNegative = true;
        numbersAfterDot = -1;
        for(int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                numbersAfterDot = number.length() - i - 1;
            }
        }
        integerPart = 0;
        for(int i = 0;  i < number.length() - numbersAfterDot - 1; i++){
            if(isNegative && i == 0)
                continue;
            int valueOfCurrentDigit = 0;
            if(Character.isDigit(number.charAt(i))){
                valueOfCurrentDigit = number.charAt(i) - '0';
            }else if(Character.isLetter(number.charAt(i))){
                valueOfCurrentDigit = number.charAt(i) - 'A' + 10;
            }
            integerPart *= base;
            integerPart += valueOfCurrentDigit;
        }
        double currentDigitMultiplier = 1;
        fractionalPart = 0;
        for(int i = number.length() - numbersAfterDot;  i < number.length(); i++){
            int valueOfCurrentDigit = 0;
            if(Character.isDigit(number.charAt(i))){
                valueOfCurrentDigit = number.charAt(i) - '0';
            }else if(Character.isLetter(number.charAt(i))){
                valueOfCurrentDigit = number.charAt(i) - 'A' + 10;
            }
            currentDigitMultiplier /= base;
            fractionalPart += valueOfCurrentDigit * currentDigitMultiplier;
        }
    }

    /**
     * Checks if string represents correct number
     * @param number String to check
     * @param base Base of desired number
     * @return True if string represents number, otherwise false
     */
    public static boolean isNumber(String number, int base){
        if(base > 36 || base < 2 || !number.equals(normalizeStringNumberForm(number))){
            return false;
        }
        boolean isAnyDigit = false;
        for(int i = 0; i < number.length(); i++){
            if(Character.isDigit(number.charAt(i)) || Character.isLetter(number.charAt(i))){
                isAnyDigit = true;
                break;
            }
        }
        if(!isAnyDigit)
            return false;
        int dots = 0;
        boolean isNumber = true;
        boolean isNegative = false;
        if(number.charAt(0) == '-')
            isNegative = true;
        for(int i = 0; i < number.length(); i++){
            if(isNegative && i == 0)
                continue;
            if((Character.isDigit(number.charAt(i)) && (number.charAt(i) - '0' >= base)) ||
                    (Character.isLetter(number.charAt(i)) && (number.charAt(i) - 'A' + 10 >= base))){
                isNumber = false;
                break;
            }else if(number.charAt(i) == '.'){
                dots++;
            }else if(!Character.isDigit(number.charAt(i)) && !Character.isLetter(number.charAt(i)) && number.charAt(i) != '.'){
                isNumber = false;
                break;
            }
        }
        if(dots > 1)
            isNumber = false;
        return isNumber;
    }

    /**
     * Changes all letters to capital and all commas to dots
     * @param number String to normalize
     * @return Normalized string
     */
    public static String normalizeStringNumberForm(String number){
        return number.toUpperCase().replace(',', '.');
    }

    /**
     * Converts this number to given base
     * @param base Base to convert to
     * @return Converted number
     */
    public String convertToBase(int base){
        String result = "";
        int integerPartCopy = integerPart;
        double fractionalPartCopy = fractionalPart;
        while(integerPartCopy > 0){
            result = "" + alphabet[(integerPartCopy % base)] + result;
            integerPartCopy /= base;
        }
        if(isNegative)
            result = "" + '-' + result;
        if(numbersAfterDot == -1)
            return result;
        result += '.';
        for(int i = 0; i < numbersAfterDot; i++){
            fractionalPartCopy *= base;
            result += alphabet[((int) fractionalPartCopy)];
            fractionalPartCopy -= ((int) fractionalPartCopy);
        }
        return result;
    }

}
