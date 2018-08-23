package com.account.service.impl;

import com.account.domain.entity.Journal;
import com.account.repository.JournalRepository;
import com.account.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> getAllJournals() {
        return journalRepository.findByIsAvailableTrue();
    }

    @Override
    public Journal createJournal(Journal journal) throws IllegalArgumentException {
        journal.setId(null);

        if (journal.getJournalName() == null) {
            throw new IllegalArgumentException("journal name can not be null");
        } else if (isExistedJournalName(journal.getJournalName())) {
            throw new IllegalArgumentException("the journal name has already existed");
        }

        if (journal.getShortCode() == null) {
            throw new IllegalArgumentException("short code can not be null");
        } else if (isExistedShortCode(journal.getShortCode())) {
            throw new IllegalArgumentException("short code has already existed");
        }

        if (journal.getJournalType() == null) {
            throw new IllegalArgumentException("journal type can not be null");
        }

        if (journal.getDefaultCreditAccount() == null) {
            throw new IllegalArgumentException("default credit account can not be null");
        }

        if (journal.getDefaultDebitAccount() == null) {
            throw new IllegalArgumentException("default debit account can not be null");
        }

        return journalRepository.save(journal);
    }


    @Override
    public Journal updateJournal(Long journalId, Journal updatedJournalInfo) throws IllegalArgumentException {
        Optional<Journal> optJournal = journalRepository.findByIdAndIsAvailableTrue(journalId);
        Journal targetJournal = optJournal.orElseThrow(() -> new IllegalArgumentException("the journal is not existed"));

        if (updatedJournalInfo.getJournalName() != null && !updatedJournalInfo.getJournalName().equals(targetJournal.getJournalName())) {
            if (isExistedJournalName(updatedJournalInfo.getJournalName())) {
                throw new IllegalArgumentException("the journal name has already existed");
            } else {
                targetJournal.setJournalName(updatedJournalInfo.getJournalName());
            }
        }

        if (updatedJournalInfo.getShortCode() != null && !updatedJournalInfo.getShortCode().equals(targetJournal.getShortCode())) {
            if (isExistedShortCode(updatedJournalInfo.getShortCode())) {
                throw new IllegalArgumentException("the short code has already existed");
            } else {
                targetJournal.setShortCode(updatedJournalInfo.getShortCode());
            }
        }

        if (updatedJournalInfo.getNextNumber() != null) {
            if (updatedJournalInfo.getNextNumber() <= 0) {
                throw new IllegalArgumentException("next number should more than 0");
            } else {
                targetJournal.setNextNumber(updatedJournalInfo.getNextNumber());
            }
        }

        if (updatedJournalInfo.getJournalType() != null) {
            targetJournal.setJournalType(updatedJournalInfo.getJournalType());
        }

        if (updatedJournalInfo.getDefaultDebitAccount() != null) {
            targetJournal.setDefaultDebitAccount(updatedJournalInfo.getDefaultDebitAccount());
        }

        if (updatedJournalInfo.getDefaultCreditAccount() != null) {
            targetJournal.setDefaultCreditAccount(updatedJournalInfo.getDefaultCreditAccount());
        }

        return journalRepository.save(targetJournal);
    }


    @Override
    public void disableJournal(Long journalId) throws IllegalArgumentException {
        Optional<Journal> optJournal = journalRepository.findByIdAndIsAvailableTrue(journalId);
        Journal targetJournal = optJournal.orElseThrow(() -> new IllegalArgumentException("the journal does not existed"));
        targetJournal.setIsAvailable(false);
        journalRepository.save(targetJournal);
    }


    @Override
    public Optional<Journal> getJournalById(Long journalId) {
        return journalRepository.findByIdAndIsAvailableTrue(journalId);
    }


    @Override
    public boolean isExistedJournalName(String journalName) {
        Optional<Journal> optJournal = journalRepository.findByJournalNameAndIsAvailableTrue(journalName);
        return optJournal.isPresent();
    }


    @Override
    public boolean isExistedShortCode(String shortCode) {
        Optional<Journal> optJournal = journalRepository.findByShortCodeAndIsAvailableTrue(shortCode);
        return optJournal.isPresent();
    }


    @Override
    public void plusJournalNextNumber(Long journalId) throws IllegalArgumentException {
        Optional<Journal> optJournal = journalRepository.findByIdAndIsAvailableTrue(journalId);
        Journal targetJournal = optJournal.orElseThrow(() -> new IllegalArgumentException("the journal does not existed"));
        long nextNumber = targetJournal.getNextNumber() + 1;
        targetJournal.setNextNumber(nextNumber);
        journalRepository.save(targetJournal);
    }

}
