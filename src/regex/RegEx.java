package regex;

/**
 * Created by Yitzi on 3/19/2016.
 */
public class RegEx {

    String regex = "";

    public RegEx() {

    }

    public RegEx(RegEx regex) {
        this.regex = regex.toString();
    }

    public RegEx(String regex) {
        this.regex = regex;
    }

    public RegEx then(RegEx s) {
        this.regex = this.regex + s;

        return this;
    }

    public static RegEx digit() {
        return new RegEx("\\d");
    }

    public static RegEx nonDigit() {
        return new RegEx("\\D");
    }

    public static RegEx alphaNumeric() {
        return new RegEx("\\w");
    }

    public static RegEx nonAlphaNumeric() {
        return new RegEx("\\W");
    }

    public static RegEx whitespace() {
        return new RegEx("\\s");
    }

    public static RegEx nonWhitespace() {
        return new RegEx("\\S)");
    }

    public static RegEx literal(char c) {
        String s;

        if (needsToBeEscaped(c))
            s = escape(c);
        else
            s = c + "";

        return new RegEx(s);
    }

    public static RegEx literal(char[] chars) {

        StringBuilder s = new StringBuilder(chars.length);

        for (char c : chars){
            if (needsToBeEscaped(c))
                s.append(escape(c));
            else
                s.append(c);
        }

        return new RegEx(s.toString());
    }

    public static RegEx literal(String s) {
        return new RegEx(literal(s.toCharArray()));
    }

    public static RegEx oneOrMore(RegEx regex) {
        return new RegEx(regex.toString() + "+");
    }

    public static RegEx zeroOrMore(RegEx regex) {
        return new RegEx(regex.toString() + "*");
    }

    public static RegEx zeroOrOne(RegEx regex) {
        return new RegEx(regex.toString() + "?");
    }

    public static RegEx in(char[] chars) {
        StringBuilder s = new StringBuilder(chars.length + 2);
        s.append('[');
        for (char c : chars) {
            if(needsToBeEscaped(c))
                s.append(escape(c));
            else
                s.append(c);
        }
        s.append(']');

        return new RegEx(s.toString());
    }

    public static RegEx in(String s) {
        return in(s.toCharArray());
    }

    public static RegEx in(RegEx regex) {
        String s = regex.toString();

        if (s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']')
            s = s.substring(1, s.length() - 1);

        return in(s);
    }

    public static RegEx notIn(char[] chars) {
        String s = in(chars).toString();
        s = s.substring(0,1) + "^" + s.substring(1);
        return new RegEx(s);
    }

    public static RegEx notIn(String s) {
        return notIn(s.toCharArray());
    }

    public static RegEx notIn(RegEx regex) {

        String s = regex.toString();

        if (s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']')
            s = s.substring(1, s.length() - 1);

        return notIn(s);
    }

    public static RegEx range(char start, char end) {
        int startValue = start;
        int endValue = end;

        if (startValue > endValue) {
            throw new IllegalArgumentException("The end char must be a higher ASCII value than the start char\n" +
                    "The current values are: \n" +
                    start + ": " + startValue + "\n" +
                    end + ": " + endValue);
        }
        else {
            StringBuilder s = new StringBuilder(endValue - startValue + 2);

            s.append("[");
            s.append(start);
            s.append("-");
            s.append(end);
            s.append("]");

            return new RegEx(s.toString());
        }

    }

    public static RegEx exactlyXNumberOfTimes(int times, RegEx regex) {
        return new RegEx(regex.regex = regex.regex + "{" + times + "}");
    }

    public static RegEx zeroToXNumberOfTimes(int times, RegEx regex) {
        return new RegEx(regex.regex = regex.regex + "{," + times + "}");
    }

    public static RegEx atLeastXNumberOfTimes(int times, RegEx regex) {
        return new RegEx(regex.regex = regex.regex + "{" + times + ",}");
    }

    public static RegEx rangeOfTimes(int low, int high, RegEx regex) {
        if (high < low) {
            throw new IllegalArgumentException("The high end of range must be greater than the low end of the range\n" +
                    "The current values are:\n" +
                    "Low: " + low + "\n" +
                    "High: " + high);
        }
        else {
            return new RegEx(regex.regex = regex.regex + "{" + low + "," + high + "}");
        }
    }

    public RegEx or(RegEx regex) {
        return new RegEx(this.regex + "|" + regex.toString());
    }

    public static RegEx group(RegEx regex) {
        return new RegEx("(" + regex.toString() + ")");
    }

    @Override
    public String toString() {
        return this.regex;
    }

    private static Boolean needsToBeEscaped(char c) {

        char[] specialChars = {'\'', '\\', '\"', '[', ']', '(', ')', '{', '}', '^'};

        if (String.valueOf(specialChars).indexOf(c) == -1)
            return false;
        else
            return true;
    }

    private static String escape(char c) {
        return "\\\\" + c;
    }

}
