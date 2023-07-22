package LA3Q2;
import LA3Q1.*; //this will import classes from the Q1 package
import static LA3Q1.AaryaDemoHashingWithLinearProbing.*; //this will import all the
//static fields and methods from the driver class of the Q1 package
public class AaryaDemoHashingWithAllOpenAddressingTechniques {
    //adding values using the quadratic probing method
    public static void addValueQuadraticProbe(Integer k){
        //variable declaration
        double i=k%tableCapacity;double j = i; double c=1;
        //changes key to positive value if negative to avoid errors
        if (j<0){
            j+=tableCapacity;
        }
        while(true){
            //inserts key into slot if j is available or null
            if(hashTable[(int)j].getKey()==-1||hashTable[(int)j].getKey()==-111){
                hashTable[(int)j].setKey(k);
                break;
            }
            //increments j if value already there
            else {
                //formula for quadratic probing
                j=i+Math.pow(c,2);
                c++;
                //too see if it iterates through the hash table
                if(c>40){
                    System.out.println("Probing failed! Use a load factor of 0.5 or less. Goodbye!");
                    break;
                }
                if(j>tableCapacity-1){
                    j=j%tableCapacity;
                }
            }
        }
    }
    //adding value using the double hashing method
    public static void addValueDoubleHashing(Integer k){
        //getting variables to be used in the while loop to find the result of a hash table that's been double hashed
        int x=k%tableCapacity;int q= thePrimeNumberForSecondHashFunction(tableCapacity);
        int y=q-k%q;int i=0;
        //changing to positive from negative
        if (x<0){
            x+=tableCapacity;
        }
        //cycling through to see if spot is open
        while (true){
            //double hashing formula
            int p=x+(y*i);
            //making sure that p fits inside the capacity
            if (p>tableCapacity){p=p%tableCapacity;}
            //searching for open spot
            if(hashTable[p].getKey()==-1||hashTable[p].getKey()==-111){
                hashTable[p].setKey(k);
                break;
            }
            //increment index
            else{i++;}
        }
    }
    //finds previous prime number
    public static int thePrimeNumberForSecondHashFunction(int n) {
        //making variable equal to parameter
        int v = n;
        //loop for finding previous prime
        while (true) {
            //if not equal and prime,break
            if (v != checkPrime(n)) {
                break;
            }
            //decrementing
            n--;
        }
        //returning the prime
        return n;
    }
    public static void emptyHashTable(){
        //empty table
        hashTable=new AaryaValueEntry[checkPrime(tableCapacity)];
        //makes spaces null
        for (int i=0;i<hashTable.length;i++){
            hashTable[i]=new AaryaValueEntry();
        }
    }
    //print array method
    public static void printArray(Integer[] a){
        System.out.print("[ "); int i;
        for (i=0;i<a.length;i++){
            System.out.print(a[i]+", ");
        }
        System.out.print("\b\b]\n");
    }
    public static void main(String []args){
        //calling header
        myHeader("Lab Assignment 3, Q2");

        //getting input from user
        System.out.println("Let's demonstrate our understanding on all the open addressing techniques...");
        System.out.print("How many data items: ");
        items = input.nextInt();
        System.out.print("What is the load factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();

        //calculating the table capacity using user's inputs and displaying
        tableCapacity=checkPrime((int)(items/lf));
        System.out.println("The minimum required table capacity would be: "+tableCapacity);

        //creating hash table and instantiating it
        hashTable=new AaryaValueEntry[tableCapacity];
        for (int i=0;i<hashTable.length;i++){
            hashTable[i]=new AaryaValueEntry();
        }
        //creating array and displaying it
        Integer[] a=new Integer[]{7,14,-21,-28,35};
        System.out.print("The given dataset is: ");
        printArray(a);

        //adding array values to hash table using linear probe adding method and displaying it
        System.out.println("Let's enter each data item from the above to the hash table:\n");
        System.out.println("Adding data - linear probing resolves collision");
        System.out.println("The Current Hash-Table: ");
        for (int i=0;i<a.length;i++){
            addValueLinearProbe(a[i]);
        }
        printHashTable();

        //emptying hash table to show the array values being added to the hash table using the quadratic probing method
        //displaying results
        emptyHashTable();
        System.out.println("\n\nAdding data - quadratic probing resolves collision");
        for (int i=0;i<a.length;i++){
            addValueQuadraticProbe(a[i]);
        }
        printHashTable();

        //emptying hash table to show the array values being added to the hash table using the double hashing method
        //displaying results
        emptyHashTable();
        System.out.println("\n\nAdding data - double-hashing resolves collision");
        for (int i=0;i<a.length;i++){
            addValueDoubleHashing(a[i]);
        }
        //getting q value for double hashing
        System.out.println("The q value for double hashing is: "+thePrimeNumberForSecondHashFunction(tableCapacity));
        printHashTable();
        emptyHashTable();

        //calling footer method
        myFooter("Completion of Lab Assignment 3, Q2 is successful!");

    }
}
