import java.util.ArrayList;
import java.util.List;

public abstract class Aluguel {

    private static int proximoCodigo = 1;

    private int codigo;
    private String cliente;
    private int dias;
    private StatusAluguel status;
    private List<Veiculo> veiculos;

    public Aluguel(String cliente, int dias) {
        this.codigo = proximoCodigo++;
        this.cliente = cliente;
        this.dias = dias;
        this.status = StatusAluguel.ATIVO;
        this.veiculos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public int getDias() {
        return dias;
    }

    public StatusAluguel getStatus() {
        return status;
    }

    public void setStatus(StatusAluguel status) {
        this.status = status;
    }

    public List<Veiculo> getVeiculos() {
        return new ArrayList<>(veiculos);
    }

    /** Associa o veículo ao aluguel. Só aceita veículos disponíveis. */
    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
            return false;
        }
        veiculos.add(veiculo);
        veiculo.setStatus(StatusVeiculo.ALUGADO);
        return true;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Veiculo v : veiculos) {
            total += v.getValorDiaria() * dias;
        }
        return total;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Aluguel #%d [%s] | Cliente: %s | Dias: %d | Status: %s | Total: R$ %.2f",
                codigo, getTipo(), cliente, dias, status, calcularValorTotal()));
        if (veiculos.isEmpty()) {
            sb.append("\n   (sem veículos associados)");
        } else {
            for (Veiculo v : veiculos) {
                sb.append("\n   - ").append(v);
            }
        }
        return sb.toString();
    }
}