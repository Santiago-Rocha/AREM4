package edu.escuelaing.arem.project.Threads;

import edu.escuelaing.arem.project.Sockets.AppSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static ServerSocket serverSocket = AppSocket.StartServerSocket();
    public static ExecutorService exService = Executors.newCachedThreadPool();
        

    public static void start() {
        while (true) {
            Socket clientSocket = AppSocket.StartClientSocket(serverSocket);
            exService.execute(new AppServerThread(clientSocket));

        }
    }

}