//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.SystemColor;
//import java.util.ArrayList;
//
//import javax.swing.JFrame;
//
//	public class TestingGraphics extends JFrame{
//		private static Graph map; 
//		private JFrame frame;
//		ArrayList<Graph.Node> nodesA = map.listRatings();
//		
//		public TestingGraphics(){
////			frame = new JFrame("Testing Graphics");
//			initFrame();
//			frame.setResizable(false);
//			frame.getContentPane().setForeground(Color.LIGHT_GRAY);
//			frame.getContentPane().setBackground(SystemColor.control);
//			frame.setBounds(100, 100, 600, 900);
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.pack();
//			frame.setVisible(true);
//			
//		}
//		
//		public void start(){
//			System.out.println("Running");
//		}
//		
//		public void initFrame(){
//			frame = new JFrame(){
//				public void paint(Graphics g){
//					g.fillOval(10, 10, 100, 100);
//				}
//			};
//		}
//		
//		public void paintComponent(Graphics g){
//			int width = 10;
//			int height = 10;
//			super.paintComponents(g);
////			Graphics g = frameTesting.getGraphics();
//			g.setColor(Color.black);
//			for(int i =0; i< nodesA.size(); i++){
//				g.fillOval(nodesA.get(i).x, nodesA.get(i).y, width, height);
//			}
//		}
//	
//	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		TestingGraphics f = new TestingGraphics();
//		f.start();
////		program.start();
////		program.paintComponent(g);
//	}
//}

/* JFramePaint1.java
 * Copyright (c) 2014, HerongYang.com, All Rights Reserved.
 */
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
public class TestingGraphics {
	private static Graph map; 
//	private JFrame frame;
//	static ArrayList<Graph.Node> nodesA = map.listRatings();
   public static void main(String[] a) {
      MyJFrame f = new MyJFrame();
      f.setTitle("Drawing Graphics in Frames");
      f.setBounds(100,50,500,300);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
   }
   static class MyJFrame extends JFrame {
	   
      public void paint(Graphics g) {
			int width = 10;
			int height = 10;
			g.setColor(Color.black);
//			for(int i =0; i< nodesA.size(); i++){
//				g.fillOval(nodesA.get(i).x, nodesA.get(i).y, width, height);
//			}
      }
   }
}