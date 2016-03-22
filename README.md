# RegExBuilder

Allows Regular Expressions to be built in a human readable format

###How to Use

All methods are static and therefore must begin with RegEx.methodName unless they're being chained.

The standard format is:  
`String regex = new RegEx().firstPartOfExpression().then(RegEx.nextPartOfExpression()).toString();`

- To add exact characters in your regex use the RegEx.literal() method with a string as its parameter  
  
- The digit(), nonDigit, alphaNumeric(), nonAlphaNumeric(), whitespace(), and nonWhitespace() methods take the place of \d, \D, \w, \W, \s, and \S  
  
- To create a character group, wrap the section in the group() method.  
Ex:  `String regex = new RegEx().group(RegEx.literal("a").or(RegEx.literal("b"))).toString() ;`  
  
- The or() method can be used as expected with a RegEx as its parameter  
  
- To create a character class use the in() and notIn() methods, which can take a String or char array as a parameter.
  
- The range() method can be used to add a range in a character class. It takes 2 chars as parameters and the first char ASCII value must be lower than the second char
  
- Besides for the first part of the regular expression which is simply chained onto the constructor, each part of the regular expression is wrapped inside of then(). This allows each part of the regex to easily viewed as a sepearte entity. For readability I suggest putting each `.then()` method onto the next line so you can easily delineate the different parts of the regular expression. 
  
- The regex can be turned into a String object using the toString() method



