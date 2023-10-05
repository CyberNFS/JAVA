public static void main( String[] args ) {
        BankAccount accountOne = new BankAccount("Natwest", "Bob Smith", "Mr", 0);

        // Test processing transactions
        accountOne.processTransaction('c', 100.00); // Credit
        accountOne.processTransaction('d', 50.00);  // Debit
        accountOne.processTransaction('f', 50.00);  // Flag

        // Check bank and title
        System.out.println("Is the bank Natwest? " + accountOne.checkBank("NatweSt"));
        System.out.println("Is the title Mr? " + accountOne.checkTitle("MR"));

        // Check if there are two names
        System.out.println("Does the account holder have two names? " + accountOne.checkTwoNames());

        // Test name matching
        System.out.println("Is 'Bob Smith' the same as the account holder's name? " + accountOne.checkName("Bob SmIth"));
        System.out.println("Is 'B Smith' the same as the account holder's name? " + accountOne.checkName("B SmIth"));

    }
