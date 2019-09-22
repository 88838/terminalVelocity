import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class playersController {

    public static void createPlayer() {
        try {
            String username = "test";
            String password = "testPassword";

            //the question marks are placeholders
            //user ID is auto-incrementing so it is not needed in the SQL statement
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Players (Username, Password) VALUES (?,?)");


            checkPassword(password);

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setString(1, username);
            ps.setString(2, password);

            //this actually execute the SQL
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());
        } catch(passwordConstraintsException exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void readPlayers() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT PlayerID, Username, Password, HighScore, Currency, SkinID  FROM Players");

            ResultSet results = ps.executeQuery();
            //returns false and stops the loop when there are no more records
            while (results.next()) {
                //the parameter matches the index of the columns in the table
                int playerID = results.getInt(1);
                String username = results.getString(2);
                String password = results.getString(3);
                String highScore = results.getString(4);
                String currency = results.getString(5);
                int skinID = results.getInt(6);
                System.out.println("Player ID: " + playerID);
                System.out.println("Username:  " + username);
                System.out.println("Password: " + password);
                System.out.println("High Score: " + highScore);
                System.out.println("Currency: " + currency);
                System.out.println("SkinID: " + skinID);
                System.out.println();
            }


        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());
        }
    }

    public static void updateUsername() {
        try {
            String username = "test";
            int playerID = 1;

            //the question marks are placeholders
            //the username can be updated depending on the PlayerID that is inputted
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Players SET Username = ?, WHERE PlayerID = ?");

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setString(1, username);
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());

        }

    }

    public static void updatePassword() {
        try {
            String password = "test";
            int playerID = 1;

            //the question marks are placeholders
            //the password can be updated depending on the PlayerID that is inputted
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Players SET Password = ?, WHERE PlayerID = ?");

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setString(1, password);
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());

        }

    }

    public static void updateSkin() {
        try {
            int skinID = 1;
            int playerID = 1;

            //the question marks are placeholders
            //the skin can be updated depending on the PlayerID that is inputted
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Players SET SkinID = ?, WHERE PlayerID = ?");

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setInt(1, skinID);
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());

        }

    }

    public static void updateHighScore() {
        try {
            int highScore = 1;
            int playerID = 1;

            //the question marks are placeholders
            //the skin can be updated depending on the PlayerID that is inputted
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Players SET HighScore = ?, WHERE PlayerID = ?");

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setInt(1, highScore);
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());

        }

    }

    public static void updateCurrency() {
        try {
            int currency = 1;
            int playerID = 1;

            //the question marks are placeholders
            //the skin can be updated depending on the PlayerID that is inputted
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Players SET Currency = ?, WHERE PlayerID = ?");

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setInt(1, currency);
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());

        }

    }

    public static void deletePlayer(){
        try {
            int playerID = 1;

            //the question is a placeholder
            //all the records referring to the PlayerID are deleted
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Players WHERE PlayerID = ?");

            //the first parameter corresponds with the index of each question mark
            //the second parameter is a variable that replaces the question marks
            ps.setInt(1, playerID);
            ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Database error code " + exception.getErrorCode() + ": " + exception.getMessage());

        }

    }
    public static class passwordConstraintsException extends Exception {
        public passwordConstraintsException(String message){
            super(message);
        }
    }
    public static void checkPassword(String password) throws passwordConstraintsException{

            boolean containsUpper = false;
            boolean containsLower = false;
            boolean containsDigit = false;
            boolean correctLength = false;

            if (password.length() >= 8) {
                correctLength = true;
                for(int i = 0; i < password.length(); i ++){
                    char currentCharacter = password.charAt(i);
                    if (Character.isLowerCase(currentCharacter)) {
                        containsLower = true;
                    }else if(Character.isUpperCase(currentCharacter)){
                        containsUpper = true;
                    }else if(Character.isDigit(currentCharacter)){
                        containsDigit = true;
                    }
                }
            }
            if (!(containsUpper && containsLower && containsDigit && correctLength)) {
                throw new passwordConstraintsException("Password must be bigger than 8 characters, contain an upper case and lower case letter, contain a digit.");
            }
    }
}
