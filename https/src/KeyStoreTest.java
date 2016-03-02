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
        KeyStoreFactory.openKeyStore();
    }

    @Test
    public void keyStoreContainsKnownKey() throws Exception {
        KeyStore ks = KeyStoreFactory.openKeyStore();

        assertTrue(ks.isKeyEntry("myalias"));
    }

    @Test
    public void knownKeyCanBeRead() throws Exception {
        KeyStore ks = KeyStoreFactory.openKeyStore();

        assertThat(ks.getKey("myalias", "mypassword".toCharArray()), not(equalTo(null)));
    }
}
