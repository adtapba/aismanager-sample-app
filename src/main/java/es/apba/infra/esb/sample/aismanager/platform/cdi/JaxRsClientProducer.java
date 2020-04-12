package es.apba.infra.esb.sample.aismanager.platform.cdi;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Producer of JAX-RS clients
 * 
 * @author fsaucedo
 */
@ApplicationScoped
public class JaxRsClientProducer {
    
    @Produces
    public Client produceClient() {
        try {
            final SSLContext context = SSLContext.getInstance("TLSv1");
            final TrustManager[] trustManagerArray = {new NullX509TrustManager()};

            context.init(null, trustManagerArray, null);

            ClientBuilder builder = ClientBuilder.newBuilder();
            builder.hostnameVerifier(new NullHostnameVerifier());
            builder.sslContext(context);

            return builder.build();
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }
    
    private static class NullHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class NullX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
    
}
