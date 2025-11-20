import java.io.*;
        import java.net.*;
        import java.nio.charset.StandardCharsets;

public class HttpPostExample {
    public static void main(String[] args) {
        String urlString = "https://httpbin.org/post"; // Change this to your actual URL
        String urlParameters = "name=John+Doe&email=john@example.com";

        try {
            // Create URL object
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set up the connection properties
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // Enable writing output
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(urlParameters.length()));

            // Write the form data to the request body
            try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                out.write(urlParameters.getBytes(StandardCharsets.UTF_8));
                out.flush();
            }

            // Get the response code and input stream
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            in.close();

            // Print the server response
            System.out.println("Server Response:");
            System.out.println(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

