package com.bookbridge.controller.v1;

import com.bookbridge.data.model.BorrowedBook;
import com.bookbridge.data.response.Response;
import com.bookbridge.security.JwtUtils;
import com.bookbridge.security.UserDetailsServiceImpl;
import com.bookbridge.services.contract.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BorrowingController.class)
@WithMockUser
class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testBorrowBook() throws Exception {
        BorrowedBook borrowedBook = new BorrowedBook();
        Response<BorrowedBook> response = successfulResponse(List.of(borrowedBook));
        when(borrowingService.borrowBook(1L, 2L)).thenReturn(response);

        mockMvc.perform(post("/api/borrow/1/patron/2"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }

    @Test
    void testReturnBook() throws Exception {
        BorrowedBook borrowedBook = new BorrowedBook();
        Response<BorrowedBook> response = successfulResponse(List.of(borrowedBook));
        when(borrowingService.returnBook(1L, 2L)).thenReturn(response);

        mockMvc.perform(put("/api/return/1/patron/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelList.length()").value(1));
    }
}