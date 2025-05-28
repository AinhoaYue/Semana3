import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Publicacion> publicaciones;

    public Biblioteca(){
        this.publicaciones = new ArrayList<>();
    }

    public void agregarPublicacion(Publicacion publicacion){
        this.publicaciones.add(publicacion);
    }

    public void prestarPublicacion(Publicacion publicacion) throws NoDisponibleException {
        if (publicacion instanceof Prestable){
            ((Prestable) publicacion).prestar();
        }
    }

    public void devolverPublicacion(Publicacion publicacion) throws NoPrestadoException {
        if (publicacion instanceof Prestable){
            ((Prestable) publicacion).devolver();
        }
    }

    public void mostrarInformacion (){
        for (Publicacion publicacion: publicaciones){
            publicacion.mostrarInformacion();
        }
    }

}

PRUEBA 

    CLASE AUTOMOVIL 
    public class Automovil {
    private int codigo;
    private String marca;
    private int cilindraje;
    private float precio;

    public Automovil(int codigo, String marca, int cilindraje, float precio) {
        this.codigo = codigo;
        this.marca = marca;
        this.cilindraje = cilindraje;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public float getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Marca: " + marca + ", Cilindraje: " + cilindraje + ", Precio: " + precio;
    }
}


VENTANA PRINCIPLA 

    import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;

public class VentanaPrincipal extends JFrame {

    private ArrayList<Automovil> autos = new ArrayList<>();
    private JTextArea area1 = new JTextArea(10, 40);
    private JTextArea area2 = new JTextArea(10, 40);
    private JTextArea area3 = new JTextArea(10, 40);

    private JComboBox<String> marcaBox;
    private JComboBox<Integer> cilindroBox;
    private JTextField codigoField;
    private JTextField precioField;

    private String[] marcas = {"KIA", "BMW", "TOYOTA", "JAC", "MAZDA"};
    private Integer[] cilindros = {1300, 1600, 2000, 2400, 2700};

    public VentanaPrincipal() {
        setTitle("Gestión de Automóviles");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Panel 1 - Ingreso de Autos
        JPanel panel1 = new JPanel();
        codigoField = new JTextField(5);
        marcaBox = new JComboBox<>(marcas);
        cilindroBox = new JComboBox<>(cilindros);
        precioField = new JTextField(10);
        JButton agregar = new JButton("Agregar Auto");

        panel1.add(new JLabel("Código (3 dígitos):"));
        panel1.add(codigoField);
        panel1.add(new JLabel("Marca:"));
        panel1.add(marcaBox);
        panel1.add(new JLabel("Cilindraje:"));
        panel1.add(cilindroBox);
        panel1.add(new JLabel("Precio:"));
        panel1.add(precioField);
        panel1.add(agregar);
        panel1.add(new JScrollPane(area1));

        agregar.addActionListener(e -> agregarAuto());

        // Panel 2 - Filtrar por Marca y Precio
        JPanel panel2 = new JPanel();
        JComboBox<String> marcaFiltro = new JComboBox<>(marcas);
        JTextField precioFiltro = new JTextField(10);
        JButton filtrar = new JButton("Filtrar");
        panel2.add(new JLabel("Marca:"));
        panel2.add(marcaFiltro);
        panel2.add(new JLabel("Precio Mínimo:"));
        panel2.add(precioFiltro);
        panel2.add(filtrar);
        panel2.add(new JScrollPane(area2));

        filtrar.addActionListener(e -> filtrarAutos(marcaFiltro.getSelectedItem().toString(), precioFiltro.getText()));

        // Panel 3 - Conteo recursivo por Marca
        JPanel panel3 = new JPanel();
        JComboBox<String> marcaContar = new JComboBox<>(marcas);
        JButton contar = new JButton("Contar por Marca");
        panel3.add(new JLabel("Marca:"));
        panel3.add(marcaContar);
        panel3.add(contar);
        panel3.add(new JScrollPane(area3));

        contar.addActionListener(e -> {
            int cantidad = contarPorMarcaRecursivo(marcaContar.getSelectedItem().toString(), autos.size() - 1);
            area3.setText("Cantidad de autos para " + marcaContar.getSelectedItem() + ": " + cantidad);
        });

        tabs.addTab("Ingreso de Autos", panel1);
        tabs.addTab("Filtrar Marca y Precio", panel2);
        tabs.addTab("Conteo Recursivo", panel3);

        add(tabs);
        setVisible(true);
    }

    private void agregarAuto() {
        try {
            int codigo = Integer.parseInt(codigoField.getText());
            if (codigo < 100 || codigo > 999) throw new NumberFormatException();
            String marca = marcaBox.getSelectedItem().toString();
            int cilindraje = (Integer) cilindroBox.getSelectedItem();
            float precio = Float.parseFloat(precioField.getText());

            if (precio < 5000 || precio > 100000) throw new NumberFormatException();

            boolean existe = false;
            for (int i = 0; i < autos.size(); i++) {
                if (autos.get(i).getCodigo() == codigo) {
                    autos.set(i, new Automovil(codigo, marca, cilindraje, precio));
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                autos.add(new Automovil(codigo, marca, cilindraje, precio));
            }

            mostrarAutos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Datos incorrectos.");
        }
    }

    private void mostrarAutos() {
        area1.setText("");
        for (Automovil a : autos) {
            area1.append(a.toString() + "\n");
        }
    }

    private void filtrarAutos(String marca, String precioStr) {
        try {
            float precio = Float.parseFloat(precioStr);
            ArrayList<Automovil> filtrados = new ArrayList<>();

            for (Automovil a : autos) {
                if (a.getMarca().equals(marca) && a.getPrecio() > precio) {
                    filtrados.add(a);
                }
            }

            filtrados.sort(Comparator.comparingDouble(Automovil::getPrecio).reversed());

            area2.setText("");
            for (Automovil a : filtrados) {
                area2.append(a.toString() + "\n");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido.");
        }
    }

    private int contarPorMarcaRecursivo(String marca, int index) {
        if (index < 0) return 0;
        int count = autos.get(index).getMarca().equals(marca) ? 1 : 0;
        return count + contarPorMarcaRecursivo(marca, index - 1);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}


