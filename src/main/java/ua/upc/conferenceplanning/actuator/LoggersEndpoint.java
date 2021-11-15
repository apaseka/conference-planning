package ua.upc.conferenceplanning.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@Endpoint(id = "loggers")
@RequiredArgsConstructor
public class LoggersEndpoint {

    private final LoggingSystem loggingSystem;

    @WriteOperation
    public void configureLogLevel(@Selector String name, @Nullable LogLevel configureLevel) {
        Assert.notNull(name, "Name must not be empty");
        loggingSystem.setLogLevel(name, configureLevel);
    }
}
