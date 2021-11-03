package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.data.Data;
import com.pojo.ConsolidatedList;
import com.pojo.Individual;

public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final Logger log = Logger.getLogger(Customer.class);

	public Customer() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException {
		String payloadRequest = Data.getDataString(request);
		log.info(payloadRequest);

		JSONObject req = new JSONObject(payloadRequest);
		log.info("Request" + req);

		JSONObject res = new JSONObject();

		res = Services(req);
		log.info("Response" + res);

		request.setCharacterEncoding("utf8");
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();
		try {
			out.println(res);
		} finally {
			out.close();
		}
	}

	private JSONObject Services(JSONObject req) throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		String finalName = req.getString("NAME");
		finalName = finalName.replaceAll("\\P{InBasic_Latin}", " ");
		String nameNoSpace = finalName.replaceAll("[^a-zA-Z0-9]", " ").trim().toUpperCase();
		String regex = "\\s+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nameNoSpace);
		nameNoSpace = matcher.replaceAll(" ").trim();

		log.info(nameNoSpace);
		try {
			URL url = new URL("https://scsanctions.un.org/resources/xml/en/consolidated.xml");
			ConsolidatedList consoilderatedList = JAXB.unmarshal(url, ConsolidatedList.class);
			List<Individual> individualList = consoilderatedList.getIndividuals().getIndividual();
			for (Individual individual : individualList) {
				String firstName = individual.getFirstName().trim();
				String secondName = individual.getSecondName();
				String thirdName = individual.getThirdName();
				String fourthName = individual.getFourthName();
				String xmlName = null;
				if (secondName == null && thirdName == null && fourthName == null) {
					xmlName = firstName;
				} else if (thirdName == null && fourthName == null) {
					xmlName = firstName + " " + secondName.trim();
				} else if (thirdName == null) {
					xmlName = firstName + " " + secondName.trim() + " " + fourthName.trim();
				} else if (fourthName == null) {
					xmlName = firstName + " " + secondName.trim() + " " + thirdName.trim();
				}

				else {
					xmlName = firstName + " " + secondName.trim() + " " + thirdName.trim() + " " + fourthName.trim();
				}

				String xmlNoNull = xmlName.replace("null", "").toUpperCase();
				xmlNoNull = xmlNoNull.replaceAll("[^a-zA-Z0-9]", " ");

				Pattern pattern1 = Pattern.compile(regex);
				Matcher matcher1 = pattern1.matcher(xmlNoNull);
				xmlNoNull = matcher1.replaceAll(" ").trim();
				log.info(xmlNoNull);
				if (xmlNoNull.equals(nameNoSpace)) {
					result.put("Message", " This name is already exist");
					throw new Exception("This name already exist");

				}
			}
			log.info("New customer");
			result.put("Message", " New customer");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("unmarshal error");
		}

		return result;
	}

}
