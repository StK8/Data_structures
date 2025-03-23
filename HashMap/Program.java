
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // You can test the class here
        HashMap<String, Integer> numbers = new HashMap<>();
        
        for (int i = 0; i < 100; i++) {
            numbers.add(String.valueOf(i), i);
        }
        
        System.out.println(numbers.getValue("75"));
        System.out.println("Number of values: " + numbers.getNumberOfValues());

        numbers.remove("80");
        System.out.println("Number of values: " + numbers.getNumberOfValues());
        System.out.println(numbers.getValue("80"));

    }

}
