package server;

import GestoreIOUser.GestoreUtenti;

import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static GestoreIOUser.GestoreUtenti.creaFileUtenti;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Configurazione {
    private final Map<String, Set<SocketChannel>> channels;

    private String IP;
    private int port;

    public Configurazione(Map<String, Set<SocketChannel>> channels, String IP, int port) {
        this.channels = channels;
        this.IP = IP;
        this.port = port;
    }

    public void creaCanale(int numeroCanali) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numeroCanali; i++) {
            System.out.println("Inserisci il nome del canale #" + (i + 1) + ":");
            String nomeCanale = scanner.nextLine();
            if (!channels.containsKey(nomeCanale)) {
                channels.put(nomeCanale, new HashSet<>());
                System.out.println("Canale '" + nomeCanale + "' creato.");
            } else {
                System.out.println("Il canale esiste già.");
            }
        }
    }

    public void configurazioneServer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'IP del server:");
        this.IP = scanner.nextLine();
        System.out.println("Inserisci la porta del server:");
        this.port = scanner.nextInt();
        scanner.nextLine(); // Consumare la newline

        System.out.println("Quanti canali vuoi creare?");
        int numeroCanali = scanner.nextInt();
        scanner.nextLine(); // Consumare la newline
        creaCanale(numeroCanali);

        // Inizializza il file utenti
        GestoreUtenti.creaFileUtenti();

        System.out.println("Configurazione completata.");
    }

    public Map<String, Set<SocketChannel>> getChannels() {
        return channels;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }
}
