import java.util.*;
// Class representing a customer
class Customer {
    int accountNumber;
    String name;
    double balance;

    // Constructor
    public Customer(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }
}

class CustomerList {
    private CustomerNode head;

    // Constructor to initialize an empty customer list
    public CustomerList() {
        head = null;
    }

    // Method to add a new customer account to the list
    public void addCustomer(Customer customer) {
        CustomerNode newNode = new CustomerNode(customer);

        if (head == null) {
            head = newNode;
        } else {
            CustomerNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a customer account by account number
    public Customer findCustomer(int accountNumber) {
        CustomerNode current = head;
        while (current != null) {
            if (current.customer.accountNumber == accountNumber) {
                return current.customer;
            }
            current = current.next;
        }
        return null; // Customer not found
    }

    // Method to delete a customer account by account number
    public boolean deleteCustomer(int accountNumber) {
        if (head == null) {
            return false; // List is empty
        }

        if (head.customer.accountNumber == accountNumber) {
            head = head.next;
            return true; // Deleted the head node
        }

        CustomerNode current = head;
        while (current.next != null) {
            if (current.next.customer.accountNumber == accountNumber) {
                current.next = current.next.next;
                return true; // Deleted a non-head node
            }
            current = current.next;
        }

        return false; // Customer not found
    }

    // Inner class representing a node in the singly linked list
    private class CustomerNode {
        Customer customer;
        CustomerNode next;

        public CustomerNode(Customer customer) {
            this.customer = customer;
            this.next = null;
        }
    }
}

class CircularCustomerList {
    private CustomerNode head;
    private int size;

    // Constructor to initialize an empty circular customer list
    public CircularCustomerList() {
        head = null;
        size = 0;
    }

    // Method to add a new customer account to the circular list
    public void addCustomer(Customer customer) {
        CustomerNode newNode = new CustomerNode(customer);
        if (head == null) {
            head = newNode;
            newNode.next = newNode; // Point to itself in a circular list
        } else {
            newNode.next = head.next;
            head.next = newNode;
            head = newNode; // Update the head to the new node
        }
        size++;
    }

    // Method to search for a customer account by account number
    public Customer findCustomer(int accountNumber) {
        if (head == null) {
            return null; // List is empty
        }

        CustomerNode current = head.next;
        while (current != head) {
            if (current.customer.accountNumber == accountNumber) {
                return current.customer;
            }
            current = current.next;
        }

        // Check the head node
        if (head.customer.accountNumber == accountNumber) {
            return head.customer;
        }

        return null; // Customer not found
    }

    // Method to delete a customer account by account number
    public boolean deleteCustomer(int accountNumber) {
        if (head == null) {
            return false; // List is empty
        }

        if (head.customer.accountNumber == accountNumber) {
            if (size == 1) {
                head = null; // List had only one element
            } else {
                head = head.next;
            }
            size--;
            return true; // Deleted the head node
        }

        CustomerNode current = head;
        while (current.next != head) {
            if (current.next.customer.accountNumber == accountNumber) {
                current.next = current.next.next;
                size--;
                return true; // Deleted a non-head node
            }
            current = current.next;
        }

        return false; // Customer not found
    }

    // Method to get the size of the circular list
    public int getSize() {
        return size;
    }

    // Inner class representing a node in the circular linked list
    private class CustomerNode {
        Customer customer;
        CustomerNode next;

        public CustomerNode(Customer customer) {
            this.customer = customer;
            this.next = null;
        }
    }
}

class TransactionHistory {
    private Stack<TransactionRecord> transactionStack;

    // Constructor to initialize an empty transaction history
    public TransactionHistory() {
        transactionStack = new Stack<>();
    }

    // Method to record a transaction
    public void recordTransaction(TransactionRecord transaction) {
        transactionStack.push(transaction);
    }

    // Method to undo the last recorded transaction
    public TransactionRecord undoTransaction() {
        if (!transactionStack.isEmpty()) {
            return transactionStack.pop();
        }
        return null; // No transaction to undo
    }

    // Method to view the transaction history without removing records
    public Stack<TransactionRecord> viewTransactionHistory() {
        return transactionStack;
    }

    // Inner class representing a transaction record
    public static class TransactionRecord {
        private String description;

        public TransactionRecord(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}

class CustomerQueue {
    private Customer[] queue;
    private int front; // Index of the front of the queue
    private int rear; // Index of the rear of the queue
    private int size; // Current number of customers in the queue
    private int capacity; // Maximum capacity of the queue

    // Constructor to initialize an empty customer queue with a specified capacity
    public CustomerQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Customer[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Method to add a customer to the end of the queue
    public void enqueue(Customer customer) {
        if (!isFull()) {
            rear = (rear + 1) % capacity; // Circular queue
            queue[rear] = customer;
            size++;
        } else {
            System.out.println("Queue is full. Cannot add more customers.");
        }
    }

    // Method to remove and return the customer at the front of the queue
    public Customer dequeue() {
        if (!isEmpty()) {
            Customer removedCustomer = queue[front];
            front = (front + 1) % capacity; // Circular queue
            size--;
            return removedCustomer;
        } else {
            System.out.println("Queue is empty. No customers to dequeue.");
            return null;
        }
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Method to get the number of customers in the queue
    public int getSize() {
        return size;
    }

    // Inner class representing a customer
    public static class Customer {
        private int accountNumber;
        private String name;

        public Customer(int accountNumber, String name) {
            this.accountNumber = accountNumber;
            this.name = name;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Customer [Account Number=" + accountNumber + ", Name=" + name + "]";
        }
    }
}

class BankTree {
    TreeNode root;

    public BankTree() {
        root = null;
    }

    // Class representing a node in the BST
    class TreeNode {
        Customer customer;
        TreeNode left;
        TreeNode right;

        public TreeNode(Customer customer) {
            this.customer = customer;
            this.left = null;
            this.right = null;
        }
    }

    // Method to insert a new customer into the BST
    public void insert(Customer customer) {
        root = insertRec(root, customer);
    }

    // Recursive method to insert a customer into the BST
    private TreeNode insertRec(TreeNode root, Customer customer) {
        if (root == null) {
            root = new TreeNode(customer);
            return root;
        }

        int nameComparison = customer.name.compareTo(root.customer.name);

        if (nameComparison < 0) {
            root.left = insertRec(root.left, customer);
        } else if (nameComparison > 0) {
            root.right = insertRec(root.right, customer);
        }

        return root;
    }

    // Method to search for a customer by account number
    public Customer findCustomer(int accountNumber) {
        return findCustomerRec(root, accountNumber);
    }

    // Recursive method to search for a customer by account number
    private Customer findCustomerRec(TreeNode root, int accountNumber) {
        if (root == null || root.customer.accountNumber == accountNumber) {
            return (root != null) ? root.customer : null;
        }

        if (accountNumber < root.customer.accountNumber) {
            return findCustomerRec(root.left, accountNumber);
        }

        return findCustomerRec(root.right, accountNumber);
    }

    // Method to delete a customer by account number
    public boolean deleteCustomer(int accountNumber) {
        TreeNode parent = null;
        TreeNode current = root;

        while (current != null) {
            if (accountNumber == current.customer.accountNumber) {
                deleteNode(parent, current);
                return true;
            } else if (accountNumber < current.customer.accountNumber) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }

        return false;
    }

    // Helper method to delete a node from the BST
    private void deleteNode(TreeNode parent, TreeNode nodeToDelete) {
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null;
            }
        } else if (nodeToDelete.left != null && nodeToDelete.right != null) {
            TreeNode successor = findMin(nodeToDelete.right);
            nodeToDelete.customer = successor.customer;
            deleteNode(nodeToDelete, successor);
        } else {
            TreeNode child = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {
                root = child;
            }
        }
    }

    // Helper method to find the minimum node in a subtree
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

class Bank {
    private CustomerList customerList;
    private CircularCustomerList circularCustomerList;
    private TransactionHistory transactionHistory;
    private CustomerQueue customerQueue;
    private BankTree bankTree;

    private Scanner scanner;

    public Bank() {
        // Initialize data structures here
        customerList = new CustomerList();
        circularCustomerList = new CircularCustomerList();
        transactionHistory = new TransactionHistory();
        bankTree = new BankTree();
        customerQueue = new CustomerQueue(100); // Set an appropriate capacity
        scanner = new Scanner(System.in);

    }

    // Method to display the main menu
    public void displayMenu() {
        System.out.println("Bank Operations Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. View Transaction History");
        System.out.println("6. Delete Account");
        System.out.println("7. View Details of All Customers in Dictionary Order");
        System.out.println("8. Exit");
    }

    // Method to perform bank operations based on user input
    public void performOperations() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    deleteAccount();
                    break;
                case 7:
                    viewAllCustomers();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();
    }

    // Method to create a new customer account
    public void createAccount() {
        System.out.println("Enter account details:");

        // Input account number
        System.out.print("Account Number: ");
        int accountNumber = scanner.nextInt();

        // Input customer name
        scanner.nextLine(); // Consume the newline character
        System.out.print("Customer Name: ");
        String name = scanner.nextLine();

        // Input initial balance
        System.out.print("Initial Balance: ");
        double balance = scanner.nextDouble();

        // Create a new customer object
        Customer newCustomer = new Customer(accountNumber, name, balance);

        // Add the new customer to the customer list
        customerList.addCustomer(newCustomer);

        // Insert the new customer into the BST
        bankTree.insert(newCustomer); // This line is essential

        System.out.println("Account created successfully.");

        // Debug: Print the customer details after insertion
        System.out.println("Customer inserted into BST:");
        System.out.println("Account Number: " + newCustomer.accountNumber);
        System.out.println("Customer Name: " + newCustomer.name);
        System.out.println("Balance: " + newCustomer.balance);
    }

    // Method to deposit money into an account
    public void deposit() {
        System.out.println("Enter account details for deposit:");

        // Input account number
        System.out.print("Account Number: ");
        int accountNumber = scanner.nextInt();

        // Input deposit amount
        System.out.print("Deposit Amount: ");
        double depositAmount = scanner.nextDouble();

        // Find the customer by account number
        Customer customer = customerList.findCustomer(accountNumber);

        if (customer != null) {
            // Update the customer's balance with the deposit amount
            customer.balance += depositAmount;

            // Create a transaction record for the deposit
            TransactionHistory.TransactionRecord transaction = new TransactionHistory.TransactionRecord(
                    "Deposited " + depositAmount + " into account " + accountNumber);

            // Record the transaction in the transaction history
            transactionHistory.recordTransaction(transaction);

            System.out.println("Deposit successful. New balance: " + customer.balance);
        } else {
            System.out.println("Account not found. Deposit failed.");
        }
    }

    // Method to withdraw money from an account
    public void withdraw() {
        System.out.println("Enter account details for withdrawal:");

        // Input account number
        System.out.print("Account Number: ");
        int accountNumber = scanner.nextInt();

        // Input withdrawal amount
        System.out.print("Withdrawal Amount: ");
        double withdrawalAmount = scanner.nextDouble();

        // Find the customer by account number
        Customer customer = customerList.findCustomer(accountNumber);

        if (customer != null) {
            if (customer.balance >= withdrawalAmount) {
                // Sufficient balance for withdrawal
                customer.balance -= withdrawalAmount;

                // Create a transaction record for the withdrawal
                TransactionHistory.TransactionRecord transaction = new TransactionHistory.TransactionRecord(
                        "Withdrawn " + withdrawalAmount + " from account " + accountNumber);

                // Record the transaction in the transaction history
                transactionHistory.recordTransaction(transaction);

                System.out.println("Withdrawal successful. New balance: " + customer.balance);
            } else {
                System.out.println("Insufficient balance. Withdrawal failed.");
            }
        } else {
            System.out.println("Account not found. Withdrawal failed.");
        }
    }

    // Method to transfer money between accounts
    public void transfer() {
        System.out.println("Enter transfer details:");

        // Input source account number
        System.out.print("Source Account Number: ");
        int sourceAccountNumber = scanner.nextInt();

        // Input destination account number
        System.out.print("Destination Account Number: ");
        int destinationAccountNumber = scanner.nextInt();

        // Input transfer amount
        System.out.print("Transfer Amount: ");
        double transferAmount = scanner.nextDouble();

        // Find the source and destination customers by account number
        Customer sourceCustomer = customerList.findCustomer(sourceAccountNumber);
        Customer destinationCustomer = customerList.findCustomer(destinationAccountNumber);

        if (sourceCustomer != null && destinationCustomer != null) {
            if (sourceCustomer.balance >= transferAmount) {
                // Sufficient balance in the source account for the transfer
                sourceCustomer.balance -= transferAmount;
                destinationCustomer.balance += transferAmount;

                // Create transaction records for both accounts
                TransactionHistory.TransactionRecord sourceTransaction = new TransactionHistory.TransactionRecord(
                        "Transferred " + transferAmount + " to account " + destinationAccountNumber);
                TransactionHistory.TransactionRecord destinationTransaction = new TransactionHistory.TransactionRecord(
                        "Received " + transferAmount + " from account " + sourceAccountNumber);

                // Record the transactions in the transaction history
                transactionHistory.recordTransaction(sourceTransaction);
                transactionHistory.recordTransaction(destinationTransaction);

                System.out.println("Transfer successful.");
                System.out.println("Source Account Balance: " + sourceCustomer.balance);
                System.out.println("Destination Account Balance: " + destinationCustomer.balance);
            } else {
                System.out.println("Insufficient balance in the source account. Transfer failed.");
            }
        } else {
            System.out.println("One or both accounts not found. Transfer failed.");
        }
    }

    // Method to view transaction history
    public void viewTransactionHistory() {
        Stack<TransactionHistory.TransactionRecord> history = transactionHistory.viewTransactionHistory();

        if (history.isEmpty()) {
            System.out.println("Transaction history is empty.");
        } else {
            System.out.println("Transaction History:");
            int count = 1;
            for (TransactionHistory.TransactionRecord record : history) {
                System.out.println(count + ". " + record.getDescription());
                count++;
            }
        }
    }

    // Method to delete a customer account by account number
    public void deleteAccount() {
        System.out.println("Enter account number to delete:");
        int accountNumber = scanner.nextInt();

        if (customerList.deleteCustomer(accountNumber)) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found. Deletion failed.");
        }
    }

    // Method to view details of all customers in dictionary order
    public void viewAllCustomers() {
        List<Customer> customers = getAllCustomersInDictionaryOrder();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("Customers (Dictionary Order):");
            for (Customer customer : customers) {
                System.out.println("Account Number: " + customer.accountNumber);
                System.out.println("Customer Name: " + customer.name);
                System.out.println("Balance: " + customer.balance);
                System.out.println("------------------------");
            }
        }
        // Debug: Print the number of customers retrieved
        System.out.println("Total customers retrieved: " + customers.size());
    }

    // Helper method to get all customers in dictionary order
    private List<Customer> getAllCustomersInDictionaryOrder() {
        List<Customer> customers = new ArrayList<>();
        getAllCustomersInDictionaryOrder(bankTree.root, customers);
        return customers;
    }

    // Helper method to get all customers in dictionary order
    private void getAllCustomersInDictionaryOrder(BankTree.TreeNode node, List<Customer> customers) {
        if (node != null) {
            getAllCustomersInDictionaryOrder(node.left, customers);
            if (node.customer != null) {
                customers.add(node.customer);
            }
            getAllCustomersInDictionaryOrder(node.right, customers);
        }
        // Debug: Print the customer details during traversal
        if (node != null && node.customer != null) {
            System.out.println("Visited customer with Account Number: " + node.customer.accountNumber);
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.performOperations();
    }
}
