package ma27inranma.eson;

public class EsonText {
  public String text;

  public boolean done;

  public EsonText(String text){
    this.text = text;
    this.done = false;
  }


  public void substring(int start){
    substring(start, this.text.length());
  }

  public void substring(int start, int end){
    if(start > this.text.length() || end > this.text.length()){
      done = true;
      this.text = "";
    }

    this.text = this.text.substring(start, end);
  }

  public void setDone(){
    this.done = true;
    this.text = "";
  }

  public boolean isDone(){
    return done;
  }
}
