package ibf2022.batch2.paf.serverstub.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.models.BankAccount;

@Repository
public class BankAccountRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String FIND_ACCOUNT_BY_NAME = "select * from bankaccount where fullname = ?";
    private final String WITHDRAW_BY_ID = "update bankaccount set balance = balance - ? where id = ?";
    private final String DEPOSIT_BY_ID = "update bankaccount set balance = balance + ? where id = ?";

    // catch exception in service layer
    public BankAccount getBankAccount(String fullname){

        BankAccount bankAcc = jdbcTemplate.queryForObject(FIND_ACCOUNT_BY_NAME, BeanPropertyRowMapper.newInstance(BankAccount.class), fullname);
        return bankAcc;

    }

    // return 0 if query fail
    public Integer withdrawFund(Integer id, Float transferAmount){

        try{
            Integer affectedRows = jdbcTemplate.update(WITHDRAW_BY_ID, transferAmount,id);
            return affectedRows;    
        }catch(Exception ex){
            return 0;
        }
       
    }

    // return 0 if query fail
    public Integer depositFund(Integer id, Float transferAmount){

        try{
            Integer affectedRows = jdbcTemplate.update(DEPOSIT_BY_ID, transferAmount,id);
            return affectedRows;    
        }catch(Exception ex){
            return 0;
        }
       
    }



}
