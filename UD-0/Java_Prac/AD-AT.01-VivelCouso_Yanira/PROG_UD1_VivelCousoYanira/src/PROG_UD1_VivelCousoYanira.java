import java.util.Scanner;

public class PROG_UD1_VivelCousoYanira {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        // 1) Método resto de división

        System.out.println("Metodo 1: Resto de una división");
        System.out.println("Introduce el primer número:");
        double num1 = sc.nextDouble();
        System.out.println("Introduce el segundo número:");
        double num2 = sc.nextDouble();
        System.out.println("El resultado es " + restoDivision(num1, num2) + "."); //Llama a la función

        System.out.println("");

        // 2) Método existe carácter

        System.out.println("Metodo 2: Existencia de caracter");
        System.out.println(existeCaracter("Hola mundo", 'o'));
        
        System.out.println("");
        
        // 3) Método suma con parte entera y decimal

        System.out.println("Metodo 3: Suma de números");
        double n1 = 12.7;
        double n2 = 3.4;
        metodoFloat(n1, n2);

        sc.close();
    }

    private static int restoDivision (double num1, double num2) {      
        int n1 = (int) Math.floor(num1);
        int n2 = (int) Math.floor(num2);

        return n1%n2;
    }

    private static String existeCaracter (String cadena, char caracter) {
        if (cadena.indexOf(caracter) >= 0){
            return "Si, '" + caracter + "' existe en '" + cadena + "'.";
        } else return "No, '" + caracter + "' no existe en '" + cadena + "'.";
    }

    private static void metodoFloat (double n1, double n2) {
        float resultado = (float) (n1 + n2);
        
        int entero = (int) resultado;
        float decimal = resultado-entero;

        System.out.println("Resultado suma: " + resultado);
        System.out.println("Parte entera del resultado: " + entero);
        System.out.println("Parte decimal del resultado: " + decimal);
    }
}
