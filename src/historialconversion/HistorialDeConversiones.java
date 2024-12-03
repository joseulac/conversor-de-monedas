package historialconversion;

import cambiomoneda.CambioMoneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class HistorialDeConversiones {
    public  void guardarJson(CambioMoneda cambioMoneda) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter escritura = new FileWriter("Historial de conversiones"+".json",true);
        escritura.write(gson.toJson(cambioMoneda));
        escritura.close();
    }

}
