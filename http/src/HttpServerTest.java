import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HttpServerTest {

    private HttpServer server;

    @Before
    public void startServer() throws Exception {
        server = HttpServer.create( new InetSocketAddress( 8000 ), 0 );
        server.createContext( "/", exchange -> {
            exchange.sendResponseHeaders( 200, 0 );
            exchange.close();
        } );
        server.createContext( "/json", exchange -> {
            exchange.getResponseHeaders().add( "content-type", "application/json" );
            exchange.sendResponseHeaders( 200, 0 );
            exchange.close();
        } );
        server.start();
    }

    @After
    public void stopServer() {
        server.stop( 1 );
    }

    @Test
    public void canAnswerOKForAnyGetRequest() throws Exception {
        URL url = new URL( "http://localhost:8000" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        assertThat( connection.getResponseCode(), equalTo( 200 ) );
    }

    @Test
    public void answersWithoutContentTypeByDefault() throws Exception {
        URL url = new URL( "http://localhost:8000" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        assertThat( connection.getHeaderField( "content-type" ), equalTo( null ) );
    }

    @Test
    public void canAnswerWithJsonContentType() throws Exception {
        URL url = new URL( "http://localhost:8000/json" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        assertThat( connection.getHeaderField( "content-type" ), equalTo( "application/json" ) );
    }
}
