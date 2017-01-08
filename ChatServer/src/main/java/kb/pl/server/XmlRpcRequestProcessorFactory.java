package kb.pl.server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory.RequestProcessorFactory;

public class XmlRpcRequestProcessorFactory implements RequestProcessorFactory {
	
	private final Object requestHandler;
    
    public XmlRpcRequestProcessorFactory(Object requestHandler) {
        this.requestHandler = requestHandler;
    }
    
	public Object getRequestProcessor(XmlRpcRequest arg0) throws XmlRpcException {
		// TODO Auto-generated method stub
		return requestHandler;
	}

}
