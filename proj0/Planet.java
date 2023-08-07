public class Planet {
    public double xxPos; /* Its current x position. */
    public double yyPos;
    public double xxVel; /* Its current velocity in the x direction. */
    public double yyVel;
    public double mass;
    public String imgFileName; /* The name of the file that correspond to the image that depicts the planet.*/

    private static final double GRAVITATIONAL = 6.67e-11;

    /*Constructor version 1. */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* Constructor version 2. */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /* Calculate the distance between two Planet.
       This method will take in a single Planet and should return a double equal to the distance between the supplied
       planet and the planet that is doing the calculation.
     */
    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    /** Take in a planet, and returns a double describing the force exerted on the planet by the given planet.
     * @param p     Given Planet.
     * @return      Double describing the force.
     */
    public double calcForceExertedBy(Planet p) {
        return Planet.GRAVITATIONAL * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * ((this.xxPos - p.xxPos) > 0 ? this.xxPos - p.xxPos : p.xxPos - this.xxPos)
                / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * ((this.yyPos - p.yyPos) > 0 ? this.yyPos - p.yyPos : p.yyPos - this.yyPos)
                / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double forceExertedByX = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            } else {
                forceExertedByX += this.calcForceExertedByX(p);
            }
        }
        return forceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double forceExertedByY = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            } else {
                forceExertedByY += this.calcForceExertedByY(p);
            }
        }
        return forceExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
