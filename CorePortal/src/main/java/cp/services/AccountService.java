package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class AccountService {
	
	public Map<String, Object> getAccountList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<Account> accountList =	DataBase.getAccountList();
		if(accountList != null){
			response.put("accountList", accountList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}
	}
}
