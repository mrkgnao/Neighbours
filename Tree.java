class Tree
{
    int max=0; //mode=false -> max; mode=true -> backtrack
    String[] words;
    boolean[][] pathTerminals; //to prevent the printing of reverse paths.
    
    void generateTree (boolean[][] matrix, String[] words)
    {
        this.words = words;
        pathTerminals = new boolean[words.length][words.length];
        
        // Calculate maximum length. Therefore mode = false;
        for(int i=0; i<matrix.length; i++)
        {
            generateTree (matrix, i, new Stack(matrix.length), false);
        }
        
        System.out.println("\nThe max path length is "+max+".\n");
        System.out.println("Paths:");
        
        //Now backtrack. Therefore mode = true;
        for(int i=0; i<matrix.length; i++)
        {
            generateTree (matrix, i, new Stack(matrix.length), true);
        }
    }
    
    // converts an incidence matrix to a tree.
    void generateTree (boolean[][] matrix, int row, Stack nodes, boolean mode)
    {
        nodes.push(row); // The current row is a node. So add it.
        boolean isLeaf = true;
        
        for(int i=0; i<matrix.length; i++)
        {
            if(matrix[row][i] && !nodes.exists(i)) // If row an i are related and i is not an ancestor.
            { isLeaf = false; generateTree (matrix, i, nodes, mode); }
        }
        
        if(isLeaf) //This node is the last node.
        {
            if(mode)
            {
                if(max == nodes.length() && !pathTerminals[nodes.getLast()][nodes.getFirst()])
                {
                    pathTerminals[nodes.getFirst()][nodes.getLast()] = true;
                    nodes.print(words);
                }
            }
            else
            {
                if(nodes.length() > max)
                max=nodes.length();
            }
        }
        
        nodes.pop(); //As we move up, delete nodes.
    }
}  