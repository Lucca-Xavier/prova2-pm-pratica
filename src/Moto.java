public class Moto extends Veiculo implements Inspecionavel {
    private int cilindradas;

    public Moto(String placa, String modelo, int anoFabricacao,
            double valorDiaria, double quilometragem, int cilindradas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    @Override
    public void realizarInspecao() {
        System.out.println("Inspeção realizada na moto " + getModelo()
                + " (placa " + getPlaca() + "). Tudo em ordem.");
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Cilindradas: %d",
                cilindradas);
    }
}
