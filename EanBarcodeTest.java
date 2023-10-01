//  This program tests the EanBarcode class.
public static String completeBarcode(String barcode) {
    // Check if the input barcode has a valid prefix (either EAN-13 or EAN-8)
    if (barcode.matches("\\d{7,13}")) {
        // Remove any non-numeric characters from the barcode
        barcode = barcode.replaceAll("\\D", "");              //"\\D" - non-digit; "" - empty string 

        int sum = 0;                                          // Initialize the sum
        int multiplier = 3;                                   // Initialize the multiplier

        // Loop through the barcode digits in reverse order
        for (int i = barcode.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(barcode.charAt(i));  // Get the current digit
            sum += digit * multiplier;                        // Add the current digit to the sum
            multiplier = 4 - multiplier;                      // Toggle between 3 and 1
        }

        /*  Calculates the check digit by subtracting the remainder of the sum of the first 12
            or 7 digits of the barcode from 10, then taking the remainder of the result when
            divided by 10. */
        int checkDigit = (10 - (sum % 10)) % 10;

        // Return the barcode with the calculated check digit
        return barcode + checkDigit;
    } else {
        // Handle an invalid barcode prefix
        System.out.println("Invalid barcode prefix");
        return barcode;
    }
}
