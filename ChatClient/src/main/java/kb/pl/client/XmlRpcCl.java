package kb.pl.client;

import java.net.MalformedURLException;

import org.apache.xmlrpc.client.util.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kb.pl.protocol.IChatService;

@Component
public class XmlRpcCl {
	
	private final ClientFactory factory;

    @Autowired
    public XmlRpcCl(ClientFactory factory) {
        this.factory = factory;
    }

    public IChatService getService() throws MalformedURLException {
        return (IChatService) factory.newInstance(IChatService.class);
    }
}
