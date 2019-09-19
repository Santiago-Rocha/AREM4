package edu.escuelaing.arem.project.Threads;

import java.io.IOException;


/**
 * @author Santiago Rocha
 */

public class Controller {
    public static void main(String[] args) throws IOException{
        AppServerThread.initialize();
        ThreadPool.start();
    }
}
