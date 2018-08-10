package Practice2;
/*
Practice 2 (Rest WS with XML):
●	Read a set of dates from file (txt/csv/xls)
●	Use these dates as parameters to request currency rates from BNM site (https://bnm.md/en/official_exchange_rates?get_xml=1&date=13.02.2018)
●	Create a list of objects based on received values
●	Save these values on separate page (sheet) for each date in xls/xlsx file
*/

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Practice2DriverClass {

    public static void main(String[] args) throws IOException, IllegalAccessException {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Practice2\\";

        System.out.println("Reading dates from " + filePath + "dates.txt file...");
        List<String> dates = new ArrayList<>();
        FileReader f = new FileReader(filePath + "dates.txt");

        Scanner sc = new Scanner(f);
        while(sc.hasNext()) dates.add(sc.nextLine());
        f.close();

        System.out.println("Requesting currency rates from BNM site and creating the list of objects...");
        List<ValCurs> valCurs = new ArrayList<>();

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Valute.class);
        xstream.processAnnotations(ValCurs.class);

        Client client = Client.create();
        String xml;
        for (String date : dates) {
            xml = readValCurs(client,date);
            valCurs.add((ValCurs) xstream.fromXML(xml));
        }

        System.out.println("Writing data to xlsx file...");
        XSSFWorkbook workbook = new XSSFWorkbook();
        for(ValCurs vc: valCurs) writeDataToSheet(workbook, vc);

        OutputStream result = new FileOutputStream(filePath + "CurrencyRates.xlsx");
        workbook.write(result);
        workbook.close();
        result.close();
        System.out.println("Please check " + filePath + "CurrencyRates.xlsx file");
    }

    private static void writeDataToSheet(Workbook workbook,ValCurs valCurs) throws IllegalAccessException {
        Sheet sheet = workbook.createSheet(valCurs.getDate());
        Row row = sheet.createRow(0);
        Cell cell;
        Field[] fields = Valute.class.getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(fields[i].getName());
            fields[i].setAccessible(true);
        }
        for (int i=0;i<valCurs.getValute().length;i++){
            row = sheet.createRow(i+1);
            Valute val = valCurs.getValute()[i];
            cell = row.createCell(0);
            cell.setCellValue(val.getId());
            cell = row.createCell(1);
            cell.setCellValue(val.getNumCode());
            cell = row.createCell(2);
            cell.setCellValue(val.getCharCode());
            cell = row.createCell(3);
            cell.setCellValue(val.getNominal());
            cell = row.createCell(4);
            cell.setCellValue(val.getName());
            cell = row.createCell(5);
            cell.setCellValue(val.getValue());
        }
    }

    private static String readValCurs(Client client, String date){
        WebResource webResource = client.resource("https://bnm.md/en/official_exchange_rates?get_xml=1&date=" + date);
        ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
        if (response.getStatus() != 200) throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        return response.getEntity(String.class);
    }
}
