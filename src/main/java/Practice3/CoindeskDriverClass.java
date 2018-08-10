package Practice3;
/*
Practice 3 (Rest WS with JSON):
●	https://api.coindesk.com/v1/bpi/currentprice.json
----------The program deserializes json to object and prints it
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.IOException;

public class CoindeskDriverClass {

    public static void main(String[] args) throws IOException {

        Client client = Client.create();
        WebResource webResource = client.resource("http://api.coindesk.com/v1/bpi/currentprice.json");
        ClientResponse response = webResource.accept("application/json").header("User-Agent","Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11").get(ClientResponse.class);
        if (response.getStatus() != 200) throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

        ObjectMapper om = new ObjectMapper();

        String responseJson = response.getEntity(String.class);
        System.out.println("Json received from WS:");
        System.out.println(responseJson);
        System.out.println();

        Coindesk coindesk = om.readValue(responseJson,Coindesk.class);
        System.out.println("Object obtained from the above Json:");
        System.out.println(coindesk);

    }
}

class Coindesk{
    Time time;
    String disclaimer;
    String chartName;
    BPI bpi;

    public Coindesk(Time time, String disclaimer, String chartName, BPI bpi) {
        this.time = time;
        this.disclaimer = disclaimer;
        this.chartName = chartName;
        this.bpi = bpi;
    }

    public Coindesk() {
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public void setBpi(BPI bpi) {
        this.bpi = bpi;
    }

    @Override
    public String toString() {
        return "Coindesk{" +
                "\n\t time=" + time + "," +
                "\n\t disclaimer='" + disclaimer + '\'' + "," +
                "\n\t chartName='" + chartName + '\'' + "," +
                "\n\t bpi=" + bpi +
                '}';
    }
}

class Time{
    String updated;
    String updatedISO;
    String updateduk;

    public Time(String updated, String updatedISO, String updateduk) {
        this.updated = updated;
        this.updatedISO = updatedISO;
        this.updateduk = updateduk;
    }

    public Time() {
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }

    @Override
    public String toString() {
        return "Time{" +
                "\n\t\t updated='" + updated + '\'' + "," +
                "\n\t\t updatedISO='" + updatedISO + '\'' + "," +
                "\n\t\t updateduk='" + updateduk + '\'' +
                '}';
    }
}
class Currency{
    String code;
    String symbol;
    String rate;
    String description;
    double rate_float;

    public Currency(String code, String symbol, String rate, String description, double rate_float) {
        this.code = code;
        this.symbol = symbol;
        this.rate = rate;
        this.description = description;
        this.rate_float = rate_float;
    }

    public Currency() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate_float(double rate_float) {
        this.rate_float = rate_float;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rate='" + rate + '\'' +
                ", description='" + description + '\'' +
                ", rate_float=" + rate_float +
                '}';
    }
}
class BPI{
    @JsonProperty("USD")
    Currency USD;
    @JsonProperty("GBP")
    Currency GBP;
    @JsonProperty("EUR")
    Currency EUR;

    public BPI(Currency USD, Currency GBP, Currency EUR) {
        this.USD = USD;
        this.GBP = GBP;
        this.EUR = EUR;
    }

    public BPI() {
    }

    public void setUSD(Currency USD) {
        this.USD = USD;
    }

    public void setGBP(Currency GBP) {
        this.GBP = GBP;
    }

    public void setEUR(Currency EUR) {
        this.EUR = EUR;
    }

    @Override
    public String toString() {
        return "BPI{" +
                "\n\t\t USD=" + USD + "," +
                "\n\t\t GBP=" + GBP + "," +
                "\n\t\t EUR=" + EUR +
                '}';
    }
}
