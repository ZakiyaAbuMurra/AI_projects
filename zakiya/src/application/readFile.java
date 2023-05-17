package application ; 
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class readFile{
    private HashMap<String,HashMap<String,Double>> des = new HashMap<String,HashMap<String,Double>>() ;
    private final String filePath ;
    HashMap<String,Integer> mp ;
    HashMap<Integer,String>mp1 ;
    public readFile(String filePath) {
        this.filePath = filePath ;
    }



    public ArrayList<Node> read_file() throws FileNotFoundException {
        ArrayList<Node>Graph = new ArrayList<>() ;
         mp = new HashMap<>() ;
        InputStream is = new FileInputStream(filePath);

        // Try block to check for exceptions
        try (Scanner sc = new Scanner(
                is, StandardCharsets.UTF_8.name())) {

            // It holds true till there is single element
            // left in the object with usage of hasNext()
            // method
            String[]  sizes = sc.nextLine().split(" ") ;
            int cities = Integer.parseInt(sizes[0]) ;
            int adjsz = Integer.parseInt(sizes[1]) ;
           // System.out.println(cities +" " + adjsz);
            for(int i =0 ;i < cities;i++){

                String [] splt = sc.nextLine().split(",") ;
                mp.put(splt[0].toLowerCase(),i);
                String name = splt[0].toLowerCase() ;
                double xReal = Double.parseDouble(splt[1]),yReal = Double.parseDouble(splt[2]);
                double xPlot = Double.parseDouble(splt[3]) , yPlot = Double.parseDouble(splt[4]); 
                Graph.add(new Node(name,xReal,yReal , xPlot , yPlot)) ;
            }
//


            for(int i =0;i<adjsz;i++){


                String[] splt = sc.nextLine().split(",") ;

                String curNode = splt[0].toLowerCase(),edgeNode = splt[1].toLowerCase() ;
                double destance = Double.parseDouble(splt[2]) ;
                //System.out.println(edgeNode);
                int idx = mp.get(curNode);
                int idx1 = mp.get(edgeNode) ;
                Node Edge = Graph.get(mp.get(edgeNode)),Edge1 = Graph.get(mp.get(curNode));
                Graph.get(idx).adj.add(new Edges(destance,Edge));
                Graph.get(idx1).adj.add(new Edges(destance,Edge1));
            }
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        is = new FileInputStream("des.txt");
        try (Scanner  sc = new Scanner(is, StandardCharsets.UTF_8.name())) {
            mp1 = new HashMap<>() ;
            String s= sc.nextLine().toLowerCase() ;
            String[] cities = s.split(",") ;
            for(int i =0 ; i < cities.length;i++){
                des.put(cities[i],new HashMap<>());
                mp1.put(i+1,cities[i]);
            }
            for(int i=0 ; i<20;i++){
             String []data = sc.nextLine().split(",") ;
             data[0] = data[0].toLowerCase() ;
             for(int j=1 ; j<data.length;j++){
                 des.get(data[0]).put(mp1.get(j),Double.parseDouble(data[j])) ;
                 des.get(mp1.get(j)).put(data[0],Double.parseDouble(data[j])) ;
             }
            }
        }
//        for(Map.Entry<String,HashMap<String,Double>> entry : des.entrySet()){
//            System.out.println(entry.getKey()+ "\n" + entry.getValue());
//
//        }


        return Graph ;
    }
    public HashMap<String, Integer> getMp() {
        return mp;
    }
    public HashMap<String, HashMap<String, Double>> getDes() {
        return des;
    }
}
