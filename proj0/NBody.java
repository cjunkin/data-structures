public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        in.readDouble();
        return in.readDouble();
    }

    /** Creates a list of bodies given the data in some text */
    public static Body[] readBodies(String file) {
        In in = new In(file);
        int max = in.readInt();
        in.readDouble();
        int i = 0;
        Body[] body_list = new Body[max];
        while (i < max) {
            body_list[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
            in.readDouble(), in.readString());
            i += 1;
        }
        return body_list;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        Body[] body_list = readBodies(filename);

        for(int i = body_list.length - 1; i >= 0; i -= 1) {
            StdDraw.picture(body_list[i].xxPos, body_list[i].yyPos, "images/" + body_list[i].imgFileName);
        }

        /** Animates the bodies and their physics
            @source Office Hours 12 - 1 pm helped correct direction of force vector */
        StdDraw.enableDoubleBuffering();
        double time = 0;
        double[] xForces = new double[body_list.length];
        double[] yForces = new double[body_list.length];
        while (time <= T) {
            for (int i = body_list.length - 1; i >= 0; i -= 1) {
                xForces[i] = body_list[i].calcNetForceExertedByX(body_list);
                yForces[i] = body_list[i].calcNetForceExertedByY(body_list);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = body_list.length - 1; i >= 0; i -= 1) {
                body_list[i].update(dt, xForces[i], yForces[i]);
                StdDraw.picture(body_list[i].xxPos, body_list[i].yyPos, "images/" + body_list[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", body_list.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < body_list.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            body_list[i].xxPos, body_list[i].yyPos, body_list[i].xxVel,
            body_list[i].yyVel, body_list[i].mass, body_list[i].imgFileName);
        }
    }
}
