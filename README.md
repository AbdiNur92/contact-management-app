# Contact Management App

A console contact manager: add, view, find, edit and delete contacts from a menu.

## Design

- A contact has a first name (required), last name, phone and email.
- Contacts are stored in an `ArrayList` inside `ContactBook`, which enforces the rules:
  a first name is required, and the exact same contact can't be added twice.
- `Contact` is the data, `ContactBook` the storage and rules, `ContactApp` the menu.

## Run it

Open `src/main/java/se/lexicon/ContactApp.java` in IntelliJ and click ▶ next to `main`.