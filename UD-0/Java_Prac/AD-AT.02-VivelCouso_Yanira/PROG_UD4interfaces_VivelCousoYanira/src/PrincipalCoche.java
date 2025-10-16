import java.util.Arrays;

public class PrincipalCoche {
    public static void main(String[] args) throws Exception {
        // Array
        Vehiculo[] flotaVehiculos = new Vehiculo[3];

        flotaVehiculos[0] = new Coche("ABCD1", 3, 2000, 2);
        flotaVehiculos[1] = new Coche("EFGH2", 5, 2020, 4);
        flotaVehiculos[2] = new Coche("ABCD1", 3, 2000, 2);
        
        // Comprobamos equals
        System.out.println("Comprobamos si coche0 y coche2 son iguales con equals:");

        if(flotaVehiculos[0].equals(flotaVehiculos[2])){
            System.out.println("El coche0 y el coche2 están repetidos.");
        } else {
            System.out.println("Coche0 y coche2 son diferentes.");
        }

        System.out.println();

        // Comprobamos compareTo
        System.out.println("Comparamos el número de puertas entre coche0 y coche2:");
        Coche c0 = (Coche) flotaVehiculos[0];
        Coche c2 = (Coche) flotaVehiculos[2];
        int resultado = c0.compareTo(c2);

        if (resultado == 0) {
            System.out.println("Coche0 y coche2 tienen el mismo número de puertas.");
        } else if (resultado == 1) {
            System.out.println("Coche0 tiene más puertas que Coche2.");
        } else if (resultado == -1) {
            System.out.println("Coche0 tiene menos puertas que Coche2.");
        } else {
            System.out.println("Error.");
        }

        System.out.println();

        // Ordenar
        CompararMatricula comparador = new CompararMatricula();

        Arrays.sort(flotaVehiculos, comparador);

        System.out.println("Ordenamos la flota por matrícula:");
        
        for (Vehiculo vl : flotaVehiculos) {
            System.out.println(vl);
        }
    }
}
