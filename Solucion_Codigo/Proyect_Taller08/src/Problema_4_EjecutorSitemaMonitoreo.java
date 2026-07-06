/**
Problema04: Una red de monitoreo ambiental tiene como objetivo registrar, analizar y reportar los 
impactos del cambio climático en diferentes regiones. En cada ubicación se instalan dispositivos 
capaces de medir distintos indicadores climáticos como temperatura, precipitación, calidad del aire, 
y humedad del suelo. Dependiendo de la región (costa, sierra y oriente), los dispositivos pueden variar 
en capacidades y protocolos de recolección.
Los datos recolectados deben almacenarse y analizarse periódicamente. Además, ciertas ubicaciones 
requieren generar reportes personalizados que destaquen riesgos ambientales como sequías, deslizamientos 
o contaminación del aire. Algunos dispositivos pueden comportarse de forma especializada para detectar 
únicamente ciertos tipos de indicadores dependiendo de la región (costa, sierra y oriente).

* Requisitos funcionales:
Representar diferentes tipos de dispositivos y sus especializaciones, para la costa, sierra y oriente.
Implementar métodos polimórficos que permitan procesar los datos según los tipos de dispositivos y sus 
especializaciones, para la costa, sierra y oriente.
Generar reportes dinámicos en función del tipo de riesgo ambiental detectado según la región

 * @author Erick Caraguay
 * @version 1.0
 */

abstract class DispositivoClimatico {
    private String ubicacion;
    private double temperatura;
    private double precipitacion;
    private double calidadAire;
    private double humedadSuelo;

    public DispositivoClimatico(String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        this.ubicacion     = ubicacion;
        this.temperatura   = temperatura;
        this.precipitacion = precipitacion;
        this.calidadAire   = calidadAire;
        this.humedadSuelo  = humedadSuelo;
    }

    public abstract void procesarDatos();
    public abstract String generarReporte();

    public String getUbicacion() { return ubicacion; }
    public double getTemperatura() { return temperatura; }
    public double getPrecipitacion() { return precipitacion; }
    public double getCalidadAire() { return calidadAire; }
    public double getHumedadSuelo() { return humedadSuelo; }

    @Override
    public String toString() {
        return "DispositivoClimatico{" + "ubicacion=" + ubicacion + ", temperatura=" + temperatura + ", precipitacion=" + precipitacion + ", calidadAire=" + calidadAire + ", humedadSuelo=" + humedadSuelo + '}';
    }
}

class DispositivoCosta extends DispositivoClimatico {
    private double nivelMar;

    public DispositivoCosta(String ubicacion, double temperatura,
                             double precipitacion, double calidadAire,
                             double humedadSuelo, double nivelMar) {
        super(ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
        this.nivelMar = nivelMar;
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos costeros en: " + getUbicacion());
        System.out.println("    Analizando nivel del mar y riesgo de inundacion...");
    }

    @Override
    public String generarReporte() {
        String riesgo;
        if (nivelMar > 2.0) {
            riesgo = "ALERTA - Riesgo alto de inundaciOn costera";
        } else if (getHumedadSuelo() > 80) {
            riesgo = "PRECAUCION - Humedad elevada, posible erosion";
        } else {
            riesgo = "NORMAL - Sin riesgos detectados";
        }
        return "Riesgo Costa: " + riesgo;
    }

    @Override
    public String toString() {
        return "DispositivoCosta{" + "nivelMar=" + nivelMar + super.toString() +'}';
    }
}

class DispositivoSierra extends DispositivoClimatico {
    private double nivelNieve;

