import java.util.Scanner;

public class LanguageTranslatorConsole {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Language Translation Tool ===");

        System.out.print("Enter text to translate: ");
        String text = sc.nextLine();

        System.out.print("Source Language (en/ta/hi/fr): ");
        String source = sc.nextLine();

        System.out.print("Target Language (en/ta/hi/fr): ");
        String target = sc.nextLine();

        System.out.println("\nTranslated Text:");

        // Simulated translation output
        if (text.equalsIgnoreCase("Hello World") && target.equals("ta")) {
            System.out.println("ஹலோ உலகம்");
        } else if (text.equalsIgnoreCase("Hello") && target.equals("hi")) {
            System.out.println("नमस्ते");
        } else {
            System.out.println("Translation Successful");
        }

        sc.close();
    }
}