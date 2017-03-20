package javaSe.netORio.nio.https;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

public class SSLContextFactory {

	public static SSLContext getContext() throws Exception {
		SSLContext ssl = SSLContext.getInstance("TLSv1");

		KeyStore ks = KeyStore.getInstance("JKS");
		char[] password = "123456".toCharArray();
		// keytool -genkey -keysize 2048 -validity 365 -keyalg RSA -dname
		// "CN=jiumao.com" -keypass 123456 -storepass 123456 -keystore
		// jiumao.jks
		InputStream in = new FileInputStream("jiumao.jks");
		ks.load(in, password);

		KeyManagerFactory keys = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

		keys.init(ks, password);
		ssl.init(keys.getKeyManagers(), null, null);

		in.close();

		return null;
	}

}
