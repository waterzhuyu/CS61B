import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440;

    private static double getFrequency(int idx) {
        return CONCERT_A * Math.pow(2, (idx - 24) / 12.);
    }

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] gsa = new GuitarString[keyboard.length()];
        for (int i = 0; i < gsa.length; i++) {
            gsa[i] = new synthesizer.GuitarString(getFrequency(i));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idx = keyboard.indexOf(key);
                if (idx > 0) {
                    gsa[idx].pluck();
                }
            }

            double sample = 0;
            for (GuitarString guitarString : gsa) {
                sample += guitarString.sample();
            }

            StdAudio.play(sample);

            for (GuitarString guitarString: gsa) {
                guitarString.tic();
            }
        }
    }
}
