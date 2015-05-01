import javax.swing.*;;

public class Main extends JFrame{
  
public static void main(String[] args)  {
   Main m = new Main();
   
   Display display = null;
   
   try {
     display = new Display();
   }
   catch (Exception e) {
     System.out.println("display could not be created.");
   }
   
   m.add(display); 
   m.pack();
   m.setVisible(true); 
 }
   }

