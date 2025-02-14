package edu.escuelaing.arep.server;

import edu.escuelaing.arep.controller.BookController;
import edu.escuelaing.arep.controller.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que implementa un servidor HTTP que escucha en el puerto 35000.
 * Permite manejar solicitudes REST y servir archivos estáticos desde una carpeta configurada.
 */
public class HttpServer {

    private static final int PORT = 35000;

    /**
     * Inicia el servidor HTTP.
     *
     * @throws IOException Si ocurre un error al crear el servidor o aceptar las conexiones de los clientes.
     */
    public static void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        WebFramework.registerControllers(new BookController());
        System.out.println("Servidor escuchando en el puerto " + PORT);

        boolean running = true;
        while (running) {
            Socket clientSocket = serverSocket.accept();
            RequestHandler.handleClient(clientSocket);
        }
    }
}