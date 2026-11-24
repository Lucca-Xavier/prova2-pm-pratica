public class Van extends Veiculo implements Avaliavel, Inspecionavel {
    private int maxPassageiros;
    private int quantidadeAvaliacoes;
    private int somaNotas;

    public Van(String placa, String modelo, int anoFabricacao,
            double valorDiaria, double quilometragem, int maxPassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        this.maxPassageiros = maxPassageiros;
        this.quantidadeAvaliacoes = 0;
        this.somaNotas = 0;

    }

    public int getMaxPassageiros() {
        return maxPassageiros;
    }

    @Override
    public void realizarInspecao() {
        System.out.println("Inspeção realizada na van " + getModelo()
                + " (placa " + getPlaca() + "). Tudo em ordem.");
    }

    public double getMediaAvaliacoes() {
        if (quantidadeAvaliacoes == 0) {
            return 0;
        }
        return (double) somaNotas / quantidadeAvaliacoes;
    }

    @Override
    public void avaliar(int nota) {
        if (nota < 0 || nota > 5) {
            System.out.println("Nota inválida! Informe um valor entre 0 e 5.");
            return;
        }
        somaNotas += nota;
        quantidadeAvaliacoes++;
        System.out.printf("Moto %s avaliado com nota %d. Média atual: %.1f%n",
                getModelo(), nota, getMediaAvaliacoes());
    }

    @Override
    public String getTipo() {
        return "Van";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Passageiros: %d | Média: %.1f (%d avaliações)",
                maxPassageiros, getMediaAvaliacoes(), quantidadeAvaliacoes);
    }

}
