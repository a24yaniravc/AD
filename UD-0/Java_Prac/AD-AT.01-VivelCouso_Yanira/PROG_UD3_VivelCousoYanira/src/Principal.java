import java.util.Scanner;

public class Principal {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    //--- Inicio juego ---//
    System.out.println("-- Iniciando partida --");
    System.out.println("");
    boolean creacionMagoCompleta = false;
    boolean creacionMonstruoCompleta = false;
    boolean creacionBosqueCompleta = false;

    // Crear mago
    System.out.println("Crear mago:");
    System.out.print("Inserte nombre: ");
    String nombreMago = sc.nextLine();
    System.out.print("Inserte vida (debe ser un número entero): ");
    int vida = sc.nextInt();
    System.out.print("Inserte nivel de magia (debe ser un número entero): ");
    int nivelMagia = sc.nextInt();
    sc.nextLine();
    System.out.println("El hechizo favorito debe ser uno de esta lista: ADIVINACION, NECROMANCIA, PIROMANCIA, INVOCACION");
    System.out.print("Inserte hechizo favorito: ");
    String hechizoString = sc.nextLine();

    Hechizo hechizo = null; 
    
    while (creacionMagoCompleta != true) {
      if (hechizoString.equals("ADIVINACION") 
        || hechizoString.equals("NECROMANCIA") 
        || hechizoString.equals("PIROMANCIA") 
        || hechizoString.equals("INVOCACION")) {
        switch (hechizoString) {
            case "ADIVINACION":
              hechizo = Hechizo.ADIVINACION;     
              break;
            case "NECROMANCIA":
              hechizo = Hechizo.NECROMANCIA;
            case "PIROMANCIA":
              hechizo = Hechizo.PIROMANCIA;
            case "INVOCACION":
              hechizo = Hechizo.INVOCACION;
            default:
              break;
        }
        creacionMagoCompleta = true;
      } else {
        System.out.println("El hechizo es inválido. Asegurese de que el texto está en mayusculas.");
        System.out.println("Además, recuerde que el hechizo favorito debe ser uno de esta lista: ADIVINACION, NECROMANCIA, PIROMANCIA, INVOCACION");
        System.out.print("Vuelva a insertar el hechizo favorito: ");
        hechizoString = sc.nextLine();
      }
    }
    
    Mago mago = new Mago(nombreMago, vida, nivelMagia, hechizo);
    System.out.println("");

    // Crear monstruo
    System.out.println("Crear monstruo:");
    System.out.print("Inserte nombre: ");
    String nombreMonstruo = sc.nextLine();
    System.out.print("Inserte vida (debe ser un número entero): ");
    int vidaMonstruo = sc.nextInt();
    System.out.print("Inserte fuerza (debe ser un número entero): ");
    int fuerzaMonstruo = sc.nextInt();
    sc.nextLine();
    System.out.println("El tipo debe ser uno de esta lista: OGRO, TROLL, ESPECTRO");
    System.out.print("Inserte tipo: ");
    String tipoString = sc.nextLine();

    TipoMonstruo tipo = null;

    while (creacionMonstruoCompleta != true) {
      if (tipoString.equals("OGRO") 
        || tipoString.equals("TROLL") 
        || tipoString.equals("ESPECTRO")) {
        switch (tipoString) {
            case "OGRO":
              tipo = TipoMonstruo.OGRO;
              break;
            case "TROLL":
              tipo = TipoMonstruo.TROLL;
              break;
            case "ESPECTRO":
              tipo = TipoMonstruo.ESPECTRO;
            default:
              break; 
        }
        creacionMonstruoCompleta = true;
      } else {
        System.out.println("El tipo es inválido. Asegurese de que el texto está en mayusculas.");
        System.out.println("Además, recuerde que el tipo del monstruo debe ser uno de esta lista: OGRO, TROLL, ESPECTRO");
        System.out.print("Vuelva a insertar el tipo: ");
        hechizoString = sc.nextLine();
      }
    }

    Monstruo monstruo = new Monstruo(nombreMonstruo, vidaMonstruo, tipo, fuerzaMonstruo);
    System.out.println("");
    
    // Crear bosque
    System.out.println("Crear bosque:");
    System.out.print("Inserte nombre: ");
    String nombreBosque = sc.nextLine();
    System.out.print("Inserte nivel de peligro (debe ser un número entero): ");
    int peligroBosque = sc.nextInt();
    
    new Bosque(nombreBosque, peligroBosque, monstruo);
    creacionBosqueCompleta = true;

    // Batalla por turnos
    while (mago.estaVivo() && monstruo.estaVivo()) {
      mago.lanzarHechizo(monstruo);
      monstruo.atacar(mago);
    }

    // Resultado
    if (mago.estaVivo()) {
      System.out.println("El mago ha ganado!");
    } else if (monstruo.estaVivo()) {
      System.out.println("El monstruo ha ganado!");
    }
  }
}
