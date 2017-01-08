package kb.pl.server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kb.pl.protocol.IChatService;

@Service
public class XmlRpcRequestProcessorFactoryFactory implements RequestProcessorFactoryFactory {
    
    @Autowired
    private IChatService xmlRpcService;

    @SuppressWarnings("rawtypes")
    public RequestProcessorFactory getRequestProcessorFactory(Class classa) throws XmlRpcException {
        if (classa.equals(IChatService.class)) {
            return new XmlRpcRequestProcessorFactory(xmlRpcService);
        } else {
            throw new IllegalArgumentException("no registered handler for class <" + classa.getName() + ">");
        }
    }

}
