// BorrowingRecord.java
package com.bookbridge.data.model;

import com.bookbridge.data.repo.contract.BaseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowedBook implements BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    @JsonManagedReference
    private Patron patron;

    private LocalDate borrowedDate;
    private LocalDate returnedDate;
}