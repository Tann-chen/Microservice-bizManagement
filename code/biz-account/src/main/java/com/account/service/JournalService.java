package com.account.service;

import com.account.domain.entity.Journal;

import java.util.List;
import java.util.Optional;

public interface JournalService {

    public List<Journal> getAllJournals();

    public Journal createJournal(Journal journal) throws IllegalArgumentException;

    public Journal updateJournal(Long journalId, Journal updatedJournalInfo) throws IllegalArgumentException;

    public void disableJournal(Long journalId) throws IllegalArgumentException;

    public Optional<Journal> getJournalById(Long journalId);

    public boolean isExistedJournalName(String journalName);

    public boolean isExistedShortCode(String shortCode);

    public void plusJournalNextNumber(Long journalId) throws IllegalArgumentException;
}
