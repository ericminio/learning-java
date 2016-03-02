import java.io.FileInputStream;
import java.security.KeyStore;

public class KeyStoreFactory {

    public static KeyStore openKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("jks");
        FileInputStream file = new FileInputStream("./src/keystore.jks");
        keyStore.load(file, "password".toCharArray());

        return keyStore;
    }
}
