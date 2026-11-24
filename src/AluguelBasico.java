public class AluguelBasico extends Aluguel {

    private int limiteKm;

    public AluguelBasico(String cliente, int dias){
        super(cliente, dias);
        this.limiteKm = 100;
    }

    @Override 
    public String getTipo(){
        return "Basico";
    }
}
