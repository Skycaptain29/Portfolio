public class Person {
    private String firstName; // nome
    private String lastName;  // cognome

    // costruttore
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // metodi get
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public boolean equals(Person Person2){
        boolean equals = false;

        if(this.firstName.equals(Person2.getFirstName()) && this.lastName.equals(Person2.getLastName())){
            equals = true;
        }

        return equals;
    }

    // ritorna una rappresentazione testuale dell'oggetto
    @Override
    public String toString(){
        return "<" + this.firstName + "," + this.lastName + ">";
    }
}
