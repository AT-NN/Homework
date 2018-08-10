package Practice3;
/*
Practice 3 (Rest WS with JSON):
●	https://yesno.wtf/api (save each answer in separate folder with file - 1000 times)
----------The program makes 5 iterations.
----------https://stackoverflow.com/questions/46081424/java-8-recv-tlsv1-2-alert-fatal-handshake-failure
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class YesnoDriverClass {

    public static void main(String[] args) throws IOException {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Practice3\\Yesno\\";

        Client client = Client.create();
        System.out.println("Saving image and json as files to a folder... ");
        for (int i=0;i<5;i++){
            String currentIteration = String.valueOf(i+1);
            System.out.print(currentIteration + " ");
            WebResource webResource = client.resource("https://yesno.wtf/api");
            ClientResponse response = webResource.get(ClientResponse.class);
            if (response.getStatus() != 200) throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

            String responseJson = response.getEntity(String.class);

            File folder = new File(filePath + currentIteration);
            folder.mkdir();
            String folderPath = folder.getPath() + "\\";
            FileWriter fw = new FileWriter(folderPath + currentIteration + ".json");
            fw.write(responseJson);
            fw.flush();
            fw.close();

            ObjectMapper om = new ObjectMapper();
            WTF wtf = om.readValue(responseJson,WTF.class);
            URL url = new URL(wtf.image);
            InputStream imageFile = url.openStream();
            File realFile = new File(folderPath + currentIteration + ".gif" );
            Files.copy(imageFile, realFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println();
        System.out.println("Please check files in " + filePath);
    }

}
class WTF{
    String answer;
    boolean forced;
    String image;

    public WTF() {
    }

    public WTF(String answer, boolean forced, String image) {
        this.answer = answer;
        this.forced = forced;
        this.image = image;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "WTF{" +
                "answer='" + answer + '\'' +
                ", forced=" + forced +
                ", image='" + image + '\'' +
                '}';
    }
}
