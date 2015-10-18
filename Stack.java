// A stack class to make life easier.

class Stack
{
    int[] arr; int top=-1;
    
    Stack(int n)
    { arr = new int[n]; }
    
    boolean isFull()
    { return top == arr.length-1; }
    
    int length()
    { return top+1;}
    
    void push(int x)
    { arr[++top]=x; }
    
    void pop()
    { top--; }
    
    boolean exists(int x)
    { 
        for(int i=0; i<=top; i++)
        if(arr[i]==x) return true;
        return false;
    }
    
    int getFirst()
    { return arr[0]; }
    
    int getLast()
    { return arr[top]; }
    
    void print(String[] words)
    {   
        for(int i=0; i<=top; i++)
        {
            System.out.print(words[arr[i]]);
            if(i!=top) System.out.print(" -> ");
        }
        System.out.println();
    }
}