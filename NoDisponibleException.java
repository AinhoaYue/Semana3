public class NoDisponibleException extends Exception {

    public NoDisponibleException(){

    }

    @Override
    public String toString(){
        return "El libro no se encuentra Disponible";
    }
}
