package com.bookbridge.services;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatronServiceImplTest {

    @Mock
    private PatronRepo patronRepo;

    @Mock
    private ModelMapper mapper;

    private PatronServiceImpl patronService;

    @BeforeEach
    void setUp() {
        patronService = new PatronServiceImpl(patronRepo, mapper);
    }

    @Test
    void testGetAll() {
        List<Patron> patrons = Arrays.asList(new Patron(), new Patron());
        when(patronRepo.getAll()).thenReturn(patrons);

        Response<Patron> response = patronService.getAll();
        assertNotNull(response);
        assertEquals(patrons, response.getModelList());
    }

    @Test
    void testGetById() {
        Patron patron = new Patron();
        when(patronRepo.getById(1L)).thenReturn(patron);

        Response<Patron> response = patronService.getById(1L);
        assertNotNull(response);
        assertEquals(List.of(patron), response.getModelList());
    }

    @Test
    void testGetByIdWhenPatronNotFound() {
        when(patronRepo.getById(1L)).thenReturn(null);

        Response<Patron> response = patronService.getById(1L);
        assertNotNull(response);
        assertEquals(List.of(), response.getModelList());
    }

    @Test
    void testCreate() {
        Patron patron = new Patron();
        PatronRequest request = new PatronRequest();
        when(mapper.map(request, Patron.class)).thenReturn(patron);
        when(patronRepo.create(patron)).thenReturn(patron);

        Response<Patron> response = patronService.create(request);
        assertNotNull(response);
        assertEquals(List.of(patron), response.getModelList());
    }

    @Test
    void testUpdate() {
        Patron patron = new Patron();
        PatronRequest request = new PatronRequest();
        when(patronRepo.getById(1L)).thenReturn(patron);
        when(patronRepo.update(any(Patron.class))).thenReturn(patron);

        Response<Patron> response = patronService.update(1L, request);
        assertNotNull(response);
        assertEquals(List.of(patron), response.getModelList());
        verify(mapper, times(1)).map(request, patron);
    }

    @Test
    void testUpdateWhenPatronNotFound() {
        PatronRequest request = new PatronRequest();
        when(patronRepo.getById(1L)).thenReturn(null);

        Response<Patron> response = patronService.update(1L, request);
        assertNotNull(response);
        assertEquals(List.of(), response.getModelList());
        verify(mapper, never()).map(any(), any());
    }

    @Test
    void testDelete() {
        Response<?> response = patronService.delete(1L);
        assertNotNull(response);
        verify(patronRepo, times(1)).delete(1L);
    }
}