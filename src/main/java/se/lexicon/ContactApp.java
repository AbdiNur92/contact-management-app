package se.lexicon;

import java.util.List;

public class ContactApp {
    //PLAN
    //Functionality:    Add view all, find by name, edit, delete, exit
    //Data:             First name. last name, phone, email
    //Data structure:   ArrayList - keeps the order, easy to loop over and sort
    //Constraints:      First name is required
    //Organisation:     Contact = the data
    //                  ContactBook = the list and its rules
    //                  ContactApp = menu, reading and printing

    static final ContactBook book = new ContactBook();

    void main() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ", 1, 6);
            if (choice == 6) {
                running = false;
            } else {
                IO.println();
                runChoice(choice);
                IO.println();
            }
        }
        IO.println("Goodbye!");
    }



    static void printMenu() {
        IO.println("===== CONTACT BOOK =====");
        IO.println("1. Add contact");
        IO.println("2. View all contacts");
        IO.println("3. Find contact by name");
        IO.println("4. Edit contact");
        IO.println("5. Delete contact");
        IO.println("6. Exit");
        IO.println("========================");
    }

    static void runChoice(int choice) {
        switch (choice) {
            case 1 -> addContact();
            case 2 -> viewAll();
            case 3 -> findContact();
            case 4 -> editContact();
            case 5 -> deleteContact();

        }
    }

    static void addContact() {
        IO.println("--- Add contact ---");
        String firstName = readRequired("First name: ");
        String lastName = readText("Last name: ");
        String phone = readText("Phone: ");
        String email = readText("Email: ");
        if (book.add(new Contact(firstName, lastName, phone, email))) {
            IO.println("Contact added.");
        } else {
            IO.println("That contact is already in the book.");
        }
    }

    static void viewAll() {
        IO.println("--- All contacts ---");
        printList(book.getAll(), "No contacts yet.");
    }

    static void findContact() {
        IO.println("--- Find contact ---");
        printList(book.findByName(readText("Name: ")), "No contacts found.");
    }
    static void editContact() {
        IO.println("--- Edit contact ---");
        Contact old = chooseContact();
        if (old == null) {
            return;
        }
        IO.println("Press Enter to keep what's in [brackets].");
        Contact updated = new Contact(readOrKeep("First name", old.firstName()), readOrKeep("Last name", old.lastName()), readOrKeep("Phone", old.phone()), readOrKeep("Email", old.email()));
        if (book.update(old, updated)) {
            IO.println("Contact updated.");
        }
        else {
            IO.println("That contact is already in the book.");
        }
    }

    static void deleteContact() {
        IO.println("--- Delete contact ---");
        Contact contact = chooseContact();
        if (contact != null && book.delete(contact)) {
            IO.println("Contact deleted.");
        }
    }

    static Contact chooseContact() {
        List<Contact> matches = book.findByName(readText("Name: "));
        printList(matches, "No contacts found.");
        if (matches.isEmpty()) {
            return null;
        }
        return matches.get(readInt("Choose a contact: ", 1, matches.size()) -1);
    }

    static void printList(List<Contact> contacts, String whenEmpty) {
        if (contacts.isEmpty()) {
            IO.println(whenEmpty);
        }
        for (int i = 0; i < contacts.size(); i++) {
            IO.println((i + 1) + ". " + describe(contacts.get(i)));
        }
    }

    static String describe(Contact contact) {
        return contact.fullName() + " | " + orDash(contact.phone()) + " | " + orDash(contact.email());
    }

    static String orDash(String text) {
        return text.isEmpty() ? "-" : text;
    }

    static String readText(String prompt) {
        return IO.readln(prompt).trim();
    }

    static String readRequired(String prompt) {
        String answer = readText(prompt);
        while (answer.isEmpty()) {
            IO.println("This field is required.");
            answer = readText(prompt);
        }
        return answer;
    }

    static String readOrKeep (String label, String current) {
        String answer = readText(label + " [" + current + "]: ");
        return answer.isEmpty() ? current : answer;
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                int number = Integer.parseInt(readText(prompt));
                if (number >= min && number <= max) {
                    return number;
                }
                IO.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                IO.println("Please enter a number.");
            }
        }
    }
}