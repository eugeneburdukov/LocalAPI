package org.eugeneb;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            // API endpoint URL
            String apiUrl = "http://localhost:3000/api/users/";

            // Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // Check if request was successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response data
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response using Gson library
                Gson gson = new Gson();
                User[] users = gson.fromJson(response.toString(), User[].class);

                // Print user information
                for (User user : users) {
                    System.out.println("User ID: " + user.getId());
                    System.out.println("User Name: " + user.getName());
                    System.out.println("User Email: " + user.getEmail());
                    System.out.println();
                }
            } else {
                // Print error message if request was not successful
                System.out.println("Failed to fetch user data. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();

        } catch (Exception e) {
            // Print exception if an error occurs
            e.printStackTrace();
        }
    }
}
