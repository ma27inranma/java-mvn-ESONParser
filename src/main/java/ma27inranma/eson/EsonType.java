package ma27inranma.eson;

public enum EsonType {
  Null,
  Boolean,
  Number,
  String,
  Array,
  Compound;

  public static EsonType fromString(String type){
    return switch(type){
      case "null" -> Null;
      case "boolean" -> Boolean;
      case "number" -> Number;
      case "string" -> String;
      case "array" -> Array;
      case "compound" -> Compound;
      default -> throw new IllegalArgumentException("Invalid EsonType: " + type);
    };
  }
}
