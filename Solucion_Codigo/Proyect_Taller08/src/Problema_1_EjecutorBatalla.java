import java.util.Random;
/**
Problema01: Juego de roles
En un juego de rol, se desea implementar un sistema de combate en el que participen diferentes 
tipos de personajes: guerreros, magos y arqueros. Cada personaje tiene atributos y habilidades únicas,
así como diferentes métodos de ataque y defensa. El objetivo del juego es enfrentar a los personajes 
en batallas y determinar el ganador en función de sus habilidades, estrategias y atributos. 
Los guerreros se destacan por su fuerza y habilidades cuerpo a cuerpo, los magos por sus hechizos 
y poderes mágicos, y los arqueros por su precisión y habilidades a distancia.
El sistema debe permitir crear nuevos personajes de cada tipo, asignarles atributos iniciales, 
como puntos de vida y nivel de experiencia, y permitirles subir de nivel a medida que ganan batallas. 
Además, se debe implementar un algoritmo de combate que evalúe las habilidades de cada personaje y 
determine el resultado de la batalla.
Utilizando programación orientada a objetos, herencia y polimorfismo, implementa el sistema 
de combate y las clases necesarias para representar a los diferentes tipos de personajes. 
Asegúrate de que cada tipo de personaje tenga sus propias habilidades y métodos de ataque 
y defensa, y que puedan interactuar entre sí en las batallas.

 * @author Erick Caraguay
 * @version 1.0
 */
abstract class Personaje {
    public int vidas, experiencia, batallasGanadas;
    public abstract boolean ataque(Personaje personaje);
    public abstract boolean defensa(Personaje personaje);

    public Personaje(int vidas) {
        this.vidas = vidas;
    }

    @Override
    public String toString() {
        return "Personaje{" + "vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGanadas=" + batallasGanadas + '}';
    }
}

class Guerrero extends Personaje {
    public int fuerza; //1...10

    public Guerrero(int fuerza, int vidas) {
        super(vidas);
        this.fuerza = fuerza;
    }
    
    public boolean ataque(Personaje personaje){
        Random ale = new Random();
        boolean lucha = ale.nextBoolean();
        
        this.experiencia++;
        personaje.experiencia++;
        
        if (lucha) {
            this.batallasGanadas++;
            personaje.vidas--;
        } else {
            this.vidas--;
            personaje.batallasGanadas++;
        }
        return lucha;
    }
    public boolean defensa(Personaje personaje){
        return false;
    }

    @Override
    public String toString() {
        return "Guerrero{" + "fuerza=" + fuerza + super.toString() +'}';
    }
}

class Mago extends Personaje {
    public String hechizo; //abracadabra, transfromasion, ...

    public Mago(String hechizo, int vidas) {
        super(vidas);
        this.hechizo = hechizo;
    }
    
    public boolean ataque(Personaje personaje){
        return false;
    }
    public boolean defensa(Personaje personaje){
        return false;
    }

    @Override
    public String toString() {
        return "Mago{" + "hechizo=" + hechizo + super.toString() +'}';
    }
}

class Arquero extends Personaje {
    public int precision, cantidadFlechas;

    public Arquero(int precision, int cantidadFlechas, int vidas) {
        super(vidas);
        this.precision = precision;
        this.cantidadFlechas = cantidadFlechas;
    }
    
    public boolean ataque(Personaje personaje){
        return false;
    }
    public boolean defensa(Personaje personaje){
        return false;
    }

    @Override
    public String toString() {
        return "Arquero{" + "precision=" + precision + ", cantidadFlechas=" + cantidadFlechas +  super.toString() +'}';
    }
}

public class Problema_1_EjecutorBatalla {
    public static void main(String[] args) {
        Personaje guerrero1 = new Guerrero(7,3);
        Personaje mago1 = new Mago("Abracadabra", 2);
        Personaje arquero = new Arquero(9, 10, 3);
        
        System.out.println("Resultado ataque:" + guerrero1.ataque(mago1));
        
        System.out.println(guerrero1);
        System.out.println(mago1);
        
        System.out.println("");
        System.out.println("Resultado ataque:" + guerrero1.ataque(arquero));
        
        System.out.println(guerrero1);
        System.out.println(arquero);
    }
}

/**
 *run:
Resultado ataque:false
Guerrero{fuerza=7Personaje{vidas=2, experiencia=1, batallasGanadas=0}}
Mago{hechizo=AbracadabraPersonaje{vidas=2, experiencia=1, batallasGanadas=1}}

Resultado ataque:true
Guerrero{fuerza=7Personaje{vidas=2, experiencia=2, batallasGanadas=1}}
Arquero{precision=9, cantidadFlechas=10Personaje{vidas=2, experiencia=1, batallasGanadas=0}}
BUILD SUCCESSFUL (total time: 0 seconds)

 */