    public DispositivoSierra(String ubicacion, double temperatura,
                              double precipitacion, double calidadAire,
                              double humedadSuelo, double nivelNieve) {
        super(ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
        this.nivelNieve = nivelNieve;
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos de sierra en: " + getUbicacion());
        System.out.println("    Analizando nivel de nieve y riesgo de deslizamiento...");
    }

    @Override
    public String generarReporte() {
        String riesgo;
        if (getPrecipitacion() > 50 && getHumedadSuelo() > 75) {
            riesgo = "ALERTA - Riesgo alto de deslizamiento";
        } else if (getTemperatura() < 0 && nivelNieve > 1.5) {
            riesgo = "PRECAUCION - Helada intensa, nieve acumulada";
        } else {
            riesgo = "NORMAL - Sin riesgos detectados";
        }
        return "Riesgo Sierra : " + riesgo;
    }

    @Override
    public String toString() {
        return "DispositivoSierra{" + "nivelNieve=" + nivelNieve + super.toString() +'}';
    }
}

class DispositivoOriente extends DispositivoClimatico {
    private double nivelDeforest;

    public DispositivoOriente(String ubicacion, double temperatura,
                               double precipitacion, double calidadAire,
                               double humedadSuelo, double nivelDeforest) {
        super(ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
        this.nivelDeforest = nivelDeforest;
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos del oriente en: " + getUbicacion());
        System.out.println("    Analizando deforestacion y calidad del aire...");
    }

    @Override
    public String generarReporte() {
        String riesgo;
        if (nivelDeforest > 40) {
            riesgo = "ALERTA - Deforestacion critica detectada";
        } else if (getCalidadAire() > 150) {
            riesgo = "PRECAUCION - Contaminacion del aire elevada";
        } else {
            riesgo = "NORMAL - Sin riesgos detectados";
        }
        return "Riesgo Oriente: " + riesgo;
    }

    @Override
    public String toString() {
        return "DispositivoOriente{" + "nivelDeforest=" + nivelDeforest + super.toString() +'}';
    }
}

public class Problema_4_EjecutorSitemaMonitoreo {
    public static void main(String[] args) {
        DispositivoCosta costa = new DispositivoCosta("Guayaquil", 32.5, 20.0, 80.0, 85.0, 1.5);

        DispositivoSierra sierra = new DispositivoSierra("Quito", -2.0, 60.0, 50.0, 50.0, 0.5);

        DispositivoOriente oriente = new DispositivoOriente("Zamora", 28.0, 200.0, 160.0, 70.0, 45.0);

        System.out.println("    MONITOREO CLIMATICO     ");
        System.out.println("============================");

        DispositivoClimatico[] dispositivos = {costa, sierra, oriente};

        System.out.println("--- Procesamiento de datos ---");
        for (DispositivoClimatico d : dispositivos) {
            d.procesarDatos();
            System.out.println();
        }

        System.out.println("--- Reportes por region ---");
        for (DispositivoClimatico d : dispositivos) {
            System.out.println(d.toString());
            System.out.println("" + d.generarReporte());
            System.out.println();
        }
    }
}

/**
 * run:
    MONITOREO CLIMATICO     
============================
--- Procesamiento de datos ---
Procesando datos costeros en: Guayaquil
    Analizando nivel del mar y riesgo de inundacion...

Procesando datos de sierra en: Quito
    Analizando nivel de nieve y riesgo de deslizamiento...

Procesando datos del oriente en: Zamora
    Analizando deforestacion y calidad del aire...

--- Reportes por region ---
DispositivoCosta{nivelMar=1.5DispositivoClimatico{ubicacion=Guayaquil, temperatura=32.5, precipitacion=20.0, calidadAire=80.0, humedadSuelo=85.0}}
Riesgo Costa: PRECAUCION - Humedad elevada, posible erosion

DispositivoSierra{nivelNieve=0.5DispositivoClimatico{ubicacion=Quito, temperatura=-2.0, precipitacion=60.0, calidadAire=50.0, humedadSuelo=50.0}}
Riesgo Sierra : NORMAL - Sin riesgos detectados

DispositivoOriente{nivelDeforest=45.0DispositivoClimatico{ubicacion=Zamora, temperatura=28.0, precipitacion=200.0, calidadAire=160.0, humedadSuelo=70.0}}
Riesgo Oriente: ALERTA - Deforestacion critica detectada

BUILD SUCCESSFUL (total time: 0 seconds)

 */