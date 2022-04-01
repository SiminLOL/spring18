import java.math.*;

public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G=6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;

	}

	public Planet(Planet p){
		p.xxPos=xxPos;
		p.yyPos=yyPos;
		p.xxVel=xxVel;
		p.yyVel=yyVel;
		p.mass=mass;
		p.imgFileName=imgFileName;
	}

	public double calcDistance(Planet one){
		return Math.sqrt((one.xxPos-xxPos)*(one.xxPos-xxPos)+(one.yyPos-yyPos)*(one.yyPos-yyPos));
	}
	
	public double calcForceExertedBy(Planet p1){
		double r=calcDistance(p1);
		return G*mass*p1.mass/r/r ;
	}

	public double calcForceExertedByX(Planet other) {
        double dist = calcDistance(other);
        double force = calcForceExertedBy(other);
        return (other.xxPos - this.xxPos) / dist * force;
    }

    public double calcForceExertedByY(Planet other) {
        double dist = calcDistance(other);
        double force = calcForceExertedBy(other);
        return (other.yyPos - this.yyPos) / dist * force;
    }

	public double calcNetForceExertedByX(Planet[] ps){
		double netX=0;
		for(Planet i:ps){
			if(!equals(i)){
				netX=netX+calcForceExertedBy(i)*(i.xxPos-xxPos)/calcDistance(i);
			}else continue;
		}
		return netX;
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double netY=0;
		for(Planet i:ps){
			if(!equals(i)){
 				netY=netY+calcForceExertedBy(i)*(i.yyPos-yyPos)/calcDistance(i);
			}else continue;
		}
		return netY;
	}

	public void update(double dt, double Fx, double Fy){
		double ax=Fx/mass;
		double ay=Fy/mass;
		xxVel=xxVel+ax*dt;
		yyVel+=ay*dt;
		xxPos+=dt*xxVel;
		yyPos+=dt*yyVel;
	}

	/*Drawing the Planet's image at the Planet's position*/
	public void draw(){
		String imgName="images/" + imgFileName;
		StdDraw.picture(xxPos,yyPos,imgName);
	}


}