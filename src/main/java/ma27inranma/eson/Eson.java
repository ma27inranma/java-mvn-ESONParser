package ma27inranma.eson;

import java.util.HashMap;

public class Eson {
  public static EsonValue parse(EsonText text, EsonType type){
    EsonType esonType = type;

    if(esonType == null){
      if(text.text.charAt(0) == '{'){
        esonType = EsonType.Compound;
      }else if(text.text.charAt(0) == '['){
        esonType = EsonType.Array;
      }else{
        esonType = EsonType.String;
      }
    }

    if(esonType == EsonType.Compound){
      HashMap<String, EsonValue> map = new HashMap<>();

      int commaPos = text.text.indexOf(',');
      if(commaPos == -1){
        commaPos = text.text.indexOf("}");
        if(commaPos == -1){
          commaPos = text.text.length();
        }
      }

      int eqlPos = text.text.indexOf('=');
      if(eqlPos == -1){
        throw new IllegalArgumentException("Invalid Eson Compound. Expected '=' in text, but not found. text: " + text.text);
      }

      EsonRange keyRange = getRealRange(text.text.substring(0, eqlPos));
      EsonRange valueRange = getRealRange(text.text.substring(eqlPos + 1, commaPos));

      String key = text.text.substring(keyRange.start, keyRange.end);
      text.text = text.text.substring(keyRange.end + 1);
      String value = text.text.substring(valueRange.start, valueRange.end);
      text.text = text.text.substring(valueRange.end + 1);

      map.put(key, parse(new EsonText(value), esonType));

      return new EsonValue(EsonType.Compound, map);
    }

    return null;
  }

  public static EsonRange getRealRange(String text){
    int start = 0;
    int end = text.length();

    if(text.startsWith("\"")){
      start = 1;
    }

    if(text.endsWith("\"")){
      end = text.length() - 1;
    }

    return new EsonRange(start, end);
  }
}
