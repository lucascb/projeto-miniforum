/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class RepositorioMensagens {
    private List<Mensagem> repositorio = new ArrayList<>();
    private final Path arquivo = Paths.get("/home/lucas/Documents/ufu/miniforum/mensagens.txt");
    
    public RepositorioMensagens() {
        try (BufferedReader br = Files.newBufferedReader(arquivo)) {
            br.lines().forEach(linha -> {
                String[] msg = linha.split(" ");
                repositorio.add(new Mensagem(msg[0], msg[1]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void salvarMensagem(Mensagem m) {
        repositorio.add(m);
        try (BufferedWriter bw = Files.newBufferedWriter(arquivo, StandardOpenOption.WRITE)) {
            repositorio.forEach(mensagem -> {
                try {
                    bw.write(mensagem.getEmail() + " " + mensagem.getMensagem() + "\n");    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List listarMensagens() {
        return repositorio;
    }
}
