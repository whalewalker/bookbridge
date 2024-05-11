// PatronRepository.java
package com.bookbridge.data.repo.contract;


import com.bookbridge.data.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatronRepo extends JpaRepository<Patron, Long> {
}