import org.junit.Test;

import java.io.FileInputStream;
import java.security.KeyStore;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class KeyStoreTest {

    @Test
    public void keyStoreCanBeOpened() throws Exception {
        openKeyStore();
    }

    private KeyStore openKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("jks");
        FileInputStream file = new FileInputStream("./src/keystore.jks");
        keyStore.load(file, "password".toCharArray());

        return keyStore;
    }

    @Test
    public void keyStoreContainsKnownKey() throws Exception {
        KeyStore ks = openKeyStore();

        assertTrue(ks.isKeyEntry("myalias"));
    }

    @Test
    public void knownKeyCanBeRead() throws Exception {
        KeyStore ks = openKeyStore();

        assertThat(ks.getKey("myalias", "mypassword".toCharArray()), not(equalTo(null)));
    }
}
