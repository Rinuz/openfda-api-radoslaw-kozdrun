package pl.kozdrun.openfdaapi.rest.api.openfda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.kozdrun.openfdaapi.exception.FdaApiResponseException;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugsResponse;
import pl.kozdrun.openfdaapi.rest.api.openfda.util.OpenFdaUrlBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenFdaApiClient {

    public static final String APPLICATION_NUMBER_PARAM = "openfda.application_number";
    public static final String MANUFACTURER_NAME_PARAM = "openfda.manufacturer_name";
    public static final String BRAND_NAME_PARAM = "openfda.brand_name";

    @Value("${application.fdaApi.url}")
    private String openFdaApiUrl;

    private final RestTemplate restTemplate;

    public FdaDrugsResponse findDrugs(int page, int limit, Pair<String, Object>... searchParams) {
        String callUrl = buildCallUrl(page, limit, searchParams);
        logger.info("Call Open FDA api with url: {}", callUrl);

        try {
            ResponseEntity<FdaDrugsResponse> response = restTemplate.getForEntity(callUrl, FdaDrugsResponse.class);
            if (response.hasBody()) {
                return response.getBody();
            }
            throw new FdaApiResponseException(callUrl, "Response from FDA has no data", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HttpClientErrorException clientResponseException) {
            throw new FdaApiResponseException(callUrl, clientResponseException.getMessage(),
                    HttpStatus.valueOf(clientResponseException.getRawStatusCode())
            );
        }
    }

    private String buildCallUrl(int page, int limit, Pair<String, Object>... searchParams) {
        return new OpenFdaUrlBuilder()
                .withBaseUrl(openFdaApiUrl)
                .withPage(page)
                .withLimit(limit)
                .withBaseUrl(searchParams)
                .buildUrl();
    }
}