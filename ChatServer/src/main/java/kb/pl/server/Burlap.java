package kb.pl.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;

import kb.pl.protocol.IChatService;

@Configuration
public class Burlap {
	
	@Autowired
    private IChatService burlapService;
	
    @Bean(name="/burlapService")
    public BurlapServiceExporter burlapService(){
        BurlapServiceExporter burlapServiceExporter = new BurlapServiceExporter();
        burlapServiceExporter.setService(burlapService);
        burlapServiceExporter.setServiceInterface(IChatService.class);
                
        return burlapServiceExporter;
    }
}
