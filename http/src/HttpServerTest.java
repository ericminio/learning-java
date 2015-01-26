import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HttpServerTest {

    private HttpServer server;

    @Before
    public void startServer() throws Exception {
        server = HttpServer.create( new InetSocketAddress( 5000 ), 0 );
        server.createContext( "/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                exchange.sendResponseHeaders( 200, 0 );
                exchange.getResponseBody().close();
            }
        });
        server.start();
    }

    @After
    public void stopServer() {
        server.stop( 100 );
    }

    @Test
    public void canAnswerOKForAnyGetRequest() throws Exception {
        URL url = new URL( "http://localhost:5000/any/resource" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        assertThat( connection.getResponseCode(), equalTo( 200 ) );
    }
}
