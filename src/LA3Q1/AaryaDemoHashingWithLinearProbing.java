package LA3Q1;
import java.util.*;
public class AaryaDemoHashingWithLinearProbing {
    //public fields
    public static int items;
    public static Scanner input = new Scanner(System.in);
    public static double lf;
    public static int tableCapacity;
    public static AaryaValueEntry[] hashTable;
    public static AaryaValueEntry[] workingHashTable;
    //method for add value linear probe
    //adds value to hash table using a parameter
    public static void addValueLinearProbe(Integer k){
        //using the key to get the index
        int i = k%(tableCapacity);
        //changing index to positive if negative
        if (i<0){
            i+=tableCapacity;
        }
        //checking for open slot in hashtable to add a value
        while (hashTable[i].getKey()!=-1 && hashTable[i].getKey()!=-111){
            i++;
            //increment index if value already in slot
            if (i>=tableCapacity){
                i=0;
            }
        }
        //adding the key to the open spot
        hashTable[i].setKey(k);
    }
    //since in hashing, the prime number has to be greater than 2, we can
    //discard 2; 0 and 1 are not prime numbers by definition
    public static int checkPrime(int n) {
        int m = n / 2;//we just need to check half of the n factors
        for (int i = 3; i <= m; i++) {
            if (n % i == 0) {//if n is not a prime number
                i = 2; //reset i to 2 so that it is incremented to 3 in the forheader
                //System.out.printf("i = %d\n",i);
                n++;//next n value
                m = n / 2;//we just need to check half of the n factors
            }
        }
        return n;
    }//end of checkPrime()
    //method for removing value from hash table
    public static void removeValueLinearProbe(Integer k){
        //using key to get index value
        //copying index value to backup index
        int j = k%(tableCapacity); boolean b=true; int i =j;
        //changes key to positive if negative
        if (j<0){
            j+=tableCapacity;
        }
        //checking for value to remove
        while (hashTable[j].getKey()!=k){
            //cycling throw spaces on the hash table to find value to remove
            j++;
            if (j>=tableCapacity){
                j=0;
            }
            //break loop if value not found
            if (j==i){
                b=false;
                break;
            }
        }
        //letting user know that value is found and is removed
        if(b==true){
            System.out.print(k+" is Found and removed! ");
            hashTable[j].setKey(-111);
        }
        //letting user know that the value does not exist
        else {
            System.out.print(k+" is not found! ");
        }
    }
    //prints the hash table
    public static void printHashTable(){
        //declaring variable
        int i;
        //other bracket being displayed
        System.out.print("[");
        //looping throw each slot in the hash table to see if the value exist, is null, or is available
        for(i=0;i<hashTable.length;i++){
            //checking if space is available
            if (hashTable[i].getKey()==-111){
                System.out.print("available, ");
            }
            //checking to see if space is null
            else if (hashTable[i].getKey()==-1){
                System.out.print("null, ");
            }
            //checking if value exists
            else {
                System.out.print(hashTable[i].getKey()+", ");
            }
        }
        //printing the other half of the bracket
        System.out.print("\b\b]\n");
    }
    //rehashing the hash table
    public static void rehashingWithLinearProbe(){
        //cycling through the hash table
        //the new rehashed table receives the keys
        for (int i=0;i<hashTable.length;i++){
            if(hashTable[i].getKey() != -1 && hashTable[i].getKey() !=-111){
                //getting index value
                int j=hashTable[i].getKey()%workingHashTable.length;
                //changing to positive if negative
                if (j<0){j+=workingHashTable.length;}
                //cycling through the hash table to find open slot
                while (true){
                    if(workingHashTable[j].getKey()==-1||workingHashTable[j].getKey()==-111){
                        workingHashTable[j].setKey(hashTable[i].getKey());
                        break;
                    }
                    //incrementing the index
                    else{
                        j++;
                        if (j>workingHashTable.length){j=0;}
                    }
                }
            }
        }
    }
    //method for the header message in the displayed output
    public static void myHeader(String lab) {
        String equalSigns = "=======================================================";
        System.out.printf("%s%n", equalSigns);
        System.out.printf("%s%nPrepared by: Aarya Patel%nStudent Number: 251215259%nGoal of Exercise: " +
                "To gain a better understanding of hash tables and it's multiple uses!%n",lab);
        System.out.printf("%s%n", equalSigns);

    }
    //method for the footer message in the displayed output
    public static void myFooter(String labEnd){
        String equalSigns = "=======================================================";
        System.out.printf("%s", equalSigns);
        System.out.printf("\n%s\nSigning off - Aarya Patel\n", labEnd);
        System.out.printf("%s%n", equalSigns);

    }
    public static void main(String []args){
        //header method called
        myHeader("Lab Assignment 3, Q1");

        //receiving responses from user
        System.out.println("Let's decide on the initial table capacity based on the load factor and dataset size");
        System.out.print("How many data items: ");
        items = input.nextInt();
        System.out.print("What is the load factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();

        //calculating and displaying the table capacity of the hash table
        tableCapacity=checkPrime((int)(items/lf));
        System.out.println("The minimum required table capacity would be: "+tableCapacity);

        //array for hash table using the table capacity as the size
        //instantiating the contents in the hash table
        hashTable=new AaryaValueEntry[tableCapacity];
        for (int i=0;i<hashTable.length;i++){
            hashTable[i]=new AaryaValueEntry();
        }
        //loop used to display the number of items needed to be entered into the hash table
        //then taking user's input and adding to the hash table
        int counter=1;
        for (int i=0;i<items;i++){
            System.out.print("Enter item "+counter+": ");
            int enter = input.nextInt();
            addValueLinearProbe(enter);
            counter++;
        }

        //printing the hash table
        printHashTable();

        //loop for removing values from hash table and displaying it
        System.out.println("Let’s remove two values from the table and then add one...");
        for (int i=1;i<=2;i++){
            System.out.print("Enter a value you want to remove: ");
            int e= input.nextInt();
            removeValueLinearProbe(e);
            printHashTable();
        }

        //adding a value to the hash table and displaying the table
        System.out.print("Enter a value to add to the table: ");
        int a= input.nextInt();
        addValueLinearProbe(a);
        System.out.print("The Current Hash-Table: ");
        printHashTable();

        //rehashing the hash table, getting the new table capacity
        //array for the rehashed table
        //instantiating the array
        System.out.println("Rehashing the table...\nThe rehashed table capacity is: "+checkPrime(tableCapacity*2));
        workingHashTable=new AaryaValueEntry[checkPrime(tableCapacity*2)];
        for (int i=0;i<workingHashTable.length;i++){
            workingHashTable[i]=new AaryaValueEntry();
        }

        //rehashing
        //new modified array for hashtable so it'll print all the values in the hash table
        rehashingWithLinearProbe();
        System.out.print("The Current Hash-Table: ");
        hashTable=new AaryaValueEntry[checkPrime(tableCapacity*2)];
        for (int i=0;i<hashTable.length;i++){
            hashTable[i]=new AaryaValueEntry();
        }
        //copying array to hash so it'll print the table
        System.arraycopy(workingHashTable,0,hashTable,0,hashTable.length);
        printHashTable();

        //calling footer method
        myFooter("Completion of Lab Assignment 3, Q1 is successful!");
    }
}
