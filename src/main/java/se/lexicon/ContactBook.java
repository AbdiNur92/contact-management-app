package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class ContactBook {
    private  final ArrayList<Contact> contacts = new ArrayList<>();

    public boolean add(Contact contact) {
        if (contact.firstName().isBlank()  || contacts.contains(contact)) {
            return false;
        }
        contacts.add(contact);
        return true;
    }

    public List<Contact> getAll() {
        return new ArrayList<>(contacts);
    }

    public List<Contact> findByName(String name) {
        String search = name.trim().toLowerCase();
        List<Contact> found = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.fullName().toLowerCase().contains(search)) {
                found.add(contact);
            }
        }
        return found;
    }

    public boolean update(Contact old, Contact updated) {
        int index = contacts.indexOf(old);
        if (index < 0 || updated.firstName().isBlank() || (!updated.equals(old) && contacts.contains(updated))) {
            return false;
        }
        contacts.set(index, updated);
        return true;
    }

    public boolean delete(Contact contact) {
        return contacts.remove(contact);
    }
}