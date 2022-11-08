package pl.kozdrun.openfdaapi.rest.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.kozdrun.openfdaapi.OpenFdaApiApplication;
import pl.kozdrun.openfdaapi.model.dto.DrugDto;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugsResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest(classes = OpenFdaApiApplication.class)
class DrugsControllerIT {

    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private DrugsController drugsController;

    @Captor
    private ArgumentCaptor<String> fdaUrlCaptor;

    @Value("${application.fdaApi.url}")
    private String fdaApiUrl;

    @Test
    public void shouldCallFdaForAllDrugs() {
        //given
        Mockito.when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.ok(new FdaDrugsResponse()));

        //when
        List<DrugDto> drugs = drugsController.getDrugs(0, 50);

        //then
        assertThat(drugs).isEmpty();
        Mockito.verify(restTemplate).getForEntity(fdaUrlCaptor.capture(), eq(FdaDrugsResponse.class));
        assertThat(fdaUrlCaptor.getValue()).isEqualTo(fdaApiUrl + "?skip=0&limit=50");
    }

    //TODO write tests
}