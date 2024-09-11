package ma27inranma.test;

import ma27inranma.eson.Eson;
import ma27inranma.eson.EsonText;
import ma27inranma.eson.EsonValue;

public class EsonTestEntry {
  public static void main(String[] args){
    EsonText text = new EsonText("{ testkey = { testvaluekey = testvaluevalue }, testkey2 = testvalue2 }");
    EsonValue value = Eson.parse(text, null);
    
    System.out.println(value);
    System.out.println(value.get("testkey").get("testvaluekey"));
  }
}
