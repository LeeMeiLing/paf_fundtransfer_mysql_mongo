package ibf2022.batch2.paf.serverstub.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class TransactionRecordRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * db.records.insertOne({
    
            transactionId : "09076a1e-2717-4708-910e-5f6262680909",
            fromAcc: 'Fred',
            toAcc: 'Barney',
            amountTransferred: 130.50, 
            transactionSuccessful: true

        })
     */
    public Document insertRecord(JsonObject recordJson){
    
        Document toInsert = Document.parse(recordJson.toString()); // alternative way is to create a new Document()
		
        Document inserted = mongoTemplate.insert(toInsert,"records");
        return inserted;
        
    }


    
}
