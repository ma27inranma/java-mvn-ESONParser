package ma27inranma.eson;

import java.util.HashMap;

public class Eson {
  public static EsonValue parse(EsonText text, EsonType type){
    EsonType esonType = type;

    System.out.println("processing \"" + text.text + "\"");

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
      text.text = text.text.substring(1).trim();

      HashMap<String, EsonValue> map = new HashMap<>();

      while(true){
        int commaPos = text.text.indexOf(",");
        if(commaPos == -1){
          commaPos = text.text.indexOf("}");
          if(commaPos == -1){
            commaPos = text.text.length();
          }
        }

        if(commaPos == 0){
          break;
        }

        EsonText keyValue = new EsonText(text.text.substring(0, commaPos).trim());
        text.substring(commaPos + 1);

        int eqlPos = keyValue.text.indexOf("=");
        if(eqlPos == -1){
          throw new IllegalArgumentException("Invalid Eson Compound. Expected '=' in text, but not found. text: " + keyValue.text);
        }

        EsonRange keyRange = getRealRange(keyValue.text.substring(0, eqlPos));
        EsonRange valueRange = getRealRange(keyValue.text.substring(eqlPos + 1));

        String key = keyValue.text.substring(keyRange.start, keyRange.end).trim();
        System.out.println("compound, key: \"" + key + "\" keyvalue: \"" + keyValue.text + "\""); 
        keyValue.substring(eqlPos + 1);
        String value = keyValue.text.substring(valueRange.start, valueRange.end).trim();

        map.put(key, parse(new EsonText(value), null));
        System.out.println("compound, parse result type: " + map.get(key).getType() + " value: " + map.get(key).getValue());
      }

      return new EsonValue(EsonType.Compound, map);
    }

    return new EsonValue(EsonType.String, text.text);
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
