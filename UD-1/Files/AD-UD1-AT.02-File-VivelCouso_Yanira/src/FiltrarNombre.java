import java.io.File;

public class FiltrarNombre implements InnerFiltrarNombre {
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".md");
    }
}
