package ma27inranma.eson;

import java.util.Map;

public class EsonValue {
  private final EsonType type;
  private final Object value;

  public EsonValue(EsonType type, Object value){
    this.type = type;
    this.value = value;
  }

  public EsonType getType(){
    return type;
  }

  public Object getValue(){
    return value;
  }

  public EsonValue get(String key){
    if(!isCompound()){
      throw new IllegalArgumentException("Value is not a compound");
    }

    return ((Map<String, EsonValue>)value).get(key);
  }


  public boolean isCompound(){
    return this.type == EsonType.Compound;
  }

  public boolean isArray(){
    return this.type == EsonType.Array;
  }

  public boolean isNull(){
    return this.type == EsonType.Null;
  }

  public boolean isBoolean(){
    return this.type == EsonType.Boolean;
  }

  public boolean isNumber(){
    return this.type == EsonType.Number;
  }

  public boolean isString(){
    return this.type == EsonType.String;
  }

  
  public String toString(){
    if(value instanceof String){
      return "\"" + value + "\"";
    }

    return value.toString();
  }
}
