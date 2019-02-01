public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double gconst = 6.67e-11;

    /** Body object constructor */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Makes a copy of the inputed body object */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Finds the distance (r) between an instance and another object */
    public double calcDistance(Body obj) {
        return Math.sqrt(Math.pow(this.xxPos - obj.xxPos, 2) + Math.pow(this.yyPos - obj.yyPos, 2));
    }

    /** Calculates the force exerted on an body object by another body using the equation
        F = (G * m1 * m2) / (r^2) */
    public double calcForceExertedBy(Body obj) {
        return (this.mass * obj.mass * gconst) / Math.pow(this.calcDistance(obj), 2);
    }

    /** calcForceExertedByX and calcForceExertedByY calculates the forces exerted on a body
        in terms of the x and y axis */
    public double calcForceExertedByX(Body obj) {
        double xforce = (this.calcForceExertedBy(obj) * (obj.xxPos - this.xxPos)) / this.calcDistance(obj);
        return xforce;
    }

    /** @source Got help from office hours */
    public double calcForceExertedByY(Body obj) {
        double yforce = (this.calcForceExertedBy(obj) * (obj.yyPos - this.yyPos)) / this.calcDistance(obj);
        return yforce;
    }

    /** Calculates the net forces in the x and y direction of a body given several bodies
        acting on it */
    public double calcNetForceExertedByX(Body[] objs) {
        double netforce = 0;
        for (int i = 0; i < objs.length; i += 1) {
            if (!objs[i].equals(this)) {
                netforce += this.calcForceExertedByX(objs[i]);
            }
        }
        return netforce;
    }

    public double calcNetForceExertedByY(Body[] objs) {
        double netforce = 0;
        for (int i = 0; i < objs.length; i += 1) {
            if (!objs[i].equals(this)) {
                netforce += this.calcForceExertedByY(objs[i]);
            }
        }
        return netforce;
    }

    /** Updates the position and velocities of a body */
    public void update(double time, double x_force, double y_force) {
        double x_accel = x_force / this.mass;
        double y_accel = y_force / this.mass;
        this.xxVel = this.xxVel + (x_accel * time);
        this.yyVel = this.yyVel + (y_accel * time);
        this.xxPos = this.xxPos + (this.xxVel * time);
        this.yyPos = this.yyPos + (this.yyVel * time);
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
