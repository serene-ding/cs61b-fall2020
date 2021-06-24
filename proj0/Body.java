public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;

    }
    public Body(Body b){
    	xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
    	double r_square;
    	r_square = (xxPos-b.xxPos)*(xxPos-b.xxPos)+(yyPos-b.yyPos)*(yyPos-b.yyPos);
    	return Math.sqrt(r_square);


    }
    public double calcForceExertedBy(Body b){

    	if (this.equals(b)){
    		return 0;
    	}

    	else {double G = 6.67e-11;
    	double Fa;
    	double r;
    	r = calcDistance(b);
    	Fa = G * mass * b.mass/(r * r);
  		return Fa;
  		}
    }

    public double calcForceExertedByX(Body b){
    	double dx;
    	dx = b.xxPos - xxPos;
    	return calcForceExertedBy(b)*dx/calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
    	double dy;
    	dy = b.yyPos - yyPos;
    	return calcForceExertedBy(b)*dy/calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodyies){
    	double NetForceX = 0;
    	for (Body body : bodyies){
    		if (!this.equals(body)){
    			NetForceX += calcForceExertedByX(body);
    		}
    	}
    	return NetForceX;
    }
    public double calcNetForceExertedByY(Body[] bodyies){
    	double NetForceY = 0;
    	for (Body body : bodyies){
    		if (!this.equals(body)){
    			NetForceY += calcForceExertedByY(body);
    		}
		}
		return NetForceY;
	}

	public void update(double dt, double fX, double fY){
		double ax,ay;
		ax = fX / mass;
		ay = fY / mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}
}
