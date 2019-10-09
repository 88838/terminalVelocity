package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class skinsController {

    public static void readSkins(){
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT SkinID, SkinName, ImageFile, Cost  FROM Skins");

            ResultSet results = ps.executeQuery();

            while (results.next()) {

                int skinID = results.getInt(1);
                String skinName = results.getString(2);
                String imageFile = results.getString(3);
                int cost = results.getInt(4);
                System.out.println("SkinID: " + skinID);
                System.out.println("Skin Name:  " + skinName);
                System.out.println("Image File: " + imageFile);
                System.out.println("Cost: " + cost);
                System.out.println();
            }
        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());
        }
    }
}