package ui.functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Functions {
    public static void savePricesToFile(String productPrice, String productFee, String filePath) {
        File file = new File(filePath);

        try {
            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Write the product price and fee to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Product Price: " + productPrice);
                writer.newLine();
                writer.write("Product Fee: " + productFee);
                writer.newLine();
            }

            System.out.println("Prices saved to file: " + filePath);

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
