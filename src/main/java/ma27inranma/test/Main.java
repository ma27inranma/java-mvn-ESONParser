package ma27inranma.test;

import ma27inranma.eson.Eson;
import ma27inranma.eson.EsonText;
import ma27inranma.eson.EsonValue;

public class Main {
  public static void main(String[] args){
    EsonText text = new EsonText("{testkey=testvalue}");
    EsonValue value = Eson.parse(text, null);
    
    System.out.println(value);
  }
}
