package hb.resources;

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

import hb.models.ExchangeRates;
import hb.services.PaymentService;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;


@Path("/payments")
@RequestScoped
public class PaymentResource {
	
	@Inject
	private PaymentService paymentService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/internalPayment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response internalPayment(@FormParam("token") String token,
									@FormParam("payer_IBAN") String payer_IBAN,
									@FormParam("beneficiary_IBAN") String beneficiary_IBAN,
									@FormParam("amount") Double amount,
									@FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = paymentService.internalPayment(payer_IBAN, beneficiary_IBAN, amount, details);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();

			responseJson.put("success", true);

			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/currentToSavingPayment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response currentToSavingPayment(@FormParam("token") String token,
									@FormParam("payer_IBAN") String payer_IBAN,
									@FormParam("beneficiary_IBAN") String beneficiary_IBAN,
									@FormParam("amount") Double amount,
									@FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		List<ExchangeRates> exchangeRates = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
		
		response = paymentService.currentToSavingPayment(payer_IBAN, beneficiary_IBAN, amount, details, exchangeRates);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();

			responseJson.put("success", true);

			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/savingToCurrentPayment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response savingToCurrentPayment(@FormParam("token") String token,
									@FormParam("payer_IBAN") String payer_IBAN,
									@FormParam("beneficiary_IBAN") String beneficiary_IBAN,
									@FormParam("amount") Double amount,
									@FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		List<ExchangeRates> exchangeRates = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
		
		response = paymentService.savingToCurrentPayment(payer_IBAN, beneficiary_IBAN, amount, details, exchangeRates);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();

			responseJson.put("success", true);

			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/currentToCreditPayment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response currentToCreditPayment(@FormParam("token") String token,
									@FormParam("payer_IBAN") String payer_IBAN,
									@FormParam("beneficiary_IBAN") String beneficiary_IBAN,
									@FormParam("amount") Double amount,
									@FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		List<ExchangeRates> exchangeRates = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
		
		response = paymentService.currentToCreditPayment(payer_IBAN, beneficiary_IBAN, amount, details, exchangeRates);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();

			responseJson.put("success", true);

			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/creditToCurrentPayment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response creditToCurrentPayment(@FormParam("token") String token,
									@FormParam("card_no") String card_no,
									@FormParam("payer_IBAN") String payer_IBAN,
									@FormParam("beneficiary_IBAN") String beneficiary_IBAN,
									@FormParam("amount") Double amount,
									@FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		List<ExchangeRates> exchangeRates = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
		
		response = paymentService.creditToCurrentPayment(card_no, payer_IBAN, beneficiary_IBAN, amount, details, exchangeRates);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();

			responseJson.put("success", true);

			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
		
	}
}
