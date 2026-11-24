import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Aluguel> alugueis = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDadosIniciais();
        int opcao;

        do {
            ExibirMenu();
            try {
                opcao = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    AssociarVeiculo();
                    break;
                case 2:
                    ExibirVeiculos();
                    break;
                case 3:
                    ExibirAlugueis();
                    break;
                case 4:
                    ExibirDisponiveis();
                    break;
                case 5:
                    Avaliar();
                    break;
                case 6:
                    Inspecionar();
                    break;
                case 7:
                    RemoverManutencao();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void carregarDadosIniciais() {
        // 3 veículos
        Carro carro = new Carro("ABC-1234", "Onix", 2022, 150.0, 12000, 4);
        Moto moto = new Moto("DEF-5678", "CG 160", 2021, 80.0, 30000, 160);
        Van van = new Van("GHI-9012", "Sprinter", 2020, 300.0, 55000, 15);

        veiculos.add(carro);
        veiculos.add(moto);
        veiculos.add(van);

        // 2 aluguéis
        AluguelBasico aluguel1 = new AluguelBasico("João Silva", 3);
        AluguelPremium aluguel2 = new AluguelPremium("Maria Souza", 5);

        alugueis.add(aluguel1);
        alugueis.add(aluguel2);

        // Opcional: já associa um veículo ao primeiro aluguel
        aluguel1.adicionarVeiculo(carro);
    }

    private static void AssociarVeiculo() {
        System.out.print("Digite o código do aluguel: ");
        int codigo = scan.nextInt();
        scan.nextLine(); // limpa o buffer
        System.out.print("Digite a placa do veículo: ");
        String placa = scan.nextLine();

        Aluguel aluguel = null;
        Veiculo veiculo = null;
        for (Aluguel a : alugueis) {
            if (codigo == a.getCodigo()) {
                System.out.println("Aluguel encontrado!");
                aluguel = a;
                break;
            }
        }
        for (Veiculo v : veiculos) {
            if (placa.equalsIgnoreCase(v.getPlaca())) {
                System.out.println("Veiculo encontrado!!");
                veiculo = v;
                break;
            }
        }

        if (aluguel == null) {
            System.out.println("Aluguel não encontrado!");
        } else if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
        } else if (aluguel.adicionarVeiculo(veiculo)) {
            System.out.println("Veículo associado ao aluguel com sucesso!");
        } else {
            System.out.println("Veículo indisponível, não foi possível associar.");
        }
    }

    private static void ExibirVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        for (Veiculo v : veiculos) {
            System.out.println(v);
        }
    }

    private static void ExibirAlugueis() {
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum Aluguel encontrado");
            return;
        }
        for (Aluguel a : alugueis) {
            System.out.println(a);
        }
    }

    private static void ExibirDisponiveis() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        for (Veiculo v : veiculos) {
            if (v.getStatus() == StatusVeiculo.DISPONIVEL) {
                System.out.println(v);
            }

        }
    }

    private static void Avaliar() {
        System.out.println("O que deseja avaliar?");
        System.out.println("1 - Veículo");
        System.out.println("2 - Aluguel");
        int opcao = scan.nextInt();
        scan.nextLine(); // limpa o buffer

        Object alvo = null;

        if (opcao == 1) {
            System.out.print("Digite a placa do veículo: ");
            String placa = scan.nextLine();
            for (Veiculo v : veiculos) {
                if (v.getPlaca().equalsIgnoreCase(placa)) {
                    alvo = v;
                    break;
                }
            }
        } else if (opcao == 2) {
            System.out.print("Digite o código do aluguel: ");
            int codigo = scan.nextInt();
            scan.nextLine();
            for (Aluguel a : alugueis) {
                if (a.getCodigo() == codigo) {
                    alvo = a;
                    break;
                }
            }
        } else {
            System.out.println("Opção inválida!");
            return;
        }

        if (alvo == null) {
            System.out.println("Item não encontrado!");
        } else if (alvo instanceof Avaliavel) {
            System.out.print("Digite a nota (0 a 5): ");
            int nota = scan.nextInt();
            scan.nextLine();
            ((Avaliavel) alvo).avaliar(nota);
        } else {
            System.out.println("Este item não pode ser avaliado.");
        }
    }

    private static void Inspecionar() {
        Veiculo alvo = null;

        System.out.print("Digite a placa do veículo: ");
        String placa = scan.nextLine();
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                alvo = v;
                break;
            }
        }
        if (alvo == null) {
            System.out.println("Item não encontrado!");
        } else if (alvo instanceof Inspecionavel) {
            ((Inspecionavel) alvo).realizarInspecao();
        } else {
            System.out.println("Este item não pode ser Inspecionado.");
        }
    }

    private static void RemoverManutencao() {
        int antes = veiculos.size();

        veiculos.removeIf(v -> v.getStatus() == StatusVeiculo.MANUTENCAO);

        int removidos = antes - veiculos.size();
        if (removidos == 0) {
            System.out.println("Nenhum veículo em manutenção para remover.");
        } else {
            System.out.println(removidos + " veículo(s) em manutenção removido(s) do sistema.");
        }
    }

    private static void ExibirMenu() {
        System.out.println("\n===== LOCADORA DE VEÍCULOS =====");
        System.out.println("1 - Associar veículo a um aluguel");
        System.out.println("2 - Exibir todos os veículos");
        System.out.println("3 - Exibir todos os aluguéis");
        System.out.println("4 - Exibir veículos disponíveis");
        System.out.println("5 - Avaliar");
        System.out.println("6 - Realizar inspeção");
        System.out.println("7 - Remover veículos em manutenção");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}
