# Proyecto AREP
Este proyecto ofrece un servidor web concurrente que responde con recursos tanto dinámicos como estáticos

## Prerrequisitos
Es necesario tener instalado Java 1.8 y Apache Maven 3.6.0


## Ejecutar
### Descagar dependencias y compilar
```
mvn package
```
### Documentación
```
mvn javadoc:jar
```
La documentación generada por el comando anterior se encuentra en la ruta **target\apidocs**
### Ejecutar
```
java -cp target/classes:target/dependency/* edu.escuelaing.arem.project.Threads.Controller
```
### Despliegue
https://arem-lab4.herokuapp.com/
#### Recursos Estáticos
```
https://arem-lab4.herokuapp.com/prueba.html
https://arem-lab4.herokuapp.com/git.png
```
#### Recursos Dinámicos
```
https://arem-lab4.herokuapp.com/app/prueba
https://arem-lab4.herokuapp.com/app/suma?num=1&num2=50
```

### Pruebas de concurrencia
Para probar que el cliente esta funcionando correctamente vamos a utilizar un cliente que haga peticiones concurrentes

Lo primero que necesitamos es un Thread que haga una peticion a un servidor, este thread va a calcular el tiempo entre la peticion y la respuesta.

``` java
public class Hilo extends Thread {
    
    public double SECOND = 1000000000.0;
    private double time = 0;
    public String host;
    
    /**
     * @param host the server to test
     */
    public Hilo(String host){
        this.host =  host;
    }
    
    @Override
    public void run() {
        try {
            long ini = System.nanoTime();
            URL url = new URL(host); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine = null; 
            while ((inputLine = reader.readLine()) != null) {}
            long fin = System.nanoTime();
            time= (fin-ini)/SECOND;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @return returns the time spent by the thread
     */
    public double getTime(){
        return time;
    }
    
    /**
     * @param time set the time spent by the thread
     */
    public void setTime(double time){
        this.time = time;
    }

}

``` 
Despues tenemos la clase principal, que simplemente va a recibir como parametro el numero de peticiones concurrentes a realizar y la URL del servidor a probar

``` java
public class ClientAWStoHeroku {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException  {
        int len = Integer.parseInt(args[0]);
        Hilo[] hilos =  new Hilo[len];
        for(int i = 0; i< len; i++){
            hilos[i] =  new Hilo(args[1]);
            hilos[i].start();
        }
        
        double result = 0;
        for(Hilo h: hilos){
            h.join();  
        }
        for(Hilo h: hilos){
            result += h.getTime();
        }
        System.err.println(result/len);
    }
}
```

Los resultados de unas pruebas realizadas en un cliente local y en un cliente en AWS se encuentran en el documento 

## Documento
Para más detalle del funcionamiento del proyecto ver el [documento](https://github.com/Santiago-Rocha/AREM4/blob/master/Articulo.pdf) adjunto

## Autor 
Santiago Rocha Durán - Escuela Colombiana de Ingenieria Julio Garavito
## Licencia
Este proyecto está licenciado bajo GNU General Public License - ver [LICENSE](https://github.com/Santiago-Rocha/AREM4/blob/master/LICENSE.txt) para más detalles 


