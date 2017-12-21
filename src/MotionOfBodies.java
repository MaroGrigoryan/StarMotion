import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing. *;

public class MotionOfBodies extends JFrame implements ActionListener{


		private JLabel body = new JLabel("Number of Bodies");
		private JTextField bodyfield = new JTextField(4);
		private JLabel timelabel = new JLabel("Integration time step");
		private JTextField dt= new JTextField(4);
		private JLabel mass = new JLabel("Masses");
		private JTextField mass1 = new JTextField(4);
		private JTextField mass2 = new JTextField(4);
		private JTextField mass3 = new JTextField(4);
		private JTextField mass4 = new JTextField(4);
		private JLabel xCoord = new JLabel("Initial X coordinates");
		private JTextField x1= new JTextField(4);
		private JTextField x2= new JTextField(4);
		private JTextField x3= new JTextField(4);
		private JTextField x4= new JTextField(4);
		private JLabel yCoord = new JLabel("Initial Y coordinates");
		private JTextField y1= new JTextField(4);
		private JTextField y2= new JTextField(4);
		private JTextField y3= new JTextField(4);
		private JTextField y4= new JTextField(4);
		private JLabel velocity_x = new JLabel("Init_velocity-Xcomponent");
		private JTextField xvel1= new JTextField(4);
		private JTextField xvel2= new JTextField(4);
		private JTextField xvel3= new JTextField(4);
		private JTextField xvel4= new JTextField(4);
		private JLabel velocity_y = new JLabel("Init_velocity-Ycomponent");
		private JTextField yvel1= new JTextField(4);
		private JTextField yvel2= new JTextField(4);
		private JTextField yvel3= new JTextField(4);
		private JTextField yvel4= new JTextField(4);
		private JButton visual = new JButton("VISUALIZE");
		private ViewMotion view = new ViewMotion(0.1);
		
		private double G = 6.673e-11;
		
