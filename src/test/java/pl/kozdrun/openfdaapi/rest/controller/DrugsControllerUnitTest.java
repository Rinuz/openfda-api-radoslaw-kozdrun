package pl.kozdrun.openfdaapi.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kozdrun.openfdaapi.BaseUnitTest;
import pl.kozdrun.openfdaapi.exception.FdaApiResponseException;
import pl.kozdrun.openfdaapi.model.dto.DrugDto;
import pl.kozdrun.openfdaapi.service.DrugEntriesService;
import pl.kozdrun.openfdaapi.service.DrugsService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DrugsController.class)
class DrugsControllerUnitTest extends BaseUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DrugsService drugsService;
    @MockBean
    private DrugEntriesService drugEntriesService;

    @Test
    public void shouldReturnEmptyDrugsList() throws Exception {
        //given
        when(drugsService.getDrugs(anyInt(), anyInt()))
                .thenReturn(Collections.emptyList());

        //when
        //then
        mockMvc.perform(get("/api/v1/drugs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .isEmpty());
    }

    @Test
    public void shouldReturnDrugsList() throws Exception {
        //given
        DrugDto drug = createDrugDto();

        when(drugsService.getDrugs(anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(drug));

        //when
        //then
        mockMvc.perform(get("/api/v1/drugs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .isNotEmpty());
    }

    @Test
    public void shouldReturnFdaCallApiException() throws Exception {
        //given
        FdaApiResponseException fdaCallException = new FdaApiResponseException(
                "http://openfda.api/drugs",
                "Fda api error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR);

        when(drugsService.getDrugs(anyInt(), anyInt()))
                .thenThrow(fdaCallException);

        //when
        //then
        mockMvc.perform(get("/api/v1/drugs"))
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                        .value("Error while invoking fda api with url: " + fdaCallException.getFdaCallUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(fdaCallException.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code")
                        .value(fdaCallException.getStatus().value()));
    }

    //TODO write tests
}