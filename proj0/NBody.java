

public class NBody {

	public static double readRadius(String fileName){
		In in=new In(fileName);
		int num=in.readInt();
		double radius=in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName){
		In in=new In(fileName);
		int num=in.readInt();
		double radius=in.readDouble();
		Planet[] plt;
		plt=new Planet[5];
		
		for(int i=0;i<5;i+=1){
			
			double xPos=in.readDouble();
			double yPos=in.readDouble();
			double xVel=in.readDouble(); 
			double yVel=in.readDouble();
			double ms=in.readDouble();
			String img=in.readString();
			Planet p=new Planet(xPos,yPos,xVel,yVel,ms,img);
			plt[i]=p;
		}	
		return plt;
	}

	public static void main(String args[]){

		/*getting input*/
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String fileName=args[2]; 
		Planet[] plt= readPlanets(fileName);
		double radius=readRadius(fileName);

		/* Drawing the background*/
		String imageToDraw="images/starfield.jpg";
		StdDraw.setScale(-radius,radius);
		StdDraw.picture(0,0,imageToDraw);

		/*drawing all of the planets*/
		int n;
		for(n=0;n<5;n+=1){
			plt[n].draw();
		}

		/*Enable double buffering to prevent flickering*/
		StdDraw.enableDoubleBuffering();

		/*animation*/
		double temps=0;
		for(temps=0;temps<T;temps+=dt){
			double[] xForces=new double[5];
			double[] yForces=new double[5];
			for(int i=0;i<5;i+=1){
				double Fx=plt[i].calcNetForceExertedByX(plt);
				xForces[i]=Fx;
				double Fy=plt[i].calcNetForceExertedByY(plt);
				yForces[i]=Fy;
			}
				for(int j=0;j<5;j+=1){
					plt[j].update(dt,xForces[j],yForces[j]);
				}
				
				StdDraw.picture(0,0,imageToDraw);

				for (Planet t:plt){
					t.draw();
				}
				StdDraw.show();
				StdDraw.pause(10);
				
			
		}

		StdOut.printf("%d\n", plt.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < plt.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  plt[i].xxPos, plt[i].yyPos, plt[i].xxVel,
                  plt[i].yyVel, plt[i].mass, plt[i].imgFileName);   
}



	}
}