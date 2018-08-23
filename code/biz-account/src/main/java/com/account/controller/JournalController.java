package com.account.controller;

import com.account.comm.exception.JsonParseException;
import com.account.comm.result.Result;
import com.account.domain.entity.Journal;
import com.account.domain.enums.JournalType;
import com.account.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @RequestMapping(path = "/journal", method = RequestMethod.GET)
    public Result getAllJournals(){
        List<Journal> journalList = journalService.getAllJournals();

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(journalList)
                .build();
    }


    @RequestMapping(path = "/journal/{journalId}", method = RequestMethod.PUT)
    public Result updateJournalInfo(@PathVariable Long journalId, @RequestBody Journal newJournalInfo) throws Exception{
        if(null == journalId){
            throw new JsonParseException("journalId");
        }

        if(null == newJournalInfo){
            throw new JsonParseException("journal");
        }

        Journal updatedJournal = journalService.updateJournal(journalId, newJournalInfo);
        List<Journal> currentJournalList = journalService.getAllJournals();
        Map<String, Object> response = new HashMap<>();
        response.put("updatedJournal", updatedJournal);
        response.put("currentJournalList", currentJournalList);

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(response)
                .build();
    }


    @RequestMapping(path = "/journal", method = RequestMethod.POST)
    public Result createJournal(@RequestBody Journal newJournal){
        if(null == newJournal){
            throw new JsonParseException("journal");
        }

        journalService.createJournal(newJournal);
        List<Journal> journalList = journalService.getAllJournals();
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(journalList)
                .build();
    }


    @RequestMapping(path = "/journal/{journalId}", method = RequestMethod.GET)
    public Result getJournalInfo(@PathVariable Long journalId){
        if(journalId == null){
            throw new JsonParseException("journalId");
        }

        Optional<Journal> optJournal = journalService.getJournalById(journalId);
        Result.Builder resultBuilder = new Result.Builder();

        if(optJournal.isPresent()){
            return resultBuilder.setCode(Result.Builder.SUCCESS).setData(optJournal.get()).build();
        }else{
            return resultBuilder.setCode(Result.Builder.FAILED).setMessage("the journal does not existed").build();
        }
    }


    @RequestMapping(path = "/journal/{journalId}", method = RequestMethod.DELETE)
    public Result deleteJournal(@PathVariable Long journalId){
        if(null == journalId){
            throw new JsonParseException("journalID");
        }

        journalService.disableJournal(journalId);
        List<Journal> currentJournals = journalService.getAllJournals();
        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(currentJournals)
                .build();
    }


    @RequestMapping(path = "/journalTypes", method = RequestMethod.GET)
    public Result getJournalTypes(){
        HashMap<String, String> response = new HashMap<>();
        JournalType[] journalTypes = JournalType.values();

        for(JournalType jt : journalTypes){
            response.put(jt.toString(), jt.getName());
        }

        return new Result.Builder()
                .setCode(Result.Builder.SUCCESS)
                .setData(response)
                .build();
    }

}
