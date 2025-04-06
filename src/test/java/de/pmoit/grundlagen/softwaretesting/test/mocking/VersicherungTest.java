package de.pmoit.grundlagen.softwaretesting.test.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.lessThan;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import de.pmoit.grundlagen.softwaretesting.Auto;
import de.pmoit.grundlagen.softwaretesting.versicherungsapi.VersicherungAKKE;


@WireMockTest(httpsEnabled = false, httpPort = 8081)
public class VersicherungTest {
    private VersicherungAKKE akke;
    private Auto auto;

    @BeforeEach
    public void setup() {
        akke = new VersicherungAKKE("http://localhost:8081", "API-KEY");
        auto = new Auto("blau", 250, "VW", "Tiguan");
    }

    @Test
    public void api_testing_akke_versicherung_with_wiremock_tarifNameIsCorrect(WireMockRuntimeInfo wmRuntimeInfo) {
        auto.setKennzeichen("TST-M-1337");

        String body = "{\"tarif\": {\"tarif-name\": \"Beispiel Tarif\",\"tarif-preis\": 39.99}}";

        stubFor(get(WireMock.urlPathEqualTo("/abfrage")).withQueryParam("q", WireMock.equalTo(auto.getKennzeichen()))
            .withQueryParam("apiKey", WireMock.notMatching("")).willReturn(aResponse().withStatus(200).withHeader(
                "Content-Type", "application/json; charset=utf-8").withBody(body)));
        String tarifName = akke.getTarifName(auto);
        assertEquals(tarifName, "Beispiel Tarif");
    }

    @Test
    public void api_testing_akke_versicherung_with_wiremock_anfrageWurdeEinmalAusgefuehrt(
        WireMockRuntimeInfo wmRuntimeInfo) {

        auto.setKennzeichen("TST-M-1337");

        String body = "{\"tarif\": {\"tarif-name\": \"Beispiel Tarif\",\"tarif-preis\": 39.99}}";
        String testUrl = "/abfrage";
        stubFor(get(WireMock.urlPathEqualTo(testUrl)).withQueryParam("q", WireMock.equalTo(auto.getKennzeichen()))
            .withQueryParam("apiKey", WireMock.notMatching("")).willReturn(aResponse().withStatus(200).withHeader(
                "Content-Type", "application/json; charset=utf-8").withBody(body)));

        akke.getTarifName(auto);
        // akke.getTarifName(auto);

        verify(lessThan(2), getRequestedFor(urlEqualTo(testUrl + "?q=" + auto.getKennzeichen() + "&apiKey=API-KEY")));
    }

    @Test
    public void api_testing_akke_versicherung_with_wiremock_anfrageWurdeVerzoegertAusgefuehrt(
        WireMockRuntimeInfo wmRuntimeInfo) {
        auto.setKennzeichen("TST-M-1337");

        String body = "{\"tarif\": {\"tarif-name\": \"Beispiel Tarif\",\"tarif-preis\": 39.99}}";
        String testUrl = "/abfrage";
        stubFor(get(WireMock.urlPathEqualTo(testUrl)).withQueryParam("q", WireMock.equalTo(auto.getKennzeichen()))
            .withQueryParam("apiKey", WireMock.notMatching("")).willReturn(aResponse().withStatus(200).withFixedDelay(500)
                .withHeader("Content-Type", "application/json; charset=utf-8").withBody(body)));

        String errorMsg = akke.getTarifName(auto);

        assertEquals("Read timed out", errorMsg);
    }

    @Test
    public void api_testing_akke_versicherung_with_wiremock_liefertInternalServerError(WireMockRuntimeInfo wmRuntimeInfo) {
        String testUrl = "/abfrage";
        stubFor(get(WireMock.urlPathEqualTo(testUrl)).withQueryParam("q", WireMock.equalTo(auto.getKennzeichen()))
            .withQueryParam("apiKey", WireMock.notMatching("")).willReturn(aResponse().withStatus(500)));

        String errorMsg = akke.getTarifName(auto);

        assertEquals("Fehler : HTTP error code : 500", errorMsg);
    }
}
