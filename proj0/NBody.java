public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int number = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[number];
        for (int i = 0; i < number; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        /* Collect all needed input. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        /* Draw the background. */
        StdDraw.setScale(-1 * radius, radius);

        StdDraw.enableDoubleBuffering(); // keep window from flickery

        StdDraw.clear();

        double time = 0;
        for (time = 0; time < T; time += dt ) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < 5; i++) {
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }

            for (int j = 0; j < planets.length; j++) {
                Planet p = planets[j];
                p.update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

//            StdOut.printf("%d\n", planets.length);
//            StdOut.printf("%.2e\n", radius);
//            for (int i = 0; i < planets.length; i++) {
//                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
//                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
//                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
//            }

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
