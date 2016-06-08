package cp.resources;

import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import cp.models.Account;
import cp.models.CPUser;
import cp.models.Rate;
import cp.services.AccountService;
import cp.services.RateService;
import cp.services.UserCPService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/usersCP")
@RequestScoped
public class UserCPResource {

	@Inject
	private UserCPService userCPService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getList(@FormParam("token") String token) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		if (httpSession.getAttribute("userCPList") == null) {
			response = userCPService.getCPUserList();
			if ((boolean) response.get("success") == true) {
				List<CPUser> userCPList = (List<CPUser>) response.get("userCPList");
				
				httpSession.setAttribute("userCPList", userCPList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("userCPList", JsonUtils.userCPListToJson(userCPList));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<CPUser> userCPList = (List<CPUser>) httpSession.getAttribute("userCPList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("userCPList", JsonUtils.userCPListToJson(userCPList));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
}