package Practice1.jsonParserCreator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class MyJSONCreator {
    public static void main(String[] args) {
        JSONObject colors = new JSONObject();
        JSONArray colorsList = new JSONArray();

        String fileName = System.getProperty("user.dir")+"\\src\\main\\java\\Practice1\\jsonParserCreator\\outputFile.json";
        //black color
        JSONObject color = new JSONObject();
        color.put("color", "black");
        color.put("category", "hue");
        color.put("type", "primary");

        JSONArray rgbaList = new JSONArray();
        rgbaList.add(new Integer(255));
        rgbaList.add(new Integer(255));
        rgbaList.add(new Integer(255));
        rgbaList.add(new Integer(1));

        JSONObject code = new JSONObject();
        code.put("rgba", rgbaList);
        code.put("hex", "#000");

        color.put("code",code);

        colorsList.add(color);

        //white color
        color = new JSONObject();
        color.put("color", "white");
        color.put("category", "value");

        rgbaList = new JSONArray();
        rgbaList.add(new Integer(0));
        rgbaList.add(new Integer(0));
        rgbaList.add(new Integer(0));
        rgbaList.add(new Integer(1));

        code = new JSONObject();
        code.put("rgba", rgbaList);
        code.put("hex", "#FFF");

        color.put("code",code);

        colorsList.add(color);

        colors.put("colors",colorsList);

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(colors.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(colors);
        System.out.println("Check created json in file: " + fileName);
    }

}
