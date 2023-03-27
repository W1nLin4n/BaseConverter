public class Main {

    public static void main(String[] args) {
        System.out.print("Enter base you want to convert from: ");
        int base1 = Reader.readInt(2, 36);
        System.out.print("Enter base you want to convert to: ");
        int base2 = Reader.readInt(2, 36);
        System.out.println("Enter number you want to convert");
        UniversalNumber number = readNumber(base1);
        System.out.println(number.convertToBase(base2));
    }

    /**
     * Method to read Universal Number
     * @param base Base of universal number
     * @return Universal Number
     */
    private static UniversalNumber readNumber(int base){
        String number;
        do{
            System.out.print("Please, enter correct number: ");
            number = UniversalNumber.normalizeStringNumberForm(Reader.readLine());
        }while(!UniversalNumber.isNumber(number, base));
        return new UniversalNumber(number, base);
    }

}