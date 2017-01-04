package kb.pl.client;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Component;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.client.HessianURLConnectionFactory;

import kb.pl.protocol.IChatService;

@Component
public class HessianClient {

    private static final String HESSIAN_SERVICE = "/hessianService";

    @Autowired
    @Value("${server.url}")
    private String url;

    public IChatService getService() {
        String serviceUrl = url + HESSIAN_SERVICE;

        HessianProxyFactory hpf = new HessianProxyFactory();
        HessianConnectionFactory hcf = new HessianURLConnectionFactory() {

            @Override
            public HessianConnection open(URL url) throws IOException {
                HessianConnection hc = super.open(url);
                hc.addHeader("User-Agent", "RC-Hessian");
                return hc;
            }
        };

        hcf.setHessianProxyFactory(hpf);
        hpf.setConnectionFactory(hcf);

        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setProxyFactory(hpf);
        factory.setConnectionFactory(hcf);
        factory.setServiceUrl(serviceUrl);
        factory.setServiceInterface(IChatService.class);

        factory.afterPropertiesSet();
        IChatService chatService = (IChatService) factory.getObject();

        return chatService;
    }
}