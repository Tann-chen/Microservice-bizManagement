package com.account.controller;

import com.account.domain.entity.Account;
import com.account.domain.enums.AccountType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountsControllerTest {

    @Autowired
    private AccountsController accountsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountsController).build();
    }

    @Test
    public void createAccount() throws Exception {
        Account updatedAccount = new Account();
        updatedAccount.setName("stock in hand");
        updatedAccount.setCode(112001L);
        updatedAccount.setAccountType(AccountType.current_assets);
        updatedAccount.setAllowReconciliation(false);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String reqBody = writer.writeValueAsString(updatedAccount);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/chart_of_account/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAccountTypes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.options("/chart_of_account/accountType"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAccountsList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chart_of_account/account").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getAccountInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chart_of_account/account/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updateAccountInfo() throws Exception {

        Account updatedAccount = new Account();
        updatedAccount.setAccountType(AccountType.bank_and_cash);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String reqBody = writer.writeValueAsString(updatedAccount);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/chart_of_account//account/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqBody)
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void deleteAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/chart_of_account//account/2"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}