import javax.swing.*;;

public class Main extends JFrame{
  
public static void main(String[] args)  {
   Main m = new Main();
   Display display = new Display(); 
   
   m.add(display); 
   m.pack();
   m.setVisible(true); 
 }
}