package com.bookbridge.controller.v1;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.security.JwtUtils;
import com.bookbridge.services.contract.PatronService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatronController.class)
@WithMockUser
class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatronService patronService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllPatrons() throws Exception {
        List<Patron> patrons = Arrays.asList(new Patron(), new Patron());
        Response<Patron> response = successfulResponse(patrons);
        when(patronService.getAll()).thenReturn(response);

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(2));
    }

    @Test
    void testGetPatronById() throws Exception {
        Patron patron = new Patron();
        Response<Patron> response = successfulResponse(List.of(patron));
        when(patronService.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testCreatePatron() throws Exception {
        Patron patron = new Patron();
        Response<Patron> response = successfulResponse(List.of(patron));
        when(patronService.create(any(PatronRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PatronRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testUpdatePatron() throws Exception {
        Patron patron = new Patron();
        Response<Patron> response = successfulResponse(List.of(patron));
        when(patronService.update(eq(1L), any(PatronRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PatronRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testDeletePatron() throws Exception {
        when(patronService.delete(1L)).thenReturn(any(Response.class));

        mockMvc.perform(delete("/api/patrons/1"))
                .andExpect(status().isOk());
    }
}