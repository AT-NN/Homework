package Practice3;
/*
Practice 3 (Rest WS with JSON):
●	https://http.cat/ (save to file)
----------The program saves 5 random files.
*/

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.*;
import java.nio.file.Files;
import java.security.SecureRandom;

public class Cats {

    public static void main(String[] args) throws IOException, IllegalAccessException {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Practice3\\Cats\\";

        int[] availableStatusCodes = {100,101,200,201,202,204,206,207,300,301,302,303,304,305,307,400,401,402,403,404,405,406,408,409,410,
                411,412,413,414,415,416,417,418,420,421,422,423,424,425,426,429,431,444,450,451,500,502,503,504,506,507,508,509,510,511,599};

        Client client = Client.create();
        SecureRandom sr = new SecureRandom();
        System.out.println("Saving files with error codes: ");
        for (int i=0;i<5;i++){
            int errorCode = availableStatusCodes[sr.nextInt(availableStatusCodes.length)];
            System.out.print(errorCode + " ");
            String url = "https://http.cat/" + errorCode;
            WebResource webResource = client.resource(url);
            ClientResponse response = webResource.get(ClientResponse.class);
            if (response.getStatus() != 200) throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

            OutputStream realFile = new FileOutputStream(filePath + errorCode + ".jpg");
            File responseFile = response.getEntity(File.class);
            Files.copy(responseFile.toPath(),realFile);
            realFile.close();
        }
        System.out.println();
        System.out.println("Please check files in " + filePath);


    }

}
