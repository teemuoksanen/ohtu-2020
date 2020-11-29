package statistics;

import statistics.matcher.*;

public class QueryBuilder {

  Matcher matcher;

  public QueryBuilder() {
    this.matcher = new All();
  }

  public QueryBuilder(Matcher matcher) {
    this.matcher = matcher;
  }

  public QueryBuilder hasAtLeast(int value, String category) {
    this.matcher = new And(new HasAtLeast(value, category), this.matcher);
    return this;
  }

  public QueryBuilder hasFewerThan(int value, String category) {
    this.matcher = new And(new HasFewerThan(value, category), this.matcher);
    return this;
  }

  public QueryBuilder playsIn(String team) {
    this.matcher = new And(new PlaysIn(team), this.matcher);
    return this;
  }

  public QueryBuilder oneOf(Matcher... matchers) {
    this.matcher = new Or(matchers);
    return this;
  }

  public Matcher build() {
    Matcher returnMatcher = this.matcher;
    this.matcher = new All();
    return returnMatcher;
  }
  
}
