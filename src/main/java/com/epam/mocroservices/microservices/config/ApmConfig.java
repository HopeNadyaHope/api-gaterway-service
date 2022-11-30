package com.epam.mocroservices.microservices.config;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class ApmConfig {

    private static final String SERVER_URL_KEY = "server_url";
    private static final String SERVICE_NAME_KEY = "service_name";
    private static final String USE_PATH_AS_TRANSACTION_NAME_KEY = "use_path_as_transaction_name";
    private static final String TRANSACTION_NAME_GROUPS_KEY = "transaction_name_groups";
    private static final String LOG_LEVEL_KEY = "log_level";

    private final String apmUrl;
    private final String appName;
    private final String usePathAsTransactionName;
    private final String transactionNameGroups;
    private final String logLevel;

    public ApmConfig(
            @Value("${apm.url}") String ampUrl,
            @Value("${spring.application.name}") String appName,
            @Value("${apm.logLevel}") String logLevel,
            @Value("${apm.usePathAsTransactionName}") String usePathAsTransactionName,
            @Value("${apm.transactionNameGroups}") String transactionNameGroups) {
        this.apmUrl = ampUrl;
        this.appName = appName;
        this.logLevel = logLevel;
        this.usePathAsTransactionName = usePathAsTransactionName;
        this.transactionNameGroups = transactionNameGroups;
    }

    @PostConstruct
    public void init() {
        Map<String, String> apmProps = new HashMap<>(2);
        apmProps.put(SERVER_URL_KEY, apmUrl);
        apmProps.put(SERVICE_NAME_KEY, appName);
        apmProps.put(USE_PATH_AS_TRANSACTION_NAME_KEY, usePathAsTransactionName);
        apmProps.put(TRANSACTION_NAME_GROUPS_KEY, transactionNameGroups);
        apmProps.put(LOG_LEVEL_KEY, logLevel);

        ElasticApmAttacher.attach(apmProps);
    }

}
