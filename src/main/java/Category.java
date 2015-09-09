import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Category {
  private int id;
  private String type;
  private String region;

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getRegion() {
    return region;
  }

  public Category(String type, String region) {
    this.type = type;
    this.region = region;
  }

  @Override
  public boolean equals(Object otherCategory) {
    if (!(otherCategory instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getType().equals(newCategory.getType()) &&
             this.getRegion().equals(newCategory.getRegion());
    }
  }



}
