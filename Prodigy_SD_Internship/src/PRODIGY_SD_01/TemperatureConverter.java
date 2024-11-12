package PRODIGY_SD_01;

import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double temp = 0.0;

        // Loop to get a valid temperature input
        while (true) {
            try {
                // Prompt the user for input
                System.out.println("Welcome to the Temperature Converter!");
                System.out.print("Enter the temperature value : ");
                temp = sc.nextDouble();
                sc.nextLine();  // Consume the newline character

                // Break out of the loop if input is valid
                break;
            } catch (Exception e) {
                // Handle invalid temperature input
                System.out.println("Invalid input for temperature. Please enter a valid number.");
                sc.nextLine(); // Clear the invalid input
            }
        }

        String unit;

        // Loop to get a valid unit input
        while (true) {
            System.out.print("Enter the unit of measurement (Celsius, Fahrenheit, Kelvin): ");
            unit = sc.nextLine().trim().toLowerCase();

            if (unit.equals("celsius") || unit.equals("fahrenheit") || unit.equals("kelvin")) {
                break;  // Valid input; exit the loop
            } else {
                System.out.println("Invalid unit. Please enter Celsius, Fahrenheit, or Kelvin.");
            }
        }

        // Convert and display the temperature in other units
        switch (unit) {
            case "celsius":
                convertFromCelsius(temp);
                break;
            case "fahrenheit":
                convertFromFahrenheit(temp);
                break;
            case "kelvin":
                convertFromKelvin(temp);
                break;
        }
        sc.close();
    }

    private static void convertFromCelsius(double celsius) {
        double fahrenheit = celsius * 9 / 5 + 32;
        double kelvin = celsius + 273.15;

        System.out.println("Temperature in Fahrenheit: " + fahrenheit + "°F");
        System.out.println("Temperature in Kelvin: " + kelvin + "K");
    }

    private static void convertFromFahrenheit(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        double kelvin = celsius + 273.15;

        System.out.println("Temperature in Celsius: " + celsius + "°C");
        System.out.println("Temperature in Kelvin: " + kelvin + "K");
    }

    private static void convertFromKelvin(double kelvin) {
        double celsius = kelvin - 273.15;
        double fahrenheit = celsius * 9 / 5 + 32;

        System.out.println("Temperature in Celsius: " + celsius + "°C");
        System.out.println("Temperature in Fahrenheit: " + fahrenheit + "°F");
    }
}
