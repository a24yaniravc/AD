public class ManejoFicherosBinarios {
    public static void main(String[] args) {
        String rutaOriginal = "AD-UD1-AT.02-File-VivelCouso_Yanira/origen.bin";
        String rutaNueva = "AD-UD1-AT.02-File-VivelCouso_Yanira/destino.bin";

        // Crear ficheros
        FicheroBinario origen = new FicheroBinario(rutaOriginal);
        FicheroBinario destino = new FicheroBinario(rutaNueva);

        // Escribir en el fichero de origen
        origen.escribir("ESTE ES EL TEXTO DE ORIGEN.");

        // Mostrar el contenido
        origen.leer();

        // Copiar el contenido
        System.out.println("Copiando información de " 
                            + rutaOriginal + " a " + 
                            rutaNueva + "..." );
        
        origen.copiar(destino);

        // Mostrar el contenido del fichero destino para comprobar
        destino.leer();

        // Borrar
        //origen.getFichero().delete();
        //destino.getFichero().delete();
    }
}
