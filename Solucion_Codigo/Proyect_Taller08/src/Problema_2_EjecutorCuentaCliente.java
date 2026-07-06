import java.util.ArrayList;
/**
Problema02: En un restaurant se tiene diferentes tipos de menú para ofrecer a los clientes. 
Una cuenta por pagar está compuesta por características como: nombre del cliente, listado de todos las cartas(menú)
solicitados por el cliente, valor a cancelar total, subtotal, Iva. Los tipos de menú del restaurant son:
* Menú a la carta
nombre del plato
valor del menú
valor inicial del menú
valor de porción de guarnición
valor de bebida
porcentaje adicional por servicio en relación del valor inicial del menú

* Menú del día
nombre del plato
valor del menú
valor inicial del menú
valor de postre
valor de bebida

* Menú de niños
nombre del plato
valor del menú
valor inicial del menú
valor de porción de helado
valor de porción de pastel

* Menú económico
nombre del plato
valor del menú
valor inicial del menú
porcentaje de descuento, en referencia al valor inicial del menú

 * @author Erick Caraguay
 * @version 1.0
 */
abstract class Menu {
    private String nombrePlato;
    private double valorInicial;
    protected double valorMenu;

    public Menu(String nombrePlato, double valorInicial) {
        this.nombrePlato  = nombrePlato;
        this.valorInicial = valorInicial;
    }

    public abstract void calcularValorMenu();

    public String getNombrePlato() { return nombrePlato; }
    public double getValorInicial() { return valorInicial; }
    public double getValorMenu() { return valorMenu; }

    public void setValorMenu(double valorMenu) {
        this.valorMenu = valorMenu;
    }
    

    @Override
    public String toString() {
        return "Menu{" + "nombrePlato=" + nombrePlato + ", valorInicial=" + valorInicial + ", valorMenu=" + valorMenu + '}';
    }
}

class Carta extends Menu {
    private double valorGuarnicion;
    private double valorBebida;
    private double porcentajeAdicional;

    public Carta(String nombrePlato, double valorInicial, double guarnicion, double bebida, double porcServicio) {
        super(nombrePlato, valorInicial);
        this.valorGuarnicion = guarnicion;
        this.valorBebida = bebida;
        this.porcentajeAdicional = porcServicio;
    }

    @Override
    public void calcularValorMenu() {
        double servicio = getValorInicial() * (porcentajeAdicional / 100);
        valorMenu = getValorInicial() + valorGuarnicion + valorBebida + servicio;
    }

    @Override
    public String toString() {
        return "\nCarta{" + "valorguarnicion=" + valorGuarnicion + ", valorbebida=" + valorBebida + ", porcentajeAdicional=" + porcentajeAdicional + super.toString() + '}';
    }
}

class Dia extends Menu {
    private double valorPostre;
    private double valorBebida;

    public Dia(String nombrePlato, double valorInicial, double postre, double bebida) {
        super(nombrePlato, valorInicial);
        this.valorPostre = postre;
        this.valorBebida = bebida;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = getValorInicial() + valorPostre + valorBebida;
    }

    @Override
    public String toString() {
        return "\nDia{" + "valorpostre=" + valorPostre + ", valorbebida=" + valorBebida + super.toString() + '}';
    }
}

class Nino extends Menu {
    private double valorHelado;
    private double valorPastel;

    public Nino(String nombrePlato, double valorInicial, double helado, double pastel) {
        super(nombrePlato, valorInicial);
        this.valorHelado = helado;
        this.valorPastel = pastel;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = getValorInicial() + valorHelado + valorPastel;
    }

    @Override
    public String toString() {
        return "\nNino{" + "valorHelado=" + valorHelado + ", valorPastel=" + valorPastel + super.toString() + '}';
    }
}

class Economico extends Menu {
    private double porcentajeDescuento;

    public Economico(String nombrePlato, double valorInicial, double porcDescuento) {
        super(nombrePlato, valorInicial);
        this.porcentajeDescuento = porcDescuento;
    }

    @Override
    public void calcularValorMenu() {
        double descuento = getValorInicial() * (porcentajeDescuento / 100);
        valorMenu = getValorInicial() - descuento;
    }

    @Override
    public String toString() {
        return "\nEconomico{" + "porcentajeDescuento=" + porcentajeDescuento + super.toString() + '}';
    }
}

class CuentaCliente {
    private String nombreCliente;
    private ArrayList<Menu> menus;
    private double subtotal;
    private double iva;
    private double totalPagar;

    public CuentaCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.menus = new ArrayList<>();
    }

    public void agregarMenu(Menu m) {
        m.calcularValorMenu();
        menus.add(m);
    }
    public void calcularCuenta() {
        subtotal = 0.0;
        for (Menu m : menus) {
            subtotal += m.getValorMenu();
        }
        
        iva = subtotal * 0.15;
        totalPagar = subtotal + iva;
    }

    @Override
    public String toString() {
        return "CuentaCliente{" + "nombreCliente=" + nombreCliente + ", menus=" + menus + ", \nsubtotal=" + subtotal + ", iva=" + iva + ", totalPagar=" + totalPagar + '}';
    }
}

public class Problema_2_EjecutorCuentaCliente {
    public static void main(String[] args) {
        CuentaCliente cliente1 = new CuentaCliente("Ferran Torres");

        cliente1.agregarMenu(new Carta("Lomo fino", 12.00, 3.50, 2.50, 10.0));
        cliente1.agregarMenu(new Dia("Pollo asado", 8.00, 1.50, 1.00));

        cliente1.calcularCuenta();
        System.out.println(cliente1);

        System.out.println();

        CuentaCliente cliente2 = new CuentaCliente("Gilberto Mora");

        cliente2.agregarMenu(new Nino("Nuggets", 5.00, 1.50, 2.00));
        cliente2.agregarMenu(new Economico("Arroz con atun", 6.00, 20.0));

        cliente2.calcularCuenta();
        System.out.println(cliente2);
    }
}

/**
 *run:
CuentaCliente{nombreCliente=Ferran Torres, menus=[
Carta{valorguarnicion=3.5, valorbebida=2.5, porcentajeAdicional=10.0Menu{nombrePlato=Lomo fino, valorInicial=12.0, valorMenu=19.2}}, 
Dia{valorpostre=1.5, valorbebida=1.0Menu{nombrePlato=Pollo asado, valorInicial=8.0, valorMenu=10.5}}], 
subtotal=29.7, iva=4.455, totalPagar=34.155}

CuentaCliente{nombreCliente=Gilberto Mora, menus=[
Nino{valorHelado=1.5, valorPastel=2.0Menu{nombrePlato=Nuggets, valorInicial=5.0, valorMenu=8.5}}, 
Economico{porcentajeDescuento=20.0Menu{nombrePlato=Arroz con atun, valorInicial=6.0, valorMenu=4.8}}], 
subtotal=13.3, iva=1.995, totalPagar=15.295000000000002}
BUILD SUCCESSFUL (total time: 0 seconds)
 */