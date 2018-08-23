package com.account.repository;

import com.account.domain.entity.Journal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends CrudRepository<Journal, Long> {

    public Optional<Journal> findByShortCodeAndIsAvailableTrue(String shortCode);

    public Optional<Journal> findByJournalNameAndIsAvailableTrue(String journalName);

    public Optional<Journal> findByIdAndIsAvailableTrue(Long Id);

    public List<Journal> findByIsAvailableTrue();
}
