import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CouponQuestion {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static void main(String[] args) {

    new CouponQuestion().run(
        new String[][] {
            { "CategoryName:Comforter Sets", "CouponName:Comforters Sale", "DateModified:2020-01-01" },
            { "CategoryName:Comforter Sets", "CouponName:Cozy Comforter Coupon", "DateModified:2021-01-01" },
            { "CategoryName:Bedding", "CouponName:Best Bedding Bargains", "DateModified:2019-01-01" },
            { "CategoryName:Bedding", "CouponName:Savings on Bedding", "DateModified:2019-01-01" },
            { "CategoryName:Bed & Bath", "CouponName:Low price for Bed & Bath", "DateModified:2018-01-01" },
            { "CategoryName:Bed & Bath", "CouponName:Bed & Bath extravaganza", "DateModified:2019-01-01" },
            { "CategoryName:Bed & Bath", "CouponName:Big Savings for Bed & Bath", "DateModified:2030-01-01" }
        },
        new String[][] {
            { "CategoryName:Comforter Sets", "CategoryParentName:Bedding" },
            { "CategoryName:Bedding", "CategoryParentName:Bed & Bath" },
            { "CategoryName:Bed & Bath", "CategoryParentName:None" },
            { "CategoryName:Soap Dispensers", "CategoryParentName:Bathroom Accessories" },
            { "CategoryName:Bathroom Accessories", "CategoryParentName:Bed & Bath" },
            { "CategoryName:Toy Organizers", "CategoryParentName:Baby And Kids" },
            { "CategoryName:Baby And Kids", "CategoryParentName:None" }
        });
  }

  static class Coupon {

    String name;
    Date date;

    public Coupon(String name, Date date) {
      this.name = name;
      this.date = date;
    }

    @Override
    public String toString() {
      return "(" + name + ", " + date + ")";
    }
  }

  private Map<String, String> constructCategoryRelationship(String[][] categories) {

    Map<String, String> categoriesParentLookup = new HashMap<>();

    for (String[] category : categories) {

      String child = category[0].split(":")[1];
      String parent = category[1].split(":")[1];

      if (!parent.equals("None")) {
        categoriesParentLookup.put(child, parent);
      }
    }

    return categoriesParentLookup;
  }

  private Map<String, Coupon> constructCouponLookup(String[][] coupons) {

    Map<String, Coupon> couponLookup = new HashMap<>();

    try {

      for (String[] coupon : coupons) {

        String category = coupon[0].split(":")[1];
        String couponName = coupon[1].split(":")[1];
        String date = coupon[2].split(":")[1];

        Date parsedDate = dateFormat.parse(date);
        long diffNew = System.currentTimeMillis() - parsedDate.getTime();

        if (couponLookup.containsKey(category)) {

          long diffExisting = System.currentTimeMillis() - couponLookup.get(category).date.getTime();

          if (diffNew > 0 && diffNew < diffExisting) {
            couponLookup.put(category, new Coupon(couponName, parsedDate));
          }

        } else {

          if (diffNew > 0) {
            couponLookup.put(category, new Coupon(couponName, parsedDate));
          }
        }
      }

      return couponLookup;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void run(String[][] coupons, String[][] categories) {

    Map<String, String> categoriesParentLookup = constructCategoryRelationship(categories);
    Map<String, Coupon> couponLookup = constructCouponLookup(coupons);
    Map<String, Optional<Coupon>> potentialCouponLookup = new HashMap<>();

    System.out.println(getCouponOptimized(couponLookup, categoriesParentLookup, "Bed & Bath", potentialCouponLookup));
    System.out.println(getCouponOptimized(couponLookup, categoriesParentLookup, "Bedding", potentialCouponLookup));
    System.out.println(
        getCouponOptimized(couponLookup, categoriesParentLookup, "Bathroom Accessories", potentialCouponLookup));
    System.out
        .println(getCouponOptimized(couponLookup, categoriesParentLookup, "Comforter Sets", potentialCouponLookup));

  }

  private Coupon getCouponOptimized(Map<String, Coupon> couponLookup, Map<String, String> categoriesParentLookup,
      String category, Map<String, Optional<Coupon>> potentialCouponLookup) {

    if (!potentialCouponLookup.containsKey(category)) {

      if (!categoriesParentLookup.containsKey(category)) {
        potentialCouponLookup.put(category, Optional.ofNullable(couponLookup.get(category)));
      } else if (couponLookup.containsKey(category)) {
        potentialCouponLookup.put(category, Optional.of(couponLookup.get(category)));
      } else {
        Coupon coupon = getCouponOptimized(couponLookup, categoriesParentLookup, categoriesParentLookup.get(category),
            potentialCouponLookup);
        potentialCouponLookup.put(category, Optional.ofNullable(coupon));
      }
    } else {
      System.out.println("path compression optimization in action...");
    }

    return potentialCouponLookup.get(category).orElse(null);
  }
}
