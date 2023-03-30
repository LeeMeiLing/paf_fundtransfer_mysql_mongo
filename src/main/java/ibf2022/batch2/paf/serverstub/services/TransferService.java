package ibf2022.batch2.paf.serverstub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.serverstub.exceptions.TransactionFailException;
import ibf2022.batch2.paf.serverstub.models.BankAccount;
import ibf2022.batch2.paf.serverstub.models.Payload;
import ibf2022.batch2.paf.serverstub.models.TransactionRecord;
import ibf2022.batch2.paf.serverstub.repositories.BankAccountRepository;
import ibf2022.batch2.paf.serverstub.repositories.TransactionRecordRepository;
import jakarta.json.JsonObject;

@Service
public class TransferService {
    
    @Autowired
    BankAccountRepository bankAccRepo;

    @Autowired
    TransactionRecordRepository transactionRecordRepo;

    @Transactional(rollbackFor = TransactionFailException.class)
    public String transferFund(Payload payload){

        String srcAccName = payload.getSrcAcct();
        String destAccName = payload.getDestAcct();
        Float transferAmount = payload.getAmount();
        
        BankAccount srcAcc;
        BankAccount destAcc;

        try{
            srcAcc = bankAccRepo.getBankAccount(srcAccName);
        }catch(Exception ex){
            throw new TransactionFailException("Cant find source account");
        }

        try{
            destAcc = bankAccRepo.getBankAccount(destAccName);
        }catch(Exception ex){
            throw new TransactionFailException("Cant find destination account");
        }
        
        // check if both accounts are valid (active)
        Boolean isSrcAccActive = srcAcc.getIsActive();
        Boolean isDestAccActive = destAcc.getIsActive();
        
        if( !isSrcAccActive){

            throw new TransactionFailException("Source Account Not Active");

        }else if(!isDestAccActive){

            throw new TransactionFailException("Destination Account Not Active");

        }
        
        // check withdrawn account has more money than withdrawal amount
        if (srcAcc.getBalance() < transferAmount){

            throw new TransactionFailException("Insufficient balance in source account");

        }

        // perform withdrawal
        Integer withdrawSuccessful = bankAccRepo.withdrawFund(srcAcc.getId(),transferAmount);
        if(withdrawSuccessful <= 0){
            throw new TransactionFailException("Fail to withdraw amount from source account");
        }

        //simulate error for rollback
        // int i = 1;
        // if(i == 1){
        //     throw new TransactionFailException("faking an error");
        // }
        
        // perform deposit
        Integer depositSuccessful = bankAccRepo.depositFund(destAcc.getId(),transferAmount);
        if(depositSuccessful <= 0){
            throw new TransactionFailException("Fail to deposit amount into destination account");
        }
       
        // create transaction record
        TransactionRecord record = new TransactionRecord();
        record.setFromAcc(srcAccName);
        record.setToAcc(destAccName);
        record.setAmountTransferred(transferAmount);
        record.setTransactionSuccessful(true);

        JsonObject recordJson = record.toJson();

        // log the transaction record
        try{
            transactionRecordRepo.insertRecord(recordJson); // this will return the inserted doc
        }catch(Exception ex){

            throw new TransactionFailException("Fail to insert transaction record"); // rollback intended
        }

        return record.getTransactionId();
        
    }

}
