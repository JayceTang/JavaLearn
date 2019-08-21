package com.jayce.effectivejava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName StaticFactyMethod
 * @description  use static factory method instead construction
 * @date 2018/10/2 16:03
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Provider framework sketch
 */

public class Services {
    private Services(){};  // prevent instance

    private static final Map<String, Provider> providers =
            new ConcurrentHashMap< String, Provider>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";


    /** Provider register API*/
    public static void registerDefauleProvider(Provider p){
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }
    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    /* Service access API */
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p != null) {
            throw new IllegalArgumentException(
                    "No provider register with name " + name
            );
        }
        return p.newService();
    }
}

interface Service {

}

interface Provider {
    Service newService();
}
