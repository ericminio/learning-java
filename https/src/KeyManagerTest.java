import org.junit.Test;

import javax.net.ssl.KeyManagerFactory;

public class KeyManagerTest {

    @Test
    public void canBeInstantiated() throws Exception {
        KeyManagerFactory sunX509 = KeyManagerFactory.getInstance("SunX509");
        sunX509.init(KeyStoreFactory.openKeyStore(), "mypassword".toCharArray());
    }
}
