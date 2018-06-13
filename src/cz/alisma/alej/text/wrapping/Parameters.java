package cz.alisma.alej.text.wrapping;

public class Parameters {
    private String[] args;
    public int width;
    public String alignerToUse;
    public String inputPath;
    //public Path inputPath;
    public boolean hasInputPath;

    public Parameters(String[] args) {
        this.args = args;
    }

    //sets parameters
    public void setParameters() {
        if (args.length >= 1) {
            switch (args[0]) {
                case "-right":
                case "--right":
                    alignerToUse = "right";
                    break;
                case "-center":
                case "--center":
                case "-centre":
                case "--centre":
                    alignerToUse = "center";
                    break;
                case "-justify":
                case "--justify":
                    alignerToUse = "block";
                    break;
                default:
                    alignerToUse = "left";
                    break;
            }
        } else {
            alignerToUse = "left";
        }

        width = 60;

        for (int i = 0; i < args.length; i++) {
            hasInputPath = false;
            if (args[i].startsWith("--width=")) {
                String[] split = args[i].split("=");
                if (split.length != 2 && split.length != 3) {
                    width = 60;
                } else {
                    width = Integer.valueOf(split[1]);
                }
            } else if (args[i].equals("-w")) {
                if (i < (args.length - 1)) {
                    width = Integer.valueOf(args[i + 1]);
                } else {
                    width = 60;
                }
            } else if (args[i].startsWith("<")) {
                inputPath = args[i].substring(1);
                //String path =  args[i].substring(1);
                //inputPath = Paths.get(path);
                hasInputPath = true;
            }
        }
    }

}


