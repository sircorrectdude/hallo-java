import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Hallo {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String nutzer = System.getProperty("user.name");
            byte[] antwort = ("Servus aus Version 2, Nutzer: " + nutzer + "\n").getBytes();
            exchange.sendResponseHeaders(200, antwort.length);
            exchange.getResponseBody().write(antwort);
            exchange.close();
        });
        server.start();
        System.out.println("Lausche auf Port 8080");
    }
}
