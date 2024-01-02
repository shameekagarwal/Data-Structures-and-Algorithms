public class CallStack {

  public static void methodThree() {
    System.out.println("three");
  }

  public static void methodTwo() {
    methodThree();
    System.out.println("two");
  }

  public static void methodOne() {
    methodTwo();
    System.out.println("one");
  }
  
  public static void main(String[] args) {
    methodOne();
  }
}