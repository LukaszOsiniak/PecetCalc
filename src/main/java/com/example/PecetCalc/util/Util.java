package com.example.PecetCalc.util;


import com.example.PecetCalc.model.Computer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {
    public static BigDecimal getRateAtDate(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date.getTime());
        final String uri = "http://api.nbp.pl/api/exchangerates/rates/A/USD/" + formattedDate + "/";

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
}
