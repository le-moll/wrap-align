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

import java.util.List;

public class BlockAligner implements Aligner {

    @Override
    public String format(List<String> words, Integer width) {

        StringBuilder result = new StringBuilder();

        int length = 0;
        for (String word : words) {
            length += word.length();
        }

        double dif = width - length;

        double each = dif / (words.size() -1 );


        double addedSpaces = 0;

        for (int i = 0; i < words.size(); i++) {

            result.append(words.get(i));

            addedSpaces += each;
            int toBeAdded = (int)addedSpaces;

            if (i != words.size() - 1) {
                for (int j = 0; j < toBeAdded; j++) {
                    result.append(" ");
                }
            }

            addedSpaces -= toBeAdded;
        }


        
        return result.toString();
    }

}