		public double distance(double x1, double x2, double y1, double y2){
			double exponent = Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2);
			return exponent * Math.sqrt(exponent);
			
		}
		
		public double Force_x(int ID,double[] mass,double[] x,double[] y){
			double result=0;
			for(int j=0; j<4; j++)
				if(j!= ID){
					result += G*mass[ID]*mass[j]*(x[j]-x[ID])/(distance(x[ID],x[j],y[ID],y[j]));
				}
				
				
				return result;
		} 
		
		public double Force_y(int ID,double[] mass,double[] x,double[] y){
			double result=0;
			for(int j=0; j<4; j++)
				if(j!=ID){
					result += G*mass[ID]*mass[j]*(y[j]-y[ID])/(distance(x[ID],x[j],y[ID],y[j]));
				}
				
				
				return result;
		} 
		
		public double[] rk(int ID,double[] mass, double[] x, double[] y, double[] xvel, double[] yvel, double dt){
			
			double[] result = new double[4];
			double x1_ = xvel[ID];
			double u1_ = Force_x(ID,mass,x,y)/mass[ID];
			double y1_ = yvel[0];
			double v1_ = Force_y(ID,mass,x,y)/mass[ID];
			
			double x2_ = xvel[ID] + x1_*(dt/2);
			double u2_ = Force_x(ID,mass,x,y)/mass[ID] + u1_*(dt/2);
			double y2_ = yvel[ID] + y1_*(dt/2);
			double v2_ = Force_y(ID,mass,x,y)/mass[ID] + v1_*(dt/2);
			
			double x3_ = xvel[ID] + x2_*(dt/2);
			double u3_ = Force_x(ID,mass,x,y)/mass[ID] + u2_*(dt/2);
			double y3_ = yvel[ID] + y2_*(dt/2);
			double v3_ = Force_y(ID,mass,x,y)/mass[ID] + v2_*(dt/2);
			double x4_ = xvel[ID] + x3_*(dt/2);
			double u4_ = Force_x(ID,mass,x,y)/mass[ID] + u3_*(dt/2);
			double y4_ = yvel[ID] + y3_*(dt/2);
			double v4_ = Force_y(ID,mass,x,y)/mass[ID] + v3_*(dt/2);;

			double x_e = x[ID] + (dt/6)*(x1_ + 2*x2_ + 2*x3_ + x4_);
			double y_e = y[ID] + (dt/6)*(y1_ + 2*y2_ + 2*y3_ + y4_);
			double xvel_e = xvel[ID] + (dt/6)*(u1_ + 2*u2_ + 2*u3_ + u4_);
			double yvel_e = yvel[ID] + (dt/6)*(v1_ + 2*v2_ + 2*v3_ + v4_);
			
			
			result[0] = x_e;
			result[1] = y_e;
			result[2] = xvel_e;
			result[3] = yvel_e;
			
			return result;
			
		}

		public MotionOfBodies() {
			super("Project 2 – Dynamics");
			setLayout(new FlowLayout());

			add(body);
			add(bodyfield);
			bodyfield.setText("4");
			add(timelabel);
			add(dt);
			add(mass);
			add(mass1);
			mass1.setText("2");
			add(mass2);
			mass2.setText("1");
			add(mass3);
			mass3.setText("1");
			add(mass4);
			mass4.setText("2");
			add(xCoord);
			add(x1);
			x1.setText("-2");
			add(x2);
			x2.setText("-1");
			add(x3);
			x3.setText("1");
			add(x4);
			x4.setText("2");
			add(yCoord);
			add(y1);
			y1.setText("0");
			add(y2);
			y2.setText("0");
			add(y3);
			y3.setText("0");
			add(y4);
			y4.setText("0");
			add(velocity_x);
			add(xvel1);
			xvel1.setText("0");
			add(xvel2);
			xvel2.setText("0");
			add(xvel3);
			xvel3.setText("0");
			add(xvel4);
			xvel4.setText("0");
			add(velocity_y);
			add(yvel1);
			yvel1.setText("-1");
			add(yvel2);
			yvel2.setText("-2");
			add(yvel3);
			yvel3.setText("2");
			add(yvel4);
			yvel4.setText("1");
			add(visual);

			visual.addActionListener(this);
			
			
			setSize(800, 200);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		public void actionPerformed(ActionEvent arg0) {
			double bodynumber = Double.parseDouble(bodyfield.getText());
			int timeint = Integer.parseInt(dt.getText());
			double m1 = Double.parseDouble(mass1.getText());
			double m2 = Double.parseDouble(mass2.getText());
			double m3 = Double.parseDouble(mass3.getText());
			double m4 = Double.parseDouble(mass4.getText());
			double[] mass_vector = { m1, m2,m3,m4};
			double x_1 = Double.parseDouble(x1.getText());
			double x_2 = Double.parseDouble(x2.getText());
			double x_3 = Double.parseDouble(x3.getText());
			double x_4 = Double.parseDouble(x4.getText());
			double[] x_vector = { x_1, x_2,x_3,x_4};
			double y_1 = Double.parseDouble(y1.getText());
			double y_2 = Double.parseDouble(y2.getText());
			double y_3 = Double.parseDouble(y3.getText());
			double y_4 = Double.parseDouble(y4.getText());
			double[] y_vector = { y_1, y_2,y_3,y_4};
			double xvel_1 = Double.parseDouble(xvel1.getText());
			double xvel_2 = Double.parseDouble(xvel2.getText());
			double xvel_3 = Double.parseDouble(xvel3.getText());
			double xvel_4 = Double.parseDouble(xvel4.getText());
			double[] xvel_vector = { xvel_1, xvel_2,xvel_3,xvel_4};
			double yvel_1 = Double.parseDouble(yvel1.getText());
			double yvel_2 = Double.parseDouble(yvel2.getText());
			double yvel_3 = Double.parseDouble(yvel3.getText());
			double yvel_4 = Double.parseDouble(yvel4.getText());
			double[] yvel_vector = { yvel_1, yvel_2,yvel_3,yvel_4};
			
			if(arg0.getSource() == visual){
				for(int i = 0; i < 4; i++){
				double newVals[] = rk(i,mass_vector, x_vector,y_vector,xvel_vector,yvel_vector,timeint);
				view.x = newVals[0];
				view._xvel = newVals[1];
				view.y = newVals[2];
				view._yvel = newVals[3];
				view.lag = timeint;
				view.exit = true;
				view.start();
				}
				
			} 
			
				
		}
			
			
			

		public static void main(String[] args) {
			new MotionOfBodies();
		}	
	}
