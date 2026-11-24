public class AluguelExecutivo extends Aluguel implements Avaliavel {
    private int quantidadeAvaliacoes;
    private int somaNotas;
    private boolean motorista;

    public AluguelExecutivo(String cliente, int dias    ) {
        super(cliente, dias);
        this.quantidadeAvaliacoes = 0;
        this.somaNotas = 0;
        this.motorista = true;
    }

    @Override
    public String getTipo() {
        return "Executivo";
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
        System.out.printf("Aluguel %s avaliado com nota %d. Média atual: %.1f%n",
                getTipo(), nota, getMediaAvaliacoes());
    }

}
