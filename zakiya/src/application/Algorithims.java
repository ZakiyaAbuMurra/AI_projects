package application;


import java.util.*;
import java.math.* ;
public class Algorithims {
    final Integer inf = Integer.MAX_VALUE ;
    private HashMap<Edges,Edges> parents  ;
    HashMap<Edges,Boolean>visited ;
    HashMap<String,Boolean> dfsvisited ;
    private ArrayList<Node>Graph ;
    HashMap<String,Integer>mp ;
    HashSet<Node> proccesed ;
    ArrayList<Edges>Path;
    Edges des_dfs ;
    private String Type,Source,Destination ;
    private char huristic_type,Astargredyuniform ;
    HashMap<String,HashMap<String,Double>> destances ;

    public Algorithims(ArrayList<Node> graph, HashMap<String, Integer> mp,String Type
            ,String Source,String Destination,char huristic_type,char Astargredyuniform,
            HashMap<String,HashMap<String,Double>> destances) {
        Graph = graph;
        this.mp = mp;
        this.Type = Type;
        this.Source=Source ;
        this.Destination =Destination ;
        this.huristic_type = huristic_type ;
        this.Astargredyuniform = Astargredyuniform ;
        this.destances = destances ;
    }
    
    public Algorithims() {}
    
	public Algorithims(ArrayList<Node> graph, HashMap<String, Integer> mp, String source,
                       String destination, char huristic_type, char astargredyuniform)
    {
        Graph = graph;
        this.mp = mp;
        Source = source;
        Destination = destination;
        this.huristic_type = huristic_type;
        Astargredyuniform = astargredyuniform;
    }

    public ArrayList<Edges> Runner(){
        this.Path =new ArrayList<>() ;

        if(Type.equals("Astar")){
            Astar_gredy_uniform();
        }
        else if (Type.equals("BFS")){
            BFS();
        }
        else if (Type.equals("DFS")){
            DFS_RUNNER();
        }
        return Path ;
    }


    private void Astar_gredy_uniform(){
        //implement Astar
        parents = new HashMap<>() ;

        ArrayList<Edges> pq = new ArrayList<>() ;
        Edges src = new Edges(0, Graph.get(mp.get(Source))) ;
        Edges des =new Edges() ;
        pq.add(src) ;
        parents.put(src,null);
        src.setDestance_and_huristic(0);
        src.setDestance1(0);
        proccesed = new HashSet<>() ;
        while (!pq.isEmpty()){

                Collections.sort(pq);
                //System.out.println(pq);
            //need to be changed
            Edges Current_edge = pq.get(0) ;
            pq.remove(0) ;

            if(proccesed.contains(Current_edge.getNode()))
                continue;


            proccesed.add(Current_edge.getNode()) ;
            if(Current_edge.getNode().getName().equals(Destination)){

                    des =Current_edge ;
                    break;
            }
            for(Edges i : Current_edge.getNode().getAdj()){
                double calcDestance =0 ;
                if(Astargredyuniform == 'a'){
                     calcDestance = calc_destance_and_huristic(i.getNode(),Graph.get(mp.get(Destination)));
                    if(huristic_type =='s'){ //staright as an aerial 
                        System.out.println(calcDestance);
                        calcDestance +=i.getDestance()+Current_edge.getDestance1() ;

                    }
                    if (huristic_type == 'w'){ // w mean walking distance 
                        System.out.println(calcDestance);
                        calcDestance = (calcDestance + destances.get(Current_edge.getNode().getName()).get(Destination)) /2.0
                                +( i.getDestance()+Current_edge.getDestance1());

                    }
                }
                if(Astargredyuniform =='u') {
                    calcDestance = i.getDestance() + Current_edge.getDestance1() ;
                }
                if(Astargredyuniform =='g'){
                    calcDestance = calc_destance_and_huristic(i.getNode(),Graph.get(mp.get(Destination)));
                }
                    if(i.getDestance_and_huristic() == Double.MAX_VALUE) {

                        i.setDestance1(Current_edge.getDestance1()+i.getDestance());
                        i.setDestance_and_huristic(calcDestance);

                        parents.put(i, Current_edge);

                        pq.add(i) ;
                    }
                    if(calcDestance < i.getDestance_and_huristic())
                        {

                            i.setDestance_and_huristic(calcDestance);
                            parents.put(i,Current_edge) ;
                            pq.add(i);
                        }
            }
        }
        get_path(des);
    }
    private void BFS(){
        //implement BFS
        visited = new HashMap<>();
        LinkedList <Edges>q = new LinkedList<>();
        Edges src= new Edges(Graph.get(mp.get(Source)));
        Edges des = new Edges() ;
        parents = new HashMap<>() ;
        q.addLast(src);
        visited.put(src,true) ;
        parents.put(src,null) ;
        while (!q.isEmpty()){
            int f=0 ;
            Edges Current_edge = q.poll() ;
            for (Edges i : Current_edge.getNode().getAdj()){
                   if(!visited.containsKey(i) || !visited.get(i)){
                       visited.put(i,true) ;
                       parents.put(i,Current_edge) ;
                       if(i.getNode().getName().equals(Destination))
                       {
                            f=1 ;
                            des =  i ;
                            break;
                       }
                       q.addLast(i);
                   }
            }
            if(f==1)
                break;
        }
        get_path(des);
    }


    private ArrayList<Edges> DFS_RUNNER(){
        Edges src = new Edges(Graph.get(mp.get(Source))) ;
        dfsvisited = new HashMap<>() ;
        parents = new HashMap<>() ;
        des_dfs = new Edges() ;
        DFS(src);
       get_path(des_dfs);
       return Path;
    }

    private void DFS(Edges src){
        //implement DFS

        if(src.getNode().getName().equals(Destination)){
            des_dfs = src;
            return;
        }
        dfsvisited.put(src.getNode().getName(),true);
         for (Edges i : src.getNode().getAdj()){
             if(!dfsvisited.containsKey(i.getNode().getName())){
                 parents.put(i,src) ;
                  DFS(i);
             }
         }

    }

    
    
    private double calc_destance_and_huristic(Node node1,Node node2){
        double destance = Math.sqrt(Math.pow(Math.abs(node1.getxReal() - node2.getxReal()),2)
                +Math.pow(Math.abs(node1.getyReal() - node2.getyReal()),2)) * 100.0 ;
        return destance ;
    }


    private void get_path(Edges edge){
        if(edge==null || edge.getNode()==null){
            return;
        }
        get_path(parents.get(edge));
        Path.add(edge) ;
    }
}
