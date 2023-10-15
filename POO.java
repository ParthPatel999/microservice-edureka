package demo;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class POO {
 
  public static void main(String[] args) {
    
    //String input1 = "a-zA-Z0-9/–?:\b\n().,‘+ !'\"#&%*+^_’{|}~”;@[\\]$><ÀÁÂÃÄÅÆÐØÞßàãæðÇÈÉÊËÌÍÎÏÆÐØÞßàãæð";
    
    String inputRegex123 = "[a-zA-Z0-9/\\\\?:().,+ !#&%*+^_'\\{|\\}~\";@\\[\\]><$]";
    
    Pattern p = Pattern.compile(inputRegex123, Pattern.MULTILINE);
    
    String validInput = "æðÇabcßàã";
    
    //System.out.println(p.matcher(validInput).find());
    
    System.out.println(StringUtils.isNumeric("1234as"));
       

  }

}
