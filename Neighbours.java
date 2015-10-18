import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

// Two words are neighbours if they differ by one letter.
// Example: {brow, crow} , {brow, row} , {blow, below} are all sets of neighbouring words.
// This program calculates the longest chain of neighbours in a list of words.

// Example sets:
// below blow bow bowl brow crow rot row
// be bed bet bud but dig do dog dug get go god got
// fat fate fight fit fright gait gate light mite quit quite right sat sigh sight sit site suite writ write
// age bat batch bath bathe bather batter cache cat catch catcher fat fate father fresh garb garbage heathen later lather mash mate matt matter rash rat thrash thresh trash

class Neighbours
{
    String[] words;
    
    boolean isNeighbour (String x, String y)
    {
        if(x.equals(y))
        return false; //Equal words are not neighbours. They are the same word !!
        
        if(x.length() > y.length())
        { String temp=x; x=y; y=temp; } //Sort them to avoid writing another function.
        
        if (y.length()-x.length()==1) //Check whether they differ due to the addition of a letter.
        return isNeighbourDiff(x,y);
        
        else if(y.length()==x.length()) //Check whether they differ due to the modification of a letter.
        return isNeighbourMod(x,y);
        
        return false;
    }
    
    boolean isNeighbourDiff (String x, String y)
    {
        int count=0; //Count the no. of added characters.
        for(int i=0; i<x.length(); i++)
        {
            if(x.charAt(i) != y.charAt(i+count)) //Check the characters one by one.
            { count++; i--; } //Increas count and reprocess the character.
            
            if(count == 2) return false;
        }
        
        return true;
    }
    
    boolean isNeighbourMod (String x, String y)
    {
        int count=0; //Count the no. of modified characters.
        for(int i=0; i<x.length(); i++)
        {
            if(x.charAt(i) != y.charAt(i)) //Check the characters one by one.
            count++;
            
            if(count == 2) return false;
        }
        
        return true;
    }
    
    void display(String[] words)
    {
        this.words=words;
        int l=words.length;
        boolean arr[][] = new boolean[l][l]; // The incidence matrix.
        
        for(int i=0; i<l; i++)
        for(int j=0; j<l; j++)
        arr[i][j] = isNeighbour (words[i], words[j]);
        
        Tree tree = new Tree();
        tree.generateTree (arr, words); // Pass onto the tree function.
    }
    
    void main() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter words, separated by spaces:");
        String str = br.readLine();
        
        // Create the array.
        Scanner s = new Scanner(str);
        int n=0;
        
        while(s.hasNext())
        { n++; s.next(); }
        
        String[] arr = new String[n];
        s = new Scanner(str);
        
        for(int i=0; i<n; i++)
        arr[i] = s.next();
        
        display (arr);
    }
}