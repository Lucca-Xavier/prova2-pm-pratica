public class Carro extends Veiculo implements Avaliavel {

    private int quantidadePortas;
    private int somaNotas;
    private int quantidadeAvaliacoes;

    public Carro(String placa, String modelo, int anoFabricacao,
            double valorDiaria, double quilometragem, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        this.quantidadePortas = quantidadePortas;
        this.somaNotas = 0;
        this.quantidadeAvaliacoes = 0;
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
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
        System.out.printf("Carro %s avaliado com nota %d. Média atual: %.1f%n",
                getModelo(), nota, getMediaAvaliacoes());
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Portas: %d | Média: %.1f (%d avaliações)",
                quantidadePortas, getMediaAvaliacoes(), quantidadeAvaliacoes);
    }
}