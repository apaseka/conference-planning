package ua.upc.conferenceplanning.adaptors.api;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

public class ConferenceApiTest extends AbstractApiTest {
    @Test
    @DataSet(value = "default-conferences.xml", strategy = SeedStrategy.REFRESH)
    public void allConferencesFromDatabaseAreReturned() {
        given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(URI.create("/conferences"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(allOf(
                        containsString("[{\"id\":105,\"name\":\"Winter session\",\"subject\":\"Unit tests\",\"participantNumber\":123,\"date\":\"2022-01-28\",\"talks\":[]},{\"id\":106,\"name\":\"Spring session\",\"subject\":\"Spring advanced\",\"participantNumber\":222,\"date\":\"2022-04-28\",\"talks\":[]},{\"id\":109,\"name\":\"Summer session\",\"subject\":\"Kafka\",\"participantNumber\":150,\"date\":\"2022-07-28\",\"talks\":[]}]")));
    }
}