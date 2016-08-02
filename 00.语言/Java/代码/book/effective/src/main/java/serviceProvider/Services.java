package serviceProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2015/2/25.
 */
public class Services {

    public Services() {
    }

    private static final Map<String, Provider> providers= new ConcurrentHashMap<String, Provider>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    public static void registerDefaultProvider(Provider provider){
        providers.put(DEFAULT_PROVIDER_NAME, provider);
    }

    public static void registerProvider(String name, Provider provider){
        providers.put(name, provider);
    }


}
