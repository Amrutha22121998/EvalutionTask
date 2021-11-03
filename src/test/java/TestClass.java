import java.io.File;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.pojo.ConsolidatedList;
import com.pojo.Individual;
import com.pojo.Individuals;

public class TestClass {

	public static void main(String[] args) throws Exception {
		String req = "SAID JAN ‘ABD AL-SALAM";
		req = req.replaceAll("[^a-zA-Z0-9]", " ");
		String nameNoSpace = req.trim().toUpperCase();
		System.out.println(nameNoSpace);
		try {

			URL url = new URL("https://scsanctions.un.org/resources/xml/en/consolidated.xml");
			ConsolidatedList consoilderatedList = JAXB.unmarshal(url, ConsolidatedList.class);
			System.out.println(consoilderatedList);
			Individuals test = consoilderatedList.getIndividuals();
			System.out.println(test);
			List<Individual> individualList = consoilderatedList.getIndividuals().getIndividual();
			System.out.println(individualList.size());
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

				String xmlNoNull = xmlName.replace("null", "").replaceAll("[^a-zA-Z0-9]", " ").toUpperCase();
				System.out.println(xmlNoNull);
				// log.info(xmlName);

				if (xmlNoNull.equals(nameNoSpace)) {
					throw new Exception("This name already exist");
				}

				// System.out.println("This name already exist"); //
				// log.info("This name already exist");
				// result.put("Message"," This name is already exist"); }
			}
			throw new Exception("New customer");
//			if(count!=0) {
//				System.out.println("This name already exist");
//			}
//			else {
//				System.out.println("New customer");
//			}

		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("unmarshal error :" + e);
//			log.info("unmarshal error");
		}

	}

}
