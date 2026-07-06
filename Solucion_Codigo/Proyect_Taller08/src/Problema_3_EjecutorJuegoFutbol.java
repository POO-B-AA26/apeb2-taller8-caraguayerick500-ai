/**
Problema03: Se desea realizar una aplicación que permita a un periodista deportivo llevar las estadísticas de 
los jugadores de un equipo de fútbol para poder valorar su actuación en el partido.
Cada jugador se identifica por su nombre, número de dorsal y Rut
Los jugadores se dividen en tres categorías:
Atacantes
Defensores
Porteros
Para todos los jugadores se desea contabilizar el número de goles marcados, además en el caso de los jugadores de
campo se contabilizan los pases realizados con éxito y el número de balones recuperados. En el caso de los porteros 
se contabilizan las atajadas realizadas.
Valoración del jugador
Cálculo base para todos los jugadores:
valor_goles = goles * 30
Valor adicional según tipo de jugador:

Atacantes
valor += recuperaciones * 3

Defensores
valor += recuperaciones * 4

Porteros
valor += atajadas * 5

 * @author Erick Caraguay
 * @version 1.0
 */
abstract class Jugador {
    private String nombre;
    private int dorsal;
    private String rut;
    private int goles;

    public Jugador(String nombre, int dorsal, String rut) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut    = rut;
    }
    public void setGoles(int goles) { this.goles = goles; }
    public int getGoles() {return goles; }

    public abstract double calcularValor();

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", dorsal=" + dorsal + ", rut=" + rut + ", goles=" + goles + '}';
    }
}

abstract class JugadorCampo extends Jugador {
    private int pases;
    private int recuperaciones;

    public JugadorCampo(int pases, int recuperaciones, String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
        this.pases = pases;
        this.recuperaciones = recuperaciones;
    }

    public int  getPases() { return pases; }
    public int  getRecuperaciones() { return recuperaciones; }

    @Override
    public String toString() {
        return "JugadorCampo{" + "pases=" + pases + ", recuperaciones=" + recuperaciones + super.toString() +'}';
    }
}

class Atacante extends JugadorCampo {
    public Atacante(int pases, int recuperaciones, String nombre, int dorsal, String rut) {
        super(pases, recuperaciones, nombre, dorsal, rut);
    }

    @Override
    public double calcularValor() {
        return (getGoles() * 30) + (getRecuperaciones() * 3);
    }

    @Override
    public String toString() {
        return "Atacante{" + super.toString() +'}';
    }
}

class Defensor extends JugadorCampo {
    public Defensor(int pases, int recuperaciones, String nombre, int dorsal, String rut) {
        super(pases, recuperaciones, nombre, dorsal, rut);
    }

    @Override
    public double calcularValor() {
        return (getGoles() * 30) + (getRecuperaciones() * 4);
    }

    @Override
    public String toString() {
        return "Defensor{" + super.toString() +'}';
    }
}

class Portero extends Jugador {
    private int atajadas;

    public Portero(int atajadas, String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
        this.atajadas = atajadas;
    }

    @Override
    public double calcularValor() {
        return (getGoles() * 30) + (atajadas * 5);
    }

    @Override
    public String toString() {
        return "Portero{" + "atajadas=" + atajadas + super.toString() +'}';
    }
}


public class Problema_3_EjecutorJuegoFutbol {
    public static void main(String[] args) {
        Jugador a1 = new Atacante(10, 5, "Pedri", 8, "12345");
        
        Jugador d1 = new Defensor(15, 9, "Van Dijk", 2, "54321");

        Jugador p1 = new Portero(10, "Galindez", 1, "68795");
        
        a1.setGoles(4);
        d1.setGoles(2);
        
        System.out.println("    ESTADISTICAS PARTIDO    ");
        System.out.println("============================\n");

        Jugador[] jugadores = {a1, d1, p1};
        for (Jugador j : jugadores) {
            System.out.println(j.toString());
            System.out.println();
        }

        System.out.println("    RESUMEN FINAL   ");
        System.out.println("====================");

        Jugador masValioso = jugadores[0];
        for (Jugador j : jugadores) {
            if (j.calcularValor() > masValioso.calcularValor()) {
                masValioso = j;
            }
        }
        System.out.println("Jugador mas valioso: " + masValioso.toString());
    }
}

/**
 *run:
    ESTADISTICAS PARTIDO    
============================

Atacante{JugadorCampo{pases=10, recuperaciones=5Jugador{nombre=Pedri, dorsal=8, rut=12345, goles=4}}}

Defensor{JugadorCampo{pases=15, recuperaciones=9Jugador{nombre=Van Dijk, dorsal=2, rut=54321, goles=2}}}

Portero{atajadas=10Jugador{nombre=Galindez, dorsal=1, rut=68795, goles=0}}

    RESUMEN FINAL   
====================
Jugador mas valioso: Atacante{JugadorCampo{pases=10, recuperaciones=5Jugador{nombre=Pedri, dorsal=8, rut=12345, goles=4}}}
BUILD SUCCESSFUL (total time: 0 seconds)
 */