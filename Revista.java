public class Revista extends Publicacion{
    private int numeroEdicion;



    public void mostrarinformacion(){
        super.mostrarInformacion();

        System.out.println("Numero de edicion" + numeroEdicion);
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }
}
