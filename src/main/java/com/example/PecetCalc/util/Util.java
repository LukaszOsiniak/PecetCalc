package com.example.PecetCalc.util;


import com.example.PecetCalc.model.Computer;
import com.example.PecetCalc.model.Invoice;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {
    public static BigDecimal getRateAtDate(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date.getTime());
        //final String uri = "http://api.nbp.pl/api/exchangerates/rates/A/USD/" + formattedDate + "/?format=json";
        final String uri = "http://api.nbp.pl/api/exchangerates/rates/A/USD/2023-06-01/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);
        JSONObject jsonObj = new JSONObject(json);
        JSONArray rates = jsonObj.getJSONArray("rates");
        Object name = rates.get(0);
        BigDecimal rate = (BigDecimal) ((JSONObject) name).get("mid");

        return rate;
    }

    public static double sumPriceOfAllComputers(List<Computer> computers) {

        double sum = 0;
        for (Computer cpu : computers) {
            sum += cpu.getPriceInUSD();
        }
        return sum;
    }

    public static void serializeToXML(Invoice invoice) {

        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            Marshaller jaxbMarshaller = context.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = simpleDateFormat.format(invoice.getInvDate().getTime());
            String pathname = invoice.getName() + formattedDate + ".xml";
            File file = new File(pathname);
            jaxbMarshaller.marshal(invoice, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
