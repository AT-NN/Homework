package Practice1.jsonParserCreator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class MyJSONQuery {
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        System.out.println("Enter a color (from the list {black, white, red, blue, yellow, green}) you are interested in:");
        Scanner sc = new Scanner(System.in);
        String colorX = sc.nextLine();

        try {
            JSONObject jsonColors = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\Practice1\\jsonParserCreator\\sourceFile.json"));

            // loop colors array
            JSONArray colors = (JSONArray) jsonColors.get("colors");
            Iterator<JSONObject> colorsIterator = colors.iterator();
            while (colorsIterator.hasNext()) {
                JSONObject jsonColor = colorsIterator.next();

                String color = (String) jsonColor.get("color");
                if (colorX.equals(color)) {
                    System.out.println("color: " + color);

                    String category = (String) jsonColor.get("category");
                    System.out.println("category: " + category);

                    String type = (String) jsonColor.get("type");
                    System.out.println("type: " + type);

                    JSONObject jsonCode = (JSONObject) jsonColor.get("code");
                    System.out.print("code: \n    rgba: ");
                    // loop rgba array
                    JSONArray rgba = (JSONArray) jsonCode.get("rgba");
                    Iterator<Integer> rgbaIterator = rgba.iterator();
                    while (rgbaIterator.hasNext()) {
                        System.out.print(rgbaIterator.next() + " ");
                    }
                    String hex = (String) jsonCode.get("hex");
                    System.out.println("\n    hex: " + hex);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}