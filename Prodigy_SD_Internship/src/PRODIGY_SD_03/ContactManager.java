package PRODIGY_SD_03;

import java.io.*;
import java.util.*;

public class ContactManager {

    private static final String FILE_NAME = "contacts.txt";
    private static Map<String, Contact> contacts = new HashMap<>();

    public static void main(String[] args) {
        loadContacts();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nContact Manager");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    editContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContacts();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to add a new contact
    private static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        contacts.put(name, new Contact(name, phone, email));
        System.out.println("Contact added successfully.");
    }

    // Method to view all contacts
    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }

    // Method to edit an existing contact
    private static void editContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to edit: ");
        String name = scanner.nextLine();

        if (contacts.containsKey(name)) {
            Contact contact = contacts.get(name);

            System.out.print("Enter new phone number (leave empty to keep current): ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) {
                contact.setPhoneNumber(phone);
            }

            System.out.print("Enter new email address (leave empty to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                contact.setEmail(email);
            }

            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to delete a contact
    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine();

        if (contacts.remove(name) != null) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to save contacts to a file
    private static void saveContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts.values()) {
                writer.println(contact.getName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail());
            }
            System.out.println("Contacts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Method to load contacts from a file
    private static void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    contacts.put(parts[0], new Contact(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Contacts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No contacts file found. Starting with an empty contact list.");
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    // Contact class to store contact details
    static class Contact {
        private String name;
        private String phoneNumber;
        private String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
        }
    }
}
