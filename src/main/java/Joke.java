import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Joke {
  private int id;
  private String question;
  private String answer;
  private int hilarity;

  public int getId() {
    return id;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  public int getHilarity() {
    return hilarity;
  }

  public Joke(String question, String answer, int hilarity) {
    this.question = question;
    this.answer = answer;
    this.hilarity = hilarity;
  }

  public void add() {
    
  }

}
