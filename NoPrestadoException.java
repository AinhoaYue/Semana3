public class NoPrestadoException extends Exception{

    public NoPrestadoException(){

    }

    @Override
    public String toString(){
        return "El libro no se encuentra prestado";
    }

}
