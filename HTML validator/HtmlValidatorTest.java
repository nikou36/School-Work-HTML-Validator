//Nick Kouthong
//Assignment 2
//CSE 143 AC :Tanvi Dighde
//7/1/16
//This program tests the various functions of the HtmlValidator object

import java.util.*;

public class HtmlValidatorTest {
	public static void main(String[] args) {
		// <b>Hi</b><br/>
		// A Queue of tags you may modify and pass to your HtmlValidator object
		Queue<HtmlTag> tags = new LinkedList<HtmlTag>();
		tags.add(new HtmlTag("b", true));      // <b>
		tags.add(new HtmlTag("b", false));     // </b>
		tags.add(new HtmlTag("br"));           // <br/>
		
		// Your code here	
      HtmlValidator test = new HtmlValidator(tags);
      System.out.println("Before modifications: " + test.getTags()); 
      HtmlTag tagC = new HtmlTag("c"); 
      test.addTag(tagC);
      System.out.println("After adding <C>:" + test.getTags());
      test.removeAll("b");
      System.out.println("After removing <b>:" + test.getTags()); 
      System.out.println("Validation:");
      test.validate();
	}
}
