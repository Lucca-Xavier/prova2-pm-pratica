public class AluguelPremium extends Aluguel {
    
    public AluguelPremium(String cliente, int dias){
        super(cliente, dias);
    }

    @Override
    public String getTipo(){
        return "Premium";
    }

}
