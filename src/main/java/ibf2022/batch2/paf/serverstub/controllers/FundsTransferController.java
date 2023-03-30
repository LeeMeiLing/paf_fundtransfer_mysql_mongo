package ibf2022.batch2.paf.serverstub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.serverstub.models.Payload;
import ibf2022.batch2.paf.serverstub.services.TransferService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/api")
public class FundsTransferController {

	@Autowired
	TransferService transferSvc;

	//{srcAcct: "fred", destAcct: "barney", amount: 120}
	@PostMapping("/transfer")
	public ResponseEntity<String> postTransfer(@RequestBody Payload payload) {

		String transactionId = transferSvc.transferFund(payload);

		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }

		JsonObject message = Json.createObjectBuilder().add("transactionId", transactionId).build();
		return ResponseEntity.ok().body(message.toString());

		// Transfer failed return the following JSON object
		// { "message", "Error message" }
		// handled in ErrorController

	}
}
