package com.example.coachapp.helper;

import java.util.LinkedHashMap;
import java.util.Map;

// see https://www.titanwolf.org/Network/q/b3c40e8f-0ca2-40c4-a756-9d4e45e2fd04/y
public class Text2Double {

    public Text2Double() {
    }

    public double parse(String text) {
        String[] units = new String[]{
                "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen",
        };

        String[] tens = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        String[] scales = new String[]{"hundred", "thousand", "million", "billion", "trillion"};

        Map<String, ScaleIncrementPair> numWord = new LinkedHashMap<>();
        numWord.put("and", new ScaleIncrementPair(1, 0));


        for (int i = 0; i < units.length; i++) {
            numWord.put(units[i], new ScaleIncrementPair(1, i));
        }

        for (int i = 1; i < tens.length; i++) {
            numWord.put(tens[i], new ScaleIncrementPair(1, i * 10));
        }

        for (int i = 0; i < scales.length; i++) {
            if (i == 0)
                numWord.put(scales[i], new ScaleIncrementPair(100, 0));
            else
                numWord.put(scales[i], new ScaleIncrementPair(Math.pow(10, (i * 3)), 0));
        }

        double current = 0;
        double result = 0;

        for(String word : text.split("[ -]"))
        {
            ScaleIncrementPair scaleIncrement = numWord.get(word);
            current = current * scaleIncrement.scale + scaleIncrement.increment;
            if (scaleIncrement.scale > 100) {
                result += current;
                current = 0;
            }
        }
        return result + current;
    }
}

class ScaleIncrementPair
{
    public double scale;
    public int increment;

    public ScaleIncrementPair(double s, int i)
    {
        scale = s;
        increment = i;
    }
}