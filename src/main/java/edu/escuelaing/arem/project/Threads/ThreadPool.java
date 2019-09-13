package edu.escuelaing.arem.project.Threads;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Santiago Rocha
 */

public class ThreadPool {
    public static void main(String[] args) throws IOException{
        AppServerThread.initialize();
        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(new AppServerThread());
    }
}
