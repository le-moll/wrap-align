/*
 * MIT License
 * Copyright (c) 2017 Gymnazium Nad Aleji
 * Copyright (c) 2017 Vojtech Horky
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cz.alisma.alej.text.wrapping;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class WrapAndAlign {
    public static int width;
    public static String alignerToUse;

    public static void main(String[] args) {
        Parameters parameters = new Parameters(args);
        parameters.setParameters();
        width = parameters.width;
        Scanner input;


        // Scanner setup:
        if (parameters.hasInputPath) {
            try {
                input = new Scanner(Paths.get(parameters.inputPath));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to read file; waiting for input");
                input = new Scanner(System.in);
            }
        } else {
            input = new Scanner(System.in);
            System.out.println("Waiting for input");
        }

        ParagraphDetector pd = new ParagraphDetector(input);

        //aligner setup
        Aligner aligner;
        switch (parameters.alignerToUse) {
            case "right":
                aligner = new RightAligner();
                break;
            case "center":
                aligner = new CenterAligner();
                break;
            case "block":
                aligner = new BlockAligner();
                break;
            default:
                aligner = new LeftAligner();
                break;
        }


        //the printing and aligning itself
        while (pd.hasNextParagraph()) {
            Paragraph para = pd.nextParagraph();
            LinePrinter line = new LinePrinter(System.out, width, aligner);
            while (para.hasNextWord()) {
                String word = para.nextWord();
                line.addWord(word);
            }
            line.flush();
            System.out.println();
        }
    }
}
