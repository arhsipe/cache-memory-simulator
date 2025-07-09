package cache_simulator;

import java.util.Scanner;

public class Cache_simulator {
    public static void main(String[] args) {
        // Scanner to read user input
        Scanner scan = new Scanner(System.in);

        // Arrays to represent cache addresses and contents
        int[] cache_endereco = new int[8];
        int[] cache_conteudo = new int[8];
        // Array to represent RAM with 256 positions
        int[] ram = new int[256];

        // Counters for statistics
        int total_acessos = 0;
        int total_hit = 0;
        int total_miss = 0;

        // Pointer to the current cache position (FIFO replacement)
        int posicao_cache = 0;

        // Initialize RAM with random values
        inicializarRam(ram);

        System.out.println("------------------------------------------");
        System.out.println("       SIMULADOR DE MEMORIA RAM E CACHE   ");
        System.out.println("------------------------------------------");

        // Initialize cache addresses to -1 (empty)
        inicializarCache(cache_endereco);

        String opcao = "s"; // Option to continue or exit

        while (opcao.equalsIgnoreCase("s")) {
            // Prompt user for a memory address
            System.out.print("\nDigite o endereco de memoria (0 a 255): ");
            int endereco = scan.nextInt();

            // Validate address range
            if (endereco < 0 || endereco >= ram.length) {
                System.out.println("Endereco invalido. Por favor, insira um valor entre 0 e 255.");
                continue; // Ask again if invalid
            }

            total_acessos++;
            boolean hit = false;

            // Search in cache to check for HIT
            for (int i = 0; i < cache_endereco.length; i++) {
                if (cache_endereco[i] == endereco) {
                    System.out.println("\nConteudo: " + cache_conteudo[i] + " na posicao " + i + " [HIT]");
                    total_hit++;
                    hit = true;
                    break; // Exit loop if HIT
                }
            }

            // If MISS, load from RAM and update cache
            if (!hit) {
                System.out.println("\nConteudo carregado da RAM: " + ram[endereco] + " [MISS]");
                cache_endereco[posicao_cache] = endereco;
                cache_conteudo[posicao_cache] = ram[endereco];
                posicao_cache++;
                total_miss++;
            }

            // If cache is full, reset position to start (FIFO policy)
            if (posicao_cache == cache_endereco.length) {
                System.out.println("\nCACHE CHEIO! COMECANDO A REALOCAR NA PRIMEIRA POSICAO");
                posicao_cache = 0;
            }

            // Ask user if they want to continue
            System.out.print("\nDeseja continuar? (s/n): ");
            opcao = scan.next();
        }

        // Final report
        System.out.println("\n-------------------------------------------");
        System.out.println("              RELATORIO FINAL              ");
        System.out.println("-------------------------------------------");
        System.out.println("Total de acessos: " + total_acessos);
        System.out.println("Total de HITS: " + total_hit);
        System.out.println("Total de MISSES: " + total_miss);

        // Calculate cache hit rate (convert integers to double)
        double taxa = ((double) total_hit / total_acessos) * 100;
        System.out.printf("Taxa de acerto da cache: %.2f%%\n", taxa);

        System.out.println("\nPrograma encerrado. Obrigado por utilizar!");
        scan.close(); // Close Scanner
    }

    // Method to initialize RAM with random values between 0 and 999
    private static void inicializarRam(int[] ram) {
        System.out.println("RAM:");
        for (int i = 0; i < ram.length; i++) {
            ram[i] = (int) (Math.random() * 1000);
            System.out.printf("%4d [%3d]   ", ram[i], i);
            // Print newline every 10 elements for readability
            if (i % 10 == 7) {
                System.out.println();
            }
        }
        System.out.println();
    }

    // Method to set all cache addresses to -1 (indicating empty)
    private static void inicializarCache(int[] cache_endereco) {
        for (int i = 0; i < cache_endereco.length; i++) {
            cache_endereco[i] = -1;
        }
    }
}