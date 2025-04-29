public class ColorHSB {
    private final int hf; // hue (0-359)
    private final int sf; // saturation (0-100)
    private final int bf; // brightness (0-100)
    
    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) 
    {
        if (h < 0 || h > 359) throw new IllegalArgumentException("Hue must be between 0 and 359");
        if (s < 0 || s > 100) throw new IllegalArgumentException("Saturation must be between 0 and 100");
        if (b < 0 || b > 100) throw new IllegalArgumentException("Brightness must be between 0 and 100");
        this.hf = h;
        this.sf = s;
        this.bf = b;
    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString()
    { return "(" + hf + ", " + sf + ", " + bf + ")"; }

    // Is this color a shade of gray?
    public boolean isGrayscale()
    {
        if (sf == 0 || bf == 0) return true; // black
        return false;
    }


    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that)
    {
        if (that == null) throw new IllegalArgumentException("Argument is null");
        int d = (int) (Math.min(Math.pow((hf - that.hf), 2), Math.pow((360 - Math.abs(hf - that.hf)), 2)) + Math.pow((sf - that.sf), 2) + Math.pow((bf - that.bf), 2));
        return d;
    }

    // Sample client (see below).
    public static void main(String[] args)
    {
        int h = Integer.parseInt(args[0]), s = Integer.parseInt(args[1]), b = Integer.parseInt(args[2]);
        ColorHSB c = new ColorHSB(h, s, b);
        String name = "";
        ColorHSB closest = null;
        while (StdIn.isEmpty() == false)
        {
            String tname = StdIn.readString();
            int th = StdIn.readInt(), ts = StdIn.readInt(), tb = StdIn.readInt();
            ColorHSB t = new ColorHSB(th, ts, tb);
            if (closest == null || c.distanceSquaredTo(t) < c.distanceSquaredTo(closest))
            {
                closest = t;
                name = tname;
            }
        }
        if (closest == null) throw new IllegalArgumentException("No colors found");
        System.out.println( name + " " + closest.toString());
    }
}