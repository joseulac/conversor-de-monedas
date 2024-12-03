package cambiomoneda;

import historialconversion.HistorialDeConversiones;
import service.ApiService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CambioMoneda {
    String monedaBase;
    String monedatarget;
    double tipoDeCambio;
    double monto;
    double total;
    String fecha;


    public void monedasACambiar(String monedaBase, String monedaObjetivo) {
        ApiService service = new ApiService();
        ConvertirDatos datosConvertidos = service.convertir(monedaBase, monedaObjetivo);

        this.monedaBase = datosConvertidos.base_code().replace(monedaBase, "(" + monedaBase + ")");
        this.monedatarget = datosConvertidos.target_code().replace(monedaBase, "(" + monedatarget + ")");
        this.tipoDeCambio = datosConvertidos.conversion_rate();

    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void resultado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        fecha = LocalDateTime.now().format(formatter);

        total = tipoDeCambio * monto;
        System.out.println(monto + monedaBase + " corresponde al valor final de > " + total + monedatarget +
                " y el tipo de cambio es :" + tipoDeCambio + monedatarget + " por " + monedaBase + " cambio realizado el " + fecha);

    }

}
