package se.lexicon;

public record Contact(String firstName, String lastName, String phone, String email) {

    public String fullName(){
        return (firstName + " " + lastName).trim();
    }
}
