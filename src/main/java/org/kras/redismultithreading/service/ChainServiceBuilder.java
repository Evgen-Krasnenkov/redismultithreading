package org.kras.redismultithreading.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class ChainServiceBuilder {
    public static final int FIRST = 0;
    private final List<StudentService> services = Collections.synchronizedList(new ArrayList<StudentService>());
    public ChainServiceBuilder setService(StudentService service){
        services.add(service);
        return this;
    }

    public StudentService build(){
        for (int i = 0; i < services.size() - 1; i++) {
            services.get(i).setNextService(services.get(i + 1));
        }
        return services.get(FIRST);
    }
}
