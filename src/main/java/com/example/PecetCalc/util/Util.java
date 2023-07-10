package com.example.PecetCalc.util;


import com.example.PecetCalc.model.Computer;
import com.example.PecetCalc.model.Invoice;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;

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

    public static Faktura convert(Invoice invoice) throws DatatypeConfigurationException {
        Faktura faktura = new Faktura();
        List<Computer> list = invoice.getComputers();
        for (Computer cpu : list) {
            Faktura.Komputer komp = new Faktura.Komputer();
            komp.setKosztPLN(cpu.getPriceInPLN());
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(cpu.getAccDate());
            XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            komp.setDataKsiegowania(xmlGregCal);
            komp.setKosztUSD(cpu.getPriceInUSD());
            komp.setNazwa(cpu.getName());
            faktura.getKomputer().add(komp);
        }
        return faktura;
    }

    public static void serializeToXML(Faktura faktura) {

        try {
            JAXBContext context = JAXBContext.newInstance(Faktura.class);
            Marshaller jaxbMarshaller = context.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String pathname = "Faktura" + faktura.getKomputer().get(1).kosztPLN + ".xml";
            File file = new File(pathname);
            jaxbMarshaller.marshal(faktura, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
