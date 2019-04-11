//Nick Kouthong
//Assignment 2
//CSE 143 AC :Tanvi Dighde
//7/1/16
//This program creates an HtmlValidator object that keeps track of passed
//in Html tags and can also validate if any errors occur with the arrangement
//of the tags.
 
import java.util.*;

public class HtmlValidator {
   
   private Queue<HtmlTag> allTag;//Keeps track of all Html tags
   private int size;//Current amount of tags stored 
   
   //Constructs an HtmlValidator object that contains no tags
   public HtmlValidator() {
      this(new LinkedList<HtmlTag>());  
   }
   
   //Pre: Passed in queue of Html tags must not be null otherwise an 
   //IllegalArgumentException is thrown.
   //Post: Takes in a Queue of HtmlTags and creates an
   //HtmlValidator object with the passed in tags 
   public HtmlValidator(Queue<HtmlTag> tags) {
      if(tags == null) {
         throw new IllegalArgumentException();
      }
      this.allTag = new LinkedList<HtmlTag>();
      this.size = tags.size();
      sortTags(allTag,tags);
   }
   
   //Pre: Passed in Html Tag must not be null otherwise an 
   //IllegalArgumentException is thrown
   //Post: Stores the passed in Html tag into the HtmlValidator object.
   public void addTag(HtmlTag tag) {
      if(tag == null) {
         throw new IllegalArgumentException();
      }
      allTag.add(tag);
      size = allTag.size();
   }
   
   //Post: Returns a queue of Html tags which is
   //a copy of the currently stored Html tags in the HtmlValidator
   //object.
   public Queue<HtmlTag> getTags() {
      Queue<HtmlTag> copy = new LinkedList<HtmlTag>();
      sortTags(copy,allTag);
      return copy;
   }
   
   //Pre: Passed in String element,which represents  
   //the element in an Html tag, must not be null, otherwise an 
   //IllegalArgumentException is thrown
   //Post: Takes in a String element and removes all occurances of the 
   //element from the validator
   public void removeAll(String element) {
      if(element == null) {
         throw new IllegalArgumentException();
      }
      for(int i = 0; i < size ; i++) {
         HtmlTag temp = allTag.remove();
         if(!temp.getElement().equals(element)) {
            allTag.add(temp);
         } 
      }
      size = allTag.size();
   }
   
   //Post:Prints out the stored tags in a format that indents a opening tag
   //until its closing tag is reached. Will also print error messages if 
   //closing tags do not match their opening tags or if opening tags are
   //not closed.
   public void validate() {
      Stack<HtmlTag> nonClosing = new Stack<HtmlTag>();//stores non self-closing open tags
      int tabs = 0;
      for(int i = 0 ; i < size; i++) {          
         HtmlTag current = allTag.remove();
         if((!nonClosing.isEmpty() && !current.matches(nonClosing.peek()) 
              && !current.isOpenTag()) || (!current.isOpenTag() && nonClosing.isEmpty())) { 
               System.out.println("ERROR unexpected tag: " + current.toString());
               tabs++;
         }else {
            for(int n = 0; n < tabs; n++) {
                  System.out.print("    ");
            }
            System.out.println(current.toString());
            if(current.isOpenTag() && !current.isSelfClosing()) { 
               nonClosing.push(current);
               if(!allTag.peek().matches(current)) {
                  tabs++;
               }
            }else if((!current.isOpenTag() && !allTag.peek().isOpenTag()) ||
               (!current.isOpenTag() && !nonClosing.isEmpty())) {
               nonClosing.pop();
            }
         } 
         if(!allTag.isEmpty() && !allTag.peek().isOpenTag() 
               && !allTag.peek().matches(current)) {
            tabs--;
         } 
         allTag.add(current);   
      }
      while(!nonClosing.isEmpty()) {
         System.out.println("ERROR unclosed tag: " + nonClosing.pop().toString());
      }
   }
   
   //Pre: Queue empty and Queue full contain Html Tags and must not be null. 
   //Post: Takes in a Queue empty and a Queue full and copies the entries
   //of full into empty.
   private void sortTags(Queue<HtmlTag> empty, Queue<HtmlTag> full) {
      for(int i = 0; i < size; i++) {
         HtmlTag temp = full.remove();
         full.add(temp);
         empty.add(temp);
      }   
   } 
}